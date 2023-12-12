package com.interactiveresume.Interactive.Resume.Backend.services.impl.auth;

import com.interactiveresume.Interactive.Resume.Backend.exceptions.TokenNotFoundException;
import com.interactiveresume.Interactive.Resume.Backend.jpa.auth.RefreshTokenJPARepository;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.RefreshToken;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.auth.RefreshTokenService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.auth.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


@Transactional
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenJPARepository refreshTokenJPARepository;
    private final UserService userService;

    /**
     * Constructor
     *
     * @param userService
     * @param refreshTokenJPARepository
     */
    public RefreshTokenServiceImpl(UserService userService, RefreshTokenJPARepository refreshTokenJPARepository) {
        this.userService = userService;
        this.refreshTokenJPARepository = refreshTokenJPARepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RefreshToken createRefreshToken(String username) {
        // Delete any previous tokens for the user
        refreshTokenJPARepository.deleteByUsername(username);

        // Create a new token
        RefreshToken refreshToken = RefreshToken.builder()
                .userInfo(userService.findByUsername(username))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))
                .build();
        return refreshTokenJPARepository.saveAndFlush(refreshToken);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (isExpired(token)) {
            refreshTokenJPARepository.delete(token);
            refreshTokenJPARepository.flush();
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }

    /**
     * Checks if a token is expired or not
     * @param token the {@link RefreshToken} to verify
     * @return a boolean, if the token is expired or not
     */
    private boolean isExpired(RefreshToken token) {
        return token.getExpiryDate().compareTo(Instant.now()) < 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RefreshToken findByToken(String token) throws TokenNotFoundException {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenJPARepository.findByToken(token);
        if (optionalRefreshToken.isPresent()) {
            return optionalRefreshToken.get();
        }
        throw new TokenNotFoundException();
    }
}
