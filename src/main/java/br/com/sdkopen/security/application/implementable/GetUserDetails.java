package br.com.sdkopen.security.application.implementable;

import org.springframework.security.core.userdetails.UserDetails;

public interface GetUserDetails {

  UserDetails execute(String username);
}
