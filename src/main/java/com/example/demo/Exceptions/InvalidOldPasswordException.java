package com.example.demo.Exceptions;

public class InvalidOldPasswordException extends Exception{
    public InvalidOldPasswordException() {
        super();
    }


    public InvalidOldPasswordException(String message) {
        super(message);
    }


    public InvalidOldPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
