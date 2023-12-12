package com.interactiveresume.Interactive.Resume.Backend.services.interfaces.auth;

import com.interactiveresume.Interactive.Resume.Backend.exceptions.TokenNotFoundException;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.RefreshToken;

public interface RefreshTokenService {
    /**
     * Creates a refresh token for a given username
     * @param username the username
     * @return the created {@link RefreshToken}
     */
    RefreshToken createRefreshToken(String username);

    /**
     * Verifies if a {@link RefreshToken} is expired or not
     * @param token the {@link RefreshToken} to check
     * @return the {@link RefreshToken} if it is not expired, otherwise an Exception is thrown
     */
    RefreshToken verifyExpiration(RefreshToken token);

    /**
     * Finds a {@link RefreshToken} by its token field value
     * @param token the token to check
     * @return the found {@link RefreshToken}
     * @throws {@link TokenNotFoundException} if the token is not found
     */
    RefreshToken findByToken(String token) throws TokenNotFoundException;
}
