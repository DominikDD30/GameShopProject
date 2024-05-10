CREATE TABLE publisher
(
    publisher_id    SERIAL       NOT NULL,
    publisher_name VARCHAR(255) ,
    unique (publisher_name),
    PRIMARY KEY (publisher_id)
);