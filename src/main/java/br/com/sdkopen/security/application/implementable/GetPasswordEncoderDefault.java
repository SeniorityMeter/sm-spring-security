package br.com.sdkopen.security.application.implementable;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class GetPasswordEncoderDefault implements GetPasswordEncoder {

  @Override
  public PasswordEncoder execute() {
    return new BCryptPasswordEncoder();
  }
}
