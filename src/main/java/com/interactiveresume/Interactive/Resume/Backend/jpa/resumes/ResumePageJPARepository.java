package com.interactiveresume.Interactive.Resume.Backend.jpa.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.ResumePage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumePageJPARepository extends JpaRepository<ResumePage, Long> {
}
