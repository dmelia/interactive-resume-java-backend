package com.interactiveresume.Interactive.Resume.Backend.data.mapping;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes.ResumeDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.Resume;
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
        ResumeDTO dto = ResumeDTO.builder()
                .id(resume.getId())
                .name(resume.getName())
                .icon(resume.getIcon())
                .build();
        List<Long> sectionIds = new ArrayList<>();
        resume.getPages().forEach(resumePage -> {
            resumePage.getSections().forEach(sectionElement -> {
                sectionIds.add(sectionElement.getId());
            });
        });
        dto.setSections(sectionIds);
        return dto;
    }
}
