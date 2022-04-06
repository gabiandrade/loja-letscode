package br.com.letscode.lojaletscode.repository.specification;

import br.com.letscode.lojaletscode.domain.Product;
import br.com.letscode.lojaletscode.dto.ProductDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductSpecification {

    public Specification<Product> productSpecification(ProductDTO filter) {
        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicateList = new ArrayList<>();

            if (!ObjectUtils.isEmpty(filter.getName())) {
                predicateList.add(
                        criteriaBuilder.like(
                                criteriaBuilder.upper(root.get("name")), "%" + filter.getName().
                                        toUpperCase().concat("%")
                        )
                );
            }

            if (!ObjectUtils.isEmpty(filter.getPrice())) {
                predicateList.add(
                        criteriaBuilder.equal(
                                root.get("price"), filter.getPrice())
                );
            }

            return criteriaBuilder.or(predicateList.toArray(Predicate[]::new));
        });

    }
}
