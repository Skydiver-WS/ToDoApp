package com.example.todoapp.web.response.comment;

import com.example.todoapp.web.response.note.NoteResponseToComment;
import com.example.todoapp.web.response.user.UserResponseToNote;
import lombok.Data;

@Data
public class CommentResponse {
    private Long id;
    private String comment;
    private UserResponseToNote nikName;
    private NoteResponseToComment noteResponseToComment;
}
