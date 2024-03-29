package com.senioritymeter.security.gateway;

import java.util.List;

public interface SMCorsConfiguration {
  List<String> allowOrigins();

  List<String> allowMethods();

  List<String> allowHeaders();
}
