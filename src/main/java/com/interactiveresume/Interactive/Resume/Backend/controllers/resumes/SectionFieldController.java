package com.interactiveresume.Interactive.Resume.Backend.controllers.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionFieldDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.mapping.SectionFieldDTOMapper;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionField;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.SectionFieldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.interactiveresume.Interactive.Resume.Backend.constants.Constants.API_ENDPOINT;

@RestController
@CrossOrigin
@RequestMapping(API_ENDPOINT)
public class SectionFieldController {
    private final SectionFieldDTOMapper sectionFieldDTOMapper;
    private final SectionFieldService sectionFieldService;

    /**
     * Constructor
     *
     * @param sectionFieldDTOMapper
     * @param sectionFieldService
     */
    public SectionFieldController(SectionFieldDTOMapper sectionFieldDTOMapper, SectionFieldService sectionFieldService) {
        this.sectionFieldDTOMapper = sectionFieldDTOMapper;
        this.sectionFieldService = sectionFieldService;
    }

    // Create / Update
    @PostMapping("/sectionFields/")
    public ResponseEntity<SectionFieldDTO> saveSectionField(@RequestBody SectionFieldDTO sectionFieldDTO) {
        SectionField savedSectionField = sectionFieldService.saveSectionField(sectionFieldDTO);
        return new ResponseEntity<>(sectionFieldDTOMapper.mapModel(savedSectionField), HttpStatus.OK);
    }

    // Read
    @GetMapping("/sectionFields/{sectionFieldId}")
    public ResponseEntity<SectionFieldDTO> getSectionField(@PathVariable Long sectionFieldId) {
        SectionField sectionField = sectionFieldService.getSectionFieldById(sectionFieldId);
        return new ResponseEntity<>(sectionFieldDTOMapper.mapModel(sectionField), HttpStatus.OK);
    }

    // Read all
    @GetMapping("/sections/{sectionId}/sectionFields/")
    public ResponseEntity<List<SectionFieldDTO>> getSectionFieldsBySectionId(@PathVariable Long sectionId) {
        List<SectionField> sectionFields = sectionFieldService.getSectionFieldsBySectionId(sectionId);
        return new ResponseEntity<>(sectionFieldDTOMapper.mapModelList(sectionFields), HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/sectionFields/{sectionFieldId}")
    public ResponseEntity deleteSectionField(@PathVariable Long sectionFieldId) {
        sectionFieldService.deleteSectionField(sectionFieldId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
