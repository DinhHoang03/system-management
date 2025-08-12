package com.company.system_management.modules.auth_service.configs;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtConfig {
    @Value("${jwt.secret}")
    String secretKey;

    @Value("${jwt.expiration-time}")
    long expirationTime;
}
