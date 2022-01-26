package com.example.tenpo.controllers;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

public abstract class Controller {
    public Object execute(Runnable runnable, Object input, WebRequest webRequest) {
        webRequest.setAttribute("request", input, RequestAttributes.SCOPE_REQUEST);

        runnable.run();


    }
}
