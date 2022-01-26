package com.example.tenpo.dtos;

public class AuthResponse {
    String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "{" +
                "token='" + token + '\'' +
                '}';
    }
}
