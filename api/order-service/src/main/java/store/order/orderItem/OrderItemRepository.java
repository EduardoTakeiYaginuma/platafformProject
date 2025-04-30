package store.order.orderItem;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItemModel, String>{

    public List<OrderItemModel> findAllByOrderId(String orderId);
    
}
