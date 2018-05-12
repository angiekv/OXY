-- MySQL dump 10.13  Distrib 5.7.20, for Win64 (x86_64)
--
-- Host: localhost    Database: oxy
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `achat`
--

DROP TABLE IF EXISTS `achat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `achat` (
  `idAchat` int(11) NOT NULL AUTO_INCREMENT,
  `produit_idProduit` int(11) NOT NULL,
  `qte` int(11) DEFAULT NULL,
  `client_idClient` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`idAchat`,`produit_idProduit`,`client_idClient`),
  KEY `fk_achat_produit1_idx` (`produit_idProduit`),
  KEY `fk_achat_client1_idx` (`client_idClient`),
  CONSTRAINT `fk_achat_client1` FOREIGN KEY (`client_idClient`) REFERENCES `client` (`idClient`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_achat_produit1` FOREIGN KEY (`produit_idProduit`) REFERENCES `produit` (`idProduit`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achat`
--

LOCK TABLES `achat` WRITE;
/*!40000 ALTER TABLE `achat` DISABLE KEYS */;
INSERT INTO `achat` VALUES (2,20,2,5,'2018-05-12 00:00:00'),(3,20,2,5,'2018-05-12 00:00:00'),(4,23,2,2,'2018-05-12 00:00:00'),(5,20,1,1,'2018-05-12 00:00:00'),(6,26,1,3,'2018-05-12 00:00:00'),(7,20,1,1,'2018-05-12 00:00:00'),(8,30,1,2,'2018-05-12 00:00:00'),(9,25,2,2,'2018-05-12 00:00:00'),(10,29,2,3,'2018-05-12 00:00:00'),(11,22,2,1,'2018-05-12 00:00:00'),(12,23,1,1,'2018-05-12 00:00:00'),(13,23,1,2,'2018-05-12 00:00:00'),(14,22,1,4,'2018-05-12 00:00:00'),(15,29,2,5,'2018-05-12 00:00:00'),(16,25,2,2,'2018-05-12 00:00:00'),(17,24,1,4,'2018-05-12 00:00:00'),(18,25,2,5,'2018-05-12 00:00:00'),(19,29,1,3,'2018-05-12 00:00:00'),(20,28,2,4,'2018-05-12 00:00:00'),(21,29,1,4,'2018-05-12 00:00:00');
/*!40000 ALTER TABLE `achat` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER maj_qte AFTER INSERT ON achat
FOR EACH ROW
UPDATE produit
SET produit.qte = produit.qte - new.qte 
WHERE new.produit_idProduit = produit.idproduit */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER histo AFTER INSERT ON achat
FOR EACH ROW
INSERT INTO historique VALUES (new.idachat,NULL,NULL,NULL,new.Produit_idProduit,new.qte,now(),"sorti") */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `archive`
--

DROP TABLE IF EXISTS `archive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `archive` (
  `idEmplacement` int(11) DEFAULT NULL,
  `idMagasin` int(11) DEFAULT NULL,
  `redevance` float DEFAULT NULL,
  `date` date DEFAULT NULL,
  KEY `fk_archive_emplacement_has_magasin1_idx` (`idEmplacement`,`idMagasin`),
  CONSTRAINT `fk_archive_emplacement_has_magasin1` FOREIGN KEY (`idEmplacement`, `idMagasin`) REFERENCES `emplacement_has_magasin` (`Emplacement_idEmplacement`, `Magasin_idMagasin`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archive`
--

LOCK TABLES `archive` WRITE;
/*!40000 ALTER TABLE `archive` DISABLE KEYS */;
/*!40000 ALTER TABLE `archive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bonlivraison`
--

DROP TABLE IF EXISTS `bonlivraison`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bonlivraison` (
  `idBonLivraison` int(11) NOT NULL AUTO_INCREMENT,
  `qte` int(11) DEFAULT NULL,
  `produit_idProduit` int(11) NOT NULL,
  `Fournisseur_idFournisseur` int(11) NOT NULL,
  PRIMARY KEY (`idBonLivraison`,`produit_idProduit`,`Fournisseur_idFournisseur`),
  KEY `fk_BonLivraison_produit1_idx` (`produit_idProduit`),
  KEY `fk_BonLivraison_Fournisseur1_idx` (`Fournisseur_idFournisseur`),
  CONSTRAINT `fk_BonLivraison_Fournisseur1` FOREIGN KEY (`Fournisseur_idFournisseur`) REFERENCES `fournisseur` (`idFournisseur`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_BonLivraison_produit1` FOREIGN KEY (`produit_idProduit`) REFERENCES `produit` (`idProduit`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bonlivraison`
--

LOCK TABLES `bonlivraison` WRITE;
/*!40000 ALTER TABLE `bonlivraison` DISABLE KEYS */;
INSERT INTO `bonlivraison` VALUES (1,10,20,1),(1,5,21,1);
/*!40000 ALTER TABLE `bonlivraison` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borne_retrait`
--

DROP TABLE IF EXISTS `borne_retrait`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `borne_retrait` (
  `idBorne_retrait` int(11) NOT NULL AUTO_INCREMENT,
  `localisation` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idBorne_retrait`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borne_retrait`
--

LOCK TABLES `borne_retrait` WRITE;
/*!40000 ALTER TABLE `borne_retrait` DISABLE KEYS */;
/*!40000 ALTER TABLE `borne_retrait` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `idClient` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `adresse` varchar(45) DEFAULT NULL,
  `cp` varchar(5) DEFAULT NULL,
  `ville` varchar(45) DEFAULT NULL,
  `mail` varchar(45) DEFAULT NULL,
  `sexe` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Sloan','Jack','CP 763, 2454 Fermentum Ave','09900','Landau','ornare.Fusce.mollis@Sed.ca','M'),(2,'Stafford','Christine','CP 560, 9937 Ante. Rd.','76631','Oordegem','Maecenas.iaculis.aliquet@enimdiam.com','F'),(3,'Chavez','Hakeem','7514 Blandit Avenue','08691','Independencia','at.pede@orciadipiscing.com','M'),(4,'Carney','Emery','CP 410, 5688 Augue Route','47394','Hartford','vel@natoquepenatibuset.org','M'),(5,'Clayton','Dorothy','Appartement 219-9936 Eu Rue','20123','San Juan de Dios','Nam.nulla@duiCum.co.uk','F');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_has_profile`
--

DROP TABLE IF EXISTS `client_has_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_has_profile` (
  `client_idClient` int(11) NOT NULL,
  `Profile_idProfile` int(11) NOT NULL,
  PRIMARY KEY (`client_idClient`,`Profile_idProfile`),
  KEY `fk_client_has_Profile_Profile1_idx` (`Profile_idProfile`),
  KEY `fk_client_has_Profile_client1_idx` (`client_idClient`),
  CONSTRAINT `fk_client_has_Profile_Profile1` FOREIGN KEY (`Profile_idProfile`) REFERENCES `profile` (`idProfile`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_client_has_Profile_client1` FOREIGN KEY (`client_idClient`) REFERENCES `client` (`idClient`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_has_profile`
--

LOCK TABLES `client_has_profile` WRITE;
/*!40000 ALTER TABLE `client_has_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_has_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emplacement`
--

DROP TABLE IF EXISTS `emplacement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emplacement` (
  `idEmplacement` int(11) NOT NULL AUTO_INCREMENT,
  `superficie` int(11) DEFAULT NULL,
  `loyer_initial` float DEFAULT NULL,
  `localisation` varchar(45) DEFAULT NULL,
  `qualite` int(11) DEFAULT NULL,
  `niveau` int(11) DEFAULT NULL,
  `freqth` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEmplacement`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emplacement`
--

LOCK TABLES `emplacement` WRITE;
/*!40000 ALTER TABLE `emplacement` DISABLE KEYS */;
INSERT INTO `emplacement` VALUES (1,161,32350,'N1',7,1,50000),(2,186,37200,'N2',9,1,80000),(3,70,14000,'N3',6,1,40000),(4,67,13400,'N4',6,1,40000),(5,106,21200,'N5',5,1,30000),(6,199,39800,'N6',8,1,70000),(7,89,17800,'N7',5,1,30000),(8,152,30400,'N8',5,1,30000),(9,177,35400,'N9',8,1,70000),(10,199,39800,'N10',8,1,70000),(11,165,33000,'N11',5,1,30000),(12,44,8800,'N12',5,1,30000),(13,107,21400,'N13',5,1,30000),(14,44,8800,'E14',5,1,30000),(15,195,39000,'E15',8,1,70000),(16,107,21400,'E16',5,1,30000),(17,136,27200,'E17',5,1,30000),(18,81,16200,'E18',6,1,40000),(19,88,17750,'E19',7,1,50000),(20,144,28950,'E20',7,1,50000),(21,106,21200,'E21',6,1,40000),(22,97,19400,'E22',5,1,30000),(23,44,8800,'E23',5,1,30000),(24,169,33800,'E24',5,1,30000),(25,82,16400,'E25',5,1,30000),(26,166,33200,'S26',5,1,30000),(27,163,32600,'S27',5,1,30000),(28,178,35600,'S28',8,1,70000),(29,68,13600,'S29',5,1,30000),(30,66,13200,'S30',5,1,30000),(31,149,29800,'S31',5,1,30000),(32,150,30000,'S32',5,1,30000),(33,43,8600,'S33',5,1,30000),(34,131,26200,'S34',5,1,30000),(35,164,32800,'S35',5,1,30000),(36,80,16000,'S36',5,1,30000),(37,59,11800,'S37',5,1,30000),(38,84,16800,'S38',5,1,30000),(39,148,29600,'S39',5,1,30000),(40,116,23200,'S40',5,1,30000),(41,197,39400,'S41',8,1,70000),(42,43,8600,'S42',5,1,30000),(43,114,22800,'S43',5,1,30000),(44,41,8200,'S44',5,1,30000),(45,83,16600,'S45',5,1,30000),(46,57,11400,'S46',5,1,30000),(47,141,28200,'S47',5,1,30000),(48,67,13400,'S48',5,1,30000),(49,82,16400,'S49',5,1,30000),(50,136,27200,'S50',5,1,30000),(51,183,36600,'S51',8,1,70000),(52,97,19400,'W52',5,1,30000),(53,98,19600,'W53',5,1,30000),(54,149,29800,'W54',5,1,30000),(55,166,33200,'W55',5,1,30000),(56,199,39800,'W56',9,1,80000),(57,128,25750,'W57',7,1,50000),(58,162,32550,'W58',7,1,50000),(59,167,33400,'W59',6,1,40000),(60,83,16600,'W60',5,1,30000),(61,63,12600,'W61',5,1,30000),(62,153,30600,'W62',5,1,30000),(63,161,32200,'W63',5,1,30000),(64,174,34800,'N64',8,1,70000),(65,168,33600,'N65',5,1,30000),(66,185,37000,'N66',8,1,70000),(67,183,36600,'N67',8,1,70000),(68,189,37800,'N68',8,1,70000),(69,108,21600,'N69',5,1,30000),(70,150,30000,'N70',5,1,30000),(71,177,35400,'N71',8,1,70000),(72,139,27800,'N72',6,1,40000),(73,69,13800,'N73',6,1,40000),(74,149,29800,'N74',6,1,40000),(75,146,29350,'N75',7,1,50000),(76,161,32200,'N76',5,2,30000),(77,105,21000,'N77',5,2,30000),(78,50,10000,'N78',5,2,30000),(79,155,31000,'N79',5,2,30000),(80,150,30000,'N80',5,2,30000),(81,69,13800,'N81',5,2,30000),(82,73,14600,'N82',5,2,30000),(83,103,20600,'N83',5,2,30000),(84,71,14200,'N84',5,2,30000),(85,67,13400,'N85',5,2,30000),(86,107,21400,'N86',5,2,30000),(87,63,12600,'N87',5,2,30000),(88,84,16800,'N88',5,2,30000),(89,161,32200,'E89',5,2,30000),(90,84,16800,'E90',5,2,30000),(91,169,33800,'E91',5,2,30000),(92,143,28600,'E92',5,2,30000),(93,71,14200,'E93',5,2,30000),(94,149,29800,'E94',5,2,30000),(95,128,25600,'E95',5,2,30000),(96,163,32600,'E96',5,2,30000),(97,111,22200,'E97',5,2,30000),(98,49,9800,'E98',5,2,30000),(99,174,34800,'E99',8,2,70000),(100,88,17600,'E100',5,2,30000),(101,170,34000,'S101',8,2,70000),(102,147,29400,'S102',5,2,30000),(103,133,26600,'S103',5,2,30000),(104,96,19200,'S104',5,2,30000),(105,73,14600,'S105',5,2,30000),(106,187,37400,'S106',8,2,70000),(107,59,11800,'S107',5,2,30000),(108,81,16200,'S108',5,2,30000),(109,74,14800,'S109',5,2,30000),(110,139,27800,'S110',5,2,30000),(111,180,36000,'S111',8,2,70000),(112,91,18200,'S112',5,2,30000),(113,108,21600,'S113',5,2,30000),(114,179,35800,'S114',8,2,70000),(115,161,32200,'S115',5,2,30000),(116,147,29400,'S116',5,2,30000),(117,128,25600,'S117',5,2,30000),(118,192,38400,'S118',8,2,70000),(119,200,40000,'S119',8,2,70000),(120,150,30000,'S120',5,2,30000),(121,96,19200,'S121',5,2,30000),(122,67,13400,'S122',5,2,30000),(123,41,8200,'S123',5,2,30000),(124,166,33200,'S124',5,2,30000),(125,198,39600,'S125',8,2,70000),(126,198,39600,'S126',8,2,70000),(127,125,25000,'W127',5,2,30000),(128,64,12800,'W128',5,2,30000),(129,41,8200,'W129',5,2,30000),(130,52,10400,'W130',5,2,30000),(131,161,32200,'W131',5,2,30000),(132,187,37400,'W132',8,2,70000),(133,104,20800,'W133',5,2,30000),(134,80,16000,'W134',5,2,30000),(135,112,22400,'W135',5,2,30000),(136,52,10400,'W136',5,2,30000),(137,54,10800,'W137',5,2,30000),(138,102,20400,'W138',5,2,30000),(139,56,11200,'N139',5,2,30000),(140,50,10000,'N140',5,2,30000),(141,59,11800,'N141',5,2,30000),(142,55,11000,'N142',5,2,30000),(143,63,12600,'N143',5,2,30000),(144,200,40000,'N144',8,2,70000),(145,153,30600,'N145',5,2,30000),(146,143,28600,'N146',5,2,30000),(147,186,37200,'N147',8,2,70000),(148,191,38200,'N148',8,2,70000),(149,65,13000,'N149',5,2,30000),(150,95,19000,'N150',5,2,30000);
/*!40000 ALTER TABLE `emplacement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emplacement_has_magasin`
--

DROP TABLE IF EXISTS `emplacement_has_magasin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emplacement_has_magasin` (
  `Emplacement_idEmplacement` int(11) NOT NULL,
  `Magasin_idMagasin` int(11) NOT NULL,
  `redevance` float DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`Emplacement_idEmplacement`,`Magasin_idMagasin`),
  KEY `fk_Emplacement_has_Magasin_Magasin1_idx` (`Magasin_idMagasin`),
  KEY `fk_Emplacement_has_Magasin_Emplacement1_idx` (`Emplacement_idEmplacement`),
  CONSTRAINT `fk_Emplacement_has_Magasin_Emplacement1` FOREIGN KEY (`Emplacement_idEmplacement`) REFERENCES `emplacement` (`idEmplacement`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Emplacement_has_Magasin_Magasin1` FOREIGN KEY (`Magasin_idMagasin`) REFERENCES `magasin` (`idMagasin`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emplacement_has_magasin`
--

LOCK TABLES `emplacement_has_magasin` WRITE;
/*!40000 ALTER TABLE `emplacement_has_magasin` DISABLE KEYS */;
INSERT INTO `emplacement_has_magasin` VALUES (12,3,10560,'2018-05-12'),(14,5,10560,'2018-05-12'),(23,6,31680,'2018-05-12'),(44,2,NULL,NULL),(107,4,NULL,NULL),(144,1,NULL,NULL);
/*!40000 ALTER TABLE `emplacement_has_magasin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fournisseur`
--

DROP TABLE IF EXISTS `fournisseur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fournisseur` (
  `idFournisseur` int(11) NOT NULL,
  `adresse` varchar(45) DEFAULT NULL,
  `cp` varchar(5) DEFAULT NULL,
  `ville` varchar(45) DEFAULT NULL,
  `mail` varchar(45) DEFAULT NULL,
  `raisonsociale` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idFournisseur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fournisseur`
--

LOCK TABLES `fournisseur` WRITE;
/*!40000 ALTER TABLE `fournisseur` DISABLE KEYS */;
INSERT INTO `fournisseur` VALUES (1,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `fournisseur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frequentation`
--

DROP TABLE IF EXISTS `frequentation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `frequentation` (
  `date` date NOT NULL,
  `Emplacement_has_Magasin_Emplacement_idEmplacement` int(11) NOT NULL,
  `Emplacement_has_Magasin_Magasin_idMagasin` int(11) NOT NULL,
  `nb_entree` int(11) DEFAULT NULL,
  PRIMARY KEY (`date`,`Emplacement_has_Magasin_Emplacement_idEmplacement`,`Emplacement_has_Magasin_Magasin_idMagasin`),
  KEY `fk_frequentation_Emplacement_has_Magasin1_idx` (`Emplacement_has_Magasin_Emplacement_idEmplacement`,`Emplacement_has_Magasin_Magasin_idMagasin`),
  CONSTRAINT `fk_frequentation_Emplacement_has_Magasin1` FOREIGN KEY (`Emplacement_has_Magasin_Emplacement_idEmplacement`, `Emplacement_has_Magasin_Magasin_idMagasin`) REFERENCES `emplacement_has_magasin` (`Emplacement_idEmplacement`, `Magasin_idMagasin`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frequentation`
--

LOCK TABLES `frequentation` WRITE;
/*!40000 ALTER TABLE `frequentation` DISABLE KEYS */;
INSERT INTO `frequentation` VALUES ('2018-05-12',12,3,10000),('2018-05-12',14,5,15000),('2018-05-12',23,6,100000);
/*!40000 ALTER TABLE `frequentation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historique`
--

DROP TABLE IF EXISTS `historique`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historique` (
  `achat_idAchat` int(11) DEFAULT NULL,
  `BonLivraison_idBonLivraison` int(11) DEFAULT NULL,
  `retourClient_idretourc` int(11) DEFAULT NULL,
  `retourFournisseur_idretourf` int(11) DEFAULT NULL,
  `produit_idProduit` int(11) DEFAULT NULL,
  `qte` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `action` varchar(45) DEFAULT NULL,
  KEY `fk_Historique_retourClient1_idx` (`retourClient_idretourc`),
  KEY `fk_Historique_retourFournisseur2_idx` (`retourFournisseur_idretourf`),
  KEY `fk_Historique_BonLivraison1_idx` (`BonLivraison_idBonLivraison`),
  KEY `fk_Historique_produit1_idx` (`produit_idProduit`),
  KEY `fk_Historique_achat1` (`achat_idAchat`),
  CONSTRAINT `fk_Historique_BonLivraison1` FOREIGN KEY (`BonLivraison_idBonLivraison`) REFERENCES `bonlivraison` (`idBonLivraison`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Historique_achat1` FOREIGN KEY (`achat_idAchat`) REFERENCES `achat` (`idAchat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Historique_produit1` FOREIGN KEY (`produit_idProduit`) REFERENCES `produit` (`idProduit`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Historique_retourClient1` FOREIGN KEY (`retourClient_idretourc`) REFERENCES `retourclient` (`idretourc`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Historique_retourFournisseur2` FOREIGN KEY (`retourFournisseur_idretourf`) REFERENCES `retourfournisseur` (`idretourf`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historique`
--

LOCK TABLES `historique` WRITE;
/*!40000 ALTER TABLE `historique` DISABLE KEYS */;
INSERT INTO `historique` VALUES (2,NULL,NULL,NULL,20,2,'2018-05-12 14:14:18','sorti'),(3,NULL,NULL,NULL,20,2,'2018-05-12 14:14:18','sorti'),(4,NULL,NULL,NULL,23,2,'2018-05-12 14:14:18','sorti'),(5,NULL,NULL,NULL,20,1,'2018-05-12 14:14:18','sorti'),(6,NULL,NULL,NULL,26,1,'2018-05-12 14:14:18','sorti'),(7,NULL,NULL,NULL,20,1,'2018-05-12 14:14:18','sorti'),(8,NULL,NULL,NULL,30,1,'2018-05-12 14:14:18','sorti'),(9,NULL,NULL,NULL,25,2,'2018-05-12 14:14:18','sorti'),(10,NULL,NULL,NULL,29,2,'2018-05-12 14:14:18','sorti'),(11,NULL,NULL,NULL,22,2,'2018-05-12 14:14:18','sorti'),(12,NULL,NULL,NULL,23,1,'2018-05-12 14:14:18','sorti'),(13,NULL,NULL,NULL,23,1,'2018-05-12 14:14:18','sorti'),(14,NULL,NULL,NULL,22,1,'2018-05-12 14:14:18','sorti'),(15,NULL,NULL,NULL,29,2,'2018-05-12 14:14:18','sorti'),(16,NULL,NULL,NULL,25,2,'2018-05-12 14:14:18','sorti'),(17,NULL,NULL,NULL,24,1,'2018-05-12 14:14:18','sorti'),(18,NULL,NULL,NULL,25,2,'2018-05-12 14:14:18','sorti'),(19,NULL,NULL,NULL,29,1,'2018-05-12 14:14:19','sorti'),(20,NULL,NULL,NULL,28,2,'2018-05-12 14:14:19','sorti'),(21,NULL,NULL,NULL,29,1,'2018-05-12 14:14:19','sorti');
/*!40000 ALTER TABLE `historique` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `magasin`
--

DROP TABLE IF EXISTS `magasin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `magasin` (
  `idMagasin` int(11) NOT NULL AUTO_INCREMENT,
  `designation` varchar(35) DEFAULT NULL,
  `description` varchar(35) DEFAULT NULL,
  `loyer` int(11) DEFAULT NULL,
  `superficie` int(11) DEFAULT NULL,
  `niveau` int(11) DEFAULT NULL,
  `localisation` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idMagasin`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `magasin`
--

LOCK TABLES `magasin` WRITE;
/*!40000 ALTER TABLE `magasin` DISABLE KEYS */;
INSERT INTO `magasin` VALUES (1,'Pharmacie','Médicament',2500,200,1,'Indifferent'),(2,'Zara','Vetement',5000,500,1,'Indifferent'),(3,'H&M','Vetement',10000,100,1,'Indifferent'),(4,'KFC','Restaurant',10000,100,1,'Indifferent'),(5,'GO SPORT','SPORT',10000,100,1,'Indifferent'),(6,'DARTY','Multimédia',10000,100,1,'Indifferent');
/*!40000 ALTER TABLE `magasin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `magasin_has_type`
--

DROP TABLE IF EXISTS `magasin_has_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `magasin_has_type` (
  `magasin_idMagasin` int(11) NOT NULL,
  `type_idType` int(11) NOT NULL,
  PRIMARY KEY (`magasin_idMagasin`,`type_idType`),
  KEY `fk_magasin_has_type_type1_idx` (`type_idType`),
  KEY `fk_magasin_has_type_magasin1_idx` (`magasin_idMagasin`),
  CONSTRAINT `fk_magasin_has_type_magasin1` FOREIGN KEY (`magasin_idMagasin`) REFERENCES `magasin` (`idMagasin`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_magasin_has_type_type1` FOREIGN KEY (`type_idType`) REFERENCES `type` (`idType`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `magasin_has_type`
--

LOCK TABLES `magasin_has_type` WRITE;
/*!40000 ALTER TABLE `magasin_has_type` DISABLE KEYS */;
INSERT INTO `magasin_has_type` VALUES (4,1),(5,2),(2,3),(3,3),(5,3),(6,4),(6,8),(1,10);
/*!40000 ALTER TABLE `magasin_has_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `panneaux_pub`
--

DROP TABLE IF EXISTS `panneaux_pub`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `panneaux_pub` (
  `idPanneaux_pub` int(11) NOT NULL AUTO_INCREMENT,
  `localisation` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPanneaux_pub`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `panneaux_pub`
--

LOCK TABLES `panneaux_pub` WRITE;
/*!40000 ALTER TABLE `panneaux_pub` DISABLE KEYS */;
/*!40000 ALTER TABLE `panneaux_pub` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parcours`
--

DROP TABLE IF EXISTS `parcours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parcours` (
  `idParcours` int(11) NOT NULL AUTO_INCREMENT,
  `client_idClient` int(11) NOT NULL,
  `magasin_idMagasin` int(11) NOT NULL,
  `Profile_idProfile` int(11) NOT NULL,
  `dateProposition` date DEFAULT NULL,
  PRIMARY KEY (`idParcours`,`client_idClient`,`magasin_idMagasin`),
  KEY `fk_magasin_has_Profile_Profile1_idx` (`Profile_idProfile`),
  KEY `fk_magasin_has_Profile_magasin1_idx` (`magasin_idMagasin`),
  KEY `fk_parcours_client1_idx` (`client_idClient`),
  CONSTRAINT `fk_magasin_has_Profile_Profile1` FOREIGN KEY (`Profile_idProfile`) REFERENCES `profile` (`idProfile`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_magasin_has_Profile_magasin1` FOREIGN KEY (`magasin_idMagasin`) REFERENCES `magasin` (`idMagasin`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_parcours_client1` FOREIGN KEY (`client_idClient`) REFERENCES `client` (`idClient`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parcours`
--

LOCK TABLES `parcours` WRITE;
/*!40000 ALTER TABLE `parcours` DISABLE KEYS */;
/*!40000 ALTER TABLE `parcours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produit`
--

DROP TABLE IF EXISTS `produit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produit` (
  `idProduit` int(11) NOT NULL AUTO_INCREMENT,
  `designation` varchar(45) DEFAULT NULL,
  `prix` float DEFAULT NULL,
  `qte` int(11) DEFAULT NULL,
  `magasin_idMagasin` int(11) NOT NULL,
  PRIMARY KEY (`idProduit`,`magasin_idMagasin`),
  KEY `fk_produit_magasin1_idx` (`magasin_idMagasin`),
  CONSTRAINT `fk_produit_magasin1` FOREIGN KEY (`magasin_idMagasin`) REFERENCES `magasin` (`idMagasin`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produit`
--

LOCK TABLES `produit` WRITE;
/*!40000 ALTER TABLE `produit` DISABLE KEYS */;
INSERT INTO `produit` VALUES (20,'Cachets',5,9994,1),(21,'Poudres',2,10000,1),(22,'Jeans',10,9997,2),(23,'Pull',1,9996,2),(24,'Tshirt',2,9999,3),(25,'Chaussures',15,9994,3),(26,'Tenders',7,9999,4),(27,'Poulet',30,10000,4),(28,'Ballon',5,9998,5),(29,'Sac',8,9994,5),(30,'Ordianteur',800,9999,6),(31,'Television',1200,10000,6);
/*!40000 ALTER TABLE `produit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `idProfile` int(11) NOT NULL AUTO_INCREMENT,
  `profilename` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idProfile`)
) ENGINE=InnoDB AUTO_INCREMENT=1011 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (1,'restaurant'),(2,'sport'),(3,'mode'),(4,'multimedia'),(5,'mode luxe'),(6,'alimentaire'),(7,'jardin'),(8,'culture'),(9,'utilitaire'),(10,'sante'),(101,'restaurant++'),(102,'sport++'),(103,'mode++'),(104,'multimedia++'),(105,'mode luxe++'),(106,'alimentaire++'),(107,'jardin++'),(108,'culture++'),(109,'utilitaire++'),(110,'sante++'),(1001,'restaurant+++'),(1002,'sport+++'),(1003,'mode+++'),(1004,'multimedia+++'),(1005,'mode luxe+++'),(1006,'alimentaire+++'),(1007,'jardin+++'),(1008,'culture+++'),(1009,'utilitaire+++'),(1010,'sante+++');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retourclient`
--

DROP TABLE IF EXISTS `retourclient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `retourclient` (
  `idretourc` int(11) NOT NULL AUTO_INCREMENT,
  `produit_idProduit` int(11) NOT NULL,
  `qte` int(11) DEFAULT NULL,
  `client_idClient` int(11) NOT NULL,
  PRIMARY KEY (`idretourc`,`produit_idProduit`,`client_idClient`),
  KEY `fk_retourClient_client1_idx` (`client_idClient`),
  KEY `fk_retourClient_produit1_idx` (`produit_idProduit`),
  CONSTRAINT `fk_retourClient_client1` FOREIGN KEY (`client_idClient`) REFERENCES `client` (`idClient`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_retourClient_produit1` FOREIGN KEY (`produit_idProduit`) REFERENCES `produit` (`idProduit`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retourclient`
--

LOCK TABLES `retourclient` WRITE;
/*!40000 ALTER TABLE `retourclient` DISABLE KEYS */;
/*!40000 ALTER TABLE `retourclient` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER maj_qte2 AFTER INSERT ON retourclient
FOR EACH ROW
UPDATE produit
SET produit.qte = produit.qte + new.qte 
WHERE produit.idProduit = new.produit_idProduit */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER histo2 AFTER INSERT ON retourclient
FOR EACH ROW
INSERT INTO historique VALUES (NULL,NULL,new.idretourc,NULL,new.Produit_idProduit,new.qte,now(),"retour client") */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `retourfournisseur`
--

DROP TABLE IF EXISTS `retourfournisseur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `retourfournisseur` (
  `idretourf` int(11) NOT NULL AUTO_INCREMENT,
  `produit_idProduit` int(11) NOT NULL,
  `qte` int(11) DEFAULT NULL,
  `Fournisseur_idFournisseur` int(11) NOT NULL,
  PRIMARY KEY (`idretourf`,`produit_idProduit`,`Fournisseur_idFournisseur`),
  KEY `fk_retourFournisseur_Fournisseur1_idx` (`Fournisseur_idFournisseur`),
  KEY `fk_retourFournisseur_produit1_idx` (`produit_idProduit`),
  CONSTRAINT `fk_retourFournisseur_Fournisseur1` FOREIGN KEY (`Fournisseur_idFournisseur`) REFERENCES `fournisseur` (`idFournisseur`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_retourFournisseur_produit1` FOREIGN KEY (`produit_idProduit`) REFERENCES `produit` (`idProduit`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retourfournisseur`
--

LOCK TABLES `retourfournisseur` WRITE;
/*!40000 ALTER TABLE `retourfournisseur` DISABLE KEYS */;
/*!40000 ALTER TABLE `retourfournisseur` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER maj_qte3 AFTER INSERT ON retourfournisseur
FOR EACH ROW
UPDATE produit
SET produit.qte = produit.qte - new.qte 
WHERE produit.idProduit = new.produit_idProduit */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER histo3 AFTER INSERT ON retourfournisseur
FOR EACH ROW
INSERT INTO historique VALUES (NULL,NULL,NULL,new.idretourf,new.Produit_idProduit,new.qte,now(),"retour fournisseur") */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type` (
  `idType` int(11) NOT NULL AUTO_INCREMENT,
  `designation` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idType`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'restaurant'),(2,'sport'),(3,'mode'),(4,'multimedia'),(5,'mode luxe'),(6,'alimentaire'),(7,'jardin'),(8,'culture'),(9,'utilitaire'),(10,'sante');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-12 15:24:09
