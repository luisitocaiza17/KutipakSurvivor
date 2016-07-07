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
-- Table structure for table `palabrassubfijosprefijos`
--

DROP TABLE IF EXISTS `palabrassubfijosprefijos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `palabrassubfijosprefijos` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PALABRA` varchar(200) DEFAULT NULL,
  `IDIOMA` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `palabrassubfijosprefijos`
--

LOCK TABLES `palabrassubfijosprefijos` WRITE;
/*!40000 ALTER TABLE `palabrassubfijosprefijos` DISABLE KEYS */;
INSERT INTO `palabrassubfijosprefijos` VALUES (1,'KA',2),(2,'MANTA',2),(4,'KAMA',2),(5,'PI',2),(6,'PAK',2),(7,'YUK',2),(8,'CHU',2),(9,'SHI',2),(10,'SHINA',2),(11,'KAMA',2),(12,'MAN',2),(13,'NI',2),(14,'N',2),(15,'KA',2),(16,'KRI',2),(17,'KPI',2),(18,'RAK',2),(19,'PASH',2),(20,'PURA',2),(21,'PAK',2),(23,'SHKA',2),(24,'KU',2),(25,'LLA',2),(26,'RAYKU',2),(27,'YUK',2),(28,'TAK ',2),(29,'PI',2),(30,'KI',2),(31,'PAK',2),(32,'ENDO',1),(33,'ANDO',1),(42,'MI',2),(43,'TA',2),(44,'WAN',2),(45,'SAPA',2),(46,'SHINA',2),(47,'ITO',1),(48,'ITA',1);
/*!40000 ALTER TABLE `palabrassubfijosprefijos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-07 15:02:26
