package com.example.microservice_employee_service.controller;

import com.example.microservice_employee_service.entity.User;
import com.example.microservice_employee_service.entity.dto.request.RegisterUserRequest;
import com.example.microservice_employee_service.entity.dto.request.UpdateUserRequest;
import com.example.microservice_employee_service.entity.dto.response.UserResponse;
import com.example.microservice_employee_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid RegisterUserRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody @Valid UpdateUserRequest request) {
        return ResponseEntity.ok(userService.update(id, request));
    }

}
