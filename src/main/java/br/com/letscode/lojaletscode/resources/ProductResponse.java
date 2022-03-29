package br.com.letscode.lojaletscode.resources;

import br.com.letscode.lojaletscode.domain.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class ProductResponse {

    private Long id;
    private String name;
    @JsonIgnoreProperties("categories")
    private Set<ProductCategoryResponse> categories;
    private BigDecimal price;

    public static ProductResponse fromDomain(final Product product) {
        ProductResponse result = new ProductResponse();
        BeanUtils.copyProperties(product, result);
        /*if (product.getCategories().size() != 0) {
            result.setCategories(ProductCategoryResponse.fromDomain(product.getCategories()));
        }*/
        return result;
    }

    public static List<ProductResponse> fromDomain(final List<Product> productList) {
        List<ProductResponse> result = new ArrayList<>();
        for (Product product : productList) {
            result.add(fromDomain(product));
        }
        return result;
    }
}
