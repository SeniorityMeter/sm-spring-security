package com.opensourcelibrary.security.interaction;

import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class LoggedUser {

  public static String getUsername() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    return ((UserDetails) principal).getUsername();
  }
}
