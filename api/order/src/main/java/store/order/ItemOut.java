package store.order;
import lombok.Builder;
import lombok.experimental.Accessors;



@Builder @Accessors(fluent = true)
public record ItemOut (

    String id,
    ProductOut2 product,
    Integer quantity,
    Double total
 
) {}
