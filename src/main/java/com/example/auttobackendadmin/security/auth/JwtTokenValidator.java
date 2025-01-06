package com.example.auttobackendadmin.security.auth;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenValidator {

    public void validateClaims(Claims claims) {
        if (claims.getExpiration().before(new Date())) {
            throw new IllegalArgumentException("Token is expired");
        }
        // Additional claim validations can be added here
    }
}
