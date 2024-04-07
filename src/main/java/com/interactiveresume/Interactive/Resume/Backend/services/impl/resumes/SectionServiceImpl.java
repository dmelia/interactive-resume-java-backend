package com.interactiveresume.Interactive.Resume.Backend.services.impl.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionFieldDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.*;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.InputInvalidException;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.SectionNotFoundException;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UnauthorizedAccessException;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;
import com.interactiveresume.Interactive.Resume.Backend.jpa.resumes.SectionFieldJPARepository;
import com.interactiveresume.Interactive.Resume.Backend.jpa.resumes.SectionJPARepository;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.auth.UserService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.ResumePageService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.SectionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SectionServiceImpl implements SectionService {
    private final SectionJPARepository sectionJPARepository;
    private final SectionFieldJPARepository sectionFieldJPARepository;
    private final UserService userService;

    private final ResumePageService resumePageService;

    /**
     * Constructor
     *
     * @param sectionJPARepository
     * @param sectionFieldJPARepository
     * @param userService
     * @param resumePageService
     */
    public SectionServiceImpl(SectionJPARepository sectionJPARepository, SectionFieldJPARepository sectionFieldJPARepository, UserService userService, ResumePageService resumePageService) {
        this.sectionJPARepository = sectionJPARepository;
        this.sectionFieldJPARepository = sectionFieldJPARepository;
        this.userService = userService;
        this.resumePageService = resumePageService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Section saveSection(SectionDTO sectionDTO) throws UserNotFoundException {
        // Check for non-null inputs
        if (sectionDTO == null || sectionDTO.getResumePageId() == null) {
            throw new InputInvalidException();
        }

        // Check resume page exists
        ResumePage resumePage = resumePageService.getResumePageById(sectionDTO.getResumePageId());
        if (resumePage == null) {
            throw new InputInvalidException();
        }

        // Check user is owner of resume
        User user = userService.getCurrentUser();
        if (resumePage.getResume().getUser() == null || !resumePage.getResume().getUser().equals(user)) {
            throw new InputInvalidException();
        }

        // Now we move on to the save/update

        Section savedEntity;
        if (sectionDTO.getId() != null) {
            // Save a pre-existing entity
            Optional<Section> optional = sectionJPARepository.findById(sectionDTO.getId());
            if (optional.isEmpty()) {
                throw new InputInvalidException();
            }
            Section found = optional.get();
            // Update changed variables
            if (sectionDTO.getPosition() != null && !sectionDTO.getPosition().equals(found.getPosition())) {
                found.setPosition(sectionDTO.getPosition());
            }

            // Apply to the database
            savedEntity = sectionJPARepository.save(found);
        } else {
            // Save a new entity
            Section newElement = Section.builder()
                    .page(resumePage)
                    .position(sectionDTO.getPosition() != null ?
                            sectionDTO.getPosition() :
                            sectionJPARepository.getNextPosition(sectionDTO.getResumePageId()))
                    .build();

            // Apply to the database
            savedEntity = sectionJPARepository.save(newElement);
        }

        return savedEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Section> getSectionsByResumePage(Long resumePageId) throws UserNotFoundException {
        ResumePage resumePage = resumePageService.getResumePageById(resumePageId);
        User user = userService.getCurrentUser();
        if (resumePage.getResume() != null && user.equals(resumePage.getResume().getUser())) {
            return resumePage.getSections();
        } else return new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSection(Long sectionId) throws UserNotFoundException {
        if (sectionId == null) throw new InputInvalidException();
        Optional<Section> optional = sectionJPARepository.findById(sectionId);

        User user = userService.getCurrentUser();
        if (optional.isPresent()) {
            Section sectionElement = optional.get();
            if (!sectionElement.getPage().getResume().getUser().equals(user)) {
                throw new UnauthorizedAccessException();
            }
        }

        sectionJPARepository.deleteById(sectionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Section getSectionById(Long sectionId) throws UserNotFoundException, UnauthorizedAccessException {
        if (sectionId == null) throw new InputInvalidException();
        Optional<Section> optional = sectionJPARepository.findById(sectionId);

        User user = userService.getCurrentUser();
        if (optional.isPresent()) {
            Section sectionElement = optional.get();
            if (!sectionElement.getPage().getResume().getUser().equals(user)) {
                throw new UnauthorizedAccessException();
            }

            return sectionElement;
        } else {
            throw new SectionNotFoundException();
        }
    }
}
