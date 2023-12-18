package com.interactiveresume.Interactive.Resume.Backend.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class TokenNotFoundException extends RuntimeException {

    public TokenNotFoundException() {
        super("JWT token could not be found");
    }
}
