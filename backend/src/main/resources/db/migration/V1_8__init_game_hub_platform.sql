CREATE TABLE platform
(
    platform_id   SERIAL          NOT NULL,
    name             VARCHAR(32)     NOT NULL,
    PRIMARY KEY (platform_id),
    unique (name)
);