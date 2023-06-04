CREATE TABLE if not exists accident (
id SERIAL PRIMARY KEY,
name varchar not null,
accident_type_id int references accident_type(id),
text varchar not null,
address varchar not null
);