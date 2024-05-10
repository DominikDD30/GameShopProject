CREATE TABLE trailer
(
    trailer_id    SERIAL       NOT NULL,
    preview_image VARCHAR(255) ,
    url           VARCHAR(255) ,
    PRIMARY KEY (trailer_id)
);