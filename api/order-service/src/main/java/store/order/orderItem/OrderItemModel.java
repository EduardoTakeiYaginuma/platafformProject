package store.order.orderItem;
import jakarta.persistence.*;
import lombok.Getter;

import lombok.Setter;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.Builder;


@Getter
@Setter
@Entity
@Table(name = "order_items")
@NoArgsConstructor                        // JPA exige construtor vazio
@AllArgsConstructor(access = AccessLevel.PRIVATE) // usado pelo @Builder
@Builder   
public class OrderItemModel {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_item_id")
    private String id;

    @Column(name = "order_id", nullable = false)
    private String orderId;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;
    
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;



}