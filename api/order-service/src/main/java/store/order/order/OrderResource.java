package store.order.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import store.order.OrderController;
import store.order.OrderIn;
import store.order.OrderOut;
import store.order.ItemOut2;

@RestController
public class OrderResource implements OrderController {

    @Autowired
    private OrderService orderService;

    @Override
    public ResponseEntity<OrderOut> create(OrderIn orderIn, String idAccount) {
        OrderOut created = orderService.create(orderIn, idAccount);
        return ResponseEntity.ok().body(created);
    }

    @Override
    public ResponseEntity<OrderOut> findById(String idAccount, String id) {
        OrderOut order = orderService.findById(id, idAccount);
        if (order == null ) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(order);
    }



    @Override
    public ResponseEntity<List<ItemOut2>> findAll(String idAccount) {
        
        return ResponseEntity
            .ok()
            .body(orderService.findAll(idAccount));
    }

}
