package com.springteam.TechProduct.exception;

import java.io.IOException;

public class FileNotFoundException extends IOException {
    public FileNotFoundException(String message) {
        super(message);
    }

    public FileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileNotFoundException(Throwable cause) {
        super(cause);
    }
}
