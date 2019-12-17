INSERT INTO `drinks` (`drink_name`, `drink_image_url`) VALUES
('Copo de Cerveja', 'https://imgur.com/vdR49Ku'),
('Caneca de Cerveja', NULL),
('Sidra', NULL),
('Shot', NULL),
('Caipirinha', NULL),
('Sangria', NULL),
('Gin', NULL);


INSERT INTO `drinks_for_sale` (`pub_id`, `drink_id`, `price`, `rating`, `pending`) VALUES
(2, 1, 0.5, 4, 0),
(2, 2, 1, 4, 0),
(2, 3, 1, 4, 0),
(3, 1, 0.3, 4, 0),
(3, 2, 1.1, 4, 0),
(3, 3, 2, 4, 0),
(4, 1, 0.8, 4, 0),
(4, 2, 1.1, 4, 0),
(4, 3, 1.5, 4, 0),
(5, 1, 0.3, 4, 0),
(5, 5, 5, 4, 0),
(2, 5, 7, 4, 0),
(2, 6, 5, 4, 0),
(2, 4, 1, 4, 0),
(2, 7, 4, 4, 0),
(3, 5, 5, 4, 0),
(6, 1, 1, 4, 1),
(5, 4, 4, 4, 0),
(6, 4, 8, 4, 1),
(7, 5, 4, 4, 1);

INSERT INTO `pubs` (`pub_name`, `entry_price`, `pub_type_id`, `xCoord`, `yCoord`, `address`, `pending`) VALUES
('Bar do Franco', 0, 2, 38.72647, -9.13301, 'Rua de Arroios 25', 0),
('Bar do Pedro', 10, 2, 38.707063, -9.152451, 'Rua Dom Carlos I', 0),
('Bar do Joaquim', 0, 3, 38.720026, -9.137789, 'Rua Nova do Desterro 33', 0),
('Come Prima', 0, 2, 38.704919927109984, -9.166374206542969, 'Rua do Olival, 258', 0),
('Bar do Callegaro', 4, 2, 38.720134, -9.137767, 'Rua Nova do Desterro, 33', 0);

INSERT INTO `pub_types` (`pub_type_id`, `pub_type_name`) VALUES
(1, 'Discoteca'),
(2, 'Bar'),
(3, 'Sala de jogos');

INSERT INTO `users` (`username`, `password`, `name`, `birthday`, `email`, `phone`, `user_privilege_id`) VALUES
('piriurna', '9839fbbf90512951adb2bb8a8e694753', 'Franco Zalamena', '2000-05-04', 'francozalamena@gmail.com', 910815947, 1),
('piccolibr', '23b023b22d0bf47626029d5961328028', 'Pedro Piccoli', '2000-07-27', 'pepepiccoli@hotmail.com', 911771381, 2),
('pedroliv4936', '7ed90f5f710a74d2665a7e5abbec89b8', 'Pedro Oliveira', '1999-06-26', 'pedroColiv@gmail.com', 987654321, 2),
('pedroliv', 'c774c5f3c36b98809ba20c12c2e28bfc', 'Pedro Oliveira ', '2000-06-26', 'poiuytrewq@gmail.com', 987654321, 2),
('PCallegaro', 'e10adc3949ba59abbe56e057f20f883e', 'Pedro Callegaro', '2001-02-04', 'pedro.callegaro@gmail.com', 916680080, 2);

INSERT INTO `user_favorite_drinks` (`user_id`, `drink_id`) VALUES
(11, 4),
(11, 1),
(11, 1),
(11, 2),
(11, 3),
(11, 4),
(11, 6),
(11, 5),
(11, 1),
(11, 3),
(11, 2),
(11, 1),
(11, 2),
(11, 3),
(11, 4),
(11, 5),
(11, 6),
(11, 7),
(11, 4),
(11, 5),
(11, 6),
(11, 3),
(11, 2),
(11, 1),
(15, 7),
(11, 2),
(11, 1),
(11, 3),
(11, 2),
(11, 5);

INSERT INTO `user_privileges` (`user_privilege_name`) VALUES
('Admin'),
('User');

