package com.example.microservice_employee_service.service;

import com.example.microservice_employee_service.entity.User;
import com.example.microservice_employee_service.entity.dto.request.RegisterUserRequest;
import com.example.microservice_employee_service.entity.dto.request.UpdateUserRequest;
import com.example.microservice_employee_service.entity.dto.response.UserResponse;
import com.example.microservice_employee_service.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    private  List<User> users = new ArrayList<>();

    @BeforeEach
    void setUp() {
        User user1 = User.builder()
                .firstName("dday")
                .lastName("lastname")
                .age(22)
                .salary(120000)
                .build();
        User user2 = User.builder()
                .firstName("dday01")
                .lastName("lastname")
                .age(22)
                .salary(120000)
                .build();

        users.add(user1);
        users.add(user2);
    }


    @Test
    void shouldSuccessWhenGetAllUsers() {
        when(userRepository.findAll()).thenReturn(users);

        List<UserResponse> response = userService.getAllUser();

        assertEquals(2, response.size());
        assertEquals("dday",response.getFirst().getFirstName());
    }

    @Test
    void shouldSuccessWhenGetUserById() {
        long id = 1L;
        User user1 = User.builder()
                .id(1L)
                .firstName("dday")
                .lastName("lastname")
                .age(22)
                .salary(120000)
                .build();
        when(userRepository.findById(id)).thenReturn(Optional.of(user1));

        UserResponse response = userService.getUserById(id);

        assertEquals("dday", response.getFirstName());

    }

    @Test
    void shouldSuccessWhenRegisterUser() {
        RegisterUserRequest request = RegisterUserRequest.builder()
                .firstName("dday")
                .lastName("lastname")
                .age(22)
                .salary(120000)
                .build();
        User user = User.builder()
                .id(1L)
                .firstName("dday")
                .lastName("lastname")
                .age(22)
                .salary(120000)
                .build();

        when(userRepository.save(any())).thenReturn(user);

        UserResponse response = userService.register(request);

        assertEquals("dday", response.getFirstName());
        assertEquals(22, response.getAge());
    }

    @Test
    void shouldSuccessWhenUpdateUser() {
        long id = 1L;
        UpdateUserRequest request = UpdateUserRequest.builder()
                .firstName("dday")
                .lastName("lastname")
                .age(22)
                .salary(120000)
                .build();
        User user = User.builder()
                .id(1L)
                .firstName("dday")
                .lastName("lastname")
                .age(22)
                .salary(120000)
                .build();

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(userRepository.save(any())).thenReturn(user);

        UserResponse response = userService.update(id, request);
        assertEquals("dday", response.getFirstName());
    }
}