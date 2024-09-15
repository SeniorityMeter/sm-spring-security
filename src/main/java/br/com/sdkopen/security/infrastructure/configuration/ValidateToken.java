package br.com.sdkopen.security.infrastructure.configuration;

import br.com.sdkopen.security.application.usecase.GetToken;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValidateToken {

  @Value("${sdkopen.security.jwt.secret}")
  private String secret;

  public String getSubject(String token) {
    return JWT.require(Algorithm.HMAC512(secret))
        .withIssuer(GetToken.DEFAULT_ISSUER)
        .build()
        .verify(token)
        .getSubject();
  }
}
