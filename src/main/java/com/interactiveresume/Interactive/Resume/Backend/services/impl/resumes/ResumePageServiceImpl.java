package com.interactiveresume.Interactive.Resume.Backend.services.impl.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.ResumePage;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.ResumePageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumePageServiceImpl implements ResumePageService {
    @Override
    public ResumePage getResumePageById(Long resumePageId) {
        return null;
    }

    @Override
    public List<ResumePage> getResumePagesByResumeId(Long resumeId) {
        return null;
    }

    @Override
    public void deleteResumePage(Long resumePageId) {

    }
}
