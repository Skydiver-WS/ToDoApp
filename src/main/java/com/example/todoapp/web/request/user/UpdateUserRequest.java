package com.example.todoapp.web.request.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    @Size(min = 1)
    private String name;
    @Size(min = 1, max = 10, message = "The value cannot be more than 10 characters.")
    private String nikName;
    @Size(min = 1)
    private String password;
}
