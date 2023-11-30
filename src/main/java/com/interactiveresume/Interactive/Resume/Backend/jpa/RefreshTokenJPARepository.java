package com.interactiveresume.Interactive.Resume.Backend.jpa;

import com.interactiveresume.Interactive.Resume.Backend.data.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenJPARepository extends JpaRepository<RefreshToken, Integer> {

    Optional<RefreshToken> findByToken(String token);
}
