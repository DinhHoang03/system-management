package com.company.system_management.modules.auth_service.resources.requests;

import com.company.system_management.modules.auth_service.validation.constraint.IDobConstraint;
import com.company.system_management.modules.auth_service.validation.constraint.IPhoneConstraint;
import com.company.system_management.modules.auth_service.validation.constraint.IUsernameSizeConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record UserRequest(
        @NotBlank(message = "NOT_BLANK")
        @IUsernameSizeConstraint(min = 8, message = "INVALID_USERNAME")
        String username,

        @NotBlank(message = "NOT_BLANK")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,16}$",
                message = "INVALID_PASSWORD")
        String password,

        @Email(message = "INVALID_EMAIL")
        @NotBlank(message = "NOT_BLANK")
        String email,

        @NotBlank(message = "NOT_BLANK")
        @IPhoneConstraint(length = 11, message = "INVALID_PHONE")
        String phone,

        @NotBlank(message = "NOT_BLANK")
        String address,

        @NotNull(message = "NOT_NULL")
        @IDobConstraint(min = 18, message = "INVALID_DOB")
        LocalDate dob
) {
}
