package com.interactiveresume.Interactive.Resume.Backend.services.interfaces.auth;

import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface JwtService {
    /**
     * Extracts the username from a JWT token
     * @param token the JWT token
     * @return the username
     */
    String extractUsername(String token);

    /**
     * Extracts the expiration date from a JWT token
     * @param token the JWT token
     * @return the expiration {@link Date}
     */
    Date extractExpiration(String token);

    /**
     * Extracts a claim from a JWT token
     * @param token the JWT
     * @param claimsResolver the function to resolve a claim
     * @return the found object
     * @param <T> any type of class which is contained within the JWT
     */
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    /**
     * Checks if a JWT token is valid or not
     * @param token the JWT token to check
     * @param userDetails the {@link UserDetails} to verify ownership of the token
     * @return {@link Boolean} if the token is valid or not
     */
    Boolean validateToken(String token, UserDetails userDetails);

    /**
     * Generates a new JWT token for a given {@link User} having the username
     * @param username the username of the {@link User}
     * @return the generated JWT token
     */
    String GenerateToken(String username);
}
