package br.com.senioritymeter.security.implementation;

import br.com.senioritymeter.security.gateway.SMAuthorizeRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class DefaultSMAuthorizeRequest implements SMAuthorizeRequest {
  @Override
  public void authorize(
      AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry
          request) {
    request.anyRequest().authenticated();
  }
}
