package br.com.letscode.lojaletscode.service;

import br.com.letscode.lojaletscode.domain.Product;
import br.com.letscode.lojaletscode.dto.ProductDTO;
import br.com.letscode.lojaletscode.exception.NotFoundException;
import br.com.letscode.lojaletscode.repository.ProductRepository;
import br.com.letscode.lojaletscode.repository.specification.ProductSpecification;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductSpecification productSpecification;

    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("product"));
    }

    @Transactional
    @Override
    public Product createProduct(String name, BigDecimal price) {

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);

        return productRepository.save(product);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    @Override
    public Product updateProduct(Long id, ProductDTO request) {
        Product productUpdate = getProductById(id);
        BeanUtils.copyProperties(request, productUpdate);
        return productRepository.save(productUpdate);
    }

    @Transactional
    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Page<Product> getAllProductsByFilter(ProductDTO dto, Pageable pageable) {
        return productRepository.findAll(productSpecification.productSpecification(dto), pageable);
    }

}
