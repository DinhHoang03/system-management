package com.company.system_management.modules.auth_service.services.interfaces;

import com.company.system_management.modules.auth_service.resources.requests.LoginRequest;
import com.company.system_management.modules.auth_service.resources.responses.AuthenticateResponse;

public interface IAuthService {
    AuthenticateResponse authenticate(LoginRequest request);
}
