package com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.ElementValueDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionElementDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.ElementValue;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionElement;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;

import java.util.List;

public interface SectionElementService {
    // TODO : javadoc

    void deleteSectionElementsByResumeId(Long id);

    SectionElement saveSectionElement(SectionElementDTO sectionElement) throws UserNotFoundException;

    ElementValue saveElementValue(ElementValueDTO value);

    List<SectionElement> getSectionElementsByResumePage(Long resumePageId) throws UserNotFoundException;

    void deleteSectionElement(Long sectionElementId) throws UserNotFoundException;
}
