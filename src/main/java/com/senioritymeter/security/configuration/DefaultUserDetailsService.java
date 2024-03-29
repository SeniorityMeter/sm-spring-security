package com.senioritymeter.security.configuration;

import com.senioritymeter.security.gateway.SMUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService {
  private final SMUserDetails SMUserDetails;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return SMUserDetails.loadUserDetails(username);
  }
}
