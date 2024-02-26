package com.example.todoapp.controller;

import com.example.todoapp.model.User;
import com.example.todoapp.web.request.user.UserRequest;
import com.example.todoapp.web.response.user.ListUsersResponse;
import com.example.todoapp.web.response.user.UserResponse;
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
    public ResponseEntity<ListUsersResponse> findAll(){
        return ResponseEntity.ok(userMapper.findAllUsers(userService.findAll()));
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest createUser){
        User user = userService.createUser(createUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.createdUser(user));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequest updateUser){
        User user = userService.updateUser(id, updateUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.updateUser(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
