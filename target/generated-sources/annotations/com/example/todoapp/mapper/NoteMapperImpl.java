package com.example.todoapp.mapper;

import com.example.todoapp.model.CheckListItem;
import com.example.todoapp.model.Comment;
import com.example.todoapp.model.Note;
import com.example.todoapp.model.User;
import com.example.todoapp.web.request.note.CreateNoteRequest;
import com.example.todoapp.web.response.note.CommentResponseToNote;
import com.example.todoapp.web.response.note.NoteResponse;
import com.example.todoapp.web.response.user.UserResponseToNote;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-29T17:28:43+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class NoteMapperImpl implements NoteMapper {

    @Override
    public Note noteToRequest(CreateNoteRequest createNoteRequest) {
        if ( createNoteRequest == null ) {
            return null;
        }

        Note note = new Note();

        List<CheckListItem> list = createNoteRequest.getCheckListItems();
        if ( list != null ) {
            note.setCheckList( new ArrayList<CheckListItem>( list ) );
        }
        note.setTitle( createNoteRequest.getTitle() );
        note.setDescription( createNoteRequest.getDescription() );
        note.setTag( createNoteRequest.getTag() );
        note.setVisibility( createNoteRequest.getVisibility() );

        return note;
    }

    @Override
    public NoteResponse noteResponseToRequest(Note note) {
        if ( note == null ) {
            return null;
        }

        NoteResponse noteResponse = new NoteResponse();

        noteResponse.setUserResponseToNote( userToUserResponseToNote( note.getUser() ) );
        noteResponse.setCommentResponseToNoteList( commentListToCommentResponseToNoteList( note.getCommentList() ) );
        noteResponse.setId( note.getId() );
        noteResponse.setTitle( note.getTitle() );
        noteResponse.setDescription( note.getDescription() );
        List<CheckListItem> list1 = note.getCheckList();
        if ( list1 != null ) {
            noteResponse.setCheckList( new ArrayList<CheckListItem>( list1 ) );
        }
        noteResponse.setVisibility( note.getVisibility() );
        noteResponse.setTag( note.getTag() );

        return noteResponse;
    }

    protected UserResponseToNote userToUserResponseToNote(User user) {
        if ( user == null ) {
            return null;
        }

        String nikName = null;

        nikName = user.getNikName();

        UserResponseToNote userResponseToNote = new UserResponseToNote( nikName );

        return userResponseToNote;
    }

    protected CommentResponseToNote commentToCommentResponseToNote(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponseToNote commentResponseToNote = new CommentResponseToNote();

        commentResponseToNote.setComment( comment.getComment() );

        return commentResponseToNote;
    }

    protected List<CommentResponseToNote> commentListToCommentResponseToNoteList(List<Comment> list) {
        if ( list == null ) {
            return null;
        }

        List<CommentResponseToNote> list1 = new ArrayList<CommentResponseToNote>( list.size() );
        for ( Comment comment : list ) {
            list1.add( commentToCommentResponseToNote( comment ) );
        }

        return list1;
    }
}
