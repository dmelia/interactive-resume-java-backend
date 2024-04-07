package com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.Section;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UnauthorizedAccessException;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;

import java.util.List;

public interface SectionService {

    /**
     * Returns a {@link Section} entity after saving changes received from the {@link SectionDTO}, checks valid parameters abd ownership
     *
     * @param sectionDTO the {@link SectionDTO} containing the new data to be saved
     * @return the saved {@link Section}
     * @throws UserNotFoundException if the authenticated user cannot be found
     */
    Section saveSection(SectionDTO sectionDTO) throws UserNotFoundException;

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
     * @param sectionId the id of entity to delete
     * @throws UserNotFoundException if the authenticated user cannot be found
     */
    void deleteSection(Long sectionId) throws UserNotFoundException;

    /**
     * Retrieves a {@link Section} by its id
     *
     * @param sectionId the id of the {@link Section}
     * @return the found {@link Section}
     * @throws UserNotFoundException       if the user is not found
     * @throws UnauthorizedAccessException if the user is not authorized
     */
    Section getSectionById(Long sectionId) throws UserNotFoundException, UnauthorizedAccessException;
}
