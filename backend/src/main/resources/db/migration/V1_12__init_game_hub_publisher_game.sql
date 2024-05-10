CREATE TABLE publisher_game
(
    publisher_game_id   SERIAL          NOT NULL,
    publisher_id  INT NOT NULL ,
    game_id  INT NOT NULL ,
    PRIMARY KEY (publisher_game_id),
    CONSTRAINT fk_publisher_game_publisher
        FOREIGN KEY (publisher_id)
            REFERENCES publisher (publisher_id),
    CONSTRAINT fk_publisher_game_game
        FOREIGN KEY (game_id)
            REFERENCES game (game_id)
);