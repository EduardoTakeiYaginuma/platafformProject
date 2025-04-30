package store.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductResource implements ProductController {

    @Autowired
    private ProductService productService;

    @Override
    public ResponseEntity<ProductOut> create(ProductIn product) {
        Product created = productService.create(to(product));
        return ResponseEntity.ok().body(to(created));
    }

    @Override
    public ResponseEntity<ProductOut> findById(String id) {
        return ResponseEntity.ok().body(to(productService.findById(id)));
    }

    @Override
    public ResponseEntity<List<ProductOut>> findAll() {
        return ResponseEntity
            .ok()
            .body(productService.findAll().stream().map(product -> to(product)).toList());
    }

    @Override
    public ResponseEntity<Void> deleteById(String id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private Product to(ProductIn productIn) {
        return Product.builder()
            .name(productIn.name())
            .unit(productIn.unit())
            .price(productIn.price())
            .build();
    }

    private ProductOut to(Product product) {
        return new ProductOut(
            product.id(),
            product.name(),
            product.unit(),
            product.price()
        );
    }

}
