package com.example.todoapp.service;

import com.example.todoapp.config.Tag;
import com.example.todoapp.model.Note;
import com.example.todoapp.web.request.note.CreateNoteRequest;
import com.example.todoapp.web.request.note.UpdateNoteRequest;

import java.util.List;

public interface NoteService {
    List<Note> findAll();

    List<Note> findNoteByTag(Tag tag);

    Note createNote(String nikName, Note note);

    Note updateNote(String nikName, String title, UpdateNoteRequest updateNote);

    void deleteNoteById(String nikName, String title);
}
