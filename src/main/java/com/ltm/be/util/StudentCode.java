package com.ltm.be.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StudentCodeValidator.class)
@Target({ElementType.FIELD,
ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StudentCode {
    String message() default "Student code is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default  {};
}
