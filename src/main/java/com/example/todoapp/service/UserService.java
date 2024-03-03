package com.example.todoapp.service;

import com.example.todoapp.entity.Role;
import com.example.todoapp.entity.User;
import com.example.todoapp.web.request.user.UpdateUserRequest;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User createUser(User user);

    User updateUser(String nikName, UpdateUserRequest createUserRequest);

    void deleteUserByNikName(String nikName);
}
