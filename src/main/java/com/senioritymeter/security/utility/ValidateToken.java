package com.senioritymeter.security.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValidateToken {

  @Value("${spring.security.jwt.secret}")
  private String secret;

  public String getSubject(String token) {
    return JWT.require(Algorithm.HMAC512(secret))
        .withIssuer("Users")
        .build()
        .verify(token)
        .getSubject();
  }
}
