package com.interactiveresume.Interactive.Resume.Backend.jpa.auth;

import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenJPARepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    @Modifying
    @Query("DELETE FROM RefreshToken rt WHERE rt.userInfo.username=:username")
    void deleteByUsername(String username);
}
