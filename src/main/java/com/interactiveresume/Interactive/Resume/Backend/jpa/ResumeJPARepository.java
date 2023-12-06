package com.interactiveresume.Interactive.Resume.Backend.jpa;

import com.interactiveresume.Interactive.Resume.Backend.data.models.Resume;
import com.interactiveresume.Interactive.Resume.Backend.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeJPARepository extends JpaRepository<Resume, Long> {
    @Query("select r from Resume as r where r.name like :name")
    Optional<Resume> findByName(String name);

    List<Resume> findByUser(User user);
}
