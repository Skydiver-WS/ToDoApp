package com.example.todoapp.anotations.valid;

import com.example.todoapp.anotations.valid.impl.VisiblyValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VisiblyValidator.class)
public @interface ValidVisibly {
    String message() default "Invalid boolean value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
