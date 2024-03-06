package com.example.todoapp.web.response.user;

import com.example.todoapp.config.enums.RoleType;
import lombok.Data;

@Data
public class RoleResponse {
    //private String userId;
    private RoleType authority;
}
