drop table if exists authorities_users;
drop table if exists authority;
drop table if exists user;

create table authorities_users (
    usuario_id integer not null,
    authority_id integer not null,
    primary key (usuario_id, authority_id)
);

create table authority (
    id integer not null auto_increment,
    authority varchar(255),
    description varchar(255),
    primary key (id)
);

create table user (
    id integer not null auto_increment,
    enabled bit,
    password varchar(255),
    username varchar(255),
    primary key (id)
);

alter table authorities_users 
    add constraint FK1hk335nyb5icwqy64y2mhov2v 
    foreign key (authority_id) 
    references authority (id)

alter table authorities_users 
    add constraint FKe10yx3h6h4yikcfimegqs8y71 
    foreign key (usuario_id) 
    references user (id)

insert into user(id,enabled,password,username) 
values (1,true,"admin","admin");

insert into user (id,enabled,password,username) 
values (2,true,"user","user");

insert into authority (id,authority) values (1,"ROLE_PROVIDER");
insert into authority (id,authority) values (2,"ROLE_CONSUMER");

insert into authorities_users (usuario_id, authority_id) values (1,1);
insert into authorities_users (usuario_id, authority_id) values (2,2);
