package br.com.senioritymeter.security.configuration;

import br.com.senioritymeter.security.gateway.SMAuthorizeRequest;
import br.com.senioritymeter.security.gateway.SMCorsConfiguration;
import br.com.senioritymeter.security.gateway.SMPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity {
  private static final String[] PERMITS_REQUEST_MATCHERS = {
    "/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs.yaml", "/actuator/**",
  };

  private final FilterToken filterToken;
  private final SMCorsConfiguration corsConfiguration;
  private final SMAuthorizeRequest authorizeRequest;
  private final SMPasswordEncoder passwordEncoder;

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration auth)
      throws Exception {
    return auth.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return passwordEncoder.get();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable)
        .cors(c -> c.configurationSource(corsConfigurationSource()))
        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorizationManagerHttpRequestsCustomizer())
        .addFilterBefore(filterToken, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  private Customizer<
          AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>
      authorizationManagerHttpRequestsCustomizer() {
    return req -> {
      req.requestMatchers(PERMITS_REQUEST_MATCHERS).permitAll();
      authorizeRequest.authorize(req);
    };
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowedOrigins(this.corsConfiguration.allowOrigins());
    corsConfiguration.setAllowedMethods(this.corsConfiguration.allowMethods());
    corsConfiguration.setAllowedHeaders(this.corsConfiguration.allowHeaders());

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfiguration);
    return source;
  }
}
