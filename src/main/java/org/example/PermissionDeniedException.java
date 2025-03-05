package org.example;

public class PermissionDeniedException extends RuntimeException {

    public PermissionDeniedException(final String message) {
        super(message);
    }
}
