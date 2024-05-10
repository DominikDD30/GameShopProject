CREATE TABLE address
(
    address_id   SERIAL          NOT NULL,
    postal_code  VARCHAR(32)     NOT NULL,
    city  VARCHAR(32)     NOT NULL,
    address  VARCHAR(64)     NOT NULL,
    PRIMARY KEY (address_id)
);