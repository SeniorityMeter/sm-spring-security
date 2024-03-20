package com.opensourcelibrary.security.gateway;

import org.springframework.security.core.userdetails.UserDetails;

public interface OSLUserDetails {

  UserDetails loadUserDetails(String username);
}
