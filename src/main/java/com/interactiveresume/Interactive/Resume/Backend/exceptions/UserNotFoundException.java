package com.interactiveresume.Interactive.Resume.Backend.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User could not be found");
    }
}
