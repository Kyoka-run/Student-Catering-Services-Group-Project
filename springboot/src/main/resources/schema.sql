-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS `catering`;;

-- Switch to the catering database
USE `catering`;;
SET FOREIGN_KEY_CHECKS = 0;;
-- Drop the items table if it exists
DROP TABLE IF EXISTS items;;

-- Create the items table
CREATE TABLE items (
  id int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT 'Name of product/ Item, repeats are allowed (in the case of multiple shops having the same item but thus a different section_id)',
  section_id int NOT NULL,
  qnty int DEFAULT '0',
  val decimal(6,2) NOT NULL DEFAULT '0.00',
  `status` enum('IN_STOCK','OUT_OF_STOCK') NOT NULL COMMENT 'Availability status, depends on qnty (quantity)',
  create_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY name_section_unique (`name`,section_id),
  KEY section_id_idx (section_id),
  CONSTRAINT section_id FOREIGN KEY (section_id) REFERENCES section (id)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;;

-- Drop the queue table if it exists
DROP TABLE IF EXISTS queue;;

-- Create the queue table
CREATE TABLE queue (
  id int NOT NULL AUTO_INCREMENT,
  section_id int NOT NULL,
  user_id int NOT NULL,
  val int DEFAULT NULL,
  ord_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY SECTION_FK_idx (section_id),
  KEY USER_FK_idx (user_id),
  CONSTRAINT SECTION_FK FOREIGN KEY (section_id) REFERENCES section (id),
  CONSTRAINT USER_FK FOREIGN KEY (user_id) REFERENCES users (id)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;;

-- Drop the roles table if it exists
DROP TABLE IF EXISTS roles;;

-- Create the roles table
CREATE TABLE roles (
  id int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  section_id int DEFAULT NULL,
  PRIMARY KEY (id),
  KEY SECTION_FK_2_idx (section_id),
  CONSTRAINT SECTION_FK_2 FOREIGN KEY (section_id) REFERENCES section (id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;;

-- Drop the section table if it exists
DROP TABLE IF EXISTS section;;

-- Create the section table
CREATE TABLE section (
  id int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  create_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;;

-- Drop the stor_occup table if it exists
DROP TABLE IF EXISTS stor_occup;;

-- Create the stor_occup table
CREATE TABLE stor_occup (
  id int NOT NULL AUTO_INCREMENT,
  num int NOT NULL,
  time_stamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;;

-- Drop the users table if it exists
DROP TABLE IF EXISTS users;;

-- Create the users table
CREATE TABLE users (
  id int NOT NULL AUTO_INCREMENT,
  fname varchar(50) NOT NULL COMMENT 'This is the full name of the user- Not the first name',
  username varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  email varchar(50) NOT NULL COMMENT 'Email, unique, must be filled',
  phone varchar(20) NOT NULL COMMENT 'Phone Number, unique, must be filled',
  role_id int NOT NULL,
  create_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY NAME_MAIL_UQ (fname,email),
  UNIQUE KEY email_UNIQUE (email),
  UNIQUE KEY phone_UNIQUE (phone),
  UNIQUE KEY username_UNIQUE (username),
  KEY ROLE_FK_idx (role_id),
  CONSTRAINT ROLE_FK FOREIGN KEY (role_id) REFERENCES roles (id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;;

-- Drop the voucher table if it exists
DROP TABLE IF EXISTS voucher;;

-- Create the voucher table
CREATE TABLE voucher (
  id int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  val decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;;

-- Drop the voucher_users table if it exists
DROP TABLE IF EXISTS voucher_users;;

-- Create the voucher_users table
CREATE TABLE voucher_users (
  id int NOT NULL AUTO_INCREMENT,
  user_id int DEFAULT NULL,
  voucher_id int DEFAULT NULL,
  `code` varchar(50) NOT NULL,
  `status` enum('READY','EXPIRED','USED') NOT NULL,
  valid_until date NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY code_UNIQUE (`code`),
  KEY USER_FK_3_idx (user_id),
  KEY VOUCHER_FK_idx (voucher_id),
  CONSTRAINT USER_FK_3 FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT VOUCHER_FK FOREIGN KEY (voucher_id) REFERENCES voucher (id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;;

-- Create a view for vouchers (DDL only, no data manipulation)
DROP VIEW IF EXISTS voucher_vw;;
CREATE VIEW `voucher_vw` AS 
SELECT 
    v.name AS name,
    v.description AS description,
    v.val AS val,
    vu.code AS code,
    vu.status AS status,
    vu.valid_until AS valid_until 
FROM 
    `voucher` v
JOIN 
    `voucher_users` vu ON v.id = vu.voucher_id;;


SET FOREIGN_KEY_CHECKS = 1;;

-- Create the trigger for users table
CREATE TRIGGER users_valid BEFORE INSERT ON users FOR EACH ROW
BEGIN
    IF NEW.username REGEXP ' ' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'White spaces not allowed';
    END IF;
END;
;;