package br.com.letscode.lojaletscode.service;

import br.com.letscode.lojaletscode.domain.Client;
import br.com.letscode.lojaletscode.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public void create(Client client) {
        clientRepository.save(client);
    }
}
