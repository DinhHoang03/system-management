package com.company.system_management.modules.auth_service.resources.responses;

import lombok.Builder;

@Builder
public record AuthenticateResponse(
        String token,
        boolean isAuthenticated
) {
}
