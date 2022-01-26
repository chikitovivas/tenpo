package com.example.tenpo.controllers;

import com.example.tenpo.services.LogService;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class Controller {

    private LogService logService;

    public Controller(LogService logService) {
        this.logService = logService;
    }

    public <T> Object executeWithReturn(Callable<Object> func, Function<Object, Object> buildResponse, T input, WebRequest webRequest, String url) throws Exception {
        saveContext(input, webRequest, url);

        //make func with return
        Object res = func.call();
        //make func with param
        Object response = buildResponse.apply(res);

        logService.log(url, input, response);
        return response;
    }

    public <T> Object execute(Consumer<T> func, Callable<Object> buildResponse, T input, WebRequest webRequest, String url) throws Exception {
        saveContext(input, webRequest, url);

        //make func without return
        func.accept(input);
        //make func without param
        Object response = buildResponse.call();

        logService.log(url, input, response);
        return response;
    }

    private void saveContext(Object input, WebRequest webRequest, String url) {
        webRequest.setAttribute("request", input, RequestAttributes.SCOPE_REQUEST);
        webRequest.setAttribute("url", url, RequestAttributes.SCOPE_REQUEST);
    }
}
