package com.company.system_management.modules.auth_service.controllers;

import com.company.system_management.custom.APIResponse;
import com.company.system_management.modules.auth_service.resources.requests.UserRequest;
import com.company.system_management.modules.auth_service.resources.responses.UserResponse;
import com.company.system_management.modules.auth_service.services.interfaces.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    IUserService userService;

    @PostMapping
    APIResponse<UserResponse> createUser(@RequestBody UserRequest request) {
        UserResponse response = userService.createUser(request);
        return APIResponse.<UserResponse>builder()
                .result(response)
                .message("Create user success")
                .build();
    }

    @PutMapping("/{userId}")
    APIResponse<UserResponse> updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody UserRequest request
    ) {
        UserResponse response = userService.updateUser(userId, request);
        return APIResponse.<UserResponse>builder()
                .result(response)
                .message("Update user " + userId + " successfully")
                .build();
    }

    @GetMapping("/{userId}")
    APIResponse<UserResponse> getUserById(@PathVariable("userId") Long userId) {
        UserResponse response = userService.getUserById(userId);
        return APIResponse.<UserResponse>builder()
                .result(response)
                .message("Get user by id successfully")
                .build();
    }

    @GetMapping
    APIResponse<Page<UserResponse>> getAllUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size
    ) {
        Page<UserResponse> responses = userService.getAllUser(page, size);
        return APIResponse.<Page<UserResponse>>builder()
                .result(responses)
                .message("Get all user successfully")
                .build();
    }

    @DeleteMapping("/{userId}")
    APIResponse<?> deleteUserById(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);

        return APIResponse.builder()
                .message("Delete user " + userId + " successfully")
                .build();
    }
}
