package br.com.letscode.lojaletscode.controller;

import br.com.letscode.lojaletscode.domain.Product;
import br.com.letscode.lojaletscode.dto.ProductDTO;
import br.com.letscode.lojaletscode.resources.ProductResponse;
import br.com.letscode.lojaletscode.service.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/store-letscode")
public class ProductController {

    private final ProductServiceImpl productService;


    /* url  ?page=0&size=2&sort=name,asc */
    @GetMapping("/all-products")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> retrieveAllProducts(@PageableDefault(size = 5)
                                                 @SortDefault.SortDefaults({
                                                         @SortDefault(sort = "name", direction = Sort.Direction.DESC),
                                                         @SortDefault(sort = "id", direction = Sort.Direction.ASC)})
                                                         Pageable pageable) {
        Page<Product> products = productService.getAllProducts(pageable);
       // return ResponseEntity.ok(products);
        return ResponseEntity.ok(ProductResponse.fromDomain(products.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(ProductResponse.fromDomain(product));
    }

    @PostMapping("/register-product")
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductDTO request) {
        Product product = productService.createProduct(request.getName(), request.getPrice());
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO request) {
        try {
            productService.updateProduct(id, request);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("mensagem", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        productService.deleteProduct(product);
        return ResponseEntity.noContent().build();
    }

}
