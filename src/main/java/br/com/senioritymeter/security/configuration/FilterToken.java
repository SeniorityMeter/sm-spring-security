package br.com.senioritymeter.security.configuration;

import br.com.senioritymeter.security.gateway.SMUserDetails;
import br.com.senioritymeter.security.utility.ValidateToken;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import lombok.NonNull;
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
      @NonNull final HttpServletRequest request,
      @NonNull final HttpServletResponse response,
      @NonNull final FilterChain filterChain)
      throws ServletException, IOException {

    var authorizationHeader = request.getHeader("Authorization");

    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      var token = authorizationHeader.replace("Bearer ", "");
      var subject = this.validateToken.getSubject(token);

      var userDetail = this.userDetails.loadUserDetails(subject);

      var authentication =
          new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }
}
