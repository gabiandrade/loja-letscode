package br.com.letscode.lojaletscode.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class ProductCategory {

    @Column(name = "product_category_id")
    private Long id;
    @Column(name = "cat_tipo")
    private String type;
}
