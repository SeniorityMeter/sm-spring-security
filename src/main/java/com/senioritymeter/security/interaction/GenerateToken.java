package com.senioritymeter.security.interaction;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GenerateToken {
  public static final String OSL_SECURITY_ISSUER = "OSL_SECURITY";

  @Value("${spring.security.jwt.secret}")
  private String secret;

  public String execute(final Input input) {
    return JWT.create()
        .withIssuer(OSL_SECURITY_ISSUER)
        .withSubject(input.getSubject())
        .withExpiresAt(input.getExpiresAt())
        .sign(Algorithm.HMAC512(secret));
  }

  @Data
  @Builder
  public static class Input {
    private String subject;
    private Instant expiresAt;
  }
}
