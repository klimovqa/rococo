-- create database "rococo-artist" with owner postgres;

create extension if not exists "uuid-ossp";

create table if not exists artist
(
    id                      UUID unique not null default uuid_generate_v1() primary key,
    name                    varchar(255) unique not null,
    biography               text,
    photo                   text
);

alter table artist
    owner to postgres;