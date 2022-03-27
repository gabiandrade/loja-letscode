package br.com.letscode.lojaletscode.controller;


import br.com.letscode.lojaletscode.dto.ProductOrdersDTO;
import br.com.letscode.lojaletscode.service.ProductOrdersService;
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
public class ProductOrdersController {

    private final ProductOrdersService productOrdersService;

    @PostMapping("/register-order-product")
    public ResponseEntity<String> create(@RequestBody ProductOrdersDTO ordersDTO){
        productOrdersService.create(ordersDTO);
        return new ResponseEntity<String>("Order registered successfully", HttpStatus.OK);
    }
}
