package com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.Resume;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.ResumePage;

import java.util.List;

public interface ResumePageService {

    /**
     * Retunr a {@link ResumePage} by its id, checks if the current user is the owner
     *
     * @param resumePageId The id of the {@link ResumePage} to find
     * @return The found {@link ResumePage} or null
     */
    ResumePage getResumePageById(Long resumePageId);

    /**
     * Return a list of {@link ResumePage} from the database by its {@link Resume} id, checks if the current user is the owner
     *
     * @param resumeId The id of the {@link Resume} to find
     * @return The {@link List<ResumePage>} or an empty list
     */
    List<ResumePage> getResumePagesByResumeId(Long resumeId);

    /**
     * Deletes a {@link ResumePage} by its id field
     *
     * @param resumePageId The id of the {@link ResumePage} to delete
     */
    void deleteResumePage(Long resumePageId);
}
