


<--1) Вывести все уникальные имена ролей пользователей.
SELECT DISTINCT role_name
FROM m_roles;




<-- 2) Подсчитать число машин у каждого пользователя. Вывести в формате User full name
-- (username + пробел + user surname) | Число машин у пользователя.

SELECT  m.username, m.surname, count(m.id)
FROM m_users m
JOIN m_cars mc on m.id = mc.user_id
GROUP BY m.username, m.surname;



<-- 3) Подсчитать для каждого диллера число машин, старше 2018 года производства с красным кузовом.

SELECT mad.name, count(m.dealer_id)
FROM m_cars m
JOIN m_auto_dealer mad on mad.id = m.dealer_id
JOIN m_body mb on m.id = mb.car_id
WHERE m.year > 2018 and mb.color = 'Red'
GROUP BY mad.name;

<-- 4) Найти пользователей не из Беларуси и России, у которых есть машина
-- 2010-2015 года выпуска из Германии и купленную в диллере не в Германии с объемом двигателя больше 3 литра.

SELECT m.username
FROM m_users m
JOIN m_cars mc on m.id = mc.user_id
JOIN m_auto_dealer mad on mc.dealer_id = mad.id
JOIN m_engine me on mc.id = me.car_id
WHERE m.country != 'BELARUS' and m.country != 'RUSSIA'
AND mad.country != 'GERMANY'
AND me.volume > 3.0
AND mc.year BETWEEN  2010 AND 2015;


<--  5) Определить логины пользователей, имеющих больше 3 машин.

SELECT login, count(mc.id)
FROM m_users
JOIN m_cars mc on m_users.id = mc.user_id
GROUP BY login
HAVING COUNT (mc.id) >= 3;


<--  6) Вывести уникальных диллеров с подсчитанной суммой стоимостей машин, связанных с ними.

SELECT DISTINCT name, SUM(mc.price)
FROM m_auto_dealer
JOIN m_cars mc on m_auto_dealer.id = mc.dealer_id
GROUP BY name;

<--   7) Подсчитать количество уникальных пользователей, владеющих хотя бы одной машиной,
-- стоимость которой превышает среднюю стоимость всех машин.


SELECT COUNT(DISTINCT m.username)
FROM m_users m
JOIN m_cars mc on m.id = mc.user_id
WHERE mc.price >(SELECT AVG(price) FROM m_cars);