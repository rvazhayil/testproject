-- changeset liquibase:1
CREATE TABLE user (name VARCHAR(4000) NOT NULL, salary NUMERIC, PRIMARY KEY (name));
insert into user values ('John',400.25);
insert into user values ('Sam',1400.25);