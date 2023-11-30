package com.interactiveresume.Interactive.Resume.Backend.jpa;

import com.interactiveresume.Interactive.Resume.Backend.data.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeJPARepository extends JpaRepository<Resume, Long> {
    @Query("select r from Resume as r where r.name like :name")
    Resume findByName(String name);
}
