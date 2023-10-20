-- create database "rococo-museum" with owner postgres;

create extension if not exists "uuid-ossp";

create table if not exists location
(
    id              UUID unique         not null default uuid_generate_v1() primary key,
    country_id      UUID                not null,
    country_name    varchar(100)        not null,
    city            varchar(100)        not null
    );

alter table location
    owner to postgres;

create table if not exists museum
(
    id                      UUID unique        not null default uuid_generate_v1() primary key,
    title                   varchar(255) unique not null,
    description             text,
    photo                   text,
    location_id             UUID,
    constraint fk_location_museum foreign key (location_id) references location (id)
);

alter table museum
    owner to postgres;