package com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionInputTypeDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionTypeDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionInputType;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionType;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;

import java.util.List;

public interface SectionTypeService {

    /**
     * Deletes a {@link SectionType} by its id, checks ownership
     *
     * @param id the id of the {@link SectionType}
     * @throws UserNotFoundException if the current user cannot be found
     */
    void deleteSectionType(Long id) throws UserNotFoundException;

    /**
     * Saves or updates a {@link SectionType} then returns it using the data from the {@link SectionTypeDTO}, checks valid parameters
     *
     * @param sectionType The {@link SectionTypeDTO} containing the data to be saved
     * @return The saved {@link SectionType}
     */
    SectionType saveSectionType(SectionTypeDTO sectionType);

    /**
     * Gets a {@link List<SectionType>} linked to the current {@link User} account
     *
     * @return The found {@link List<SectionType>}
     * @throws UserNotFoundException if the user is not found
     */
    List<SectionType> getSectionTypes() throws UserNotFoundException;

    /**
     * Finds all the generic instances of {@link SectionType} from the database
     *
     * @return The found {@link List<SectionType>}
     */
    List<SectionType> getGenericSectionTypes();

    /**
     * Deletes a {@link SectionInputType} from the database by its id, checks ownership
     *
     * @param id the id to search for
     * @throws UserNotFoundException if the user is not found
     */
    void deleteSectionInputType(Long id) throws UserNotFoundException;

    /**
     * Saves or updates a {@link SectionInputType} then returns it using the data from the {@link SectionInputTypeDTO}, checks valid parameters
     *
     * @param sectionInputType The {@link SectionInputTypeDTO} containing the data
     * @return The saved {@link SectionInputType}
     * @throws UserNotFoundException if the user is not found
     */
    SectionInputType saveSectionInputType(SectionInputTypeDTO sectionInputType) throws UserNotFoundException;

    /**
     * Returns a {@link SectionType} by its id, checks ownership
     *
     * @param id the id to search for
     * @return The found {@link SectionType}
     * @throws UserNotFoundException if the user is not found
     */
    SectionType getSectionTypeById(Long id) throws UserNotFoundException;

    /**
     * Returns a {@link SectionInputType} by its id, checks ownership
     *
     * @param id the id to search for
     * @return The found {@link SectionInputType}
     * @throws UserNotFoundException if the user is not found
     */
    SectionInputType getSectionInputTypeById(Long id) throws UserNotFoundException;
}
