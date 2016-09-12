-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema bookshelf
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bookshelf` ;

-- -----------------------------------------------------
-- Schema bookshelf
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bookshelf` DEFAULT CHARACTER SET utf8 ;
USE `bookshelf` ;

-- -----------------------------------------------------
-- Table `books`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `books` ;

CREATE TABLE IF NOT EXISTS `books` (
  `isbn` VARCHAR(50) NOT NULL,
  `title` VARCHAR(100) NULL,
  `cover_img_url` VARCHAR(2048) NULL,
  PRIMARY KEY (`isbn`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `author`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `author` ;

CREATE TABLE IF NOT EXISTS `author` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `author_name` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `book_author`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `book_author` ;

CREATE TABLE IF NOT EXISTS `book_author` (
  `isbn` VARCHAR(50) NOT NULL,
  `author_id` INT NOT NULL,
  PRIMARY KEY (`isbn`, `author_id`),
  INDEX `fk_author_idx` (`author_id` ASC),
  CONSTRAINT `fk_book_isbn`
    FOREIGN KEY (`isbn`)
    REFERENCES `books` (`isbn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_author`
    FOREIGN KEY (`author_id`)
    REFERENCES `author` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO student;
 DROP USER student;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'student' IDENTIFIED BY 'student';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'student';

-- -----------------------------------------------------
-- Data for table `books`
-- -----------------------------------------------------
START TRANSACTION;
USE `bookshelf`;
INSERT INTO `books` (`isbn`, `title`, `cover_img_url`) VALUES ('9780547851396', 'EarthSea', 'http://books.google.com/books/content?id=FD72ekYZqIkC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api');
INSERT INTO `books` (`isbn`, `title`, `cover_img_url`) VALUES ('9780307887436', 'Ready Player One', 'http://books.google.com/books/content?id=J8ahqXjUhAAC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api');
INSERT INTO `books` (`isbn`, `title`, `cover_img_url`) VALUES ('9780670023141', 'The Magician King', 'http://books.google.com/books/content?id=XFZ9tQAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api');
INSERT INTO `books` (`isbn`, `title`, `cover_img_url`) VALUES ('9780062300546', 'Hillbilly Elegy', 'http://books.google.com/books/content?id=-wFtjgEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api');
INSERT INTO `books` (`isbn`, `title`, `cover_img_url`) VALUES ('9780310342991', 'Present Over Perfect', 'http://books.google.com/books/content?id=vMg1jwEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api');
INSERT INTO `books` (`isbn`, `title`, `cover_img_url`) VALUES ('9781600583971', 'Creative Lettering and Beyond', 'https://books.google.com/books/content?id=4875oAEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api');
INSERT INTO `books` (`isbn`, `title`, `cover_img_url`) VALUES ('9780316769488', 'Fahrenheit 451', 'http://books.google.com/books/content?id=y3CyRurE7P4C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api');

COMMIT;


-- -----------------------------------------------------
-- Data for table `author`
-- -----------------------------------------------------
START TRANSACTION;
USE `bookshelf`;
INSERT INTO `author` (`id`, `author_name`) VALUES (1, 'Ursula');
INSERT INTO `author` (`id`, `author_name`) VALUES (2, 'Ernest');
INSERT INTO `author` (`id`, `author_name`) VALUES (3, 'Lev');
INSERT INTO `author` (`id`, `author_name`) VALUES (4, 'J.D.');
INSERT INTO `author` (`id`, `author_name`) VALUES (5, 'Shauna');
INSERT INTO `author` (`id`, `author_name`) VALUES (6, 'Gabri');
INSERT INTO `author` (`id`, `author_name`) VALUES (7, 'J.D.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `book_author`
-- -----------------------------------------------------
START TRANSACTION;
USE `bookshelf`;
INSERT INTO `book_author` (`isbn`, `author_id`) VALUES ('9780547851396', 1);
INSERT INTO `book_author` (`isbn`, `author_id`) VALUES ('9780307887436', 2);
INSERT INTO `book_author` (`isbn`, `author_id`) VALUES ('9780670023141', 3);
INSERT INTO `book_author` (`isbn`, `author_id`) VALUES ('9780062300546', 4);
INSERT INTO `book_author` (`isbn`, `author_id`) VALUES ('9780310342991', 5);
INSERT INTO `book_author` (`isbn`, `author_id`) VALUES ('9781600583971', 6);
INSERT INTO `book_author` (`isbn`, `author_id`) VALUES ('9780316769488', 7);

COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
