package br.com.letscode.lojaletscode.service;

import br.com.letscode.lojaletscode.config.utils.JWTUtils;
import br.com.letscode.lojaletscode.domain.User;
import br.com.letscode.lojaletscode.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@AllArgsConstructor
@Service
public class SecurityServiceImpl implements SecurityService, UserDetailsService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    @Transactional
    @Override
    public String authenticate(final String username, final String password) {
        final Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(username, password)
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final User user = this.loadUserByUsername(username);

        return JWTUtils.generateToken(user.getId(), user.getUsername(), user.getRole());
    }

    @Transactional
    @Override
    public void authenticate(final String token) {
        final Claims claims = JWTUtils.parseToken(token);

        User user = new User();
        user.setId(Long.parseLong(claims.getSubject()));
        user.setPassword("");
        user.setUsername(claims.get(JWTUtils.TOKEN_CLAIM_USERNAME).toString());
        user.setRole(claims.get(JWTUtils.TOKEN_CLAIM_ROLES).toString());

        // Setting up Authentication...
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities())
        );
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Transactional
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
    }

}
