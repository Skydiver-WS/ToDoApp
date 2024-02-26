package com.example.todoapp.web.request.user;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotNull
    @Min(value = 1)
    private String name;
    @NotNull
    @Min(value = 1)
    @Max(value = 10)
    private String nikName;

}
