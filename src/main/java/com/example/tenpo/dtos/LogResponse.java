package com.example.tenpo.dtos;

import com.example.tenpo.persistence.models.Log;

import java.util.List;

public class LogResponse {
    List<Log> logs;

    public LogResponse(List<Log> logs) {
        this.logs = logs;
    }

    public List<Log> getLogs() {
        return logs;
    }

    @Override
    public String toString() {
        return "{" +
                "logs=" + logs +
                '}';
    }
}
