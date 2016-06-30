create table users (
  id serial primary key,
  username varchar(45),
  password varchar(45),
  enabled boolean default true
);

create table roles (
  id serial primary key,
  user_id int not null references users(id),
  role varchar(45)
);

insert into users(username, password) values ('user','123');
insert into roles(user_id, role) 
values ((select id from users where username='user'),'ROLE_USER');