-- -----------------------------------------------------
-- Schema pub_finder
-- -----------------------------------------------------
CREATE SCHEMA  pub_finder;
USE pub_finder ;

-- -----------------------------------------------------
-- Table pub_finder.pub_types
-- -----------------------------------------------------
CREATE TABLE pub_finder.pub_types (
  pub_type_id INT NOT NULL AUTO_INCREMENT,
  pub_type_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (pub_type_id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table pub_finder.pubs
-- -----------------------------------------------------
CREATE TABLE pub_finder.pubs (
  pub_id INT NOT NULL AUTO_INCREMENT,
  pub_name VARCHAR(45) NOT NULL,
  entry_price DOUBLE NULL,
  pub_type_id INT NOT NULL,
  xCoord DOUBLE NOT NULL,
  yCoord DOUBLE NOT NULL,
  address VARCHAR(45) NOT NULL,
  pending TINYINT NULL,
  PRIMARY KEY (pub_id),
  CONSTRAINT fk_pub_type_id
    FOREIGN KEY (pub_type_id)
    REFERENCES pub_finder.pub_types (pub_type_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table pub_finder.drinks
-- -----------------------------------------------------
CREATE TABLE pub_finder.drinks (
  drink_id INT NOT NULL AUTO_INCREMENT,
  drink_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (drink_id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table pub_finder.drinks_for_sale
-- -----------------------------------------------------
CREATE TABLE pub_finder.drinks_for_sale (
  drink_sale_id INT NOT NULL AUTO_INCREMENT,
  pub_id INT NOT NULL,
  drink_id INT NOT NULL,
  price DOUBLE NOT NULL,
  rating DOUBLE NOT NULL,
  pending TINYINT NULL,
  PRIMARY KEY (drink_sale_id),
  CONSTRAINT fk_pub_selling_id
    FOREIGN KEY (pub_id)
    REFERENCES pub_finder.pubs (pub_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_drink_type_id
    FOREIGN KEY (drink_id)
    REFERENCES pub_finder.drinks (drink_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table pub_finder.user_privileges
-- -----------------------------------------------------
CREATE TABLE pub_finder.user_privileges (
  user_privilege_id INT NOT NULL AUTO_INCREMENT,
  user_privilege_name VARCHAR(25) NOT NULL,
  PRIMARY KEY (user_privilege_id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table pub_finder.users
-- -----------------------------------------------------
CREATE TABLE pub_finder.users (
  user_id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  name VARCHAR(45) NOT NULL,
  birthday DATE NOT NULL,
  email VARCHAR(45) NOT NULL,
  phone VARCHAR(9) NOT NULL,
  user_privilege_id INT NOT NULL,
  PRIMARY KEY (user_id),
  CONSTRAINT fk_privilege_type_id
    FOREIGN KEY (user_privilege_id)
    REFERENCES pub_finder.user_privileges (user_privilege_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table pub_finder.users_favorite_drinks
-- -----------------------------------------------------
CREATE TABLE  pub_finder.users_favorite_drinks (
  user_id INT NOT NULL,
  drink_id INT NOT NULL,
  PRIMARY KEY (user_id, drink_id),
  CONSTRAINT fk_user_id
    FOREIGN KEY (user_id)
    REFERENCES pub_finder.users (user_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_User_has_Drink_Drink1
    FOREIGN KEY (drink_id)
    REFERENCES pub_finder.drinks (drink_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

DELIMITER //
Create procedure verUsers()
	begin
		Select * from users;
    end//
DELIMITER ;

DELIMITER //
Create procedure verPubs()
	begin
		Select * from pubs;
    end//
DELIMITER  ;

DELIMITER //
Create procedure verDrinksForSale()
	begin
		SELECT * FROM drinks_for_sale WHERE pending=0;
    end //
DELIMITER ;

DELIMITER //
Create procedure getAllFromUserType(IN type_id INT)
	begin
		Select * from users where user_privilege_id = type_id;
    end //
DELIMITER ;

DELIMITER //
Create procedure distancia(IN Xcoord double,IN Ycoord double)
	begin
		Select * from pubs ORDER BY SQRT(POW(Xcoord - pubs.xCoord,2) + POW(Ycoord - pubs.yCoord,2)) ASC;
    end //
DELIMITER ;

call distancia(-9.137789,38.720026);


