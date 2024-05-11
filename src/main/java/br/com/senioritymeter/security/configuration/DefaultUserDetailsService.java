package br.com.senioritymeter.security.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService {
  private final br.com.senioritymeter.security.gateway.SMUserDetails SMUserDetails;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return SMUserDetails.loadUserDetails(username);
  }
}
