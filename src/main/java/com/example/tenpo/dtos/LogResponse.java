package com.example.tenpo.dtos;

import com.example.tenpo.persistence.models.Log;
import org.springframework.data.domain.Page;

public class LogResponse {
    Page<Log> logs;

    public LogResponse(Page<Log> logs) {
        this.logs = logs;
    }

    public Page<Log> getLogs() {
        return logs;
    }

    @Override
    public String toString() {
        return "{" +
                "logs=" + logs +
                '}';
    }
}
