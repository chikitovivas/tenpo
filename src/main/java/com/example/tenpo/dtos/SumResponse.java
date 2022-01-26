package com.example.tenpo.dtos;

public class SumResponse {
    Integer sum;

    public SumResponse(Integer sum) {
        this.sum = sum;
    }

    public Integer getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return "{" +
                "sum=" + sum +
                '}';
    }
}
