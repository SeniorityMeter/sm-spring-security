package com.opensourcelibrary.security.gateway;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface OSLPasswordEncoder {
  PasswordEncoder get();
}
