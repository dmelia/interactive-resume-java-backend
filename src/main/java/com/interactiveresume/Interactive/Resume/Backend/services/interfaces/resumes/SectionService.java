package com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionFieldDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionField;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.Section;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;

import java.util.List;

public interface SectionService {

    /**
     * Deletes all {@link Section} entities from the database for a given resume id
     *
     * @param resumeId the id of the resume
     */
    void deleteSectionsByResumeId(Long resumeId);

    /**
     * Returns a {@link Section} entity after saving changes received from the {@link SectionDTO}, checks valid parameters abd ownership
     *
     * @param sectionDTO the {@link SectionDTO} containing the new data to be saved
     * @return the saved {@link Section}
     * @throws UserNotFoundException if the authenticated user cannot be found
     */
    Section saveSection(SectionDTO sectionDTO) throws UserNotFoundException;


    /**
     * Returns a saved {@link SectionField} entity after saving changes from the {@link SectionFieldDTO} to the database, checks valid parameters and ownership
     *
     * @param value the {@link SectionFieldDTO} to be saved
     * @return the saved {@link SectionField}
     * @throws UserNotFoundException if the authenticated user cannot be found
     */
    SectionField saveSectionField(SectionFieldDTO value) throws UserNotFoundException;

    /**
     * Returns all {@link Section} for a given resume page
     *
     * @param resumePageId the id of the resume page
     * @return the found list of {@link Section}
     * @throws UserNotFoundException if the authenticated user cannot be found
     */
    List<Section> getSectionsByResumePage(Long resumePageId) throws UserNotFoundException;

    /**
     * Deletes a {@link Section} from the database by id, checks ownership
     *
     * @param sectionElementId the id of entity to delete
     * @throws UserNotFoundException if the authenticated user cannot be found
     */
    void deleteSection(Long sectionElementId) throws UserNotFoundException;
}
