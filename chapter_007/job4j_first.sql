CREATE DATABASE job4j_first;

CREATE TABLE roles (
role_id SERIAL PRIMARY KEY,
role_name VARCHAR(50) NOT NULL
);

CREATE TABLE rights (
right_id SERIAL PRIMARY KEY,
rig VARCHAR(50) NOT NULL DEFAULT 'empty'
);

CREATE TABLE role_rights (
role_id INT NOT NULL,
right_id INT NOT NULL,
FOREIGN KEY(role_id) REFERENCES roles(role_id),
FOREIGN KEY(right_id) REFERENCES rights(right_id)
);

CREATE TABLE users (
user_id SERIAL PRIMARY KEY,
name VARCHAR(50) NOT NULL DEFAULT 'empty',
role_id INT NOT NULL,
FOREIGN KEY(role_id) REFERENCES roles(role_id)
);

CREATE TABLE category (
cat_id SERIAL PRIMARY KEY,
description VARCHAR(50) NOT NULL DEFAULT 'empty desc'
);

CREATE TABLE status (
status_id SERIAL PRIMARY KEY,
status VARCHAR(1) NOT NULL DEFAULT 'N' CHECK(status IN('N', 'Y'))
);

CREATE TABLE file (
file_id SERIAL PRIMARY KEY,
url VARCHAR(300) NOT NULL
);

CREATE TABLE requests (
req_id SERIAL PRIMARY KEY,
req_name VARCHAR(300) NOT NULL,
req_category INT NOT NULL,
req_status INT NOT NULL,
comments VARCHAR(300) NOT NULL DEFAULT 'empty comment',
FOREIGN KEY(req_category) REFERENCES category(cat_id),
FOREIGN KEY(req_status) REFERENCES status(status_id)
);

CREATE TABLE user_request (
req_id INT NOT NULL,
user_id INT NOT NULL,
FOREIGN KEY(req_id) REFERENCES requests(req_id),
FOREIGN KEY(user_id) REFERENCES users(user_id)
);

CREATE TABLE req_files (
req_id INT NOT NULL,
file_id INT NOT NULL,
FOREIGN KEY(req_id) REFERENCES requests(req_id),
FOREIGN KEY(file_id) REFERENCES file(file_id)
);
