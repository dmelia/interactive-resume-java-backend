package com.interactiveresume.Interactive.Resume.Backend.data.mapping;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.Section;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionField;
import org.springframework.stereotype.Component;

@Component
public class SectionDTOMapper implements DTOMapper<SectionDTO, Section> {

    /**
     * {@inheritDoc}
     */
    @Override
    public SectionDTO mapModel(Section section) {
        return SectionDTO.builder()
                .id(section.getId())
                .fields(section.getFields().stream().map(SectionField::getId).toList())
                .position(section.getPosition())
                .resumePageId(section.getPage().getId())
                .build();
    }
}