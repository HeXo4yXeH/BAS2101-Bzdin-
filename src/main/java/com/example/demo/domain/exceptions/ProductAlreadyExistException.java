package com.example.demo.domain.exceptions;

import static java.lang.String.format;

public class ProductAlreadyExistException extends Exception{
    public ProductAlreadyExistException(String name) {
        super(format("product with name %s is already exist", name));
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
