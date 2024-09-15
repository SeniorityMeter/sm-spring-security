package br.com.sdkopen.security.application.usecase;

import br.com.sdkopen.security.domain.dto.JwtToken;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "spring.security.enabled", havingValue = "true")
public class GetToken {
  public static final String DEFAULT_ISSUER = "SDK_OPEN_SECURITY";

  @Value("${sdkopen.security.jwt.secret}")
  private String secret;

  public JwtToken execute(final Input input) {
    final var token =
        JWT.create()
            .withIssuer(DEFAULT_ISSUER)
            .withSubject(input.getSubject())
            .withExpiresAt(input.getExpiresAt())
            .sign(Algorithm.HMAC512(secret));

    return JwtToken.builder().accessToken(token).expiresAt(input.getExpiresAt()).build();
  }

  @Getter
  @Builder
  public static class Input {
    private String subject;
    private Instant expiresAt;
  }
}
