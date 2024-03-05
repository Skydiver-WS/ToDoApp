package com.example.todoapp.service.secure.impl;

import com.example.todoapp.entity.User;
import com.example.todoapp.security.AppUserPrincipal;
import com.example.todoapp.security.UserDetailsServiceImpl;
import com.example.todoapp.service.secure.AuthService;
import com.example.todoapp.service.secure.JwtService;
import com.example.todoapp.service.simple.UserService;
import com.example.todoapp.web.request.UserSignInRequest;
import com.example.todoapp.web.request.user.CreateUserRequest;
import com.example.todoapp.web.response.jwtresponse.JwtAuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthResponse getToken(CreateUserRequest userRequest) {
        return null;
    }

    @Override
    public JwtAuthResponse singIn(UserSignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getUsername(),
                signInRequest.getPassword()));
        User user = userService.findByNikName(signInRequest.getUsername());
        String jwt = jwtService.generateJwtToken(new AppUserPrincipal(user));
        return new JwtAuthResponse(jwt);
    }
}
