CREATE TABLE customer
(
    customer_id   SERIAL          NOT NULL,
    name             VARCHAR(32),
    surname             VARCHAR(32),
    email             VARCHAR(64),
    PRIMARY KEY (customer_id),
    unique (email)
);