package br.com.letscode.lojaletscode.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private ZonedDateTime orderTime;

    @UpdateTimestamp
    private ZonedDateTime orderUpdated;

    /*esse relacionamento tem que ser ManyToMany, após ver o
    * comportamento para questões de estudo, mudar a anotação
    * e ver o novo comportamento */
    @OneToMany
    private List<Product> products;

    /*Quando se usa @JoinTable o joinColumns vai o nome do id da class e
    o inverseJoinColumns vai o nome do id da outra class*/
    @ManyToMany
    @JoinTable(
            name="client_orders",
            joinColumns = @JoinColumn(name="orders_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client> clients;
}
