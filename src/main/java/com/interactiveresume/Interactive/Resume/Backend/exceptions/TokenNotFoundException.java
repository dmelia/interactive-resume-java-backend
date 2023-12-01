package com.interactiveresume.Interactive.Resume.Backend.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TokenNotFoundException extends Exception {

    public TokenNotFoundException() {
        super("JWT token could not be found");
    }

    public TokenNotFoundException(Throwable cause) {
        super(cause);
    }
}
