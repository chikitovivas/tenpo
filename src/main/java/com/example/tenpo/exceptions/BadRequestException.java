package com.example.tenpo.exceptions;

public class BadRequestException extends ServiceException {
    public BadRequestException(String message) {
        super(message);
    }
}
