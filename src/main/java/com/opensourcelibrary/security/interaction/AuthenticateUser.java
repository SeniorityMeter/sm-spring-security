package com.opensourcelibrary.security.interaction;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticateUser {
  private final AuthenticationManager authenticationManager;

  public void authenticate(String email, String password) {
    var auth = new UsernamePasswordAuthenticationToken(email, password);

    this.authenticationManager.authenticate(auth);
  }
}
