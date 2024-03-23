package com.opensourcelibrary.security.configuration;

import com.opensourcelibrary.security.gateway.OSLAuthorizeRequest;
import com.opensourcelibrary.security.gateway.OSLCorsConfiguration;
import com.opensourcelibrary.security.gateway.OSLPasswordEncoder;
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

  private final FilterToken filterToken;
  private final OSLCorsConfiguration oslCorsConfiguration;
  private final OSLAuthorizeRequest oslAuthorizeRequest;
  private final OSLPasswordEncoder oslPasswordEncoder;

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration auth)
      throws Exception {
    return auth.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return oslPasswordEncoder.get();
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
      oslAuthorizeRequest.authorize(req);
      req.anyRequest().authenticated();
    };
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowedOrigins(oslCorsConfiguration.allowOrigins());
    corsConfiguration.setAllowedMethods(oslCorsConfiguration.allowMethods());
    corsConfiguration.setAllowedHeaders(oslCorsConfiguration.allowHeaders());

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfiguration);
    return source;
  }
}
