-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: kutipak
-- ------------------------------------------------------
-- Server version	5.5.33

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
-- Table structure for table `tipospalabras`
--

DROP TABLE IF EXISTS `tipospalabras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipospalabras` (
  `TIPOID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRETIPO` varchar(20) DEFAULT NULL,
  `NEMOTECNICO` varchar(10) DEFAULT NULL,
  `CODIGOKTPAK` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`TIPOID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipospalabras`
--

LOCK TABLES `tipospalabras` WRITE;
/*!40000 ALTER TABLE `tipospalabras` DISABLE KEYS */;
INSERT INTO `tipospalabras` VALUES (1,'INDEFINIDOS','INDE','0'),(2,'ADJETIVOS','ADJ','D'),(4,'SUSTANTIVO','SUS','Z'),(7,'ARTICULO','ART','A'),(8,'ADVERBIO','ADV','B'),(9,'PRONOMBRE','PRO','5'),(10,'VERBO','VER','V'),(11,'PREPOSICIÓN','PRE','7'),(12,'CONJUNCIÓN','CON','8'),(13,'INTERJECCIÓN','INTER','9'),(14,'DETERMINANTE','DET','T'),(15,'NUMERAL','NUM','N'),(16,'EXPRESIÓN','EXP','E'),(17,'INTERROGACIÓN','INTERROG','I'),(18,'MORFEMA','MOR','M'),(19,'SER','SER','S'),(20,'PREFIJO','FIJ','P'),(21,'PREGUNTA','PREG','Z');
/*!40000 ALTER TABLE `tipospalabras` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-07 15:02:23
