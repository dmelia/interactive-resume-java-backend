package com.interactiveresume.Interactive.Resume.Backend.exceptions;

import lombok.Getter;

@Getter
public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
