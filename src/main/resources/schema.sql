drop table if exists users_roles;
drop table if exists dishes;
drop table if exists restaurants;
drop table if exists users;
drop table if exists roles;

create sequence hibernate_sequence start with 1 increment by 1;

create table restaurants
(
    id   bigint not null,
    name varchar(255),
    primary key (id)
);

create table dishes
(
    id            bigint         not null,
    name          varchar(255),
    price         numeric(19, 2) not null,
    restaurant_id bigint         not null,
    primary key (id)
);

alter table dishes
    add constraint dishes_unique_name_idx unique (name);

alter table dishes
    add constraint FKpslsa9mci7gsfhwukb3mx7s6n
        foreign key (restaurant_id)
            references restaurants;

create table users
(
    id         bigint not null,
    first_name varchar(255),
    last_name  varchar(255),
    login      varchar(255),
    password   varchar(255),
    primary key (id)
);

create table roles
(
    id   bigint not null,
    name varchar(255),
    primary key (id)
);

alter table roles
    add constraint roles_unique_name_idx unique (name);

create table users_roles
(
    users_id bigint not null,
    roles_id bigint not null
);

alter table users_roles
    add constraint FKa62j07k5mhgifpp955h37ponj
        foreign key (roles_id)
            references roles;

alter table users_roles
    add constraint FKml90kef4w2jy7oxyqv742tsfc
        foreign key (users_id)
            references users;



