package com.moufflet.ecommerce_backend.auth;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

  private static final String SECRECT_KEY = "54GVFG56DSD345";

  public String getToken(UserDetails userDetails) {
    return getToken(new HashMap<>(), userDetails);
  }

  public String getToken(HashMap<String, Object> extraClaims, UserDetails userDetails) {
    return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
        .signWith(getKey(), SignatureAlgorithm.HS256).compact();
  }

  private Key getKey() {
    byte[] key2 = Decoders.BASE64.decode(SECRECT_KEY);
    return Keys.hmacShaKeyFor(key2);
  }

}
