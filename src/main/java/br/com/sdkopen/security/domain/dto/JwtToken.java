package br.com.sdkopen.security.domain.dto;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtToken {
  private String accessToken;
  private Instant expiresAt;
}
