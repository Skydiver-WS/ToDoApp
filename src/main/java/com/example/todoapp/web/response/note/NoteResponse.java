package com.example.todoapp.web.response.note;

import com.example.todoapp.web.response.user.UserResponseToNote;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteResponse {
    private UserResponseToNote userResponseToNote;
    private String title;
    private String description;
    private List<CommentResponseToNote> commentResponseToNoteList;
}
