package com.example.todoapp.config.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Tag {
    IMPORTANT,
    ARCHIVE,
    DEPRECATED,
    REGULAR;



    @JsonCreator
    public static Tag forValue(String value){
        return Tag.valueOf(value.toUpperCase());
    }

}
