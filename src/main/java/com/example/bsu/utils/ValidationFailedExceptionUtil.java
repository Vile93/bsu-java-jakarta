package com.example.bsu.utils;

public class ValidationFailedExceptionUtil extends Exception {
    public ValidationFailedExceptionUtil(String message) {
        super(message);
    }
    public String getInfo() {
        return "";
    }
}
