package com.example.bsu.annotations;

import com.example.bsu.validators.UniqueUserEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueUserEmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUserEmail {
    String message() default "Value must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
