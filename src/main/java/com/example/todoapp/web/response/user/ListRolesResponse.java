package com.example.todoapp.web.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.List;

@Data
@AllArgsConstructor
public class ListRolesResponse {
    private List<RoleResponse> roles;
}
