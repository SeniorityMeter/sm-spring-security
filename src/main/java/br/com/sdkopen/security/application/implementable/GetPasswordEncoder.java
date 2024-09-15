package br.com.sdkopen.security.application.implementable;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface GetPasswordEncoder {
  PasswordEncoder execute();
}
