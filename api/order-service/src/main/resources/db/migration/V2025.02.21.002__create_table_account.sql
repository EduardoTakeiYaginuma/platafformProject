-- Se precisar derrubar a antiga tabela (cuidado com dados!)
-- DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
    id_order    VARCHAR(36) PRIMARY KEY,
    id_account  VARCHAR(36)            NOT NULL,
    order_date  VARCHAR      NOT NULL,
    total       NUMERIC(12,2)
);

