create table roles (
  id serial primary key,
  name varchar(200)
);

create table users (
  id serial primary key,
  username varchar(200),
  fullname varchar(200),
  password varchar(200),
  enabled boolean default true,
  phone varchar(200),
  email varchar(200),
  role_id int not null references roles(id)
);

create table pet_types (
 id serial primary key,
 name varchar(200)
);

create table pets (
 id serial primary key,
 nick varchar(200),
 type_id int not null references pet_types(id),
 user_id int not null references users(id)
);

create table messages (
		id serial primary key,
		text character varying,
		created timestamp not null default now(),
		owner_id int not null references users(id),
		author_id int not null references users(id)
);

insert into roles(name) values ('ROLE_ADMIN');
insert into users(username, password, role_id) values ('root','root' , (select id from roles where name='ROLE_ADMIN'));