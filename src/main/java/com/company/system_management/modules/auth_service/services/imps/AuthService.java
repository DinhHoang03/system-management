package com.company.system_management.modules.auth_service.services.imps;

import com.company.system_management.exception.code.EApplicationCode;
import com.company.system_management.exception.custom.AppException;
import com.company.system_management.modules.auth_service.models.entities.User;
import com.company.system_management.modules.auth_service.models.repositories.IUserRepository;
import com.company.system_management.modules.auth_service.resources.requests.LoginRequest;
import com.company.system_management.modules.auth_service.resources.responses.AuthenticateResponse;
import com.company.system_management.modules.auth_service.services.interfaces.IAuthService;
import com.company.system_management.modules.auth_service.services.interfaces.IJwtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService implements IAuthService {
    IUserRepository userRepository;
    PasswordEncoder passwordEncoder;
    IJwtService jwtService;

    @Override
    public AuthenticateResponse authenticate(LoginRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new AppException(EApplicationCode.ACCOUNT_NOT_EXIST));

        boolean authenticated = passwordEncoder.matches(request.password(), user.getPassword());

        if (!authenticated) throw new AppException(EApplicationCode.UNAUTHENTICATED);

        String token = jwtService.generateToken(user);

        return AuthenticateResponse.builder()
                .token(token)
                .isAuthenticated(true)
                .build();
    }
}
