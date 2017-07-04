-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: dsl
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `uploads`
--

DROP TABLE IF EXISTS `uploads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uploads` (
  `uploadID` int(11) NOT NULL AUTO_INCREMENT,
  `employeeID` int(11) NOT NULL,
  `uploadType` varchar(45) NOT NULL,
  `filePath` varchar(45) NOT NULL,
  `uploadDate` timestamp(6) NULL DEFAULT NULL,
  PRIMARY KEY (`uploadID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uploads`
--

LOCK TABLES `uploads` WRITE;
/*!40000 ALTER TABLE `uploads` DISABLE KEYS */;
INSERT INTO `uploads` VALUES (8,21231,'Leave Request','C:\\DSL-Saved Files\\21231\\products','2017-04-03 19:33:45.156000'),(9,7,'Medical/Health','C:\\DSL-Saved Files\\7\\Benny Co BIR.pdf','2017-04-13 12:28:52.249000'),(10,21231,'Medical/Health','C:\\DSL-Saved Files\\21231\\Debbie Co BIR.pdf','2017-04-13 12:29:41.994000'),(11,123456789,'Medical/Health','C:\\DSL-Saved Files\\123456789\\Benny Co BIR.pdf','2017-04-15 13:29:22.361000'),(12,21231,'Medical/Health','C:\\DSL-Saved Files\\21231\\AngularJS.pdf','2017-04-16 16:22:00.808000'),(13,21231,'Medical/Health','C:\\DSL-Saved Files\\21231\\styles.css','2017-04-16 16:22:22.250000'),(14,21231,'Medical/Health','C:\\DSL-Saved Files\\21231\\Benny Co BIR.pdf','2017-04-19 14:49:37.054000'),(15,21231,'Medical/Health','C:\\DSL-Saved Files\\21231\\topic.pptx','2017-06-11 17:22:22.070000'),(16,21231,'Others','C:\\DSL-Saved Files\\21231\\AngularJS.pdf','2017-06-11 17:23:05.840000'),(17,3,'Medical/Health','C:\\DSL-Saved Files\\3\\AngularJS.pdf','2017-06-22 02:15:38.888000');
/*!40000 ALTER TABLE `uploads` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-04 12:29:23
