package com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionInputTypeDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionTypeDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionInputType;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionType;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;

import java.util.List;

public interface SectionTypeService {
    // TODO : javadoc

    void deleteSectionType(Long id) throws UserNotFoundException;

    SectionType saveSectionType(SectionTypeDTO sectionType);

    List<SectionType> getSectionTypes(User user);

    List<SectionType> getGenericSectionTypes();

    void deleteSectionInputType(Long id) throws UserNotFoundException;

    SectionInputType saveSectionInputType(SectionInputTypeDTO sectionInputType);

    SectionType getSectionTypeById(Long id);

    SectionInputType getSectionInputTypeById(Long sectionInputTypeId);
}
