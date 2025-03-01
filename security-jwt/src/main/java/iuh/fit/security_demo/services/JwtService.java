package iuh.fit.security_demo.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    @Value("${jwt.secret-key}")
    private String key;

    @Value("${jwt.expiration-time}")
    private long jwtExpirationInMs;

    private SecretKey getSignInKey() {
        byte[] keyBytes = Base64.getDecoder().decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userDetails, Map<String, Object> claims) {
        return buildJwtToken(userDetails, claims);
    }

    private String buildJwtToken(UserDetails userDetails, Map<String, Object> claims) {
        return Jwts.builder()
                .claims(claims)
                .issuer("Security Demo")
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpirationInMs))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extraAllClaims(String token) {
        return Jwts.parser().verifyWith(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean isExpiredToken(String token) {
        return extraAllClaims(token).getExpiration().before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return extraAllClaims(token).getSubject().equals(userDetails.getUsername());
    }

}

