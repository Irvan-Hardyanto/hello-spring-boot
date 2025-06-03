CREATE TABLE if not exists users (
    id serial PRIMARY KEY,
    username varchar(12) not null,
    password varchar(12) not null
)