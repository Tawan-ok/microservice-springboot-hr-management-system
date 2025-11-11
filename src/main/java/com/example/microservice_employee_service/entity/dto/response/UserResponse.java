package com.example.microservice_employee_service.entity.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String firstName;
    private String lastName;
    private int age;
    private int salary;
    private String location;
}
