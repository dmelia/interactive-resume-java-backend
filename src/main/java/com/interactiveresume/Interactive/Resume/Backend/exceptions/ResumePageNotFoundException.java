package com.interactiveresume.Interactive.Resume.Backend.exceptions;

import lombok.Getter;

@Getter
public class ResumePageNotFoundException extends RuntimeException {
    public ResumePageNotFoundException() {
        super("Resume page could not be found");
    }
}