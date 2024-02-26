package com.example.todoapp.controller;

import com.example.todoapp.model.Comment;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final Commentmapper commentMapper;

    @GetMapping
    public ResponseEntity<List<CommentResponse>> findAllComments(){
        return ResponseEntity.ok(commentMapper.commentListToResponse(commentService.findAll()));
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long userId,
                                                         @RequestBody @Valid CommentRequest createCommentRequest){
        Comment comment = commentService.createComment(userId, createCommentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentMapper.commentResponseToComment(comment));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long userId,
                                                         @RequestBody @Valid CommentRequest updateCommentRequest){
        Comment comment = commentService.createComment(userId, updateCommentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentMapper.commentResponseToComment(comment));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long userId, @RequestBody @Valid DeleteRequest deleteRequest){
        commentService.deleteComment(userId, deleteRequest);
        return ResponseEntity.noContent().build();
    }
}
