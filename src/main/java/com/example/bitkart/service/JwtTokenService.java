package com.example.bitkart.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import java.util.function.Function;
import java.util.Date;
import java.security.Key;


import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class JwtTokenService {
  @Value("${jwt.secret}")
  private String secretKey;
  @Value("${jwt.expiration}")
  private long expirationTime;

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generateToken(String email) {
    return Jwts.builder()
        .subject(email)
        .signWith(getSignInKey())
        .compact();
  }

  private Claims extractAllClaims(String token) {
    return Jwts.claims()
        .build();
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }
}
