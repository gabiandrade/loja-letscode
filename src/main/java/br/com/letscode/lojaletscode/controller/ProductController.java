package br.com.letscode.lojaletscode.controller;

import br.com.letscode.lojaletscode.domain.Product;
import br.com.letscode.lojaletscode.repository.ProductRepository;
import br.com.letscode.lojaletscode.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/store-letscode")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/register-product")
    public ResponseEntity<String> create(@RequestBody Product product) {
        productService.create(product);
        return new ResponseEntity<>("Product registered successfully", HttpStatus.OK);

    }
}
