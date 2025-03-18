package com.ivan.softserve.ldm.exception.exceptions;

import lombok.experimental.StandardException;

/**
 * Exception that is thrown when user enters an incorrect secretKey.
 */
@StandardException
public class BadSecretKeyException extends RuntimeException {
    public BadSecretKeyException(String message) {
        super(message);
    }
}
