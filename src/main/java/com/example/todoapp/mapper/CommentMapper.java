package com.example.todoapp.mapper;

import com.example.todoapp.model.Comment;
import com.example.todoapp.web.response.comment.CommentResponse;
import com.example.todoapp.web.response.comment.ListCommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "nikName.nikName", source = "comment.user.nikName")
    @Mapping(target = "noteResponseToComment.title", source = "comment.note.title")
    CommentResponse commentResponseToComment(Comment comment);

    default ListCommentResponse commentList(List<Comment> commentList) {
        return new ListCommentResponse(commentList.stream().map(this::commentResponseToComment).toList());
    }
}
