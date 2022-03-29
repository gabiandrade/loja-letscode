package br.com.letscode.lojaletscode.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "ec_product_category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parentId")
    @JsonIgnore
    private ProductCategory parent;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    @JsonIgnoreProperties("categories")
    private Set<Product> products;

    @OneToMany(mappedBy = "parent")
    @JsonIgnoreProperties("parent")
    private Set<ProductCategory> childCategories;

}
