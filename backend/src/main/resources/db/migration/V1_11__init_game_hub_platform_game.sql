CREATE TABLE platform_game
(
    platform_game_id   SERIAL          NOT NULL,
    left_in_stock   INT      NOT NULL,
    price  NUMERIC(5,2) NOT NULL,
    version BIGINT NOT NULL,
    platform_id  INT NOT NULL ,
    game_id  INT NOT NULL ,
    PRIMARY KEY (platform_game_id),
    CONSTRAINT fk_platform_game_platform
        FOREIGN KEY (platform_id)
            REFERENCES platform (platform_id),
    CONSTRAINT fk_platform_game_game
        FOREIGN KEY (game_id)
            REFERENCES game (game_id)
);