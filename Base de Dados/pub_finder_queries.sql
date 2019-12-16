use pub_finder;

SELECT * FROM drinks_for_sale 
INNER JOIN drinks ON drinks_for_sale.drink_id = drinks.drink_id 
INNER JOIN pubs ON drinks_for_sale.pub_id = pubs.pub_id 
INNER JOIN pub_types ON pubs.pub_type_id = pub_types.pub_type_id 
WHERE drinks_for_sale.pending = 0;

SELECT * FROM drinks_for_sale 
INNER JOIN drinks ON drinks_for_sale.drink_id = drinks.drink_id 
WHERE pub_id = ?;

SELECT * FROM drinks_for_sale 
INNER JOIN drinks ON drinks_for_sale.drink_id = drinks.drink_id 
INNER JOIN pubs ON drinks_for_sale.pub_id = pubs.pub_id 
INNER JOIN pub_types ON pubs.pub_type_id = pub_types.pub_type_id 
WHERE pending = 1;

DELETE FROM drinks_for_sale WHERE drink_sale_id = ?;

SELECT * FROM user_favorite_drinks 
INNER JOIN drinks ON user_favorite_drinks.drink_id = drinks.drink_id 
WHERE user_id = ?;

INSERT INTO user_favorite_drinks (user_id, drink_id) VALUES(?, ?);

INSERT INTO drinks_for_sale (pub_id, drink_id, price, rating, pending) VALUES (?,?,?,?,?);

SELECT * FROM drinks_for_sale 
INNER JOIN drinks ON drinks_for_sale.drink_id = drinks.drink_id 
INNER JOIN pubs ON drinks_for_sale.pub_id = pubs.pub_id 
INNER JOIN pub_types ON pubs.pub_type_id = pub_types.pub_type_id 
WHERE drinks_for_sale.drink_id IN ("+ iMarks + ") AND drinks_for_sale.pending = 0 ;

SELECT * FROM drinks;

UPDATE drinks_for_sale SET pending = 0 WHERE drink_sale_id = ?;

INSERT INTO users (username, password, name, birthday, email, phone, user_privilege_id) VALUES(?,MD5(?),?,?,?,?,?);

SELECT * FROM users WHERE username = ? AND password = MD5(?);

SELECT * FROM pubs WHERE pub_name = ?;

SELECT * FROM pubs 
INNER JOIN pub_types ON pubs.pub_type_id = pub_types.pub_type_id;

Select * from pubs 
INNER JOIN pub_types ON pubs.pub_type_id = pub_types.pub_type_id 
ORDER BY SQRT(POW(? - pubs.xCoord,2) + POW(? - pubs.yCoord,2)) ASC;

SELECT * FROM pubs 
INNER JOIN pub_types 
ON pubs.pub_type_id = pub_types.pub_type_id 
WHERE pending = 0;

SELECT * FROM pub_types;

SELECT * FROM pubs WHERE pending = 1;

UPDATE pubs SET pending = 0 WHERE pub_id = ?;

DELETE FROM pubs WHERE pub_id = ?;

INSERT INTO pubs (pub_name, entry_price, pub_type_id, xCoord, yCoord, address, pending) VALUES (?, ?, ?, ?, ?, ?, ?);


