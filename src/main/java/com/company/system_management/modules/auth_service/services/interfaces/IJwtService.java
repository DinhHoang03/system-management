package com.company.system_management.modules.auth_service.services.interfaces;

import com.company.system_management.modules.auth_service.models.entities.User;

public interface IJwtService {
    String generateToken(User user);
}
