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

    public SectionTypeServiceImpl(SectionTypeJPARepository sectionTypeJPARepository, UserService userService, SectionInputTypeJPARepository sectionInputTypeJPARepository) {
        this.sectionTypeJPARepository = sectionTypeJPARepository;
        this.userService = userService;
        this.sectionInputTypeJPARepository = sectionInputTypeJPARepository;
    }

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

    @Override
    public SectionType saveSectionType(SectionTypeDTO sectionType) {
        // TODO : the rest of the function
        return null;
    }

    @Override
    public List<SectionType> getSectionTypes(User user) {
        // TODO : check ownership
        return sectionTypeJPARepository.findSectionTypesByUser(user);
    }

    @Override
    public List<SectionType> getGenericSectionTypes() {
        // TODO : check ownership
        return sectionTypeJPARepository.getGenericSectionTypes();
    }

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

    @Override
    public SectionInputType saveSectionInputType(SectionInputTypeDTO sectionInputType) {

        return null;
    }

    public SectionInputType saveSectionInputType(SectionInputType sectionInputType, Long sectionTypeId) throws UserNotFoundException {
        if (sectionTypeId != null) {
            User user = userService.getCurrentUser();
            Optional<SectionType> optional = sectionTypeJPARepository.findById(sectionTypeId);
            if (optional.isPresent()) {
                SectionType sectionType = optional.get();
                if (sectionType.getUser() != null && sectionType.getUser().equals(user)) {
                    // Here we have verified the section type is owned by the user
                    // TODO : the rest of the function
                }
            } else {

            }
        }
        return null;
    }

    @Override
    public SectionType getSectionTypeById(Long id) {
        // TODO : this function
        return null;
    }

    @Override
    public SectionInputType getSectionInputTypeById(Long sectionInputTypeId) {
        if (sectionInputTypeId == null) {
            throw new InputInvalidException();
        }
        // TODO : check ownership
        return sectionInputTypeJPARepository.findById(sectionInputTypeId).orElse(null);
    }
}
