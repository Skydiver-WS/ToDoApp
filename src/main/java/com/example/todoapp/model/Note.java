package com.example.todoapp.model;

import com.example.todoapp.config.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"title"}))
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String description;
    private Tag tag;
    private Boolean visibility;

    @ManyToOne
    @JsonIgnore
    private User user;
    @OneToMany
    @JsonIgnore
    private List<Comment> commentList;
    @ElementCollection
    private List<CheckListItem> checkList;

}
