CREATE TABLE game
(
    game_id     SERIAL      NOT NULL,
    game_number VARCHAR(64) NOT NULL,
    name VARCHAR(64) NOT NULL,
    description TEXT NOT NULL,
    main_photo VARCHAR(128) NOT NULL,
    is_continuously_delivered  BOOLEAN NOT NULL,
    is_sold_out BOOLEAN NOT NULL,
    trailer_id INT,
    PRIMARY KEY (game_id),
    CONSTRAINT fk_game_trailer
        FOREIGN KEY (trailer_id)
            REFERENCES trailer (trailer_id),
    unique (game_number),
    unique (main_photo)
);