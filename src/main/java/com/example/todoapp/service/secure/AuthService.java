package com.example.todoapp.service.secure;

import com.example.todoapp.web.request.UserSignInRequest;
import com.example.todoapp.web.request.user.CreateUserRequest;
import com.example.todoapp.web.response.jwtresponse.JwtAuthResponse;

public interface AuthService {
    JwtAuthResponse getToken(CreateUserRequest userRequest);

    JwtAuthResponse singIn(UserSignInRequest signInRequest);
}
