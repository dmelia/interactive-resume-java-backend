package com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.ElementValueDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionElementDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.ElementValue;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionElement;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;

import java.util.List;

public interface SectionElementService {

    /**
     * Deletes all {@link SectionElement} entities from the database for a given resume id
     *
     * @param resumeId the id of the resume
     */
    void deleteSectionElementsByResumeId(Long resumeId);

    /**
     * Returns a {@link SectionElement} entity after saving changes received from the {@link SectionElementDTO}, checks valid parameters abd ownership
     *
     * @param sectionElementDTO the {@link SectionElementDTO} containing the new data to be saved
     * @return the saved {@link SectionElement}
     * @throws UserNotFoundException if the authenticated user cannot be found
     */
    SectionElement saveSectionElement(SectionElementDTO sectionElementDTO) throws UserNotFoundException;


    /**
     * Returns a saved {@link ElementValue} entity after saving changes from the {@link ElementValueDTO} to the database, checks valid parameters and ownership
     *
     * @param value the {@link ElementValueDTO} to be saved
     * @return the saved {@link ElementValue}
     * @throws UserNotFoundException if the authenticated user cannot be found
     */
    ElementValue saveElementValue(ElementValueDTO value) throws UserNotFoundException;

    /**
     * Returns all {@link SectionElement} for a given resume page
     *
     * @param resumePageId the id of the resume page
     * @return the found {@link List<SectionElement>}
     * @throws UserNotFoundException if the authenticated user cannot be found
     */
    List<SectionElement> getSectionElementsByResumePage(Long resumePageId) throws UserNotFoundException;

    /**
     * Deletes a {@link SectionElement} from the database by id, checks ownership
     *
     * @param sectionElementId the id of entity to delete
     * @throws UserNotFoundException if the authenticated user cannot be found
     */
    void deleteSectionElement(Long sectionElementId) throws UserNotFoundException;
}
