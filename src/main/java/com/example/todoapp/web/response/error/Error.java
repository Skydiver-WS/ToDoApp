package com.example.todoapp.web.response.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.Instant;

@AllArgsConstructor
@Data
public class Error {
    private String httpStatus;
    private String message;
    private Instant dataTime;
}
