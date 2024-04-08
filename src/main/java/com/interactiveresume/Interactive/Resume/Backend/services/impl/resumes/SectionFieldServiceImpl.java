package com.interactiveresume.Interactive.Resume.Backend.services.impl.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionFieldDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.Section;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionField;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.*;
import com.interactiveresume.Interactive.Resume.Backend.jpa.resumes.SectionFieldJPARepository;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.auth.UserService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.SectionFieldService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.SectionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SectionFieldServiceImpl implements SectionFieldService {
    private final SectionService sectionService;

    private final SectionFieldJPARepository sectionFieldJPARepository;

    private final UserService userService;

    /**
     * Constructor
     *
     * @param sectionService
     * @param sectionFieldJPARepository
     * @param userService
     */
    public SectionFieldServiceImpl(SectionService sectionService, SectionFieldJPARepository sectionFieldJPARepository, UserService userService) {
        this.sectionService = sectionService;
        this.sectionFieldJPARepository = sectionFieldJPARepository;
        this.userService = userService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SectionField saveSectionField(SectionFieldDTO sectionFieldDTO) throws UserNotFoundException, UnauthorizedAccessException {
        // Check input
        if (sectionFieldDTO == null || sectionFieldDTO.getSectionId() == null) {
            throw new InputInvalidException();
        }

        // Check SectionElement exists in the database and that the current user is the owner
        Section section = sectionService.getSectionById(sectionFieldDTO.getSectionId());

        SectionField savedEntity;
        if (sectionFieldDTO.getId() != null) {
            // Update an entity to the database
            Optional<SectionField> optionalSectionField = sectionFieldJPARepository.findById(sectionFieldDTO.getId());
            if (optionalSectionField.isEmpty()) {
                throw new InputInvalidException();
            }

            // Update the values
            SectionField foundEntity = optionalSectionField.get();
            if (sectionFieldDTO.getValue() != null) {
                foundEntity.setValue(sectionFieldDTO.getValue());
            }
            savedEntity = sectionFieldJPARepository.save(foundEntity);
        } else {
            if (sectionFieldDTO.getType() == null || sectionFieldDTO.getType().isEmpty())
                throw new InputInvalidException();

            // Save a new entity to the database
            SectionField entityToSave = SectionField.builder()
                    .value(sectionFieldDTO.getValue())
                    .type(sectionFieldDTO.getType())
                    .section(section)
                    .build();

            savedEntity = sectionFieldJPARepository.save(entityToSave);
        }

        return savedEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SectionField getSectionFieldById(Long sectionFieldId) throws UserNotFoundException, UnauthorizedAccessException {
        if (sectionFieldId == null) throw new InputInvalidException();

        Optional<SectionField> sectionFieldOptional = sectionFieldJPARepository.findById(sectionFieldId);
        if (sectionFieldOptional.isEmpty()) throw new SectionFieldNotFoundException();
        User currentUSer = userService.getCurrentUser();
        SectionField sectionField = sectionFieldOptional.get();
        if (sectionField.getSection().getPage().getResume().getUser() != currentUSer) {
            throw new UnauthorizedAccessException();
        }

        return sectionField;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SectionField> getSectionFieldsBySectionId(Long sectionId) throws SectionNotFoundException, UserNotFoundException, UnauthorizedAccessException {
        if (sectionId == null) {
            throw new InputInvalidException();
        }
        Section section = sectionService.getSectionById(sectionId);
        return sectionFieldJPARepository.findSectionFieldsBySection(section);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSectionField(Long sectionFieldId) throws UserNotFoundException, UnauthorizedAccessException {
        SectionField sectionField = getSectionFieldById(sectionFieldId);
        sectionFieldJPARepository.delete(sectionField);
    }
}
