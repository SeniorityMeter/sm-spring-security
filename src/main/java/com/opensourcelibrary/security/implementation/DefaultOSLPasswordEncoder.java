package com.opensourcelibrary.security.implementation;

import com.opensourcelibrary.security.gateway.OSLPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultOSLPasswordEncoder implements OSLPasswordEncoder {

  @Override
  public PasswordEncoder get() {
    return new BCryptPasswordEncoder();
  }
}
