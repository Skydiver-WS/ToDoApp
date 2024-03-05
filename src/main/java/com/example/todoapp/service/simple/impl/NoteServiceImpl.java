package com.example.todoapp.service.simple.impl;

import com.example.todoapp.config.enums.Tag;
import com.example.todoapp.entity.CheckListItem;
import com.example.todoapp.entity.Note;
import com.example.todoapp.entity.User;
import com.example.todoapp.repository.NoteRepository;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.service.simple.NoteService;
import com.example.todoapp.web.request.note.UpdateNoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public List<Note> findNoteByTag(Tag tag) {
        return noteRepository.findNoteByTagAndVisibility(tag, true);
    }

    @Override
    public Note createNote(String nikName, Note note) {
        Optional<User> optionalUser = userRepository.findUserByNikName(nikName);
        if (optionalUser.isPresent()) {
            note.setUser(optionalUser.get());
            return noteRepository.save(note);
        }
        return null;
    }

    @Override
    public Note updateNote(String nikName, String title, UpdateNoteRequest updateNote) {
        Optional<Note> optionalNote = noteRepository.findNoteByUserNikNameAndTitle(nikName, title);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            if(updateNote.getTitle() != null){
                note.setTitle(updateNote.getTitle());
            }
            if(updateNote.getDescription() != null){
                note.setDescription(updateNote.getDescription());
            }
            if(updateNote.getCheckListItems() != null){
                List<CheckListItem> listItems = note.getCheckList();
                listItems.addAll(updateNote.getCheckListItems());
                note.setCheckList(listItems);
            }
            if (updateNote.getVisibility() != null){
                note.setVisibility(updateNote.getVisibility());
            }
            if (updateNote.getTag() != null){
                note.setTag(updateNote.getTag());
            }
            return noteRepository.save(note);
        }
        return null;
    }

    @Override
    public void deleteNoteById(String nikName, String title) {
        noteRepository.deleteByTitleAndUserNikName(title, nikName);
    }
}
