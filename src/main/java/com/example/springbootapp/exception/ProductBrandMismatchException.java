package com.example.springbootapp.exception;

public class ProductBrandMismatchException extends RuntimeException {
    public ProductBrandMismatchException(String message) {
        super(message);
    }
}
