package com.example.todoapp.mapper;

import com.example.todoapp.entity.Comment;
import com.example.todoapp.entity.Note;
import com.example.todoapp.entity.Role;
import com.example.todoapp.entity.User;
import com.example.todoapp.web.request.user.CreateUserRequest;
import com.example.todoapp.web.response.comment.CommentResponse;
import com.example.todoapp.web.response.note.NoteResponse;
import com.example.todoapp.web.response.user.UserResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-05T10:35:06+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private NoteMapper noteMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public User userToRequest(CreateUserRequest createUserRequest, List<Role> roles) {
        if ( createUserRequest == null && roles == null ) {
            return null;
        }

        User user = new User();

        if ( createUserRequest != null ) {
            user.setName( createUserRequest.getName() );
            user.setNikName( createUserRequest.getNikName() );
            user.setPassword( createUserRequest.getPassword() );
        }
        List<Role> list = roles;
        if ( list != null ) {
            user.setRoles( new ArrayList<Role>( list ) );
        }

        return user;
    }

    @Override
    public UserResponse userResponseToRequest(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setNotesResponseList( noteListToNoteResponseList( user.getNoteList() ) );
        userResponse.setCommentResponseList( commentListToCommentResponseList( user.getCommentList() ) );
        userResponse.setId( user.getId() );
        userResponse.setNikName( user.getNikName() );
        userResponse.setName( user.getName() );

        return userResponse;
    }

    protected List<NoteResponse> noteListToNoteResponseList(List<Note> list) {
        if ( list == null ) {
            return null;
        }

        List<NoteResponse> list1 = new ArrayList<NoteResponse>( list.size() );
        for ( Note note : list ) {
            list1.add( noteMapper.noteResponseToRequest( note ) );
        }

        return list1;
    }

    protected List<CommentResponse> commentListToCommentResponseList(List<Comment> list) {
        if ( list == null ) {
            return null;
        }

        List<CommentResponse> list1 = new ArrayList<CommentResponse>( list.size() );
        for ( Comment comment : list ) {
            list1.add( commentMapper.commentResponseToComment( comment ) );
        }

        return list1;
    }
}
