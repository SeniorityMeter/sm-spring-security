package com.opensourcelibrary.security.interaction;

import com.opensourcelibrary.security.gateway.OSLCorsConfiguration;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DefaultOSLCorsConfiguration implements OSLCorsConfiguration {

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
