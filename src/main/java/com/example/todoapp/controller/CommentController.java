package com.example.todoapp.controller;

import com.example.todoapp.mapper.CommentMapper;
import com.example.todoapp.entity.Comment;
import com.example.todoapp.service.simple.CommentService;
import com.example.todoapp.web.request.comment.CommentRequest;
import com.example.todoapp.web.response.comment.CommentResponse;
import com.example.todoapp.web.response.comment.ListCommentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @GetMapping
    public ResponseEntity<ListCommentResponse> findAllComments() {
        return ResponseEntity.ok(commentMapper.commentList(commentService.findAll()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/create/{nikName}")
    public ResponseEntity<CommentResponse> createComment(@PathVariable String nikName,
                                                         @RequestBody @Valid CommentRequest createCommentRequest) {
        Comment comment = commentService.createComment(nikName, createCommentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentMapper.commentResponseToComment(comment));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{nikName}/{id}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable String nikName,
                                                         @PathVariable Long id,
                                                         @RequestBody @Valid CommentRequest updateCommentRequest) {
        Comment comment = commentService.updateComment(id, nikName, updateCommentRequest);
        return ResponseEntity.ok(commentMapper.commentResponseToComment(comment));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{nikName}/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, @PathVariable String nikName) {
        commentService.deleteComment(id, nikName);
        return ResponseEntity.noContent().build();
    }
}
