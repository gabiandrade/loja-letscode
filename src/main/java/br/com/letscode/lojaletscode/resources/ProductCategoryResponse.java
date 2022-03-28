package br.com.letscode.lojaletscode.resources;

import br.com.letscode.lojaletscode.domain.ProductCategory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class ProductCategoryResponse {

    private Long id;
    private String name;
    private ProductCategoryResponse parent;

    public static ProductCategoryResponse fromDomain(final ProductCategory productCategory) {
        ProductCategoryResponse result = new ProductCategoryResponse();
        BeanUtils.copyProperties(productCategory, result);
        return result;
    }

    public static Set<ProductCategoryResponse> fromDomain(final Set<ProductCategory> productCategoryList) {
        Set<ProductCategoryResponse> result = new HashSet<>();
        for (ProductCategory productCategory : productCategoryList) {
            result.add(fromDomain(productCategory));
        }
        return result;
    }
}
