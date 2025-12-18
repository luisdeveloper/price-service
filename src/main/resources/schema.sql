DROP TABLE if EXISTS prices;
CREATE TABLE prices (
  brand_id     INTEGER NOT NULL,
  product_id   INTEGER NOT NULL,
  price_list   INTEGER NOT NULL,
  start_date   TIMESTAMP NOT NULL,
  end_date     TIMESTAMP NOT NULL,
  priority     INTEGER NOT NULL,
  price        DECIMAL(10,2) NOT NULL,
  curr         VARCHAR(3) NOT NULL,
  PRIMARY KEY (brand_id, product_id, price_list)
);