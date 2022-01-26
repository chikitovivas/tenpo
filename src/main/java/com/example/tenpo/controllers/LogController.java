package com.example.tenpo.controllers;

import com.example.tenpo.dtos.LogForm;
import com.example.tenpo.dtos.LogResponse;
import com.example.tenpo.persistence.models.Log;
import com.example.tenpo.services.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
public class LogController extends Controller {

    private LogService logService;

    public LogController(LogService logService) {
        super(logService);
        this.logService = logService;
    }

    @PostMapping("/logs")
    public ResponseEntity<LogResponse> logs(@RequestBody LogForm logForm, WebRequest webRequest) throws Exception {
        LogResponse response = (LogResponse) executeWithReturn(
                () -> logService.getAll(logForm.getToken(), logForm.getIndex(), logForm.getPageSize()),
                (Object logs) -> new LogResponse((List<Log>)logs),
                logForm,
                webRequest,
                "/logs");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
