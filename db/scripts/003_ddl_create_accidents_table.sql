CREATE TABLE if not exists accidents (
id SERIAL PRIMARY KEY,
name varchar not null,
accident_type_id int references accident_types(id),
description varchar not null,
address varchar not null
);