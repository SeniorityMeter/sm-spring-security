package br.com.senioritymeter.security.interaction;

import br.com.senioritymeter.security.valueobject.Token;
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

  public Token execute(final Input input) {
    final var token =
        JWT.create()
            .withIssuer(OSL_SECURITY_ISSUER)
            .withSubject(input.getSubject())
            .withExpiresAt(input.getExpiresAt())
            .sign(Algorithm.HMAC512(secret));

    return Token.builder().accessToken(token).expiresAt(input.getExpiresAt()).build();
  }

  @Data
  @Builder
  public static class Input {
    private String subject;
    private Instant expiresAt;
  }
}
