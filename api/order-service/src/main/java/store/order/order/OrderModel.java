package store.order.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;


@Entity
@Table(name = "orders")         // evita palavra reservada ORDER
@Getter
@Setter
@NoArgsConstructor
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_order")
    private String id;

    @Column(name = "id_account", nullable = false)
    private String accountId;

    @Column(name = "order_date", nullable = false)
    private String date;

    @Column(name = "total")
    private Double total;


    public OrderModel( String id_account, Double total) {
   
        this.total = total;
        this.accountId = id_account;
        this.date  = LocalDateTime.now().toString();
    }

    /* ---------- DTO DE SAÍDA ---------- */
    public Order to() {
        return new Order(id, date, total);
    }
}
