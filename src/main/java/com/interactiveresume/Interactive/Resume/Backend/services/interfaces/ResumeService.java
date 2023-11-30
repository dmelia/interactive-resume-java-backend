package com.interactiveresume.Interactive.Resume.Backend.services.interfaces;

import com.interactiveresume.Interactive.Resume.Backend.models.Resume;
import com.interactiveresume.Interactive.Resume.Backend.models.User;

import java.util.List;

public interface ResumeService {
    List<Resume> findResumeByUser(User user);

    void saveResume(Resume resume);

    void deleteResume(Resume resume);
}
