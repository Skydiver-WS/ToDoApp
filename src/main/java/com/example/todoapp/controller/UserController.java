package com.example.todoapp.controller;

import com.example.todoapp.anotations.aop.Check;
import com.example.todoapp.mapper.UserMapper;
import com.example.todoapp.model.User;
import com.example.todoapp.web.request.user.CreateUserRequest;
import com.example.todoapp.web.request.user.UpdateUserRequest;
import com.example.todoapp.web.response.user.ListUsersResponse;
import com.example.todoapp.web.response.user.UserResponse;
import com.example.todoapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<ListUsersResponse> findAll() {
        return ResponseEntity.ok(userMapper.listUsers(userService.findAll()));
    }

    @PostMapping("/create")
    @Check
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid CreateUserRequest createUser) {
        User user = userService.createUser(userMapper.userToRequest(createUser));
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.userResponseToRequest(user));
    }
//Сделать проверку по наличию
    @PutMapping("/update/{nikName}")
    @Check
    public ResponseEntity<UserResponse> updateUser(@PathVariable String nikName, @RequestBody @Valid UpdateUserRequest updateUser) {
        User user = userService.updateUser(nikName, updateUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.userResponseToRequest(user));
    }

    @DeleteMapping("/delete/{nikName}")
    public ResponseEntity<Void> deleteUser(@PathVariable String nikName) {
        userService.deleteUserByNikName(nikName);
        return ResponseEntity.noContent().build();
    }
}
