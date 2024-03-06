package com.example.todoapp.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Schema(description = "Авторизация пользователя")
public class UserSignInRequest {
    @Schema(description = "Уникальный ник пользователя", example = "Vano")
    @NotNull
    private String username;
    @Schema(description = "Пароль", example = "my_1secret1_password")
    @NotNull
    private String password;
}
