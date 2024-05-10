CREATE TABLE category_game
(
    category_game_id SERIAL NOT NULL ,
    category_id      INT    NOT NULL,
    game_id       INT    NOT NULL,
    PRIMARY KEY (category_game_id),
    CONSTRAINT fk_category_game_category
        FOREIGN KEY (category_id)
            REFERENCES category (category_id),
    CONSTRAINT fk_category_game_game
        FOREIGN KEY (game_id)
            REFERENCES game (game_id)
);