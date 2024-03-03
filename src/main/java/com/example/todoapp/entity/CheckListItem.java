package com.example.todoapp.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class CheckListItem {
    private String item;

}
