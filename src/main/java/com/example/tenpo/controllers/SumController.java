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

@RestController
public class SumController {

    private SumService sumService;
    private LogService logService;

    public SumController(SumService sumService, LogService logService) {
        this.sumService = sumService;
        this.logService = logService;
    }

    @PostMapping("/sum")
    public ResponseEntity<SumResponse> sum(@RequestBody SumForm sumForm) {
        Integer sum =  sumService.makeSum(sumForm.getToken(), sumForm.getNum1(), sumForm.getNum2());

        SumResponse sumResponse = new SumResponse(sum);
        logService.log("/sum", sumForm, sumResponse);
        return new ResponseEntity<>(sumResponse, HttpStatus.OK);
    }
}
