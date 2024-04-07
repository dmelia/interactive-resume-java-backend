package com.interactiveresume.Interactive.Resume.Backend.data.mapping;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.ResumeDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.Resume;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.ResumePage;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.Section;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResumeDTOMapper implements DTOMapper<ResumeDTO, Resume> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ResumeDTO mapModel(Resume resume) {
        return ResumeDTO.builder()
                .id(resume.getId())
                .name(resume.getName())
                .icon(resume.getIcon())
                .pages(resume.getPages().stream().map(ResumePage::getId).toList())
                .build();
    }
}
