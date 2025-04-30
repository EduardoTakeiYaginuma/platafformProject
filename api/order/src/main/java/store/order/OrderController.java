package store.order;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "order", url = "http://order:8080")
public interface OrderController {

    @PostMapping("/order")
    public ResponseEntity<OrderOut> create(
        @RequestBody OrderIn orderIn,
        @RequestHeader(name = "id-account", required = true) String idAccount
    );
    
    @GetMapping("/order")
    public ResponseEntity<List<ItemOut2>> findAll(
        @RequestHeader(name = "id-account", required = true) String idAccount
    );

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderOut> findById(
        @RequestHeader(name = "id-account", required = true) String idAccount,
        @org.springframework.web.bind.annotation.PathVariable("id") String id   
    );



    // @GetMapping("/order/{id}")
    // public ResponseEntity<Order> findById(
    //     @org.springframework.web.bind.annotation.PathVariable("id") String id
    // );

}
