-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: kutipak
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
  PRIMARY KEY (`sugerenciasID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sugerencias`
--

LOCK TABLES `sugerencias` WRITE;
/*!40000 ALTER TABLE `sugerencias` DISABLE KEYS */;
INSERT INTO `sugerencias` VALUES (1,'','\0','seeee'),(2,'','\0','pruebas'),(3,'','',''),(4,'','','sf'),(5,'\0','\0','PRUEBAS'),(6,'','\0',''),(7,'\0','','FHDH'),(8,'','\0','todo correcto'),(9,'','','todo correcto'),(10,'','','todo correcto'),(11,'','\0','todo bien'),(12,'','','pruebas'),(13,'','','sadfasfda'),(14,'','','sadfasfdaasdf'),(15,'','','sadfasfdaasdf'),(16,'','','sadfasfdaasdfsfadfsf'),(17,'','','sadfasfdaasdfsfadfsf'),(18,'','',''),(19,'','','dddsfgs'),(20,'','',''),(21,'','','asdasd'),(22,'','','asdasd'),(23,'','','ASDFASFD'),(24,'','','ASDFASFD'),(25,'','','ASDFASFD'),(26,'','','ASDFASFD'),(27,'','',''),(28,'','',''),(29,'','',''),(30,'','',''),(31,'','','sdfa'),(32,'','','PRUEBAS CON COMENTARIOS'),(33,'','','PRUEBAS CON COMENTARIOS'),(34,'','','PRUEBAS CON COMENTARIOS 4'),(35,'','','PRUEBAS CON COMENTARIOS SDFSAFDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD'),(36,'','','PRUEBAS CON COMENTARIOS SDFSAFDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD');
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

-- Dump completed on 2016-01-12 14:43:42
