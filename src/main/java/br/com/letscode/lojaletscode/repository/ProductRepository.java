package br.com.letscode.lojaletscode.repository;

import br.com.letscode.lojaletscode.domain.Product;
import br.com.letscode.lojaletscode.dto.ProductCategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query(
            value = "select p.nm_product AS name, p.price AS price, pc.name AS categoryName from ec_product as p " +
                    "inner join ec_product_category_aux as pca on p.id = pca.product_id " +
                    "inner join ec_product_category as pc on pc.id = pca.category_id", nativeQuery = true
    )
    Page<ProductCategoryDTO> findByProductAndCategory(Pageable pageable);

}
