package com.example.todoapp.repository;

import com.example.todoapp.config.Tag;
import com.example.todoapp.model.Note;
import com.example.todoapp.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findNoteByTagAndVisibility(Tag tag, Boolean visibility);
    Optional<Note> findNoteByUserIdAndTitle(Long id, String title);
    Optional<Note> findNoteByUserNikNameAndTitle(String nikName, String title);
    Optional<Note> findNoteByUserNikName(String nikName);

    @Transactional
    void deleteByTitleAndUserNikName(String title, String nikName);
}
