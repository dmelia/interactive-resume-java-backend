package com.interactiveresume.Interactive.Resume.Backend.jpa.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionInputType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionInputTypeJPARepository extends JpaRepository<SectionInputType, Long> {

    @Query("SELECT COUNT(si.id) FROM SectionInputType si WHERE si.sectionType.id=:sectionTypeId")
    int getNextPosition(Long sectionTypeId);
}
