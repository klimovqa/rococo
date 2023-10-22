-- create database "rococo-geo" with owner postgres;

create extension if not exists "uuid-ossp";

create table if not exists country
(
    id                      UUID unique not null,
    name                    varchar(100) unique not null,
    primary key (id)
);

alter table country
    owner to postgres;