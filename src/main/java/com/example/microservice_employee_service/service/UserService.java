package com.example.microservice_employee_service.service;

import com.example.microservice_employee_service.entity.User;
import com.example.microservice_employee_service.entity.dto.request.RegisterUserRequest;
import com.example.microservice_employee_service.entity.dto.request.UpdateUserRequest;
import com.example.microservice_employee_service.entity.dto.response.UserResponse;
import com.example.microservice_employee_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserResponse> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToUserResponse)
                .toList();
    }

    public UserResponse getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id not found"));
        return mapToUserResponse(user);
    }

    public UserResponse register(RegisterUserRequest request) {
        User user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .location(request.getLocation())
                .salary(request.getSalary())
                .build();
        userRepository.save(user);

        return mapToUserResponse(user);
    }

    public UserResponse update(long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("user not found"));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setAge(request.getAge());
        user.setSalary(request.getSalary());
        user.setLocation(request.getLocation());

        userRepository.save(user);

        return  mapToUserResponse(user);
    }

    public UserResponse mapToUserResponse(User user) {
        return  UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .location(user.getLocation())
                .salary(user.getSalary())
                .build();
    }
}
