package com.ivan.softserve.ldm.exception.exceptions;

import lombok.experimental.StandardException;

@StandardException
public class FileReadException extends RuntimeException {
    public FileReadException(String message) {
        super(message);
    }
}
