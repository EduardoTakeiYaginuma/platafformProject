package store.order.orderItem;
import lombok.Builder;

import lombok.experimental.Accessors;


@Builder
@Accessors(fluent = true)
public record OrderItem(
        String productId,
        int    quantity,
        double price,
        double total               // preço unitário
) {

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }    

        public double getTotal() {
                return total;
        }

}