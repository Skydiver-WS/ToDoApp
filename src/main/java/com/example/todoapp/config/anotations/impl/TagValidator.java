package com.example.todoapp.config.anotations.impl;

import com.example.todoapp.config.Tag;
import com.example.todoapp.config.anotations.ValidTag;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TagValidator implements ConstraintValidator<ValidTag, Tag> {
    @Override
    public void initialize(ValidTag constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Tag value, ConstraintValidatorContext context) {
        return value == Tag.ARCHIVE ||
                value == Tag.DEPRECATED ||
                value == Tag.IMPORTANT ||
                value == Tag.REGULAR;
    }
}
