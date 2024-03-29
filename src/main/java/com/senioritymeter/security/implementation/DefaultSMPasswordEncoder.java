package com.senioritymeter.security.implementation;

import com.senioritymeter.security.gateway.SMPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultSMPasswordEncoder implements SMPasswordEncoder {

  @Override
  public PasswordEncoder get() {
    return new BCryptPasswordEncoder();
  }
}
