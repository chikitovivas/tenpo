package com.example.tenpo.dtos;

public class SumForm {
    private String token;
    private Integer num1;
    private Integer num2;

    public String getToken() {
        return token;
    }

    public Integer getNum1() {
        return num1;
    }

    public Integer getNum2() {
        return num2;
    }

    @Override
    public String toString() {
        return "{" +
                "token='" + token + '\'' +
                ", num1=" + num1 +
                ", num2=" + num2 +
                '}';
    }
}
