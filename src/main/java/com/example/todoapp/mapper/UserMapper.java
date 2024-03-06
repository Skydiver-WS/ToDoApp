package com.example.todoapp.mapper;

import com.example.todoapp.entity.Role;
import com.example.todoapp.entity.User;
import com.example.todoapp.web.request.user.CreateUserRequest;
import com.example.todoapp.web.response.user.ListUsersResponse;
import com.example.todoapp.web.response.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {NoteMapper.class, CommentMapper.class, RolesMapper.class})
public interface UserMapper {
    User userToRequest(CreateUserRequest createUserRequest, List<Role> roles);

    @Mapping(target = "notesResponseList", source = "user.noteList")
    @Mapping(target = "commentResponseList", source = "user.commentList")
    @Mapping(target = "roles", source = "user.roles")
    UserResponse userResponseToRequest(User user);

    default ListUsersResponse listUsers(List<User> userList) {
        return new ListUsersResponse(userList.stream().map(this::userResponseToRequest).toList());
    }
}
