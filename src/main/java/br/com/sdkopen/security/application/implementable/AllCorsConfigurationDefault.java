package br.com.sdkopen.security.application.implementable;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AllCorsConfigurationDefault implements AllCorsConfiguration {

  @Override
  public List<String> allowOrigins() {
    return List.of("*");
  }

  @Override
  public List<String> allowMethods() {
    return List.of("GET", "POST", "PUT", "DELETE", "OPTIONS");
  }

  @Override
  public List<String> allowHeaders() {
    return List.of("Authorization", "Content-Type", "Access-Control-Allow-Origin");
  }
}
