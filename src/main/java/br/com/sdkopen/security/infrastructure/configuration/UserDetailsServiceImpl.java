package br.com.sdkopen.security.infrastructure.configuration;

import br.com.sdkopen.security.application.implementable.GetUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final GetUserDetails getUserDetails;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return getUserDetails.execute(username);
  }
}
