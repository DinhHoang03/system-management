package com.company.system_management.modules.auth_service.services.imps;

import com.company.system_management.modules.auth_service.configs.JwtConfig;
import com.company.system_management.modules.auth_service.models.entities.User;
import com.company.system_management.modules.auth_service.services.interfaces.IJwtService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtService implements IJwtService {
    JwtConfig jwtConfig;
    Key key;

    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtConfig.getSecretKey()));
    }

    /**
     * Tạo JWT token cho user
     * @param user cần tạo token
     * @return JWT token dạng string
     */

    @Override
    public String generateToken(User user) {
        //Header sử dụng thuật toán HS512
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("system-management")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(jwtConfig.getExpirationTime(), ChronoUnit.MILLIS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(key.getEncoded()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cant create token", e);
            throw new RuntimeException(e);
        }
    }

}
