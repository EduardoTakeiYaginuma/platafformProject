package store.order;

import lombok.Builder;
import lombok.experimental.Accessors;

@Builder @Accessors(fluent = true)
public record ProductDTO (

    String id,
    String name,
    String unit,
    Double price
    
) {}
