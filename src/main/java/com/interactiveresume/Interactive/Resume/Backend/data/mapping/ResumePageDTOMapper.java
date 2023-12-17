package com.interactiveresume.Interactive.Resume.Backend.data.mapping;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.ResumePageDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.ResumePage;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionElement;
import org.springframework.stereotype.Component;

/**
 * The ResumePage and ResumePageDTO mapper
 */
@Component
public class ResumePageDTOMapper implements DTOMapper<ResumePageDTO, ResumePage> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ResumePageDTO mapModel(ResumePage resumePage) {
        return ResumePageDTO.builder()
                .resumeId(resumePage.getResume().getId())
                .index(resumePage.getIndex())
                .id(resumePage.getId())
                .name(resumePage.getName())
                .sections(resumePage.getSections().stream().map(SectionElement::getId).toList())
                .build();
    }
}
