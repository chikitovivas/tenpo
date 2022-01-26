package com.example.tenpo.controllers;

import com.example.tenpo.dtos.AuthResponse;
import com.example.tenpo.dtos.Response;
import com.example.tenpo.dtos.TokenForm;
import com.example.tenpo.dtos.UserForm;
import com.example.tenpo.services.AuthService;
import com.example.tenpo.services.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;
    private LogService logService;

    public AuthController(AuthService authService, LogService logService) {
        this.authService = authService;
        this.logService = logService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserForm userForm) {
        String token =  authService.login(userForm.getUsername(), userForm.getPassword());

        AuthResponse authResponse = new AuthResponse(token);
        logService.log("/login", userForm, authResponse);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Response> logout(@RequestBody TokenForm tokenForm) {
        authService.logout(tokenForm.getToken());

        Response response = new Response(HttpStatus.OK.value(), "Logout successfully");
        logService.log("/logout", tokenForm, response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
