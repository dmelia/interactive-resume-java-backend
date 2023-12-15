package com.interactiveresume.Interactive.Resume.Backend.services.impl.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.ResumeDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.mapping.ResumeDTOMapper;
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

    private final ResumeDTOMapper resumeDTOMapper;

    /**
     * Constructor
     * @param resumeJPARepository
     * @param userService
     * @param resumeDTOMapper
     */
    public ResumeServiceImpl(ResumeJPARepository resumeJPARepository, UserService userService, ResumeDTOMapper resumeDTOMapper) {
        this.resumeJPARepository = resumeJPARepository;
        this.userService = userService;
        this.resumeDTOMapper = resumeDTOMapper;
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
    public Resume saveResume(ResumeDTO resumeDTO) throws UserNotFoundException {
        if (resumeDTO.getId() != null) {
            // We are dealing with an existing Resume object
            Optional<Resume> existingResumeOpt = resumeJPARepository.findById(resumeDTO.getId());
            if (existingResumeOpt.isEmpty()) {
                // id input was not valid, if it is a new object to be saved, then we require the id field to be empty
                throw new InputInvalidException();
            } else {
                Resume existingResume = existingResumeOpt.get();

                // TODO : check ownership

                // We only update if something has changed, we do not allow change of ownership
                if (resumeDTO.getName() != null) {
                    existingResume.setName(resumeDTO.getName());
                }
                return resumeJPARepository.save(existingResume);
            }
        }

        // Here we are creating a new Resume in the database
        // Check the fields are properly set
        if (resumeDTO.getName() == null || resumeDTO.getName().isEmpty()) {
            // We need a name, throw an exception
            throw new InputInvalidException();
        }
        // Set the owner as the connected user
        User currentUser = userService.getCurrentUser();
        Resume resume = resumeDTOMapper.mapDTO(resumeDTO);
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
            // TODO : check ownership
            resumeJPARepository.deleteById(id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Resume getResume(Long id) {
        Optional<Resume> optional = resumeJPARepository.findById(id);
        // TODO : check ownership
        return optional.orElse(null);
    }
}
