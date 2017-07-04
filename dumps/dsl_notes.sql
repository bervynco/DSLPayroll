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
-- Table structure for table `notes`
--

DROP TABLE IF EXISTS `notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notes` (
  `noteID` int(11) NOT NULL AUTO_INCREMENT,
  `employeeID` int(11) NOT NULL,
  `note` varchar(500) NOT NULL,
  `noteDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`noteID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notes`
--

LOCK TABLES `notes` WRITE;
/*!40000 ALTER TABLE `notes` DISABLE KEYS */;
INSERT INTO `notes` VALUES (1,21231,'Hello world.','2017-04-01 13:41:45'),(2,21231,'Hello world, this is bendrhick co logged in.','2017-04-01 13:39:41'),(3,21231,'Im so handsome','2017-04-01 13:41:59'),(4,123,'Hello world, this is bendrhick co logged in.','2017-04-01 13:39:41'),(5,21231,'Hello world, this is bendrhick co logged in.','2017-04-01 13:39:41'),(6,21231,'I dont care.','2017-04-01 13:42:12'),(7,145161221,'Hello world this is juan dela cruz from Blahblahaaaaaaaaaaaaaaaaaaab','2017-04-01 23:55:26'),(8,2,'This is a test message','2017-04-02 14:54:06'),(9,7,'Hello World aaaaaaaaaaaaaaasdddddddddddddddddddddddvvvv','2017-04-13 12:25:44'),(10,7,'This is to test nl;asssssssssssssssssssssssssssssssssssssssssssssssss','2017-04-13 12:28:21'),(11,123456789,'qwdd','2017-04-19 13:55:10'),(12,123456789,'Sample 1','2017-06-11 16:45:08'),(13,21231,'This is a sample note','2017-06-10 16:48:56'),(14,21231,'21231 is the employee id','2017-06-10 16:51:14'),(15,21231,'hello world test','2017-06-12 02:18:07'),(16,21231,'Hello World','2017-06-12 09:18:08'),(17,123456789,'qwerty','2017-06-21 12:05:35');
/*!40000 ALTER TABLE `notes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-04 12:29:22
