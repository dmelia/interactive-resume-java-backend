package com.interactiveresume.Interactive.Resume.Backend.services.impl.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.ElementValueDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionElementDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.*;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.InputInvalidException;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;
import com.interactiveresume.Interactive.Resume.Backend.jpa.resumes.ElementValueJPARepository;
import com.interactiveresume.Interactive.Resume.Backend.jpa.resumes.SectionElementJPARepository;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.auth.UserService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.ResumePageService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.SectionElementService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.SectionTypeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SectionElementServiceImpl implements SectionElementService {


    private final SectionTypeService sectionTypeService;
    private final SectionElementJPARepository sectionElementJPARepository;
    private final ElementValueJPARepository elementValueJPARepository;
    private final UserService userService;

    private final ResumePageService resumePageService;

    /**
     * Constructor
     *
     * @param sectionElementJPARepository
     * @param elementValueJPARepository
     * @param sectionTypeService
     * @param userService
     * @param resumePageService
     */
    public SectionElementServiceImpl(SectionElementJPARepository sectionElementJPARepository, ElementValueJPARepository elementValueJPARepository, SectionTypeService sectionTypeService, UserService userService, ResumePageService resumePageService) {
        this.sectionElementJPARepository = sectionElementJPARepository;
        this.elementValueJPARepository = elementValueJPARepository;
        this.sectionTypeService = sectionTypeService;
        this.userService = userService;
        this.resumePageService = resumePageService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSectionElementsByResumeId(Long id) {
        if (id == null) {
            throw new InputInvalidException();
        }

        // TODO : check ownership
        sectionElementJPARepository.deleteSectionElementsByResumeId(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SectionElement saveSectionElement(SectionElementDTO sectionElementDTO) throws UserNotFoundException {
        // Check for non-null inputs
        if (sectionElementDTO == null || sectionElementDTO.getSectionTypeId() == null || sectionElementDTO.getResumePageId() == null) {
            throw new InputInvalidException();
        }

        // Check section type exists
        SectionType sectiontype = sectionTypeService.getSectionTypeById(sectionElementDTO.getSectionTypeId());
        if (sectiontype == null) {
            throw new InputInvalidException();
        }

        // Check resume page exists
        ResumePage resumePage = resumePageService.getResumePageById(sectionElementDTO.getResumePageId());
        if (resumePage == null) {
            throw new InputInvalidException();
        }

        // Check user is owner of section type and resume
        User user = userService.getCurrentUser();
        if (sectiontype.getUser() == null || !sectiontype.getUser().equals(user) || resumePage.getResume().getUser() == null || !resumePage.getResume().getUser().equals(user)) {
            throw new InputInvalidException();
        }

        // Now we move on to the save/update

        SectionElement savedEntity;
        if (sectionElementDTO.getId() != null) {
            // Save a pre-existing entity
            Optional<SectionElement> optional = sectionElementJPARepository.findById(sectionElementDTO.getId());
            if (optional.isEmpty()) {
                throw new InputInvalidException();
            }
            SectionElement found = optional.get();
            // Update changed variables
            if (sectionElementDTO.getPosition() != null && !sectionElementDTO.getPosition().equals(found.getPosition())) {
                found.setPosition(sectionElementDTO.getPosition());
            }

            // Apply to the database
            savedEntity = sectionElementJPARepository.save(found);
        } else {
            // Save a new entity
            SectionElement newElement = SectionElement.builder()
                    .sectionType(sectiontype)
                    .page(resumePage)
                    .position(sectionElementDTO.getPosition() != null ?
                            sectionElementDTO.getPosition() :
                            sectionElementJPARepository.getNextPosition(sectionElementDTO.getResumePageId(), sectionElementDTO.getSectionTypeId()))
                    .build();

            // Apply to the database
            savedEntity = sectionElementJPARepository.save(newElement);
        }

        return savedEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ElementValue saveElementValue(ElementValueDTO elementValueDTO) {
        // Check input
        if (elementValueDTO == null || elementValueDTO.getSectionInputTypeId() == null || elementValueDTO.getSectionElementId() == null) {
            throw new InputInvalidException();
        }

        // Check SectionElement exists in the database
        Optional<SectionElement> optionalSectionElement = sectionElementJPARepository.findById(elementValueDTO.getSectionElementId());
        if (optionalSectionElement.isEmpty()) {
            throw new InputInvalidException();
        }

        // Check SectionInputType exists in the database
        SectionInputType sectionInputType = sectionTypeService.getSectionInputTypeById(elementValueDTO.getSectionInputTypeId());
        if (sectionInputType == null) {
            throw new InputInvalidException();
        }

        // TODO : check ownership

        ElementValue savedEntity;
        if (elementValueDTO.getId() != null) {
            // Update an entity to the database
            Optional<ElementValue> optionalElementValue = elementValueJPARepository.findById(elementValueDTO.getId());
            if (optionalElementValue.isEmpty()) {
                throw new InputInvalidException();
            }

            // Update the values
            ElementValue foundEntity = optionalElementValue.get();
            if (elementValueDTO.getValue() != null) {
                foundEntity.setValue(elementValueDTO.getValue());
            }
            savedEntity = elementValueJPARepository.save(foundEntity);
        } else {
            // Save a new entity to the database
            ElementValue entityToSave = ElementValue.builder()
                    .value(elementValueDTO.getValue())
                    .sectionElement(optionalSectionElement.get())
                    .inputType(sectionInputType)
                    .build();

            savedEntity = elementValueJPARepository.save(entityToSave);
        }

        return savedEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SectionElement> getSectionElementsByResumePage(Long resumePageId) throws UserNotFoundException {
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
    public void deleteSectionElement(Long sectionElementId) throws UserNotFoundException {
        if (sectionElementId == null) throw new InputInvalidException();
        Optional<SectionElement> optional = sectionElementJPARepository.findById(sectionElementId);

        User user = userService.getCurrentUser();
        if (optional.isPresent()) {
            SectionElement sectionElement = optional.get();
            if (!sectionElement.getPage().getResume().getUser().equals(user)) {
                throw new InputInvalidException();
            }
        }

        sectionElementJPARepository.deleteById(sectionElementId);
    }
}
