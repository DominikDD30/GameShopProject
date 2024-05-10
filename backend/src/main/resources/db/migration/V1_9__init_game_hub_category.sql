CREATE TABLE category
(
    category_id SERIAL      NOT NULL,
    category    VARCHAR(32) NOT NULL,
    background_url    VARCHAR(255) NOT NULL,
    PRIMARY KEY (category_id),
    unique (category)
);