package br.com.sdkopen.security.application.usecase;

import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class GetAuthenticatedUsername {

  public static String execute() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    return ((UserDetails) principal).getUsername();
  }
}
