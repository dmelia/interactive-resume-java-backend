package com.interactiveresume.Interactive.Resume.Backend.jpa.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionElement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionElementJPARepository extends JpaRepository<SectionElement, Long> {
}
