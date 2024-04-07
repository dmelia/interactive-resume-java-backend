package com.interactiveresume.Interactive.Resume.Backend.data.mapping;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.SectionFieldDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionField;
import org.springframework.stereotype.Component;

@Component
public class SectionFieldDTOMapper implements DTOMapper<SectionFieldDTO, SectionField> {

    /**
     * {@inheritDoc}
     */
    @Override
    public SectionFieldDTO mapModel(SectionField sectionField) {
        return SectionFieldDTO.builder()
                .sectionId(sectionField.getSection().getId())
                .value(sectionField.getValue())
                .type(sectionField.getType())
                .build();
    }
}
