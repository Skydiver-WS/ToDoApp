package com.example.todoapp.controller;

import com.example.todoapp.config.Tag;
import com.example.todoapp.mapper.NoteMapper;
import com.example.todoapp.model.CheckListItem;
import com.example.todoapp.model.Note;
import com.example.todoapp.service.NoteService;
import com.example.todoapp.web.request.note.CreateNoteRequest;
import com.example.todoapp.web.request.note.UpdateNoteRequest;
import com.example.todoapp.web.response.note.ListNotesResponse;
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
        return ResponseEntity.ok(noteMapper.listNotes(noteService.findAll()));
    }

    @GetMapping("/findByTag/{tag}")
    public ResponseEntity<ListNotesResponse> findByTag(@PathVariable Tag tag){
        return ResponseEntity.ok(noteMapper.listNotes(noteService.findNoteByTag(tag)));
    }

    @PostMapping("/create/{nikName}")
    public ResponseEntity<NoteResponse> createNote(@PathVariable String nikName,
                                                   @RequestBody @Valid CreateNoteRequest createNote){
        Note note = noteService.createNote(nikName, noteMapper.noteToRequest(createNote));
        return ResponseEntity.status(HttpStatus.CREATED).body(noteMapper.noteResponseToRequest(note));
    }
    @PutMapping("/update/{nikName}/{title}") // скорее всего преобразовать через маппер и сделать проверку по наличию
    public ResponseEntity<NoteResponse> updateNote(@PathVariable String nikName,
                                                   @PathVariable String title,
                                                   @RequestBody @Valid UpdateNoteRequest updateNote){
        Note note = noteService.updateNote(nikName, title, updateNote);
        return ResponseEntity.status(HttpStatus.CREATED).body(noteMapper.noteResponseToRequest(note));
    }

    @DeleteMapping("/delete/{nikName}/{title}")
    public ResponseEntity<Void> deleteNote(@PathVariable String nikName,
                                           @PathVariable String title){
        noteService.deleteNoteById(nikName, title);
        return ResponseEntity.noContent().build();
    }
}