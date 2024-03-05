package com.example.todoapp.service.simple.impl;

import com.example.todoapp.entity.User;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.service.simple.UserService;
import com.example.todoapp.web.request.user.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(user.getRoles().stream().peek(r -> r.setUser(user)).toList());
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User updateUser(String nikName, UpdateUserRequest createUserRequest) {
        Optional<User> optionalUser = userRepository.findUserByNikName(nikName);
        if (optionalUser.isPresent()) {
            User newUser = optionalUser.get();
            if(createUserRequest.getName() != null){
                newUser.setName(createUserRequest.getName());
            }
            if ((createUserRequest.getNikName() != null)){
                newUser.setNikName(createUserRequest.getNikName());
            }
            if(createUserRequest.getPassword() != null){
                newUser.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
            }
            return userRepository.saveAndFlush(newUser);
        }
        return null;
    }

    @Override
    public User findByNikName(String nikName) {
        return userRepository.findUserByNikName(nikName).orElseThrow(() ->
                new RuntimeException(String.format("User in %s not found", nikName)));
    }

    @Override
    public void deleteUserByNikName(String nikName) {
        userRepository.deleteByNikName(nikName);
    }
}
