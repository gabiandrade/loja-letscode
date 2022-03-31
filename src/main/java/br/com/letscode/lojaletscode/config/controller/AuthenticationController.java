package br.com.letscode.lojaletscode.config.controller;

import br.com.letscode.lojaletscode.config.resources.AuthenticationResponse;
import br.com.letscode.lojaletscode.config.resources.CredentialsDTO;
import br.com.letscode.lojaletscode.service.SecurityService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@RestController
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final SecurityService securityService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody @Valid CredentialsDTO credentialsDTO) {
        final String username = credentialsDTO.getUsername();
        final String password = credentialsDTO.getPassword();

        // Authenticating...
        final String token = securityService.authenticate(username, password);

        logger.debug("User '{}' authenticated successfully -> Token: '{}'", username, token);
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }


}
