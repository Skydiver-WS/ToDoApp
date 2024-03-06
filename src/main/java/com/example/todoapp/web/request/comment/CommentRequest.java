package com.example.todoapp.web.request.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на создание/обновление комментария.")
public class CommentRequest {
    @Schema(description = "Уникальный заголовок записи.", example = "This is new news!!!")
    @NotNull
    private String titleNote;
    @Schema(description = "Уникальный заголовок записи.", example = "This is very cool news.")
    @NotNull
    @Size(min = 10, max = 100)
    private String comment;
}
