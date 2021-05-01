CREATE TABLE `parameta_db`.`employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `names` VARCHAR(100) NOT NULL,
  `surname` VARCHAR(100) NOT NULL,
  `document_type` VARCHAR(45) NOT NULL,
  `document_number` VARCHAR(45) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `date_of_joining_company` DATE NOT NULL,
  `position` VARCHAR(100) NOT NULL,
  `salary` DECIMAL NOT NULL,
  PRIMARY KEY (`id`));