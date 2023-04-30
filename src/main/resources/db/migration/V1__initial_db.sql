CREATE TABLE roles
(
    id   INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE "user"
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    email      VARCHAR(255),
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    password   VARCHAR(255),
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE movie
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title        VARCHAR(255),
    overview     VARCHAR(1000),
    release_date date,
    poster_path  VARCHAR(255),
    CONSTRAINT pk_movie PRIMARY KEY (id)
);