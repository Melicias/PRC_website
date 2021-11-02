package com.example.prc.exceptions;

public class MyEntityNotFoundException extends Exception{
    public MyEntityNotFoundException() {
    }

    public MyEntityNotFoundException(String message) {
        super(message);
    }
}
