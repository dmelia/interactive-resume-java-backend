package com.interactiveresume.Interactive.Resume.Backend.exceptions;

import lombok.Getter;

@Getter
public class SectionFieldNotFoundException extends RuntimeException {
    public SectionFieldNotFoundException() {
        super("Section field could not be found");
    }
}
