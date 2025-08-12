package com.company.system_management.modules.auth_service.resources.responses;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserResponse(
        Long userId,
        String username,
        String email,
        String phone,
        String address,
        LocalDate dob
) {
}
