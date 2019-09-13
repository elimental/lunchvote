delete from users_roles;
delete from restaurants;
delete from users;
alter sequence hibernate_sequence restart with 10000;

insert into users values (1, 'userlogin', '$2a$10$R/PK4CchTgzzXdswLcQLaewZQp9iEYNv2yVz8pUVkr/70YPmw14cG');
insert into users_roles values (1, 'USER');

insert into users values (2, 'admin', '$2a$10$jZub8BDRKmE1pFp7ppJKxeHOo6Yi325sjE0ENXtkZKz/Qqv4b.bZi');
insert into users_roles values (2, 'ADMIN');
insert into users_roles values (2, 'USER');

insert into restaurants values (3, 'mcdonalds');
insert into restaurants values (4, 'kfc');
insert into restaurants values (5, 'burger king');