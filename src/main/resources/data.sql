DROP TABLE products IF EXISTS;

CREATE TABLE products (
    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(200),
    brand VARCHAR(200),
    price NUMBER,
    stock NUMBER,
    state VARCHAR(20),
    discount_percent NUMBER
);