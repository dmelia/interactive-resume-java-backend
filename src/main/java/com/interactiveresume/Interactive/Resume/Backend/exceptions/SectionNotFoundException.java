package com.interactiveresume.Interactive.Resume.Backend.exceptions;

import lombok.Getter;

@Getter
public class SectionNotFoundException extends RuntimeException {
    public SectionNotFoundException() {
        super("Section could not be found");
    }
}
