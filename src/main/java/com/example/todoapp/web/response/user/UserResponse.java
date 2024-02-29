package com.example.todoapp.web.response.user;

import com.example.todoapp.web.response.comment.CommentResponse;
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
    private List<NoteResponse> notesResponseList;
    private List<CommentResponse> commentResponseList;
}
