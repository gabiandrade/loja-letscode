package br.com.letscode.lojaletscode.service;

import br.com.letscode.lojaletscode.domain.Product;
import br.com.letscode.lojaletscode.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public void create(Product product) {
        productRepository.save(product);
    }
}
