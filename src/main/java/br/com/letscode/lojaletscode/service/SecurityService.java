package br.com.letscode.lojaletscode.service;

import br.com.letscode.lojaletscode.domain.User;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

public interface SecurityService {

    String authenticate(final String username, final String password);

    void authenticate(final String token);

    User getCurrentUser();

}
