package com.ltm.be.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StudentCodeValidator implements ConstraintValidator<StudentCode, String> {
    @Override
    public void initialize(StudentCode constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null) {
            return false;
        }
        String regex = "^[Bb]\\d{2}[a-zA-Z]{4}\\d{3}$";
        return s.matches(regex);
    }
}
