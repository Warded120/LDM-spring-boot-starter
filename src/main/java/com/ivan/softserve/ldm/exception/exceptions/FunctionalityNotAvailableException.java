package com.ivan.softserve.ldm.exception.exceptions;

import lombok.experimental.StandardException;

@StandardException
public class FunctionalityNotAvailableException extends RuntimeException {
    public FunctionalityNotAvailableException(String message) {
        super(message);
    }
}
