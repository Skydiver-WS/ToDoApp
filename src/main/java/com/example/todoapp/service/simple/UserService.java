package com.example.todoapp.service.simple;

import com.example.todoapp.entity.Role;
import com.example.todoapp.entity.User;
import com.example.todoapp.web.request.user.UpdateUserRequest;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User createUser(User user);

    User updateUser(String nikName, UpdateUserRequest createUserRequest);

    User findByNikName(String nikName);

    void deleteUserByNikName(String nikName);
}
