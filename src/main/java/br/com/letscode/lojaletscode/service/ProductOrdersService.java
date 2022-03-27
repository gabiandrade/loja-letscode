package br.com.letscode.lojaletscode.service;

import br.com.letscode.lojaletscode.domain.Client;
import br.com.letscode.lojaletscode.domain.Product;
import br.com.letscode.lojaletscode.domain.ProductOrders;
import br.com.letscode.lojaletscode.dto.ProductOrdersDTO;
import br.com.letscode.lojaletscode.repository.ClientRepository;
import br.com.letscode.lojaletscode.repository.ProductOrdersRepository;
import br.com.letscode.lojaletscode.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductOrdersService {

    private final ProductOrdersRepository productOrdersRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    public void create(ProductOrdersDTO ordersDTO) {
        ProductOrders orders = new ProductOrders();
        orders.setClients(List.of(getClientById(ordersDTO.getClientId()).get()));
        orders.setProducts(getProductsByIds(ordersDTO.getProductIdList()));
        productOrdersRepository.save(orders);
    }

    private List<Product> getProductsByIds(List<Long> productsIds){
        return productRepository.findAllById(productsIds);
    }

    private Optional<Client> getClientById(Long clientId) {
        return clientRepository.findById(clientId);
    }

}
