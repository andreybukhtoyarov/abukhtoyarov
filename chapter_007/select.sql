/*
В системе заданы таблицы

product(id, name, type_id, expired_date, price)

type(id, name)

Задание.

1. Написать запрос получение всех продуктов с типом "СЫР"

2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"

3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.

4. Написать запрос, который выводит самый дорогой продукт.

5. Написать запрос, который выводит количество всех продуктов определенного типа.

6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"

7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.

8. Вывести все продукты и их тип.
*/

SELECT * FROM product AS p
INNER JOIN product_type AS t
ON t.name = 'сыр' AND t.id = p.type_id;

SELECT * FROM product AS p
WHERE p.name LIKE '%мороженое%';

SELECT * FROM product AS p
WHERE (SELECT (EXTRACT(MONTH FROM CURRENT_DATE) + 1) = EXTRACT(MONTH FROM p.expired_date)
AND EXTRACT(YEAR FROM CURRENT_DATE) = EXTRACT(YEAR FROM p.expired_date));

SELECT * FROM product AS p
WHERE p.price = (SELECT max(p.price) FROM product AS p);

SELECT count(p.name), t.name FROM product AS p
INNER JOIN product_type AS t
ON p.type_id = t.id
GROUP BY t.name;

SELECT * FROM product AS p
INNER JOIN product_type AS t
ON t.name = 'сыр' AND t.id = p.type_id OR t.name = 'молоко' AND t.id = p.type_id;

SELECT t.name FROM product_type AS t
INNER JOIN product AS p
ON t.id = p.type_id
GROUP BY t.name
HAVING count(p.name) < 10;

SELECT p.name, t.name FROM product AS p
INNER JOIN product_type AS t
ON p.type_id = t.id;
