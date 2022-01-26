package com.example.tenpo.config;

import com.example.tenpo.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    public ControllerExceptionHandler() {}

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> entityNotFoundException(EntityNotFoundException e, WebRequest request) {
        ApiError apiError = new ApiError("entity_not_found", String.format("Entity not found: %s", e.getMessage()), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(apiError.getStatus())
                .body(apiError);
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiError> UnauthorizedException(UnauthorizedException e) {
        ApiError apiError = new ApiError("unauthorized", String.format("Unauthorized: %s", e.getMessage()), HttpStatus.UNAUTHORIZED.value());
        return ResponseEntity.status(apiError.getStatus())
                .body(apiError);
    }
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> entityAlreadyCreatedException(ConflictException e) {
        ApiError apiError = new ApiError("entity_already_exists", String.format("Entity already Exists: %s", e.getMessage()), HttpStatus.CONFLICT.value());
        return ResponseEntity.status(apiError.getStatus())
                .body(apiError);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> badRequestException(BadRequestException e, WebRequest request) {
        ApiError apiError = new ApiError("bad_request", String.format("Error with your request: %s %s", e.getMessage(), request.getAttribute("request", RequestAttributes.SCOPE_REQUEST)), HttpStatus.CONFLICT.value());
        return ResponseEntity.status(apiError.getStatus())
                .body(apiError);
    }
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiError> unknownError(ServiceException e) {
        ApiError apiError = new ApiError("Error", String.format("Error: %s", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(apiError.getStatus())
                .body(apiError);
    }

}