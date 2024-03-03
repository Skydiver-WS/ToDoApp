package com.example.todoapp.mapper;

import com.example.todoapp.entity.Note;
import com.example.todoapp.web.request.note.CreateNoteRequest;
import com.example.todoapp.web.response.note.ListNotesResponse;
import com.example.todoapp.web.response.note.NoteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    @Mapping(target = "checkList", source = "createNoteRequest.checkListItems")
    Note noteToRequest(CreateNoteRequest createNoteRequest);
    @Mapping(target = "userResponseToNote.nikName", source = "note.user.nikName")
    @Mapping(target = "commentResponseToNoteList", source = "note.commentList")
    NoteResponse noteResponseToRequest(Note note);

    default ListNotesResponse listNotes(List<Note> noteList){
        return new ListNotesResponse(noteList.stream().map(this::noteResponseToRequest).toList());
    }


}
