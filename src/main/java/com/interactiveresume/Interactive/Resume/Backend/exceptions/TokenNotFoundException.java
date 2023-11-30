package com.interactiveresume.Interactive.Resume.Backend.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TokenNotFoundException extends Exception {

    private final HttpStatus status;
    public TokenNotFoundException() {
        super("JWT token could not be found");
        this.status = HttpStatus.NOT_FOUND;
    }

    public TokenNotFoundException(HttpStatus status) {
        super("JWT token could not be found");
        this.status = status;
    }

    public TokenNotFoundException(Throwable cause) {
        super(cause);
        this.status = HttpStatus.NOT_FOUND;
    }
}
