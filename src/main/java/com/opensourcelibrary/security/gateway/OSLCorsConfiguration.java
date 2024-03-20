package com.opensourcelibrary.security.gateway;

import java.util.List;

public interface OSLCorsConfiguration {
  List<String> allowOrigins();

  List<String> allowMethods();

  List<String> allowHeaders();
}
