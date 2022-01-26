package com.example.tenpo.dtos;

public class TokenForm {
    String token;

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
