package com.opensourcelibrary.security.configuration;


import com.opensourcelibrary.security.gateway.OSLUserDetails;
import com.opensourcelibrary.security.utility.ValidateToken;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FilterToken extends OncePerRequestFilter {
  private final ValidateToken validateToken;
  private final OSLUserDetails oslUserDetails;

  @Override
  protected void doFilterInternal(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final FilterChain filterChain)
      throws ServletException, IOException {

    var authorizationHeader = request.getHeader("Authorization");

    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      var token = authorizationHeader.replace("Bearer ", "");
      var subject = this.validateToken.getSubject(token);

      var userDetails = oslUserDetails.loadUserDetails(subject);

      var authentication =
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }
}
