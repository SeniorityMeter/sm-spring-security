package br.com.senioritymeter.security.utility;

import br.com.senioritymeter.security.interaction.GenerateToken;
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
        .withIssuer(GenerateToken.OSL_SECURITY_ISSUER)
        .build()
        .verify(token)
        .getSubject();
  }
}
