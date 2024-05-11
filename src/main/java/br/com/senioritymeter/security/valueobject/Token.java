package br.com.senioritymeter.security.valueobject;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Token {
  private String accessToken;
  private Instant expiresAt;
}
