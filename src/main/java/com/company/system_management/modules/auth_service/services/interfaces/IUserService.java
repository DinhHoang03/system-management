package com.company.system_management.modules.auth_service.services.interfaces;

import com.company.system_management.modules.auth_service.resources.requests.UserRequest;
import com.company.system_management.modules.auth_service.resources.responses.UserResponse;
import org.springframework.data.domain.Page;

public interface IUserService {
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(Long userId, UserRequest request);
    UserResponse getUserById(Long userId);
    Page<UserResponse> getAllUser(int page, int size);
    void deleteUser(Long userId);
}
