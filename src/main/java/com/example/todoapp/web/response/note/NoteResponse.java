package com.example.todoapp.web.response.note;

import com.example.todoapp.config.enums.Tag;
import com.example.todoapp.entity.CheckListItem;
import com.example.todoapp.web.response.user.UserResponseToNote;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteResponse {
    private Long id;
    private String title;
    private String description;
    private List<CheckListItem> checkList;
    private List<CommentResponseToNote> commentResponseToNoteList;
    private Boolean visibility;
    private Tag tag;
    private UserResponseToNote userResponseToNote;
}
