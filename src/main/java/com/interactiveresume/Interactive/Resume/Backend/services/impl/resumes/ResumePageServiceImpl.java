package com.interactiveresume.Interactive.Resume.Backend.services.impl.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.ResumePageDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.Resume;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.ResumePage;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.InputInvalidException;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;
import com.interactiveresume.Interactive.Resume.Backend.jpa.resumes.ResumePageJPARepository;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.auth.UserService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.ResumePageService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.ResumeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResumePageServiceImpl implements ResumePageService {

    private final ResumePageJPARepository resumePageJPARepository;

    private final ResumeService resumeService;

    private final UserService userService;

    /**
     * Constructor
     *
     * @param resumePageJPARepository
     * @param resumeService
     * @param userService
     */
    public ResumePageServiceImpl(ResumePageJPARepository resumePageJPARepository, ResumeService resumeService, UserService userService) {
        this.resumePageJPARepository = resumePageJPARepository;
        this.resumeService = resumeService;
        this.userService = userService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResumePage getResumePageById(Long resumePageId) throws UserNotFoundException {
        Optional<ResumePage> optional = resumePageJPARepository.findById(resumePageId);
        if (optional.isEmpty()) return null;
        ResumePage resumePage = optional.get();
        User currentUser = userService.getCurrentUser();
        if (resumePage.getResume() == null || resumePage.getResume().getUser() == null || !resumePage.getResume().getUser().equals(currentUser)) {
            throw new InputInvalidException();
        } else {
            return resumePage;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ResumePage> getResumePagesByResumeId(Long resumeId) throws UserNotFoundException {
        Resume resume = resumeService.getResume(resumeId);
        if (resume == null) return null;
        return resume.getPages();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteResumePage(Long resumePageId) throws UserNotFoundException {
        ResumePage resumePage = getResumePageById(resumePageId);
        if (resumePage != null) {
            resumePageJPARepository.deleteById(resumePageId);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResumePage addResumePage(ResumePageDTO resumePageDTO) throws UserNotFoundException {

        Resume resume = resumeService.getResume(resumeId);
        if (resume == null) return null;

        ResumePage resumePage = ResumePage.builder()
                .resume(resume)
                .index(resumePageJPARepository.getNextResumePageIndex(resume.getId()))
                .name()
                .build();
    }
}
