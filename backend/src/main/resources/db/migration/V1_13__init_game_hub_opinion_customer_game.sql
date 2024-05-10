CREATE TABLE opinion_customer_game
(
    opinion_customer_game_id SERIAL NOT NULL,
    star                     SMALLINT CHECK (star in (1, 2, 3, 4, 5)),
    description              TEXT   ,
    date                     TIMESTAMP WITH TIME ZONE NOT NULL  ,
    game_id                  INT    NOT NULL,
    customer_id              INT    NOT NULL,
    PRIMARY KEY (opinion_customer_game_id),
    CONSTRAINT fk_opinion_customer_game_game
        FOREIGN KEY (game_id)
            REFERENCES game (game_id),
    CONSTRAINT fk_opinion_customer_game_customer
        FOREIGN KEY (customer_id)
            REFERENCES customer (customer_id)
);