package com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionFieldDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.Section;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionField;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.SectionFieldNotFoundException;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.SectionNotFoundException;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UnauthorizedAccessException;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;

import java.util.List;

public interface SectionFieldService {

    /**
     * Returns a saved {@link SectionField} entity after saving changes from the {@link SectionFieldDTO} to the database, checks valid parameters and ownership
     *
     * @param value the {@link SectionFieldDTO} to be saved
     * @return the saved {@link SectionField}
     * @throws UserNotFoundException if the authenticated user cannot be found
     */
    SectionField saveSectionField(SectionFieldDTO value) throws UserNotFoundException;

    /**
     * Retrieves a {@link SectionField} by its id
     *
     * @param sectionFieldId the id of the {@link SectionField}
     * @return the found {@link SectionField}
     * @throws UserNotFoundException if the user is not found
     * @throws UnauthorizedAccessException if the current user is not the owner of the {@link SectionField}
     * @throws SectionFieldNotFoundException if the {@link SectionField} cannot be found
     */
    SectionField getSectionFieldById(Long sectionFieldId) throws UserNotFoundException, UnauthorizedAccessException, SectionFieldNotFoundException;

    /**
     * Retrieves a {@link List<SectionField>} by their {@link Section}
     *
     * @param sectionId the {@link Section} id
     * @return the found {@link List<SectionField>}
     * @throws UserNotFoundException if the user is not found
     * @throws UnauthorizedAccessException if the current user is not the owner of the {@link Section}
     * @throws SectionNotFoundException if the {@link Section} cannot be found
     */
    List<SectionField> getSectionFieldsBySectionId(Long sectionId) throws SectionNotFoundException, UserNotFoundException, UnauthorizedAccessException;

    /**
     * Deletes a {@link SectionField} by its id
     *
     * @param sectionFieldId the id
     * @throws UserNotFoundException if the user is not found
     * @throws UnauthorizedAccessException if the current user is not the owner
     */
    void deleteSectionField(Long sectionFieldId) throws UserNotFoundException, UnauthorizedAccessException;
}
