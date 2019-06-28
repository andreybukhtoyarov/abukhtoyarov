/*Вывести список всех машин и все привязанные к ним детали.*/
SELECT c.car_model, b.body_model, e.engine_model, t.transm_model FROM cars AS c
INNER JOIN car_body AS b ON c.body_id = b.body_id
INNER JOIN engine AS e ON c.engine_id = e.engine_id
INNER JOIN transmission AS t ON c.transm_id = t.transm_id;

/*Вывести отдельно детали, которые не используются в машине: кузова, двигатели, коробки передач.*/
SELECT b.body_model FROM car_body AS b
LEFT OUTER JOIN cars AS c ON c.body_id = b.body_id WHERE c.car_model IS NULL;

SELECT e.engine_model FROM engine AS e
LEFT OUTER JOIN cars AS c ON c.engine_id = e.engine_id WHERE c.car_model IS NULL;

SELECT t.transm_model FROM transmission AS t
LEFT OUTER JOIN cars AS c ON c.transm_id = t.transm_id WHERE c.car_model IS NULL;
