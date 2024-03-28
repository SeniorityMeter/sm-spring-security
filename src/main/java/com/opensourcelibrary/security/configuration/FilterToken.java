package com.opensourcelibrary.security.configuration;

import com.opensourcelibrary.security.gateway.SMUserDetails;
import com.opensourcelibrary.security.utility.ValidateToken;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class FilterToken extends OncePerRequestFilter {
  private final ValidateToken validateToken;
  private final SMUserDetails userDetails;

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

      var userDetails = this.userDetails.loadUserDetails(subject);

      var authentication =
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }
}
