package com.interactiveresume.Interactive.Resume.Backend.services.interfaces.auth;

import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.TokenNotFoundException;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.RefreshToken;

import java.time.Instant;

public interface RefreshTokenService {

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

    /**
     * Creates a new token in the database for the current connected {@link User}
     * @param username the username
     * @param expiryDate the expiry date
     * @return the saved {@link RefreshToken}
     */
    RefreshToken createToken(String username, Instant expiryDate);

    /**
     * Deletes a {@link RefreshToken} by its username
     * @param username the value of the username
     */
    void deleteByUser(String username);
}
