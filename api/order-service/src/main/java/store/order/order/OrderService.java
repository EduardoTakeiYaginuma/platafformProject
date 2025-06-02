package store.order.order;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.order.ItemIn;
import store.order.ItemOut;
import store.order.OrderIn;
import store.order.OrderOut;
import store.order.ProductOut2;
import store.order.ItemOut2;
import store.order.orderItem.OrderItemModel;
import store.order.orderItem.OrderItemRepository;
import store.product.ProductController;
import org.springframework.cache.annotation.Cacheable;
import store.product.ProductOut;

@Service
public class OrderService {

    @Autowired
    private OrderRepository OrderRepository;

    @Autowired
    private OrderItemRepository OrderItemRepository;

    @Autowired
    private ProductController productController;


    public OrderOut create(OrderIn orderIn, String accountId) {
        List<ItemIn> items = orderIn.items();

        OrderModel entity = new OrderModel();
        entity.setDate(LocalDateTime.now().toString());
        entity.setAccountId(accountId);
        OrderRepository.save(entity);

        List<ItemOut> itemOuts = new ArrayList<ItemOut>();
        Double total = 0.0;
        for (ItemIn item : items) {

            String id_product = item.idProduct();
            Integer quantity = item.quantity();

            ProductOut product = productController.findById(id_product).getBody();
            Double valor = product.price() * quantity;

            OrderItemModel orderItemModel = OrderItemModel.builder()
                .orderId(entity.getId())
                .productId(id_product)
                .quantity(quantity)
                .unitPrice(product.price())
                .totalPrice(valor)
                .build();
            OrderItemRepository.save(orderItemModel);

            ProductOut2 productOut2 = new ProductOut2(
                product.id()
            );

            itemOuts.add(ItemOut.builder()
                .id(orderItemModel.getId())
                .product(productOut2)
                .quantity(quantity)
                .total(valor)
                .build());

            total += valor;
        }

        
        entity.setTotal(total);
        Order savedOrder = OrderRepository.save(entity).to();   
        OrderOut orderOut = OrderOut.builder()
                .id(savedOrder.id())
                .date(savedOrder.orderDate())   // se passou a usar LocalDateTime, ajuste
                .items(itemOuts)
                .total(savedOrder.total())
                .build();

        return orderOut;
    }


    @Cacheable(value = "orders", key = "#accountId")
    public List<ItemOut2> findAll(String accountId) {

        // Este método precisa existir no seu OrderRepository
        List<ItemOut2> items = new ArrayList<ItemOut2>();
        List<OrderModel> models = OrderRepository.findAllByAccountId(accountId);
        for (OrderModel model : models) {
            Order order = model.to();

            String date = order.orderDate();
            LocalDateTime dateTime = LocalDateTime.parse(date);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            String formatted = dateTime.format(formatter);

            ItemOut2 itemOut = new ItemOut2(
                order.id(),
                formatted,
                order.total()
            );
            items.add(itemOut);
        }
        return items;
    }


    @Cacheable(value = "order", key = "#id + #accountId")
    public OrderOut findById(String id, String accountId) {
        OrderModel orderModel = OrderRepository.findById(id).get();
        if (orderModel == null || !orderModel.getAccountId().equals(accountId)) {
            return null;
        }
        Order order = orderModel.to();
        List<OrderItemModel> orderItems = OrderItemRepository.findAllByOrderId(order.id());
        List<ItemOut> items = new ArrayList<ItemOut>();
        for (OrderItemModel orderItem : orderItems) {
            String id_product = orderItem.getProductId();
            ProductOut product = productController.findById(id_product).getBody();
            items.add(ItemOut.builder()
                .id(orderItem.getId())
                .product(new ProductOut2(product.id()))
                .quantity(orderItem.getQuantity())
                .total(orderItem.getTotalPrice())
                .build());
        }
        return OrderOut.builder()
            .id(order.id())
            .date(order.orderDate())
            .items(items)
            .total(order.total())
            .build();
    }

}
