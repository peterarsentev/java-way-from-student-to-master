create table users (
 id serial primary key,
 name varchar(200),
 created timestamp not null default now()
);

create table pets (
 id serial primary key,
 nick varchar(200),
 user_id int not null references users(id)
);