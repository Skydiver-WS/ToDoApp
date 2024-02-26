package com.example.todoapp.web.request.note;

import com.example.todoapp.model.CheckListItem;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteRequest {
    @NotNull
    @Min(value = 1)
    @Max(value = 30)
    private String title;
    @NotNull
    private String description;
    private List<CheckListItem> checkListItems;
}
