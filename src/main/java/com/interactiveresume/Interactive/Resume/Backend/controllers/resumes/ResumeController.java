package com.interactiveresume.Interactive.Resume.Backend.controllers.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.ResumeDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.mapping.ResumeDTOMapper;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.Resume;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.UserNotFoundException;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.auth.UserService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.ResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.interactiveresume.Interactive.Resume.Backend.constants.Constants.API_ENDPOINT;

@RestController
@RequestMapping(API_ENDPOINT)
public class ResumeController {

    private final ResumeDTOMapper resumeDTOMapper;

    private final ResumeService resumeService;

    private final UserService userService;

    public ResumeController(ResumeDTOMapper resumeDTOMapper, ResumeService resumeService, UserService userService) {
        this.resumeDTOMapper = resumeDTOMapper;
        this.resumeService = resumeService;
        this.userService = userService;
    }

    // Create / Update
    @PostMapping("/resumes")
    public ResponseEntity<ResumeDTO> createResume(@RequestBody ResumeDTO resumeDTO) {
        Resume savedResume = resumeService.saveResume(resumeDTO);
        return new ResponseEntity<>(resumeDTOMapper.mapModel(savedResume), HttpStatus.OK);
    }

    // Read
    @GetMapping("/resumes")
    public ResponseEntity<List<ResumeDTO>> getResumes() {
        User user = userService.getCurrentUser();
        List<ResumeDTO> resumeDTOS = resumeDTOMapper.mapModelList(resumeService.findResumeByUser(user));
        return new ResponseEntity<>(resumeDTOS, HttpStatus.OK);
    }

    // Read all
    @GetMapping("/resumes/{resumeId}")
    public ResponseEntity<ResumeDTO> getResume(@PathVariable("resumeId") Long id) {
        Resume resume = resumeService.getResume(id);
        return new ResponseEntity<>(resumeDTOMapper.mapModel(resume), HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/resumes/{resumeId}")
    public ResponseEntity<String> deleteResume(@PathVariable("resumeId") Long id) {
        resumeService.deleteResume(id);
        return new ResponseEntity<>("done",HttpStatus.OK);
    }
}
