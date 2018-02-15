-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema oxy
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema oxy
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `oxy` DEFAULT CHARACTER SET utf8 ;
USE `oxy` ;

-- -----------------------------------------------------
-- Table `oxy`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oxy`.`client` (
  `idClient` INT(11) NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NULL DEFAULT NULL,
  `prenom` VARCHAR(45) NULL DEFAULT NULL,
  `adresse` VARCHAR(45) NULL DEFAULT NULL,
  `cp` VARCHAR(5) NULL DEFAULT NULL,
  `ville` VARCHAR(45) NULL DEFAULT NULL,
  `mail` VARCHAR(45) NULL DEFAULT NULL,
  `sexe` VARCHAR(45) NULL,
  PRIMARY KEY (`idClient`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `oxy`.`type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oxy`.`type` (
  `idType` INT(11) NOT NULL AUTO_INCREMENT,
  `designation` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idType`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `oxy`.`magasin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oxy`.`magasin` (
  `idMagasin` INT(11) NOT NULL AUTO_INCREMENT,
  `designation` VARCHAR(35) NULL DEFAULT NULL,
  `description` VARCHAR(35) NULL DEFAULT NULL,
  `Type_idType` INT(11) NOT NULL,
  PRIMARY KEY (`idMagasin`),
  INDEX `fk_Magasin_Type1_idx` (`Type_idType` ASC),
  CONSTRAINT `fk_Magasin_Type1`
    FOREIGN KEY (`Type_idType`)
    REFERENCES `oxy`.`type` (`idType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `oxy`.`produit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oxy`.`produit` (
  `idProduit` INT(11) NOT NULL AUTO_INCREMENT,
  `designation` VARCHAR(45) NULL DEFAULT NULL,
  `prix` FLOAT NULL DEFAULT NULL,
  `qte` INT(11) NULL DEFAULT NULL,
  `Magasin_idMagasin` INT(11) NOT NULL,
  PRIMARY KEY (`idProduit`),
  INDEX `fk_Produit_Magasin1_idx` (`Magasin_idMagasin` ASC),
  CONSTRAINT `fk_Produit_Magasin1`
    FOREIGN KEY (`Magasin_idMagasin`)
    REFERENCES `oxy`.`magasin` (`idMagasin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `oxy`.`achat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oxy`.`achat` (
  `idAchat` INT(11) NOT NULL,
  `qte` INT(11) NULL DEFAULT NULL,
  `Produit_idProduit` INT(11) NOT NULL,
  `Client_idClient` INT(11) NOT NULL,
  `date` DATETIME NULL,
  PRIMARY KEY (`idAchat`),
  INDEX `fk_Achat_Produit1_idx` (`Produit_idProduit` ASC),
  INDEX `fk_Achat_Client1_idx` (`Client_idClient` ASC),
  CONSTRAINT `fk_Achat_Client1`
    FOREIGN KEY (`Client_idClient`)
    REFERENCES `oxy`.`client` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Achat_Produit1`
    FOREIGN KEY (`Produit_idProduit`)
    REFERENCES `oxy`.`produit` (`idProduit`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `oxy`.`borne_retrait`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oxy`.`borne_retrait` (
  `idBorne_retrait` INT(11) NOT NULL AUTO_INCREMENT,
  `localisation` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idBorne_retrait`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `oxy`.`emplacement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oxy`.`emplacement` (
  `idEmplacement` INT(11) NOT NULL AUTO_INCREMENT,
  `superficie` INT(11) NULL DEFAULT NULL,
  `loyer_initial` FLOAT NULL DEFAULT NULL,
  `localisation` VARCHAR(45) NULL,
  `qualite` INT NULL,
  PRIMARY KEY (`idEmplacement`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `oxy`.`emplacement_has_magasin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oxy`.`emplacement_has_magasin` (
  `Emplacement_idEmplacement` INT(11) NOT NULL,
  `Magasin_idMagasin` INT(11) NOT NULL,
  `redevance` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`Emplacement_idEmplacement`, `Magasin_idMagasin`),
  INDEX `fk_Emplacement_has_Magasin_Magasin1_idx` (`Magasin_idMagasin` ASC),
  INDEX `fk_Emplacement_has_Magasin_Emplacement1_idx` (`Emplacement_idEmplacement` ASC),
  CONSTRAINT `fk_Emplacement_has_Magasin_Emplacement1`
    FOREIGN KEY (`Emplacement_idEmplacement`)
    REFERENCES `oxy`.`emplacement` (`idEmplacement`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Emplacement_has_Magasin_Magasin1`
    FOREIGN KEY (`Magasin_idMagasin`)
    REFERENCES `oxy`.`magasin` (`idMagasin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `oxy`.`entree`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oxy`.`entree` (
  `date` DATETIME NOT NULL,
  `qte` INT(11) NULL DEFAULT NULL,
  `Produit_idProduit` INT(11) NOT NULL,
  PRIMARY KEY (`date`, `Produit_idProduit`),
  INDEX `fk_Entree_Produit1_idx` (`Produit_idProduit` ASC),
  CONSTRAINT `fk_Entree_Produit1`
    FOREIGN KEY (`Produit_idProduit`)
    REFERENCES `oxy`.`produit` (`idProduit`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `oxy`.`frequentation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oxy`.`frequentation` (
  `date` DATE NOT NULL,
  `Emplacement_has_Magasin_Emplacement_idEmplacement` INT(11) NOT NULL,
  `Emplacement_has_Magasin_Magasin_idMagasin` INT(11) NOT NULL,
  `nb_entree` INT NULL,
  PRIMARY KEY (`date`, `Emplacement_has_Magasin_Emplacement_idEmplacement`, `Emplacement_has_Magasin_Magasin_idMagasin`),
  INDEX `fk_frequentation_Emplacement_has_Magasin1_idx` (`Emplacement_has_Magasin_Emplacement_idEmplacement` ASC, `Emplacement_has_Magasin_Magasin_idMagasin` ASC),
  CONSTRAINT `fk_frequentation_Emplacement_has_Magasin1`
    FOREIGN KEY (`Emplacement_has_Magasin_Emplacement_idEmplacement` , `Emplacement_has_Magasin_Magasin_idMagasin`)
    REFERENCES `oxy`.`emplacement_has_magasin` (`Emplacement_idEmplacement` , `Magasin_idMagasin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `oxy`.`panneaux_pub`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oxy`.`panneaux_pub` (
  `idPanneaux_pub` INT(11) NOT NULL AUTO_INCREMENT,
  `localisation` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idPanneaux_pub`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `oxy`.`parcours`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oxy`.`parcours` (
  `idparcours` INT NOT NULL,
  `client_idClient` INT(11) NOT NULL,
  PRIMARY KEY (`idparcours`),
  INDEX `fk_parcours_client1_idx` (`client_idClient` ASC),
  CONSTRAINT `fk_parcours_client1`
    FOREIGN KEY (`client_idClient`)
    REFERENCES `oxy`.`client` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
