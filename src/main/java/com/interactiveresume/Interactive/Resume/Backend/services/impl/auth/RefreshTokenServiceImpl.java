package com.interactiveresume.Interactive.Resume.Backend.services.impl.auth;

import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
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
     *
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
        Optional<RefreshToken> optionalRefreshToken = refreshTokenJPARepository.findByToken(UUID.fromString(token));
        if (optionalRefreshToken.isPresent()) {
            return optionalRefreshToken.get();
        }
        throw new TokenNotFoundException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RefreshToken createToken(String username, Instant expiryDate) {
        User user = userService.findByUsername(username);
        RefreshToken refreshToken = RefreshToken.builder()
                .expiryDate(expiryDate)
                .token(UUID.randomUUID())
                .userInfo(user)
                .build();
        return refreshTokenJPARepository.save(refreshToken);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByUser(String username) {
        refreshTokenJPARepository.deleteByUsername(username);
        refreshTokenJPARepository.flush();
    }
}
