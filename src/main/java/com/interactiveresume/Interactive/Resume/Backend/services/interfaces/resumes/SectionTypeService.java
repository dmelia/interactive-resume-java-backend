package com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionInputType;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionType;

import java.util.List;

public interface SectionTypeService {

    void deleteSectionType(Long id);

    SectionType saveSectionType(SectionType sectionType);

    List<SectionType> getSectionTypes(User user);

    List<SectionType> getGenericSectionTypes();

    void deleteSectionInputType(Long id);

    SectionInputType saveSectionInputType(SectionInputType sectionInputType);

    SectionType getSectionTypeById(Long id);
}
