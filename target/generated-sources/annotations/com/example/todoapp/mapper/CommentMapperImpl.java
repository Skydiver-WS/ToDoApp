package com.example.todoapp.mapper;

import com.example.todoapp.entity.Comment;
import com.example.todoapp.entity.Note;
import com.example.todoapp.entity.User;
import com.example.todoapp.web.response.comment.CommentResponse;
import com.example.todoapp.web.response.note.NoteResponseToComment;
import com.example.todoapp.web.response.user.UserResponseToNote;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-05T10:35:06+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentResponse commentResponseToComment(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponse commentResponse = new CommentResponse();

        commentResponse.setNikName( userToUserResponseToNote( comment.getUser() ) );
        commentResponse.setNoteResponseToComment( noteToNoteResponseToComment( comment.getNote() ) );
        commentResponse.setId( comment.getId() );
        commentResponse.setComment( comment.getComment() );

        return commentResponse;
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

    protected NoteResponseToComment noteToNoteResponseToComment(Note note) {
        if ( note == null ) {
            return null;
        }

        NoteResponseToComment noteResponseToComment = new NoteResponseToComment();

        noteResponseToComment.setTitle( note.getTitle() );

        return noteResponseToComment;
    }
}
