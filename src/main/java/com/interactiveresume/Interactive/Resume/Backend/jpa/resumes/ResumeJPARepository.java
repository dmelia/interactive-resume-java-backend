package com.interactiveresume.Interactive.Resume.Backend.jpa.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.Resume;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeJPARepository extends JpaRepository<Resume, Long> {
    List<Resume> findByUser(User user);

    @Modifying
    @Query("DELETE FROM Resume r where r.user.username = :username")
    void deleteByUsername(String username);
}
