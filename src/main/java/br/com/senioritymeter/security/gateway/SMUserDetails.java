package br.com.senioritymeter.security.gateway;

import org.springframework.security.core.userdetails.UserDetails;

public interface SMUserDetails {

  UserDetails loadUserDetails(String username);
}
