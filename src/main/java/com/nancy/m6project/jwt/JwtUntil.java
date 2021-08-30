package com.nancy.m6project.jwt;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;

@Service
public class JwtUntil {
    private final String JWT_SECRET = "dangpham";
    // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết


    //Thời gian có hiệu lực của chuỗi jwt
    private final long JWT_EXPIRATION = 4000 * 60 * 60 * 4;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        try{
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }catch (MalformedJwtException malEx){
            Logger.getLogger("Invalid JWT token");
        }catch (ExpiredJwtException exJwt){
            Logger.getLogger("Expired JWT token");
        }catch (UnsupportedJwtException unJwt){
            Logger.getLogger("Unsupported JWT token");
        }
        return false;

    }
}
