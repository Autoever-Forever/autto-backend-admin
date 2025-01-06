package com.example.auttobackendadmin.security.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.auttobackendadmin.security.UserPrincipal;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret}") //TODO: jwt SECRET 추가 (application.yml)
    private String secretKey;

    private final JwtTokenValidator jwtTokenValidator;

    public boolean validateToken(String token) {
        try {
            Claims claims = extractClaims(token);
            jwtTokenValidator.validateClaims(claims);
            return true;
        } catch (Exception e) {
            log.error("Token validation failed", e);
            return false;
        }
    }

    public void setAuthentication(String token) {
        Authentication authentication = getAuthentication(token);
        org.springframework.security.core.context.SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public Authentication getAuthentication(String token) {
        Claims claims = extractClaims(token);
        String email = claims.getSubject();
        String role = claims.get("auth", String.class);
        
        UserPrincipal userPrincipal = new UserPrincipal(
            email, 
            role
        );
        
        return new UsernamePasswordAuthenticationToken(userPrincipal, token, userPrincipal.getAuthorities());
    }

    private Claims extractClaims(String token) {
        // return Jwts.parserBuilder()
        //         .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
        //         .build()
        //         .parseClaimsJws(token)
        //         .getBody();
        try {
            log.debug("Validating token with secret key length: {}", secretKey.length());  // 디버깅용
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("Token validation error: {}", e.getMessage());  // 상세 에러 로깅
            throw e;
        }
    }
}