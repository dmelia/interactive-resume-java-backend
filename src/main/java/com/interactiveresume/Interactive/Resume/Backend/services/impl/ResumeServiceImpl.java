package com.interactiveresume.Interactive.Resume.Backend.services.impl;

import com.interactiveresume.Interactive.Resume.Backend.data.models.Resume;
import com.interactiveresume.Interactive.Resume.Backend.data.models.User;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.ResumeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ResumeServiceImpl implements ResumeService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Resume> findResumeByUser(User user) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveResume(Resume resume) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteResume(Resume resume) {

    }
}
