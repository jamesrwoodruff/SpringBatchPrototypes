DROP TABLE some_schema.people IF EXISTS;

CREATE TABLE some_schema.people  (
   person_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   first_name VARCHAR(20),
   last_name VARCHAR(20)
);