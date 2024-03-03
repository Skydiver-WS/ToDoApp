package com.example.todoapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String comment;

    @ManyToOne
    private User user;

    @ManyToOne
    private Note note;

//    @OneToMany
//    private List<Comment> subComment;
}
