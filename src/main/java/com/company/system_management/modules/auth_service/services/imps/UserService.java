package com.company.system_management.modules.auth_service.services.imps;

import com.company.system_management.exception.code.EApplicationCode;
import com.company.system_management.exception.custom.AppException;
import com.company.system_management.modules.auth_service.resources.requests.UserRequest;
import com.company.system_management.modules.auth_service.resources.responses.UserResponse;
import com.company.system_management.modules.auth_service.models.entities.User;
import com.company.system_management.modules.auth_service.models.repositories.IUserRepository;
import com.company.system_management.modules.auth_service.services.interfaces.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {
    IUserRepository userRepository;
    PasswordEncoder passwordEncoder;


    @Override
    public UserResponse createUser(UserRequest request) {
        if (request == null) throw new AppException(EApplicationCode.REQUEST_NULL);

        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new AppException(EApplicationCode.ACCOUNT_EXIST);
        }

        User user = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .email(request.email())
                .phone(request.phone())
                .address(request.address())
                .dob(request.dob())
                .build();

        User result = userRepository.save(user);

        return UserResponse.builder()
                .userId(result.getUserId())
                .username(result.getUsername())
                .email(result.getEmail())
                .phone(result.getPhone())
                .address(request.address())
                .dob(result.getDob())
                .build();
    }

    @Override
    public UserResponse updateUser(Long userId, UserRequest request) {
        if (request == null) throw new AppException(EApplicationCode.REQUEST_NULL);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(EApplicationCode.ACCOUNT_NOT_EXIST));

        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setEmail(request.email());
        user.setPhone(request.phone());
        user.setAddress(request.address());
        user.setDob(request.dob());

        User result = userRepository.save(user);

        return UserResponse.builder()
                .userId(result.getUserId())
                .username(result.getUsername())
                .email(result.getEmail())
                .phone(result.getPhone())
                .address(result.getAddress())
                .dob(result.getDob())
                .build();
    }

    @Override
    public UserResponse getUserById(Long userId) {
        if (userId == null) throw new AppException(EApplicationCode.REQUEST_NULL);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(EApplicationCode.ACCOUNT_NOT_EXIST));

        return UserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .dob(user.getDob())
                .build();
    }

    @Override
    public Page<UserResponse> getAllUser(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPages = userRepository.findAll(pageable);

        if (userPages.isEmpty()) throw new AppException(EApplicationCode.LIST_EMPTY);

        Page<UserResponse> response = userPages.map(user -> {
            return UserResponse.builder()
                    .userId(user.getUserId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .address(user.getAddress())
                    .dob(user.getDob())
                    .build();
        });

        return response;
    }

    @Override
    public void deleteUser(Long userId) {
        if (userId == null) throw new AppException(EApplicationCode.REQUEST_NULL);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(EApplicationCode.ACCOUNT_NOT_EXIST));

        userRepository.delete(user);
    }
}
