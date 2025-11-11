package com.example.microservice_employee_service.exception;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ApiError {
    private int status;
    private String message;
    private long timestamp;
    private Map<String, String> validationErrors;
}
