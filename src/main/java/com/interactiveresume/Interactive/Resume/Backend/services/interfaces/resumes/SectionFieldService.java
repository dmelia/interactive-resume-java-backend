package com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionFieldDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionField;
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
     *
     * @param sectionFieldId
     * @return
     * @throws UserNotFoundException
     * @throws UnauthorizedAccessException
     */
    SectionField getSectionFieldById(Long sectionFieldId) throws UserNotFoundException, UnauthorizedAccessException;

    /**
     *
     * @param sectionId
     * @return
     * @throws SectionNotFoundException
     * @throws UserNotFoundException
     * @throws UnauthorizedAccessException
     */
    List<SectionField> getSectionFieldsBySectionId(Long sectionId) throws SectionNotFoundException, UserNotFoundException, UnauthorizedAccessException;

    /**
     *
     * @param sectionFieldId
     * @throws UserNotFoundException
     * @throws UnauthorizedAccessException
     */
    void deleteSectionField(Long sectionFieldId) throws UserNotFoundException, UnauthorizedAccessException;
}
