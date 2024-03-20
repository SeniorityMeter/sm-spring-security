package com.opensourcelibrary.security.interaction;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class GenerateToken {
  @Value("${spring.security.jwt.secret}")
  private String secret;

  public String execute(final Input input) {
    return JWT.create()
        .withIssuer(input.getIssuer())
        .withSubject(input.getSubject())
        .withExpiresAt(input.getExpiresAt())
        .sign(Algorithm.HMAC512(secret));
  }

  @Data
  @Builder
  public static class Input {
    private String issuer;
    private String subject;
    private Instant expiresAt;
  }
}
