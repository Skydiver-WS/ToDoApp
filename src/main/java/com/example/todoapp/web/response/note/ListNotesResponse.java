package com.example.todoapp.web.response.note;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListNotesResponse {
    private List<NoteResponse> noteResponseList = new ArrayList<>();
}
