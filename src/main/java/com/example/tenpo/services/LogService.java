package com.example.tenpo.services;

import com.example.tenpo.exceptions.UnauthorizedException;
import com.example.tenpo.persistence.models.Log;
import com.example.tenpo.persistence.repository.LogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class LogService {
    private LogRepository logRepository;
    private AuthService authService;

    private final Integer DEFAULT_INDEX = 0;
    private final Integer DEFAULT_PAGE_SIZE = 10;

    public LogService(LogRepository logRepository, AuthService authService) {
        this.logRepository = logRepository;
        this.authService = authService;
    }

    public void log(String url, Object Request, Object Response) {
        Log log = new Log(Request.toString(), Response.toString(), OffsetDateTime.now(), url);
        logRepository.save(log);
    }

    public Page<Log> getAll(String token, Integer index, Integer pageSize) {
        if (!authService.isValidToken(token)) {
            throw new UnauthorizedException("Invalid token");
        }

        Pageable pageable = getPageable(index, pageSize);
        return logRepository.findAll(pageable);
    }

    private Pageable getPageable(Integer index, Integer pageSize) {
        return PageRequest.of(
                index == null ? DEFAULT_INDEX : index,
                pageSize == null ? DEFAULT_PAGE_SIZE : pageSize
        );
    }
}
