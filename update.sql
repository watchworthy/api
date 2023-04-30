
    create table public.movie (
       id bigserial not null,
        overview varchar(1000),
        poster_path varchar(255),
        release_date date,
        title varchar(255),
        primary key (id)
    );

    create table public.roles (
       id serial not null,
        name varchar(255),
        primary key (id)
    );

    create table public.user (
       id bigserial not null,
        email varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        primary key (id)
    );
