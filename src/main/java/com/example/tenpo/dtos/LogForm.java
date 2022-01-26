package com.example.tenpo.dtos;

public class LogForm {
    private String token;
    private Integer index;
    private Integer pageSize;

    public LogForm(String token, Integer index, Integer pageSize) {
        this.token = token;
        this.index = index;
        this.pageSize = pageSize;
    }

    public String getToken() {
        return token;
    }

    public Integer getIndex() {
        return index;
    }

    public Integer getPageSize() {
        return pageSize;
    }
}
