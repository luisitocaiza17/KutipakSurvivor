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
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sugerencias`
--

LOCK TABLES `sugerencias` WRITE;
/*!40000 ALTER TABLE `sugerencias` DISABLE KEYS */;
INSERT INTO `sugerencias` VALUES (1,'','\0','seeee',NULL),(2,'','\0','pruebas',NULL),(3,'','','',NULL),(4,'','','sf',NULL),(5,'\0','\0','PRUEBAS',NULL),(6,'','\0','',NULL),(7,'\0','','FHDH',NULL),(8,'','\0','todo correcto',NULL),(9,'','','todo correcto',NULL),(10,'','','todo correcto',NULL),(11,'','\0','todo bien',NULL),(12,'','','pruebas',NULL),(13,'','','sadfasfda',NULL),(14,'','','sadfasfdaasdf',NULL),(15,'','','sadfasfdaasdf',NULL),(16,'','','sadfasfdaasdfsfadfsf',NULL),(17,'','','sadfasfdaasdfsfadfsf',NULL),(18,'','','',NULL),(19,'','','dddsfgs',NULL),(20,'','','',NULL),(21,'','','asdasd',NULL),(22,'','','asdasd',NULL),(23,'','','ASDFASFD',NULL),(24,'','','ASDFASFD',NULL),(25,'','','ASDFASFD',NULL),(26,'','','ASDFASFD',NULL),(27,'','','',NULL),(28,'','','',NULL),(29,'','','',NULL),(30,'','','',NULL),(31,'','','sdfa',NULL),(32,'','','PRUEBAS CON COMENTARIOS',NULL),(33,'','','PRUEBAS CON COMENTARIOS',NULL),(34,'','','PRUEBAS CON COMENTARIOS 4',NULL),(35,'','','PRUEBAS CON COMENTARIOS SDFSAFDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD',NULL),(36,'','','PRUEBAS CON COMENTARIOS SDFSAFDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD',NULL),(37,'','','dfsa','2016-01-12 23:29:32'),(38,'','','me parece Diem','2016-01-12 23:49:57'),(39,'','','sdafasfda','2016-01-13 00:55:35'),(40,'','','Me parece una buena aplicación Traduce de forma rapida','2016-01-25 17:05:52'),(41,'','','A pesar que faltan algunas palabras la traduccion me parecio que esta correcta','2016-01-25 17:06:17'),(42,'','','Me gustaria que tambien permitan escuchar los sonidos de la palabras, para complementar','2016-01-25 17:06:46'),(43,'','','Tengo un pequeño problema con las conjugaciones espero eso puedan mejorar','2016-01-25 17:07:16'),(44,'','','Me fue de mucha ayuda para mis deberes no perdi el tiempo con otras paginas que no traducen nada','2016-01-25 17:07:44'),(45,'','','Si me fue util, pero tambien quisiera que puedan traducir documentos de word como lo hacen en google traslate','2016-01-25 17:08:19'),(46,'','','Me gusto la aplicacion creo que es un aporte para valorar nuestras raices','2016-01-25 17:08:47'),(47,'','','La traduccion no es 100% correcta pero mantiene el significado, lo que me parece importante','2016-01-25 17:09:27'),(48,'','','Quisiera poder trabajar con ustedes para mejorar la aplicacion, y tener un producto mucho mas estable, felicitaciones','2016-01-25 17:10:02'),(49,'','','La pagina es un poco pesada pero vale la pena por el tiempo que ahorre al no traducir con paginas que no traducen nada','2016-01-25 17:10:34'),(50,'','','Me parece interesante la aplicación, solo una observación yo quisiera compiar lo que traduzco pero no me deja copiar....gracias','2016-01-25 21:02:58'),(51,'','','nos vemos','2016-01-26 20:11:08'),(52,'','','El programa implementado es de gran ayuda para los estudiantes, pues en mi caso se me complicaba el tiempo y la traducción de ciertas oraciones y con este proyecto tan llamativo las cosas mejoraron. Tan solo una observación si pudieran ayudarnos con los morfemas y con el audio para de esta manera escuchar incluso la pronunciación de las palabras. GRACIAS y FELICITACIONES  a los creadores de tan magnifica plataforma.','2016-01-28 03:12:16'),(53,'','','Excelente aplicación me ayudo mucho en mi tarea','2016-03-14 00:30:31'),(54,'','','Me usto mucho es mejor que otras que utilizado, la verdad es una gran ayuda para los estudiantes, ya que traducir español a kichwa es una tarea deficil de realizar, sin las herramientas necesarias, felicitaciones','2016-03-14 00:32:08'),(55,'','','Es una aplicación muy buena me gusta','2016-04-08 22:22:24'),(56,'','','excelente me ayudo para la tarea, muchas gracias','2016-06-09 06:29:03'),(57,'','','Excelente!!','2016-06-13 06:40:15'),(58,'','','Muy completa, la recomiendo, me ayudo bastante en mi tarea ','2016-06-13 16:28:30');
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

-- Dump completed on 2016-07-07 15:02:21
