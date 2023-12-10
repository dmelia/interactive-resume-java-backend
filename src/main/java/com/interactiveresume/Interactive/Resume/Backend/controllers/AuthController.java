package com.interactiveresume.Interactive.Resume.Backend.controllers;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.AuthRequestDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.dtos.JwtResponseDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.dtos.RefreshTokenRequestDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.dtos.UserDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.mapping.UserDTOMapper;
import com.interactiveresume.Interactive.Resume.Backend.data.models.RefreshToken;
import com.interactiveresume.Interactive.Resume.Backend.data.models.User;
import com.interactiveresume.Interactive.Resume.Backend.exceptions.TokenNotFoundException;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.JwtService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.RefreshTokenService;
import com.interactiveresume.Interactive.Resume.Backend.services.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import static com.interactiveresume.Interactive.Resume.Backend.constants.Constants.AUTH_ENDPOINT;

@RestController
@RequestMapping(AUTH_ENDPOINT)
public class AuthController {
    private final UserService userService;

    private final JwtService jwtService;

    private final RefreshTokenService refreshTokenService;

    private final AuthenticationManager authenticationManager;

    private final UserDTOMapper userDTOMapper;

    public AuthController(UserService userService, JwtService jwtService, RefreshTokenService refreshTokenService, AuthenticationManager authenticationManager, UserDTOMapper userDTOMapper) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.authenticationManager = authenticationManager;
        this.userDTOMapper = userDTOMapper;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
            User user = userService.createUser(userDTOMapper.mapDTO(userDTO));
            System.out.println(user.getUsername());
            return new ResponseEntity<>(userDTOMapper.mapModel(user), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin/test")
    public String test() {
        try {
            return "Welcome";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
            return new ResponseEntity<>(JwtResponseDTO.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()))
                    .token(refreshToken.getToken()).build(), HttpStatus.OK);
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<JwtResponseDTO> refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) throws TokenNotFoundException {
        RefreshToken token = refreshTokenService.findByToken(refreshTokenRequestDTO.getToken());
        token = refreshTokenService.verifyExpiration(token);
        User userInfo = token.getUserInfo();
        String accessToken = jwtService.GenerateToken(userInfo.getUsername());
        return new ResponseEntity<>(JwtResponseDTO.builder().accessToken(accessToken).token(refreshTokenRequestDTO.getToken()).build(), HttpStatus.OK);
    }
}
