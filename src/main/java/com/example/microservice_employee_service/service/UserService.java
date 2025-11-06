package com.example.microservice_employee_service.service;

import com.example.microservice_employee_service.entity.User;
import com.example.microservice_employee_service.entity.dto.request.RegisterUserRequest;
import com.example.microservice_employee_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User register(RegisterUserRequest request) {
        User user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .location(request.getLocation())
                .salary(request.getSalary())
                .build();

        return userRepository.save(user);
    }

//    public User update(RegisterUserRequest request) {
//
//    }
}
