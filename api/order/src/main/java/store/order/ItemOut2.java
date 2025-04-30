package store.order;
import lombok.Builder;
import lombok.experimental.Accessors;


@Builder @Accessors(fluent = true)
public record ItemOut2 (

    String id,
    String date,
    Double total
) {}

