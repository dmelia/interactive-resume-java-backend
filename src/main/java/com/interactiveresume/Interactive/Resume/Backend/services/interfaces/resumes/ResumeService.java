package com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.ResumeDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.Resume;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;

import java.util.List;

public interface ResumeService {
    // TODO : update javadoc
    /**
     * Finds a {@link List<Resume>} by {@link User} which owns them
     * @param user the {@link User}
     * @return the found {@link List<Resume>}
     */
    List<Resume> findResumeByUser(User user);

    /**
     * Creates or updates a {@link Resume} to the database
     * @param resume The {@link ResumeDTO} to create or update
     * @return the saved {@link Resume}
     */
    Resume saveResume(ResumeDTO resume) throws UserNotFoundException;

    /**
     * Deletes a {@link Resume} from the database
     * @param id The id of the {@link Resume} to remove
     */
    void deleteResume(Long id);

    /**
     * Returns a {@link Resume} by its id
     * @param id the id of the {@link Resume} to find
     * @return the found {@link Resume} or null
     */
    Resume getResume(Long id);
}
