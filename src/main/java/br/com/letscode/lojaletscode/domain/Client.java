package br.com.letscode.lojaletscode.domain;

import ch.qos.logback.classic.db.names.TableName;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nm_client")
    private String name;


    /*Quando se usa @JoinTable o joinColumns vai o nome do id da class e
     o inverseJoinColumns vai o nome do id da outra class
     */
    @ManyToMany
    @JoinTable(
            name="client_orders",
            joinColumns = @JoinColumn(name="client_id"),
            inverseJoinColumns = @JoinColumn(name = "orders_id")
    )
    private List<ProductOrders> productOrdersList;

}
