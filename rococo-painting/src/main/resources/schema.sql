-- create database "rococo-painting" with owner postgres;

create extension if not exists "uuid-ossp";

create table if not exists painting
(
    id                      UUID unique        not null default uuid_generate_v1() primary key,
    title                   varchar(255) unique not null,
    description             text,
    content                 text,
    museum_id               UUID,
    artist_id               UUID
);

alter table painting
    owner to postgres;