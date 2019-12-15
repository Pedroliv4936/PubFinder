USE pub_finder;

------------------------------------------------------------------------------------
-- INSERIR TIPOS DE UTILIZADORES
------------------------------------------------------------------------------------
INSERT INTO user_privileges (user_privilege_name) VALUES ('Admin');
INSERT INTO user_privileges (user_privilege_name) VALUES ('User');
------------------------------------------------------------------------------------
-- INSERIR UTILIZADORES
------------------------------------------------------------------------------------
INSERT INTO users (username, password, name, birthday, email, phone, user_privilege_id) VALUES ('piriurna', MD5('franco123'), 'Franco Zalamena', '2000-05-04', 'francozalamena@gmail.com', '910815947', '1');
INSERT INTO users (username, password, name, birthday, email, phone, user_privilege_id) VALUES ('piccolibr', MD5('7337'), 'Pedro Piccoli', '2000-07-27', 'pepepiccoli@hotmail.com', '911771381', '2');
------------------------------------------------------------------------------------
-- INSERIR TIPOS DE PUBS
------------------------------------------------------------------------------------
INSERT INTO pub_types (pub_type_name) VALUES ('Discoteca');
INSERT INTO pub_types (pub_type_name) VALUES ('Bar');
INSERT INTO pub_types (pub_type_name) VALUES ('Sala de jogos');
------------------------------------------------------------------------------------
-- INSERIR PUBS
------------------------------------------------------------------------------------
INSERT INTO pubs (pub_name, entry_price, pub_type_id, xCoord, yCoord, address, pending) VALUES ('Bar do Franco', '0', '2', '38.726470','-9.133010',  'Rua de Arroios 25', '0');
INSERT INTO pubs (pub_name, entry_price, pub_type_id, xCoord, yCoord, address, pending) VALUES ('Bar do Pedro', '10', '2',  '38.707063','-9.152451', 'Rua Dom Carlos I', '0');
INSERT INTO pubs (pub_name, entry_price, pub_type_id, xCoord, yCoord, address, pending) VALUES ('Bar do Joaquim', '0', '1',  '38.720026','-9.137789', 'Rua Nova do Desterro 33', '1');
------------------------------------------------------------------------------------
-- INSERIR TIPOS DE BEBIDAS
------------------------------------------------------------------------------------
INSERT INTO drinks (drink_name) VALUES ('Copo de Cerveja');
INSERT INTO drinks (drink_name) VALUES ('Caneca de Cerveja');
INSERT INTO drinks (drink_name) VALUES ('Sidra');
INSERT INTO drinks (drink_name) VALUES ('Shot');
INSERT INTO drinks (drink_name) VALUES ('Caipirinha');
INSERT INTO drinks (drink_name) VALUES ('Sangria');
INSERT INTO drinks (drink_name) VALUES ('Gin');
