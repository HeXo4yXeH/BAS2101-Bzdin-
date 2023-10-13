package com.example.demo.domain.exceptions;

import static java.lang.String.format;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String name) {
        super(format("product with name %s not found", name));
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
