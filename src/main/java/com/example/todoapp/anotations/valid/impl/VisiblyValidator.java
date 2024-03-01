package com.example.todoapp.anotations.valid.impl;

import com.example.todoapp.anotations.valid.ValidVisibly;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;




public class VisiblyValidator implements ConstraintValidator<ValidVisibly, Boolean> {

    @Override
    public void initialize(ValidVisibly constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
        return value != null; // Значение прошло валидацию
    }
}
