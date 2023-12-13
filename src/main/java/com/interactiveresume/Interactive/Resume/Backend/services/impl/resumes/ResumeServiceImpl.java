package com.interactiveresume.Interactive.Resume.Backend.services.impl.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.Resume;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.InputInvalidException;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;
import com.interactiveresume.Interactive.Resume.Backend.jpa.resumes.ResumeJPARepository;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.ResumeService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.auth.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResumeServiceImpl implements ResumeService {

    private final ResumeJPARepository resumeJPARepository;

    private final UserService userService;

    /**
     * Constructor
     * @param resumeJPARepository
     * @param userService
     */
    public ResumeServiceImpl(ResumeJPARepository resumeJPARepository, UserService userService) {
        this.resumeJPARepository = resumeJPARepository;
        this.userService = userService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Resume> findResumeByUser(User user) {
        return resumeJPARepository.findByUser(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Resume saveResume(Resume resume) throws UserNotFoundException {
        if (resume.getId() != null) {
            // We are dealing with an existing Resume object
            Optional<Resume> existingResumeOpt = resumeJPARepository.findById(resume.getId());
            if (existingResumeOpt.isEmpty()) {
                // id input was not valid, if it is a new object to be saved, then we require the id field to be empty
                throw new InputInvalidException();
            } else {
                Resume existingResume = existingResumeOpt.get();
                // We only update if something has changed, we do not allow change of ownership
                if (resume.getName() != null) {
                    existingResume.setName(resume.getName());
                }
                return resumeJPARepository.save(existingResume);
            }
        }

        // Here we are creating a new Resume in the database
        // Check the fields are properly set
        if (resume.getName() == null || resume.getName().isEmpty()) {
            // We need a name, throw an exception
            throw new InputInvalidException();
        }
        // Set the owner as the connected user
        User currentUser = userService.getCurrentUser();
        resume.setUser(currentUser);

        // Save the resume
       return resumeJPARepository.save(resume);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteResume(Long id) {
        if (id != null) {
            // Thanks to orphan removal set to true on the models, this should also delete any straggling resume data
            resumeJPARepository.deleteById(id);
        }
    }
}
