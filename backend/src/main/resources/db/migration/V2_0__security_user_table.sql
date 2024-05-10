CREATE TABLE _user
(
    user_id    SERIAL       NOT NULL,
    username   VARCHAR(32)  NOT NULL,
    first_name VARCHAR(32)  NOT NULL,
    last_name  VARCHAR(32)  NOT NULL,
    email      VARCHAR(64)  NOT NULL,
    password   VARCHAR(128) NOT NULL,
    role       VARCHAR(32)  NOT NULL,
    active     BOOLEAN      NOT NULL,
    PRIMARY KEY (user_id),
    unique (email)
);