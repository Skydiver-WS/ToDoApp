package com.example.todoapp.controller;

import com.example.todoapp.model.Note;
import com.example.todoapp.web.request.note.NoteRequest;
import com.example.todoapp.web.response.note.NoteResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;
    private final NoteMapper noteMapper;

    @GetMapping
    public ResponseEntity<ListNotesResponse> findAll(){
        return ResponseEntity.ok(noteMapper.findAllNotes(noteService.findAll()));
    }

    @GetMapping("/{tag}")
    public ResponseEntity<ListNotesResponse> findByTag(@PathVariable String tag){
        return ResponseEntity.ok(noteMapper.findAllNotes(noteService.findNoteByTag(tag)));
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<NoteResponse> createUser(@PathVariable Long userId, @RequestBody @Valid NoteRequest createNote){
        Note note = noteService.createNote(userId, createNote);
        return ResponseEntity.status(HttpStatus.CREATED).body(noteMapper.createdNote(note));
    }
    @PutMapping("/update/{userId}/{id}") // скорее всего преобразовать через маппер и сделать проверку по наличию
    public ResponseEntity<NoteResponse> updateUser(@PathVariable Long userId, @PathVariable Long id,
                                                   @RequestBody @Valid UpdateNote updateNote){
        Note note = noteService.updateNote(id, userId, updateUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(noteMapper.updateNote(note));
    }

    @DeleteMapping("/delete/{userid}/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userid, @PathVariable Long id){
        noteService.deleteNoteById(id);
        return ResponseEntity.noContent().build();
    }
}
