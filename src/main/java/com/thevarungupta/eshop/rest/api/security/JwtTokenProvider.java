package com.thevarungupta.eshop.rest.api.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private String jwtSecret = "dGhpcyBpcyBteSBqd3QgdG9rZW4ga2V5IGZvciBzZWN1cmluZyBteSBhcHBsaWNhdGlvbiA=";
    private Long jwtExpirationInMs = 604800000L;

    // Generate token
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + jwtExpirationInMs);
        String token = Jwts.builder()
                .subject(username)
                .issuedAt(currentDate)
                .expiration(expiryDate)
                .signWith(key())
                .compact();
        return token;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // get username from token
    public String getUsernameFromToken(String token){
        return Jwts
                .parser()
                .verifyWith((SecretKey)key() )
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }

    // validate token
    /**
     * token expire
     * invalid token
     * unsupported token
     * jwt claims is null or empty
     * **/
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new MalformedJwtException("Invalid token");
        } catch (ExpiredJwtException ex) {
            throw new ExpiredJwtException(null, null, "Token expired");
        } catch (UnsupportedJwtException ex) {
            throw new UnsupportedJwtException("Unsupported token");
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("JWT claims string is empty");
        } catch (Exception ex) {
            throw new RuntimeException("Invalid token");
        }
    }
}
