package com.interactiveresume.Interactive.Resume.Backend.services.impl.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.ResumePage;
import com.interactiveresume.Interactive.Resume.Backend.jpa.resumes.ResumePageJPARepository;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.ResumePageService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ResumePageServiceImpl implements ResumePageService {

    private final ResumePageJPARepository resumePageJPARepository;

    /**
     * Constructor
     *
     * @param resumePageJPARepository
     */
    public ResumePageServiceImpl(ResumePageJPARepository resumePageJPARepository) {
        this.resumePageJPARepository = resumePageJPARepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResumePage getResumePageById(Long resumePageId) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ResumePage> getResumePagesByResumeId(Long resumeId) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteResumePage(Long resumePageId) {

    }
}
