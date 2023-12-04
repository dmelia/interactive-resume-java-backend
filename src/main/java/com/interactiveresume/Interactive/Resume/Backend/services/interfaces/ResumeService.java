package com.interactiveresume.Interactive.Resume.Backend.services.interfaces;

import com.interactiveresume.Interactive.Resume.Backend.data.models.Resume;
import com.interactiveresume.Interactive.Resume.Backend.data.models.User;

import java.util.List;

public interface ResumeService {
    /**
     * Finds a {@link List<Resume>} by {@link User} which owns them
     * @param user the {@link User}
     * @return the found {@link List<Resume>}
     */
    List<Resume> findResumeByUser(User user);

    /**
     * Creates or updates a {@link Resume} to the database
     * @param resume The {@link Resume} to create or update
     */
    void saveResume(Resume resume);

    /**
     * Deletes a {@link Resume} from the database
     * @param resume The {@link Resume} to remove
     */
    void deleteResume(Resume resume);
}
