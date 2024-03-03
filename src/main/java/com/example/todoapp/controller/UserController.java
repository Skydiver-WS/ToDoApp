package com.example.todoapp.controller;

import com.example.todoapp.config.anotations.aop.Check;
import com.example.todoapp.config.enums.RoleType;
import com.example.todoapp.entity.Role;
import com.example.todoapp.mapper.UserMapper;
import com.example.todoapp.entity.User;
import com.example.todoapp.web.request.user.CreateUserRequest;
import com.example.todoapp.web.request.user.UpdateUserRequest;
import com.example.todoapp.web.response.user.ListUsersResponse;
import com.example.todoapp.web.response.user.UserResponse;
import com.example.todoapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ListUsersResponse> findAll() {
        return ResponseEntity.ok(userMapper.listUsers(userService.findAll()));
    }

    @PostMapping("/create")
    @Check
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid CreateUserRequest createUser, @RequestParam RoleType roleType) {
        User user = userService.createUser(userMapper
                .userToRequest(createUser,
                        Collections.singletonList(Role.from(roleType))));
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.userResponseToRequest(user));
    }

    @PutMapping("/update/{nikName}")
    @Check
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String nikName, @RequestBody @Valid UpdateUserRequest updateUser) {
        User user = userService.updateUser(nikName, updateUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.userResponseToRequest(user));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{nikName}")
    public ResponseEntity<Void> deleteUser(@PathVariable String nikName) {
        userService.deleteUserByNikName(nikName);
        return ResponseEntity.noContent().build();
    }
}
