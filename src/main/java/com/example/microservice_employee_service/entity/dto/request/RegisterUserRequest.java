package com.example.microservice_employee_service.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class RegisterUserRequest {
    @NotBlank(message = "first name must not be null")
    private String firstName;
    @NotBlank(message = "last name must not null")
    private String lastName;
    @NotBlank(message = "age must not null")
    private int age;
    private int salary;
    private String location;
}
