package com.senioritymeter.security.gateway;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface SMPasswordEncoder {
  PasswordEncoder get();
}
