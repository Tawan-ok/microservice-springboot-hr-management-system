package com.example.microservice_employee_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ApiError errorResponse = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Validation Failed")
                .timestamp(System.currentTimeMillis())
                .validationErrors(errors)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgumentExceptions(IllegalArgumentException ex) {

        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getMessage(), ex.getMessage());
        ApiError errorResponse = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Validation Failed")
                .timestamp(System.currentTimeMillis())
                .validationErrors(errors)
                .build();

        return  new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
 }
