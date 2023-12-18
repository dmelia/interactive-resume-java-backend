package com.interactiveresume.Interactive.Resume.Backend.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class InputInvalidException extends RuntimeException {

    public InputInvalidException() {
        super("Input is invalid");
    }

    public InputInvalidException(String message) {
        super(message);
    }
}
