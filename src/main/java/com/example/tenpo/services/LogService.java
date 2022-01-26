package com.example.tenpo.services;

import com.example.tenpo.persistence.models.Log;
import com.example.tenpo.persistence.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class LogService {
    private LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void log(String url, Object Request, Object Response) {
        Log log = new Log(Request.toString(), Response.toString(), OffsetDateTime.now(), url);
        logRepository.save(log);
    }

    public Iterable<Log> getAll(String token, Integer index, Integer pageSize) {
        return logRepository.findAll();
    }
}
