package com.interactiveresume.Interactive.Resume.Backend.jpa.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SectionElementJPARepository extends JpaRepository<SectionElement, Long> {
    @Modifying
    void deleteSectionElementsByResumeId(Long resumeId);

    List<SectionElement> findSectionElementsByResumeId(Long resumeId);
}
