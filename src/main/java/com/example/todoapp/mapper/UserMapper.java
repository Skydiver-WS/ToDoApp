package com.example.todoapp.mapper;

import com.example.todoapp.model.User;
import com.example.todoapp.web.request.user.CreateUserRequest;
import com.example.todoapp.web.response.user.ListUsersResponse;
import com.example.todoapp.web.response.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {NoteMapper.class, CommentMapper.class})
public interface UserMapper {
    User userToRequest(CreateUserRequest createUserRequest);

    @Mapping(target = "notesResponseList", source = "user.noteList")
    @Mapping(target = "commentResponseList", source = "user.commentList")
    UserResponse userResponseToRequest(User user);

    default ListUsersResponse listUsers(List<User> userList) {
        return new ListUsersResponse(userList.stream().map(this::userResponseToRequest).toList());
    }
}
