package com.example.todoapp.config.anotations.valid.impl;

import com.example.todoapp.config.enums.Tag;
import com.example.todoapp.config.anotations.valid.ValidTag;
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
