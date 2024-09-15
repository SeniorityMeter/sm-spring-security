package br.com.sdkopen.security.application.implementable;

import java.util.List;

public interface AllCorsConfiguration {
  List<String> allowOrigins();

  List<String> allowMethods();

  List<String> allowHeaders();
}
