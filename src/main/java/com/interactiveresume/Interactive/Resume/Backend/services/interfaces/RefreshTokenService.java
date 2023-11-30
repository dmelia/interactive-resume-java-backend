package com.interactiveresume.Interactive.Resume.Backend.services.interfaces;

import com.interactiveresume.Interactive.Resume.Backend.exceptions.TokenNotFoundException;
import com.interactiveresume.Interactive.Resume.Backend.models.RefreshToken;

public interface RefreshTokenService {
    public RefreshToken createRefreshToken(String username);
    public RefreshToken verifyExpiration(RefreshToken token);
    public RefreshToken findByToken(String token) throws TokenNotFoundException;
}
