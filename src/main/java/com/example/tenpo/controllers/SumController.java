package com.example.tenpo.controllers;

import com.example.tenpo.dtos.SumForm;
import com.example.tenpo.dtos.SumResponse;
import com.example.tenpo.services.LogService;
import com.example.tenpo.services.SumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class SumController extends Controller {

    private SumService sumService;

    public SumController(SumService sumService, LogService logService) {
        super(logService);
        this.sumService = sumService;
    }

    @PostMapping("/sum")
    public ResponseEntity<SumResponse> sum(@RequestBody SumForm sumForm, WebRequest webRequest) throws Exception {
        SumResponse response = (SumResponse) executeWithReturn(
                () -> sumService.makeSum(sumForm.getToken(), sumForm.getNum1(), sumForm.getNum2()),
                (Object sum) -> new SumResponse((Integer)sum),
                sumForm,
                webRequest,
                "/sum");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
