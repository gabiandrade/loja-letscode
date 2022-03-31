package br.com.letscode.lojaletscode.config.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtils {

    public static final String TOKEN_SECRET_KEY = "ECommerceAppSecretKey";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String TOKEN_CLAIM_USERNAME = "username";
    public static final String TOKEN_CLAIM_ROLES = "roles";
    public static final long TOKEN_EXPIRATION = 3_600_000; // 1 hour

    public static String generateToken(final Long userId, final String username, final String userRole) {
        final Date now = new Date();
        final Date exp = new Date(now.getTime() + TOKEN_EXPIRATION);

        String jwtToken =
                Jwts.builder()
                        .setSubject(userId.toString())
                        .claim(TOKEN_CLAIM_USERNAME, username)
                        .claim(TOKEN_CLAIM_ROLES, userRole)
                        .setIssuedAt(now)
                        .setNotBefore(now)
                        .setExpiration(exp)
                        .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET_KEY)
                        .compact();

        return TOKEN_PREFIX + " " + jwtToken;
    }

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(TOKEN_SECRET_KEY)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();
    }
}
