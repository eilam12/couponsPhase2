package com.example.Project2.exceptions;

public class EmailOrPasswordAreWrongException extends Exception {
    public EmailOrPasswordAreWrongException() {
        super("Email or password are wrong");
    }
}

