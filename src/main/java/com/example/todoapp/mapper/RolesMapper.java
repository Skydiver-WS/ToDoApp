package com.example.todoapp.mapper;

import com.example.todoapp.entity.Role;
import com.example.todoapp.web.response.user.RoleResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolesMapper {
    List<RoleResponse> roleToRoleResponse(List<Role> roleList);
}
