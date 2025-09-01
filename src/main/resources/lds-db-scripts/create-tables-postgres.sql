DROP TABLE IF EXISTS user_model;
DROP TABLE IF EXISTS product;


CREATE TABLE user_model
(
    id       SERIAL     not null,
    email    varchar(50) not null,
    fullname varchar(50) not null,
    password varchar(50) not null,
    PRIMARY KEY (id)
);

CREATE TABLE product
(
    id       SERIAL     not null,
    name    varchar(50) not null,
    price double precision,
    PRIMARY KEY (id)
);

