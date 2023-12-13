package com.interactiveresume.Interactive.Resume.Backend.services.impl.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.ElementValue;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionElement;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionType;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.InputInvalidException;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;
import com.interactiveresume.Interactive.Resume.Backend.jpa.resumes.ElementValueJPARepository;
import com.interactiveresume.Interactive.Resume.Backend.jpa.resumes.SectionElementJPARepository;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.auth.UserService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.SectionElementService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.SectionTypeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SectionElementServiceImpl implements SectionElementService {


    private final SectionTypeService sectionTypeService;
    private final SectionElementJPARepository sectionElementJPARepository;
    private final ElementValueJPARepository elementValueJPARepository;
    private final UserService userService;

    public SectionElementServiceImpl(SectionElementJPARepository sectionElementJPARepository, ElementValueJPARepository elementValueJPARepository, SectionTypeService sectionTypeService, UserService userService) {
        this.sectionElementJPARepository = sectionElementJPARepository;
        this.elementValueJPARepository = elementValueJPARepository;
        this.sectionTypeService = sectionTypeService;
        this.userService = userService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSectionElementsByResumeId(Long id) {
        if (id == null) throw new InputInvalidException();
        sectionElementJPARepository.deleteSectionElementsByResumeId(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SectionElement saveSectionElement(SectionElement sectionElement) {

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SectionElement createSectionElement(SectionElement sectionElement, Long sectionTypeId) throws UserNotFoundException {
        SectionType sectiontype = sectionTypeService.getSectionTypeById(sectionTypeId);
        if (sectiontype != null) {
            User user = userService.getCurrentUser();
            // Set the user
            sectiontype.setUser(user);
            sectionElement.setSectionType(sectiontype);
        } else {
            // Invalid SectionType
            throw new InputInvalidException();
        }

        // Save the SectionElement
        return sectionElementJPARepository.save(sectionElement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ElementValue saveElementValue(ElementValue value) {

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ElementValue createElementValue(ElementValue value, Long sectionTypeInputId) {

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SectionElement> getSectionElementsByResume(Long resumeId) {
        //TODO : check the user is the owner of the resume
        return sectionElementJPARepository.findSectionElementsByResumeId(resumeId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSectionElement(Long sectionElementId) {
        if (sectionElementId == null) throw new InputInvalidException();
        //TODO : check the user is the owner of the section
        sectionElementJPARepository.deleteById(sectionElementId);
    }
}
