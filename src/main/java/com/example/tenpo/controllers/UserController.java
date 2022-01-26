package com.example.tenpo.controllers;

import com.example.tenpo.dtos.Response;
import com.example.tenpo.dtos.UserForm;
import com.example.tenpo.services.LogService;
import com.example.tenpo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/user")
public class UserController extends Controller {

    private UserService userService;
    private LogService logService;

    public UserController(UserService userService, LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    @PostMapping("/sing-up")
    public ResponseEntity<Response> signUp(@RequestBody UserForm userForm, WebRequest webRequest) {
        userService.signUp(userForm.getUsername(), userForm.getPassword());

        Response response = new Response(HttpStatus.OK.value(), "Created Successfully");
        logService.log("/sing-up", userForm, response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
