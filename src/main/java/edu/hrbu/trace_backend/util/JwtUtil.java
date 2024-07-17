package edu.hrbu.trace_backend.util;

import edu.hrbu.trace_backend.entity.enums.Secret;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    public static String createJWT(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + Long.parseLong(Secret.JWT_EXPIRE.getValue()));
        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, Secret.JWT.getValue())
                .compact();
    }

    public static String createJWT(String username, long time) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * time);
        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, Secret.JWT.getValue())
                .compact();
    }

    public static Claims parseJWT(String token) {
        return Jwts.parser()
                .setSigningKey(Secret.JWT.getValue())
                .parseClaimsJws(token)
                .getBody();
    }

}
