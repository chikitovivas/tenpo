package com.example.tenpo.exceptions;

public class ApiError {
    private String error;
    private String message;
    private int status;

    public ApiError(String error, String message, int status) {
        this.error = error;
        this.message = message;
        this.status = status;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
