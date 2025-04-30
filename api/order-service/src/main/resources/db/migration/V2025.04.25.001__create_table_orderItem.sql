CREATE TABLE order_items (
    order_item_id VARCHAR(36) PRIMARY KEY,
    order_id      VARCHAR(36) NOT NULL,
    product_id    VARCHAR(36) NOT NULL,
    quantity      INTEGER   NOT NULL,
    unit_price    NUMERIC(10,2) NOT NULL,
    total_price    NUMERIC(12,2)
);
