package com.interactiveresume.Interactive.Resume.Backend.controllers.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.ResumePageDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.mapping.ResumePageDTOMapper;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.ResumePage;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.ResumePageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.interactiveresume.Interactive.Resume.Backend.constants.Constants.API_ENDPOINT;

@RestController
@CrossOrigin
@RequestMapping(API_ENDPOINT)
public class ResumePageController {

    private final ResumePageService resumePageService;

    private final ResumePageDTOMapper resumePageDTOMapper;

    public ResumePageController(ResumePageDTOMapper resumePageDTOMapper, ResumePageService resumePageService) {
        this.resumePageDTOMapper = resumePageDTOMapper;
        this.resumePageService = resumePageService;
    }

    // Create / Update
    @PostMapping("/resumes/{resumeId}/pages/")
    public ResponseEntity<ResumePageDTO> createPage(@RequestBody ResumePageDTO resumePageDTO) {
        ResumePage savedResumePage = resumePageService.saveResumePage(resumePageDTO);
        ResumePageDTO savedResumePageDTO = resumePageDTOMapper.mapModel(savedResumePage);
        return new ResponseEntity<>(savedResumePageDTO, HttpStatus.OK);
    }

    // Read all
    @GetMapping("/resumes/{resumeId}/pages/")
    public ResponseEntity<List<ResumePageDTO>> getResumePages(@PathVariable Long resumeId) {
        List<ResumePage> pagesByResumeId = resumePageService.getResumePagesByResumeId(resumeId);
        List<ResumePageDTO> resumePageDTOList = resumePageDTOMapper.mapModelList(pagesByResumeId);
        return new ResponseEntity<>(resumePageDTOList, HttpStatus.OK);
    }


    // Read
    @GetMapping("/resumes/{resumeId}/pages/{resumePageId}")
    public ResponseEntity<ResumePageDTO> getResumePages(@PathVariable Long resumeId, @PathVariable Long resumePageId) {
        ResumePage resumePageById = resumePageService.getResumePageById(resumePageId);
        ResumePageDTO resumePageDTO = resumePageDTOMapper.mapModel(resumePageById);
        return new ResponseEntity<>(resumePageDTO, HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("resumes/{resumeId}/pages/{resumePageId}")
    public ResponseEntity<HttpStatus> deleteResumePage(@PathVariable Long resumeId, @PathVariable Long resumePageId) {
        resumePageService.deleteResumePage(resumePageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
