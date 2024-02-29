package com.example.todoapp.web.response.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCommentResponse {
    private List<CommentResponse> commentResponses = new ArrayList<>();
}
