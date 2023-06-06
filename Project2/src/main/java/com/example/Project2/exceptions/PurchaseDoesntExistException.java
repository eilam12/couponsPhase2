package com.example.Project2.exceptions;

public class PurchaseDoesntExistException extends Exception {
    public PurchaseDoesntExistException(String message) {
        super(message);
    }
}
