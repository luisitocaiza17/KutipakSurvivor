-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
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
-- Table structure for table `sugerencias`
--

DROP TABLE IF EXISTS `sugerencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sugerencias` (
  `sugerenciasID` int(11) NOT NULL AUTO_INCREMENT,
  `sirvio` bit(1) DEFAULT NULL,
  `tiempo` bit(1) DEFAULT NULL,
  `comentario` varchar(500) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`sugerenciasID`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sugerencias`
--

LOCK TABLES `sugerencias` WRITE;
/*!40000 ALTER TABLE `sugerencias` DISABLE KEYS */;
INSERT INTO `sugerencias` VALUES (1,'','\0','seeee',NULL),(2,'','\0','pruebas',NULL),(3,'','','',NULL),(4,'','','sf',NULL),(5,'\0','\0','PRUEBAS',NULL),(6,'','\0','',NULL),(7,'\0','','FHDH',NULL),(8,'','\0','todo correcto',NULL),(9,'','','todo correcto',NULL),(10,'','','todo correcto',NULL),(11,'','\0','todo bien',NULL),(12,'','','pruebas',NULL),(13,'','','sadfasfda',NULL),(14,'','','sadfasfdaasdf',NULL),(15,'','','sadfasfdaasdf',NULL),(16,'','','sadfasfdaasdfsfadfsf',NULL),(17,'','','sadfasfdaasdfsfadfsf',NULL),(18,'','','',NULL),(19,'','','dddsfgs',NULL),(20,'','','',NULL),(21,'','','asdasd',NULL),(22,'','','asdasd',NULL),(23,'','','ASDFASFD',NULL),(24,'','','ASDFASFD',NULL),(25,'','','ASDFASFD',NULL),(26,'','','ASDFASFD',NULL),(27,'','','',NULL),(28,'','','',NULL),(29,'','','',NULL),(30,'','','',NULL),(31,'','','sdfa',NULL),(32,'','','PRUEBAS CON COMENTARIOS',NULL),(33,'','','PRUEBAS CON COMENTARIOS',NULL),(34,'','','PRUEBAS CON COMENTARIOS 4',NULL),(35,'','','PRUEBAS CON COMENTARIOS SDFSAFDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD',NULL),(36,'','','PRUEBAS CON COMENTARIOS SDFSAFDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD',NULL),(37,'','','dfsa','2016-01-12 23:29:32'),(38,'','','me parece Diem','2016-01-12 23:49:57'),(39,'','','sdafasfda','2016-01-13 00:55:35');
/*!40000 ALTER TABLE `sugerencias` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-19  6:13:05
