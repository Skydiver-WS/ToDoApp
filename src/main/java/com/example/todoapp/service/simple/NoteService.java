package com.example.todoapp.service.simple;

import com.example.todoapp.config.enums.Tag;
import com.example.todoapp.entity.Note;
import com.example.todoapp.entity.User;
import com.example.todoapp.web.request.note.UpdateNoteRequest;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    List<Note> findAll();

    List<Note> findNoteByTag(Tag tag);

    Note createNote(String nikName, Note note);

    Note updateNote(String nikName, String title, UpdateNoteRequest updateNote);

   User findUserByTitle(String title);

    void deleteNoteById(String nikName, String title);
}
