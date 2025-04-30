package store.order.order;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;


@Builder
@Data @Accessors(fluent = true)
public class Order {

    private String id;
    private String orderDate;          
    private Double total;

    
    public Order(String id, String date,
                 Double total) {
        this.id        = id;
        this.orderDate = date;
        this.total     = total;
    }
}
