package com.example.Project2.exceptions;

public class NameOrEmailAlreadyExistsException extends Exception {
    public NameOrEmailAlreadyExistsException(String message) {
        super(message);
    }
}
