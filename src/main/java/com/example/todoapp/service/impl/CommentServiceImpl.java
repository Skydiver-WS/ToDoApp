package com.example.todoapp.service.impl;

import com.example.todoapp.entity.Comment;
import com.example.todoapp.entity.Note;
import com.example.todoapp.repository.CommentRepository;
import com.example.todoapp.repository.NoteRepository;
import com.example.todoapp.service.CommentService;
import com.example.todoapp.web.request.comment.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final NoteRepository noteRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment createComment(String nikName, CommentRequest createCommentRequest) {
        Optional<Note> optionalNote = noteRepository.findNoteByTitle(createCommentRequest.getTitleNote());
        if (optionalNote.isPresent()) {
            Comment comment = new Comment();
            comment.setComment(createCommentRequest.getComment());
            comment.setNote(optionalNote.get());
            comment.setUser(optionalNote.get().getUser());
            return commentRepository.save(comment);
        }
        return null;
    }

    @Override
    public Comment updateComment(Long id, String nikName, CommentRequest updateCommentRequest) {
        Optional<Comment> optionalComment = commentRepository.findCommentByIdAndUserNikName(id, nikName);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setComment(updateCommentRequest.getComment());
            return commentRepository.save(comment);
        }
        return null;
    }

    @Override
    public void deleteComment(Long id, String nikName) {
        commentRepository.deleteByIdAndUserNikName(id, nikName);
    }
}
