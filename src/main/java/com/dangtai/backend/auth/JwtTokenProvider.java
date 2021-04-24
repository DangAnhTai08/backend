package com.dangtai.backend.auth;

import com.dangtai.backend.common.CommonResource;
import com.dangtai.backend.dto.UserPrincipal;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private final CommonResource commonResource;

    // Tạo ra token từ chuỗi authentication
    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + commonResource.getJwtExpiration());

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, commonResource.getJwtSecret())
                .compact();
    }

    // Lấy id_user từ token đã được mã hóa
    public int getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(commonResource.getJwtSecret())
                .parseClaimsJws(token)
                .getBody();

        return (int) Long.parseLong(claims.getSubject());
    }

    // check token
    public boolean validateToken(String authToken) throws SignatureException {
        try {
            Jwts.parser().setSigningKey(commonResource.getJwtSecret()).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
