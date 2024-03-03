package com.example.todoapp.web.request.note;

import com.example.todoapp.config.anotations.valid.ValidVisibly;
import com.example.todoapp.config.enums.Tag;
import com.example.todoapp.config.anotations.valid.ValidTag;
import com.example.todoapp.entity.CheckListItem;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNoteRequest {
    @NotNull
    @Size(min = 1, max = 30)
    private String title;
    @NotNull
    private String description;
    @NotNull
    @ValidVisibly
    private Boolean visibility;
    @ValidTag
    private Tag tag;
    private List<CheckListItem> checkListItems;
}
