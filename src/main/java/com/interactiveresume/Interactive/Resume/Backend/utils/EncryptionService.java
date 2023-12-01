package com.interactiveresume.Interactive.Resume.Backend.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncryptionService {
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private EncryptionService() {
    }
}
