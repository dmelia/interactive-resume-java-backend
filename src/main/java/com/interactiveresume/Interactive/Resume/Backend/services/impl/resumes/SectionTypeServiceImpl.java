package com.interactiveresume.Interactive.Resume.Backend.services.impl.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionInputTypeDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionTypeDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionInputType;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionType;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.InputInvalidException;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;
import com.interactiveresume.Interactive.Resume.Backend.jpa.resumes.SectionInputTypeJPARepository;
import com.interactiveresume.Interactive.Resume.Backend.jpa.resumes.SectionTypeJPARepository;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.auth.UserService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.SectionTypeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SectionTypeServiceImpl implements SectionTypeService {

    private final UserService userService;

    private final SectionTypeJPARepository sectionTypeJPARepository;

    private final SectionInputTypeJPARepository sectionInputTypeJPARepository;

    /**
     * Constructor
     *
     * @param sectionTypeJPARepository
     * @param userService
     * @param sectionInputTypeJPARepository
     */
    public SectionTypeServiceImpl(SectionTypeJPARepository sectionTypeJPARepository, UserService userService, SectionInputTypeJPARepository sectionInputTypeJPARepository) {
        this.sectionTypeJPARepository = sectionTypeJPARepository;
        this.userService = userService;
        this.sectionInputTypeJPARepository = sectionInputTypeJPARepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSectionType(Long id) throws UserNotFoundException {
        Optional<SectionType> optional = sectionTypeJPARepository.findById(id);
        if (optional.isPresent()) {
            SectionType sectionType = optional.get();
            User user = userService.getCurrentUser();
            if (sectionType.getUser() != null && sectionType.getUser().equals(user) && !sectionType.isGeneric()) {
                sectionTypeJPARepository.deleteById(id);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SectionType saveSectionType(SectionTypeDTO sectionType) {
        // TODO : the rest of the function
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SectionType> getSectionTypes() throws UserNotFoundException {
        User currentUser = userService.getCurrentUser();
        return sectionTypeJPARepository.findSectionTypesByUser(currentUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SectionType> getGenericSectionTypes() {
        return sectionTypeJPARepository.getGenericSectionTypes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSectionInputType(Long id) throws UserNotFoundException {
        if (id != null) {
            Optional<SectionInputType> optional = sectionInputTypeJPARepository.findById(id);
            if (optional.isPresent()) {
                SectionInputType sectionInputType = optional.get();
                User user = sectionInputType.getSectionType().getUser();
                User currentUser = userService.getCurrentUser();
                if (user != null && user.equals(currentUser)) {
                    sectionInputTypeJPARepository.deleteById(id);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SectionInputType saveSectionInputType(SectionInputTypeDTO sectionInputTypeDTO) throws UserNotFoundException {
        if (sectionInputTypeDTO == null || sectionInputTypeDTO.getSectionTypeId() == null) {
            throw new InputInvalidException();
        }
        Long sectionTypeId = sectionInputTypeDTO.getSectionTypeId();
        Optional<SectionType> optional = sectionTypeJPARepository.findById(sectionTypeId);
        if (optional.isEmpty()) {
            throw new InputInvalidException();
        }

        SectionType sectionType = optional.get();
        User user = userService.getCurrentUser();
        if (sectionType.getUser() == null || !sectionType.getUser().equals(user)) {
            throw new InputInvalidException();
        }

        // Ownership and section type is verified here
        SectionInputType savedEntity;
        if (sectionInputTypeDTO.getId() != null) {
            Optional<SectionInputType> optionalFound = sectionInputTypeJPARepository.findById(sectionInputTypeDTO.getId());
            if (optionalFound.isEmpty()) {
                throw new InputInvalidException();
            }
            SectionInputType foundEntity = optionalFound.get();
            if (sectionInputTypeDTO.getTitle() != null) foundEntity.setTitle(sectionInputTypeDTO.getTitle());
            if (sectionInputTypeDTO.getType() != null) foundEntity.setType(sectionInputTypeDTO.getType());
            if (sectionInputTypeDTO.getPosition() != null) foundEntity.setPosition(sectionInputTypeDTO.getPosition());

            savedEntity = sectionInputTypeJPARepository.save(foundEntity);
        } else {
            SectionInputType entityToSave = SectionInputType.builder()
                    .type(sectionInputTypeDTO.getType())
                    .sectionType(sectionType)
                    .title(sectionInputTypeDTO.getTitle())
                    .position(sectionInputTypeDTO.getPosition() != null ? sectionInputTypeDTO.getPosition() : sectionInputTypeJPARepository.getNextPosition(sectionTypeId))
                    .build();

            savedEntity = sectionInputTypeJPARepository.save(entityToSave);
        }

        return savedEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SectionType getSectionTypeById(Long sectionTypeId) throws UserNotFoundException {
        if (sectionTypeId == null) {
            throw new InputInvalidException();
        }
        SectionType sectionType = sectionTypeJPARepository.findById(sectionTypeId).orElse(null);
        if (sectionType == null) {
            return null;
        }
        if (sectionType.getUser() == null) {
            throw new InputInvalidException();
        }
        User currentUser = userService.getCurrentUser();
        if (!currentUser.equals(sectionType.getUser())) {
            throw new InputInvalidException();
        }

        return sectionType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SectionInputType getSectionInputTypeById(Long sectionInputTypeId) throws UserNotFoundException {
        if (sectionInputTypeId == null) {
            throw new InputInvalidException();
        }
        SectionInputType sectionInputType = sectionInputTypeJPARepository.findById(sectionInputTypeId).orElse(null);
        if (sectionInputType != null) {
            SectionType sectionType = sectionInputType.getSectionType();
            if (sectionType != null) {
                User currentUser = userService.getCurrentUser();
                if (!currentUser.equals(sectionType.getUser())) {
                    throw new InputInvalidException();
                }
            } else {
                throw new InputInvalidException();
            }
        }
        return sectionInputType;
    }
}
