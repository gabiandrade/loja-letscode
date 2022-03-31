package br.com.letscode.lojaletscode.config.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationResponse {

    private final String token;
}
