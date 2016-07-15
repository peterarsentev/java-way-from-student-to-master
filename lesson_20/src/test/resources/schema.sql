create table if not exists roles (
  id identity primary key,
  name varchar(200)
);

create table if not exists users (
  id identity primary key,
  username varchar(200),
  fullname varchar(200),
  password varchar(200),
  enabled boolean,
  phone varchar(200),
  email varchar(200),
  role_id int not null references roles(id)
);

create table if not exists pet_types (
 id identity primary key, name varchar(200)
);

create table if not exists pets (
 id identity primary key,
 nick varchar(200),
 type_id int not null references pet_types(id),
 user_id int not null references users(id)
);

create table if not exists messages (
		id identity primary key,
		text character varying,
		created timestamp not null,
		owner_id int not null references users(id),
		author_id int not null references users(id)
);