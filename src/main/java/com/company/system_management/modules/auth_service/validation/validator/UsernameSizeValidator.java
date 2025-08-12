package com.company.system_management.modules.auth_service.validation.validator;

import com.company.system_management.modules.auth_service.validation.constraint.IUsernameSizeConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class UsernameSizeValidator implements ConstraintValidator<IUsernameSizeConstraint, String> {
    private int min;

    @Override
    public void initialize(IUsernameSizeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) return true;

        int length = value.length();

        return length >= min;
    }
}
