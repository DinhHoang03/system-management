package com.company.system_management.modules.auth_service.resources.requests;

import com.company.system_management.modules.auth_service.validation.constraint.IUsernameSizeConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginRequest(
        @NotBlank(message = "NOT_BLANK")
        @IUsernameSizeConstraint(min = 8, message = "INVALID_USERNAME")
        String username,

        @NotBlank(message = "NOT_BLANK")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,16}$",
                message = "INVALID_PASSWORD")
        String password
) {
}
