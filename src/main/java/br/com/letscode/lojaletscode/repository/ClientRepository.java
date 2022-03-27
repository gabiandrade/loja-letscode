package br.com.letscode.lojaletscode.repository;

import br.com.letscode.lojaletscode.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
