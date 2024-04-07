package com.interactiveresume.Interactive.Resume.Backend.exceptions;

import lombok.Getter;

@Getter
public class ResumeNotFoundException  extends RuntimeException {
    public ResumeNotFoundException() {
        super("Resume could not be found");
    }
}
