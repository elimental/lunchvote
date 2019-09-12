drop table if exists users_roles cascade;
drop table if exists dishes cascade;
drop table if exists restaurants cascade;
drop table if exists users cascade;
drop table if exists roles cascade;
drop table if exists votes cascade;
drop sequence if exists hibernate_sequence;

create sequence hibernate_sequence start with 10000 increment by 1;

create table dishes
(
    id            bigint         not null,
    date          date           not null,
    name          varchar(255),
    price         numeric(19, 2) not null,
    restaurant_id bigint         not null,
    primary key (id)
);

create table restaurants
(
    id   bigint not null,
    name varchar(255),
    primary key (id)
);

create table users
(
    id       bigint not null,
    login    varchar(20),
    password varchar(100),
    primary key (id)
);

create table users_roles
(
    users_id bigint not null,
    role     varchar(255)
);

create table votes
(
    id            bigint not null,
    date          timestamp,
    restaurant_id bigint not null,
    user_id       bigint not null,
    primary key (id)
);

alter table dishes
    add constraint dishes_unique_name_idx unique (name);

alter table dishes
    add constraint FKpslsa9mci7gsfhwukb3mx7s6n
        foreign key (restaurant_id)
            references restaurants;

alter table votes
    add constraint FK93nqd6kky7cyvbe4q1eup9gcx
        foreign key (restaurant_id)
            references restaurants;

alter table votes
    add constraint FKli4uj3ic2vypf5pialchj925e
        foreign key (user_id)
            references users;

alter table users_roles
    add constraint FKml90kef4w2jy7oxyqv742tsfc
        foreign key (users_id)
            references users;