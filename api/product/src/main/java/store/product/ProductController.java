package store.product;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "product", url = "http://product:8080")
public interface ProductController {

    @PostMapping("/product")
    public ResponseEntity<ProductOut> create(
        @RequestBody ProductIn product
    );
    
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductOut> findById(
        @org.springframework.web.bind.annotation.PathVariable("id") String id
    );

    @GetMapping("/product")
    public ResponseEntity<List<ProductOut>> findAll();

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteById(
        @org.springframework.web.bind.annotation.PathVariable("id") String id
    );

}
