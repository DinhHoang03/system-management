package com.company.system_management.modules.auth_service.controllers;

import com.company.system_management.custom.APIResponse;
import com.company.system_management.modules.auth_service.resources.requests.LoginRequest;
import com.company.system_management.modules.auth_service.resources.responses.AuthenticateResponse;
import com.company.system_management.modules.auth_service.services.interfaces.IAuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    IAuthService authService;

    @PostMapping("/login")
    APIResponse<AuthenticateResponse> login(@RequestBody LoginRequest request) {
        AuthenticateResponse response = authService.authenticate(request);
        return APIResponse.<AuthenticateResponse>builder()
                .result(response)
                .message("Login successfully")
                .build();
    }

}
