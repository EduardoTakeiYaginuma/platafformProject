package store.product;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    @Autowired
    private ProductRepository ProductRepository;

    

    public Product findById(String id) {
        return ProductRepository.findById(id).get().to();
    }

    public Product create(Product product) {
        return ProductRepository.save(new ProductModel(product)).to();
    }

    public void delete(String id) {
        ProductRepository.deleteById(id);
        return;
    }


    public List<Product> findAll() {
        return StreamSupport
            .stream(ProductRepository.findAll().spliterator(), false)
            .map(ProductModel::to)
            .toList();
    }

}
