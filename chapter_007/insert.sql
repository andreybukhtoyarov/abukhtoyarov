INSERT INTO roles (role_name) VALUES ('admin');
INSERT INTO rights (rig) VALUES ('get all');
INSERT INTO rights (rig) VALUES ('set all');
INSERT INTO role_rights (role_id, right_id) VALUES (
(SELECT role_id FROM roles WHERE role_name = 'admin'),
(SELECT right_id FROM rights WHERE rig = 'get all')
);
INSERT INTO role_rights (role_id, right_id) VALUES (
(SELECT role_id FROM roles WHERE role_name = 'admin'),
(SELECT right_id FROM rights WHERE rig = 'set all')
);
INSERT INTO users (name, role_id) VALUES (
'Andrey', 
(SELECT role_id FROM roles WHERE role_name = 'admin')
);

INSERT INTO category (description) VALUES ('bid');
INSERT INTO status (status) VALUES ('N');
INSERT INTO status (status) VALUES ('Y');
INSERT INTO file (url) VALUES ('/home/andrey/doc.txt');
INSERT INTO requests (req_name, req_category, req_status, comments) VALUES (
'first',
(SELECT cat_id FROM category WHERE description = 'bid'),
(SELECT status_id FROM status WHERE status = 'N'),
'this is comment'
);
INSERT INTO req_files (req_id, file_id) VALUES (
(SELECT req_id FROM requests WHERE req_name = 'first'),
(SELECT file_id FROM file WHERE url = '/home/andrey/doc.txt')
);
INSERT INTO user_request (req_id, user_id) VALUES (
(SELECT req_id FROM requests WHERE req_name = 'first'),
(SELECT user_id FROM users WHERE name = 'Andrey')
);
