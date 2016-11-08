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
-- Temporary view structure for view `pantallapalabras`
--

DROP TABLE IF EXISTS `pantallapalabras`;
/*!50001 DROP VIEW IF EXISTS `pantallapalabras`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `pantallapalabras` AS SELECT 
 1 AS `palabraid`,
 1 AS `IDIOMA`,
 1 AS `PALABRAS`,
 1 AS `SIGNIFICADO`,
 1 AS `TIPO`,
 1 AS `NOMBRETIPO`,
 1 AS `NOMBRETIEMPO`,
 1 AS `TIEMPO`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `diccionario`
--

DROP TABLE IF EXISTS `diccionario`;
/*!50001 DROP VIEW IF EXISTS `diccionario`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `diccionario` AS SELECT 
 1 AS `PALABRA`,
 1 AS `SIGNIFICADO`,
 1 AS `IDIOMA`,
 1 AS `TIPO`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `pantallapalabras`
--

/*!50001 DROP VIEW IF EXISTS `pantallapalabras`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `pantallapalabras` AS (select `pal`.`PALABRAID` AS `palabraid`,`idi`.`NOMBRE` AS `IDIOMA`,`pal`.`NOMBREPALABRA` AS `PALABRAS`,`pal`.`SIGNIFICADO` AS `SIGNIFICADO`,`tipo`.`NEMOTECNICO` AS `TIPO`,`tipo`.`NOMBRETIPO` AS `NOMBRETIPO`,`tiem`.`NOMBRETIEMPO` AS `NOMBRETIEMPO`,`tiem`.`NEMOTECNICOTIEMPO` AS `TIEMPO` from (((`palabras` `pal` join `idiomas` `idi` on((`pal`.`IDIOMAID` = `idi`.`IDIOMAID`))) join `tipospalabras` `tipo` on((`pal`.`TIPOID` = `tipo`.`TIPOID`))) join `tiempos` `tiem` on((`tiem`.`TIEMPOSID` = `pal`.`TIEMPOSID`)))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `diccionario`
--

/*!50001 DROP VIEW IF EXISTS `diccionario`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `diccionario` AS select `p`.`NOMBREPALABRA` AS `PALABRA`,`p`.`SIGNIFICADO` AS `SIGNIFICADO`,`i`.`NOMBRE` AS `IDIOMA`,`t`.`NOMBRETIPO` AS `TIPO` from ((`palabras` `p` join `idiomas` `i` on((`p`.`IDIOMAID` = `i`.`IDIOMAID`))) join `tipospalabras` `t` on((`p`.`TIPOID` = `t`.`TIPOID`))) order by `i`.`NOMBRE`,`p`.`NOMBREPALABRA` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-08 20:16:17
