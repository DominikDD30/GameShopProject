CREATE TABLE game_purchase
(
    game_purchase_id SERIAL        NOT NULL,
    game_name     VARCHAR(64)   NOT NULL,
    game_image    VARCHAR(256)   NOT NULL,
    quantity      INT           NOT NULL,
    platform      VARCHAR(32)   NOT NULL,
    price         NUMERIC(5, 2) NOT NULL,
    purchase_id      INT           NOT NULL,
    PRIMARY KEY (game_purchase_id),
    CONSTRAINT fk_game_purchase_purchase
        FOREIGN KEY (purchase_id)
            REFERENCES purchase (purchase_id)
);