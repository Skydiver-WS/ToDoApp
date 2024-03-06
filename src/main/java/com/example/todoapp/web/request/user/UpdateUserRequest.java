package com.example.todoapp.web.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на обновление пользователя")
public class UpdateUserRequest {
    @Schema(description = "Имя пользователя", example = "Иван")
    @Size(min = 1)
    private String name;
    @Schema(description = "Уникальный ник пользователя", example = "SuperVano")
    @Size(min = 1, max = 10, message = "The value cannot be more than 10 characters.")
    private String nikName;
    @Schema(description = "Пароль", example = "wdddww")
    @Size(min = 1)
    private String password;
}
