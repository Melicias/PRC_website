package com.example.prc.exceptions;

public class MyEntityExistsException extends Exception{
    public MyEntityExistsException() {
    }

    public MyEntityExistsException(String message) {
        super(message);
    }
}
