package com.opensourcelibrary.security.interaction;

import com.opensourcelibrary.security.gateway.OSLAuthorizeRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class DefaultOSLAuthorizeRequest implements OSLAuthorizeRequest {
  @Override
  public void authorize(
      AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry
          request) {
    request.anyRequest().authenticated();
  }
}
