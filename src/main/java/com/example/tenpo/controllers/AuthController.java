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
import org.springframework.web.context.request.WebRequest;


@RestController
@RequestMapping("/auth")
public class AuthController extends Controller {

    private AuthService authService;

    public AuthController(AuthService authService, LogService logService) {
        super(logService);
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserForm userForm, WebRequest webRequest) throws Exception {

        AuthResponse response = (AuthResponse) executeWithReturn(
                () -> authService.login(userForm.getUsername(), userForm.getPassword()),
                (Object token) -> new AuthResponse(token.toString()),
                userForm,
                webRequest,
                "/login");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Response> logout(@RequestBody TokenForm tokenForm, WebRequest webRequest) throws Exception {
        Response response = (Response) execute(
                (TokenForm form) -> authService.logout(form.getToken()),
                () -> new Response(HttpStatus.OK.value(), "Logout successfully"),
                tokenForm,
                webRequest,
                "/logout");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
