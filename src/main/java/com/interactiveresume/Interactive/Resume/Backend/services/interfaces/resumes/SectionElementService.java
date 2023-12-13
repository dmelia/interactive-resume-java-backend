package com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.ElementValue;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionElement;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;

import java.util.List;

public interface SectionElementService {

    void deleteSectionElementsByResumeId(Long id);

    SectionElement saveSectionElement(SectionElement sectionElement);

    SectionElement createSectionElement(SectionElement sectionElement, Long sectionTypeId) throws UserNotFoundException;

    ElementValue saveElementValue(ElementValue value);

    ElementValue createElementValue(ElementValue value, Long sectionTypeInputId);

    List<SectionElement> getSectionElementsByResume(Long resumeId);

    void deleteSectionElement(Long sectionElementId);
}
