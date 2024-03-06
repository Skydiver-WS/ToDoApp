package com.example.todoapp.repository;

import com.example.todoapp.entity.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findCommentByIdAndUserNikName(Long id, String nikName);
    Optional<Comment> findCommentByNoteTitle(String note);
    @Transactional
    void deleteByIdAndUserNikName(Long id, String nikName);
}
