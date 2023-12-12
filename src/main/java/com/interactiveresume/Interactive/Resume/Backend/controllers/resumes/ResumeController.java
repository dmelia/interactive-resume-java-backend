package com.interactiveresume.Interactive.Resume.Backend.controllers.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.ResumeDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.mapping.ResumeDTOMapper;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.Resume;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.ResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.interactiveresume.Interactive.Resume.Backend.constants.Constants.API_ENDPOINT;

@RestController
@RequestMapping(API_ENDPOINT)
public class ResumeController {

    private final ResumeDTOMapper resumeDTOMapper;

    private final ResumeService resumeService;

    public ResumeController(ResumeDTOMapper resumeDTOMapper, ResumeService resumeService) {
        this.resumeDTOMapper = resumeDTOMapper;
        this.resumeService = resumeService;
    }

    @PostMapping("/resumes")
    public ResponseEntity<ResumeDTO> createResume(@RequestBody ResumeDTO resumeDTO) throws UserNotFoundException {
        Resume resumeToSave = resumeDTOMapper.mapDTO(resumeDTO);
        Resume savedResume = resumeService.saveResume(resumeToSave);

        return new ResponseEntity<>(resumeDTOMapper.mapModel(savedResume), HttpStatus.OK);
    }
}
