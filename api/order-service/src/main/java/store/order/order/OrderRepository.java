package store.order.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


/*
 * https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
 */

@Repository
public interface OrderRepository extends CrudRepository<OrderModel, String> {  
    List<OrderModel> findAllByAccountId(String accountId);

    OrderModel findByIdAndAccountId(String id, String accountId);

}
