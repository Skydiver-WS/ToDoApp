package com.example.todoapp.web.response.user;

import com.example.todoapp.web.response.note.NoteResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private String nikName;
    private List<NoteResponse> notesResponseList;
    private List<CommentResponse> commentResponseList;
}
