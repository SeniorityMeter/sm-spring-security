package br.com.sdkopen.security.application.implementable;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class AllAuthorizeRequestDefault implements AllAuthorizeRequest {

  @Override
  public void authorize(
      AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry
          request) {
    request.anyRequest().authenticated();
  }
}
