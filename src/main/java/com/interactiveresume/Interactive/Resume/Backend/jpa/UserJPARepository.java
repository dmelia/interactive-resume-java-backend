package com.interactiveresume.Interactive.Resume.Backend.jpa;

import com.interactiveresume.Interactive.Resume.Backend.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}