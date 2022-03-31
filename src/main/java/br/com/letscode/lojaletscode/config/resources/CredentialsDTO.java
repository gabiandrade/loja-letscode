package br.com.letscode.lojaletscode.config.resources;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class CredentialsDTO {

    @NotNull(message = "username is required")
    @Size(message = "username must be equal to or lower than 50", min = 1, max = 50)
    private String username;

    @NotNull(message = "password is required")
    @Size(message = "password must be equal to or lower than 50", min = 1, max = 50)
    private String password;

}
