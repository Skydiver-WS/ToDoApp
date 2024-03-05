package com.example.todoapp.web.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserSignInRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
