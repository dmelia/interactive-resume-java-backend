package com.interactiveresume.Interactive.Resume.Backend.jpa.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.ResumePage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumePageJPARepository extends JpaRepository<ResumePage, Long> {

    @Query("SELECT MAX(rp.index) + 1 FROM ResumePage rp WHERE rp.resume.id=:resumeId")
    int getNextResumePageIndex(Long resumeId);
}
