package com.example.todoapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user_table", uniqueConstraints = @UniqueConstraint(columnNames = "nik_name"))
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String nikName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Note> noteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comment> commentList;
}
