package com.interactiveresume.Interactive.Resume.Backend.controllers;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.UserDTO;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.JwtService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.RefreshTokenService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.interactiveresume.Interactive.Resume.Backend.constants.Constants.API_ENDPOINT;

@RestController
@RequestMapping(API_ENDPOINT)
public class UserController {
    private final UserService userService;

    private final JwtService jwtService;

    private final RefreshTokenService refreshTokenService;

    public UserController(UserService userService, JwtService jwtService, RefreshTokenService refreshTokenService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }
}
