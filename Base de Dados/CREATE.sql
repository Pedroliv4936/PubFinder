
SET time_zone = "+00:00";
-- -----------------------------------------------------
-- Schema pub_finder
-- -----------------------------------------------------
CREATE SCHEMA  pub_finder;
USE pub_finder ;

----------------------------------------------------------------
-- CREATE TABLE pub_types
-----------------------------------------------------------------

CREATE TABLE `pub_types` (
  `pub_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `pub_type_name` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
   PRIMARY KEY (`pub_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

----------------------------------------------------------------
-- CREATE TABLE drinks
-----------------------------------------------------------------

CREATE TABLE `drinks` (
  `drink_id` int(11) NOT NULL AUTO_INCREMENT,
  `drink_name` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `drink_image_url` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
   PRIMARY KEY (`drink_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

----------------------------------------------------------------
-- CREATE TABLE pubs
-----------------------------------------------------------------

CREATE TABLE `pubs` (
  `pub_id` int(11) NOT NULL AUTO_INCREMENT,
  `pub_name` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `entry_price` double NOT NULL,
  `pub_type_id` int(11) NOT NULL,
  `xCoord` double NOT NULL,
  `yCoord` double NOT NULL,
  `address` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `pending` tinyint(1) NOT NULL,
   PRIMARY KEY (`pub_id`),
   CONSTRAINT `pub_type_id` FOREIGN KEY (`pub_type_id`)
   REFERENCES `pub_types` (`pub_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

----------------------------------------------------------------
-- CREATE TABLE drinks_for_sale
-----------------------------------------------------------------

CREATE TABLE `drinks_for_sale` (
  `drink_sale_id` int(11) NOT NULL AUTO_INCREMENT,
  `pub_id` int(11) NOT NULL,
  `drink_id` int(11) NOT NULL,
  `price` double NOT NULL,
  `rating` double NOT NULL,
  `n_rating` int(4),
  `pending` tinyint(1) NOT NULL,
   PRIMARY KEY(`drink_sale_id`),
   CONSTRAINT `drink_id` FOREIGN KEY (`drink_id`)
   REFERENCES `drinks` (`drink_id`) ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT `pub_id` FOREIGN KEY (`pub_id`)
   REFERENCES `pubs` (`pub_id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

----------------------------------------------------------------
-- CREATE TABLE user_privileges
-----------------------------------------------------------------

CREATE TABLE `user_privileges` (
  `user_privilege_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_privilege_name` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_privilege_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

----------------------------------------------------------------
-- CREATE TABLE users
-----------------------------------------------------------------

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(55) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `birthday` date NOT NULL,
  `email` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `phone` int(9) NOT NULL,
  `user_privilege_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `user_user_privilege` FOREIGN KEY (`user_privilege_id`)
  REFERENCES `user_privileges` (`user_privilege_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

----------------------------------------------------------------
-- CREATE TABLE user_favorite_drinks
-----------------------------------------------------------------

CREATE TABLE `user_favorite_drinks` (
  `user_id` int(11) NOT NULL,
  `drink_id` int(11) NOT NULL,
  CONSTRAINT `favorite_drink_id` FOREIGN KEY (`drink_id`)
  REFERENCES `drinks` (`drink_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_fav_id` FOREIGN KEY (`user_id`)
  REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
