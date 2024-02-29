package com.example.todoapp.service.impl;

import com.example.todoapp.model.User;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.service.UserService;
import com.example.todoapp.web.request.user.CreateUserRequest;
import com.example.todoapp.web.request.user.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String nikName, UpdateUserRequest createUserRequest) {
        Optional<User> optionalUser = userRepository.findUserByNikName(nikName);
        if (optionalUser.isPresent()) {
            User newUser = optionalUser.get();
            newUser.setName(createUserRequest.getName());
            newUser.setNikName(createUserRequest.getNikName());
            return userRepository.save(newUser);
        }
        return null;
    }

    @Override
    public void deleteUserByNikName(String nikName) {
        userRepository.deleteByNikName(nikName);
    }
}
