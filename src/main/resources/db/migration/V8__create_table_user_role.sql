CREATE TABLE user_role (
    id bigint not null auto_increment,
    user_id bigint not null,
    role_id bigint not null,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES user(id),
    FOREIGN KEY(role_id) REFERENCES role(id)
);

INSERT INTO user_role (id, user_id, role_id) VALUES (1, 1, 1);