package br.com.letscode.lojaletscode.domain;

import javax.persistence.*;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nm_product")
    private String name;

    @Column(name = "qtd_estoque")
    private Long quantidadeEstoque;

    private BigDecimal price;

    @Embedded
    private ProductCategory category;


}
