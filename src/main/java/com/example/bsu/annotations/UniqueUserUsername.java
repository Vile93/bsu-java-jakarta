package com.example.bsu.annotations;

import com.example.bsu.validators.UniqueUserUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueUserUsernameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUserUsername {
    String message() default "Value must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
