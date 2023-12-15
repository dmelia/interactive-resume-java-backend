package com.interactiveresume.Interactive.Resume.Backend.jpa.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionElementJPARepository extends JpaRepository<SectionElement, Long> {
    @Modifying
    void deleteSectionElementsByResumeId(Long resumeId);

    List<SectionElement> findSectionElementsByResumeId(Long resumeId);

    @Query("SELECT COUNT(se.id) FROM SectionElement se WHERE se.page.id=:resumePageId AND se.sectionType.id=:sectionTypeId")
    int getNextPosition(Long sectionTypeId, Long resumePageId);
}
