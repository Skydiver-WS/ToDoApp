package com.example.todoapp.config.anotations.valid;

import com.example.todoapp.config.anotations.valid.impl.TagValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TagValidator.class)
public @interface ValidTag {
    String message() default "Invalid tag value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
