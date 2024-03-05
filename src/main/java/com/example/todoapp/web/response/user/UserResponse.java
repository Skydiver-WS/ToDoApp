package com.example.todoapp.web.response.user;

import com.example.todoapp.entity.Role;
import com.example.todoapp.web.response.comment.CommentResponse;
import com.example.todoapp.web.response.jwtresponse.JwtAuthResponse;
import com.example.todoapp.web.response.note.NoteResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private Long id;
    private String nikName;
    private String name;
    private ListRolesResponse roles;
    //private String password;
    //private JwtAuthResponse jwtAuthResponse; // это тестовый ответ токена
    private List<NoteResponse> notesResponseList;
    private List<CommentResponse> commentResponseList;
}
