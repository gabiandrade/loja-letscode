package br.com.letscode.lojaletscode.service;

import br.com.letscode.lojaletscode.domain.Product;
import br.com.letscode.lojaletscode.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface ProductService {

    Page<Product> getAllProducts(Pageable page);

    Product getProductById(Long id);

    Product createProduct(String name, BigDecimal price);

    Product updateProduct(Long id, ProductDTO request);

    void deleteProduct(Product product);

}
