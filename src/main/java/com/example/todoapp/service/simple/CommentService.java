package com.example.todoapp.service.simple;

import com.example.todoapp.entity.Comment;
import com.example.todoapp.web.request.comment.CommentRequest;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();

    Comment createComment(String nikName, CommentRequest createCommentRequest);

    Comment updateComment(Long id, String nikName, CommentRequest updateCommentRequest);

    void deleteComment(Long id, String nikName);
}
