package br.com.sdkopen.security.application.usecase;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticateUser {
  private final AuthenticationManager authenticationManager;

  public void authenticate(@NonNull String username, @NonNull String password) {
    var auth = new UsernamePasswordAuthenticationToken(username, password);

    this.authenticationManager.authenticate(auth);
  }
}
