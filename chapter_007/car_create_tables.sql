CREATE TABLE car_body (
body_id SERIAL PRIMARY KEY,
body_model VARCHAR(50) NOT NULL
);

CREATE TABLE engine (
engine_id SERIAL PRIMARY KEY,
engine_model VARCHAR(50) NOT NULL
);

CREATE TABLE transmission (
transm_id SERIAL PRIMARY KEY,
transm_model VARCHAR(50) NOT NULL
);

CREATE TABLE cars (
car_id SERIAL PRIMARY KEY,
car_model VARCHAR(50) NOT NULL,
body_id INT NOT NULL,
engine_id INT NOT NULL,
transm_id INT NOT NULL,
FOREIGN KEY (body_id) REFERENCES car_body (body_id),
FOREIGN KEY (engine_id) REFERENCES engine (engine_id),
FOREIGN KEY (transm_id) REFERENCES transmission (transm_id)
);

INSERT INTO car_body (body_model) VALUES
('lada_body'), ('zil_body'), ('zibben_body'), ('zis_body');

INSERT INTO engine (engine_model) VALUES
('lada_engine'), ('zil_engine'), ('zibben_engine'), ('zis_engine');

INSERT INTO transmission (transm_model) VALUES
('lada_transmission'), ('zil_transmission'), ('zibben_transmission'), ('zis_transmission');

INSERT INTO cars (car_model, body_id, engine_id, transm_id) VALUES
('strt', 1, 2, 3), ('bbls', 4, 3, 2), ('mstr', 2, 1, 4);
