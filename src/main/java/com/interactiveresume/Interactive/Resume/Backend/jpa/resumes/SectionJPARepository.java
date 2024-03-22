package com.interactiveresume.Interactive.Resume.Backend.jpa.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionJPARepository extends JpaRepository<Section, Long> {
    @Modifying
    void deleteSectionByPageId(Long resumeId);

    @Query("SELECT (MAX(se.position) + 1) FROM Section se WHERE se.page.id=:resumePageId")
    int getNextPosition(Long resumePageId);
}
