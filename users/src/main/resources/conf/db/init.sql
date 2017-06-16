create table if not exists users (
    id varchar(38) PRIMARY KEY,
    name varchar(150) not null,
    group_id varchar(38) default NULL 
);