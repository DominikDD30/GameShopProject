CREATE TABLE photo
(
    photo_id   SERIAL          NOT NULL,
    url             VARCHAR(255)     NOT NULL,
    game_id             INT     NOT NULL,
    PRIMARY KEY (photo_id),
    CONSTRAINT fk_photo_game
        FOREIGN KEY (game_id)
            REFERENCES game (game_id)
);