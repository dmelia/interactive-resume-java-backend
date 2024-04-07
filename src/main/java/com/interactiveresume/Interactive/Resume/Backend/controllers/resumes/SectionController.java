package com.interactiveresume.Interactive.Resume.Backend.controllers.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.mapping.SectionDTOMapper;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.Section;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.interactiveresume.Interactive.Resume.Backend.constants.Constants.API_ENDPOINT;

@RestController
@RequestMapping(API_ENDPOINT)
public class SectionController {

    private final SectionService sectionService;
    private final SectionDTOMapper sectionDTOMapper;

    public SectionController(SectionService sectionService, SectionDTOMapper sectionDTOMapper) {
        this.sectionService = sectionService;
        this.sectionDTOMapper = sectionDTOMapper;
    }

    // Create / Update
    @PostMapping("/sections/")
    public ResponseEntity<SectionDTO> saveSection(@RequestBody SectionDTO sectionDTO) {
        Section savedSection = sectionService.saveSection(sectionDTO);
        return new ResponseEntity<>(sectionDTOMapper.mapModel(savedSection), HttpStatus.OK);
    }

    // Read
    @GetMapping("/sections/{sectionId}")
    public ResponseEntity<SectionDTO> getSection(@PathVariable Long sectionId) {
        Section section = sectionService.getSectionById(sectionId);
        return new ResponseEntity<>(sectionDTOMapper.mapModel(section), HttpStatus.OK);
    }

    // Read all
    @GetMapping("/resumes/{resumeId}/resumepages/{resumePageId}/sections/")
    public ResponseEntity<List<SectionDTO>> getSections(@PathVariable Long resumeId, @PathVariable Long resumePageId) {
        List<Section> sections = sectionService.getSectionsByResumePage(resumePageId);
        return new ResponseEntity<>(sectionDTOMapper.mapModelList(sections), HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/sections/{sectionId}")
    public ResponseEntity<HttpStatus> deleteSection(@PathVariable Long sectionId) {
        sectionService.deleteSection(sectionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
