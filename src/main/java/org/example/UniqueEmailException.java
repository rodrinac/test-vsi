package org.example;

public class UniqueEmailException extends RuntimeException {
    public UniqueEmailException() {
        super("Email already exists");
    }
}
