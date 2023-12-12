package com.interactiveresume.Interactive.Resume.Backend.data.mapping;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.ResumeDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.Resume;
import org.springframework.stereotype.Component;

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
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Resume mapDTO(ResumeDTO resumeDTO) {
        return Resume.builder()
                .name(resumeDTO.getName())
                .id(resumeDTO.getId())
                .icon(resumeDTO.getIcon())
                .build();
    }
}
