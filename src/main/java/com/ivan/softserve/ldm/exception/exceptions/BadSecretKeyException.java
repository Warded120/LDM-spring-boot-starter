package com.ivan.softserve.ldm.exception.exceptions;

import lombok.experimental.StandardException;

@StandardException
public class BadSecretKeyException extends RuntimeException {
    public BadSecretKeyException(String message) {
        super(message);
    }
}
