package com.interactiveresume.Interactive.Resume.Backend.services.impl.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.ResumePageDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.Resume;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.ResumePage;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.*;
import com.interactiveresume.Interactive.Resume.Backend.jpa.resumes.ResumePageJPARepository;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.auth.UserService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.ResumePageService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.ResumeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResumePageServiceImpl implements ResumePageService {

    private final ResumePageJPARepository resumePageJPARepository;

    private final ResumeService resumeService;

    private final UserService userService;

    /**
     * Constructor
     *
     * @param resumePageJPARepository
     * @param resumeService
     * @param userService
     */
    public ResumePageServiceImpl(ResumePageJPARepository resumePageJPARepository, ResumeService resumeService, UserService userService) {
        this.resumePageJPARepository = resumePageJPARepository;
        this.resumeService = resumeService;
        this.userService = userService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResumePage getResumePageById(Long resumePageId) throws UserNotFoundException, ResumePageNotFoundException, UnauthorizedAccessException {
        Optional<ResumePage> optional = resumePageJPARepository.findById(resumePageId);
        if (optional.isEmpty()) throw new ResumePageNotFoundException();
        ResumePage resumePage = optional.get();
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) throw new UserNotFoundException();
        if (resumePage.getResume() == null) {
            throw new InputInvalidException();
        }
        if (!resumePage.getResume().getUser().equals(currentUser)) throw new UnauthorizedAccessException();
        return resumePage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ResumePage> getResumePagesByResumeId(Long resumeId) throws UserNotFoundException {
        Resume resume = resumeService.getResume(resumeId);
        if (resume == null) return null;
        return resume.getPages();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteResumePage(Long resumePageId) throws UserNotFoundException, UnauthorizedAccessException {
        ResumePage resumePage = getResumePageById(resumePageId);
        if (resumePage != null) {
            User currentUser = userService.getCurrentUser();
            if (currentUser != resumePage.getResume().getUser()) throw new UnauthorizedAccessException();
            resumePageJPARepository.deleteById(resumePageId);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResumePage saveResumePage(ResumePageDTO resumePageDTO) throws UserNotFoundException, ResumeNotFoundException, UnauthorizedAccessException {
        if (resumePageDTO.getResumeId() == null) {
            throw new InputInvalidException();
        }

        Resume resume = resumeService.getResume(resumePageDTO.getResumeId());
        if (resume == null) throw new ResumeNotFoundException();
        User currentUser = userService.getCurrentUser();
        if (resume.getUser() != currentUser) throw new UnauthorizedAccessException();

        if (resumePageDTO.getId() == null && (resumePageDTO.getName() == null || resumePageDTO.getName().isEmpty())) {
            throw new InputInvalidException();
        }

        ResumePage savedEntity;
        if (resumePageDTO.getId() == null) {
            ResumePage resumePage = ResumePage.builder()
                    .resume(resume)
                    .index(resumePageJPARepository.getNextResumePageIndex(resume.getId()))
                    .name(resumePageDTO.getName())
                    .build();
            savedEntity = resumePageJPARepository.save(resumePage);
        } else {
            Optional<ResumePage> optionalResumePage = resumePageJPARepository.findById(resumePageDTO.getId());

            if (optionalResumePage.isEmpty()) {
                throw new ResumeNotFoundException();
            }
            ResumePage foundEntity = optionalResumePage.get();
            if (resumePageDTO.getName() != null && !resumePageDTO.getName().isEmpty())
                foundEntity.setName(resumePageDTO.getName());
            if (resumePageDTO.getIndex() != null) {
                foundEntity.setIndex(resumePageDTO.getIndex());
            }
            savedEntity = resumePageJPARepository.save(foundEntity);
        }

        return savedEntity;
    }
}
