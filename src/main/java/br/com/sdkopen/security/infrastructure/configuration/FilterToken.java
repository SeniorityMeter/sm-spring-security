package br.com.sdkopen.security.infrastructure.configuration;

import br.com.sdkopen.security.application.implementable.GetUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
  private final GetUserDetails getUserDetails;

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
      var userDetail = this.getUserDetails.execute(subject);

      var authentication =
          new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }
}
