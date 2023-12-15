package com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.ResumePage;

import java.util.List;

public interface ResumePageService {
    // TODO : javadoc
    ResumePage getResumePageById(Long resumePageId);

    List<ResumePage> getResumePagesByResumeId(Long resumeId);

    void deleteResumePage(Long resumePageId);
}
