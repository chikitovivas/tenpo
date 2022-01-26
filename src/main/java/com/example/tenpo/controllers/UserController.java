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

    public UserController(UserService userService, LogService logService) {
        super(logService);
        this.userService = userService;
    }

    @PostMapping("/sing-up")
    public ResponseEntity<Response> signUp(@RequestBody UserForm userForm, WebRequest webRequest) throws Exception {
        Response response = (Response) execute(
                (UserForm form) -> userService.signUp(userForm.getUsername(), userForm.getPassword()),
                () -> new Response(HttpStatus.OK.value(), "Created Successfully"),
                userForm,
                webRequest,
                "/sing-up");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
