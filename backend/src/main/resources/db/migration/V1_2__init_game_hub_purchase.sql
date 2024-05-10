CREATE TABLE purchase
(
    purchase_id   SERIAL          NOT NULL,
    purchase_number VARCHAR(64)    NOT NULL,
    date_started TIMESTAMP WITH TIME ZONE NOT NULL,
    date_completed TIMESTAMP WITH TIME ZONE,
    status VARCHAR(32)  NOT NULL,
    delivery_type VARCHAR(32)  NOT NULL,
    email_to_send VARCHAR(64),
    pickup_point VARCHAR(32),
    customer_id INT NOT NULL ,
    address_id INT,

    PRIMARY KEY (purchase_id),
    UNIQUE (purchase_number),
    CONSTRAINT fk_purchase_customer
        FOREIGN KEY (customer_id)
            REFERENCES customer (customer_id),
    CONSTRAINT fk_purchase_address
        FOREIGN KEY (address_id)
            REFERENCES address (address_id)
);