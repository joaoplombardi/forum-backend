CREATE TABLE role (
    id bigint not null auto_increment,
    name varchar(50) not null,
    PRIMARY KEY(id)
);

INSERT INTO role (id, name) VALUES (1, 'READ_WRITE');
