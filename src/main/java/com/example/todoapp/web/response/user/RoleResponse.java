package com.example.todoapp.web.response.user;

import com.example.todoapp.entity.Role;
import lombok.Data;

@Data
public class RoleResponse {
    private String userId;
    private Role role;
}
