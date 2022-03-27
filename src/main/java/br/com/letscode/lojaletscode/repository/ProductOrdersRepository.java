package br.com.letscode.lojaletscode.repository;

import br.com.letscode.lojaletscode.domain.ProductOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrdersRepository extends JpaRepository<ProductOrders, Long> {
}
