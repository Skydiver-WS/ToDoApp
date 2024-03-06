package com.example.todoapp.service.simple;

import com.example.todoapp.entity.Comment;
import com.example.todoapp.entity.User;
import com.example.todoapp.web.request.comment.CommentRequest;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();

    Comment createComment(String nikName, CommentRequest commentRequest);

    Comment updateComment(Long id, String nikName, CommentRequest updateCommentRequest);
    User findUserByCommentId(Long id);

    void deleteComment(Long id, String nikName);
}
