package com.example.prc.exceptions;

public class MyIllegalArgumentException extends Exception{

    public MyIllegalArgumentException() {
    }

    public MyIllegalArgumentException(String message) {
        super(message);
    }
}
