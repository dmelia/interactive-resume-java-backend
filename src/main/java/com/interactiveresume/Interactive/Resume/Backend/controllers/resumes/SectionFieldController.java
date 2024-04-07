package com.interactiveresume.Interactive.Resume.Backend.controllers.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.mapping.SectionFieldDTOMapper;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.resumes.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.interactiveresume.Interactive.Resume.Backend.constants.Constants.API_ENDPOINT;

@RestController
@RequestMapping(API_ENDPOINT)
public class SectionFieldController {
    @Autowired
    private SectionFieldDTOMapper sectionFieldDTOMapper;
    @Autowired
    private SectionService sectionService;

    // Create / Update

    // Read

    // Read all

    // Delete
}
