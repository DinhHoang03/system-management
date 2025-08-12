package com.company.system_management.modules.auth_service.validation.constraint;

import com.company.system_management.modules.auth_service.validation.validator.UsernameSizeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {UsernameSizeValidator.class})
public @interface IUsernameSizeConstraint {
    String message() default "Invalid username size";

    int min();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
