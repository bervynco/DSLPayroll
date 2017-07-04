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
-- Table structure for table `salary_extra`
--

DROP TABLE IF EXISTS `salary_extra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salary_extra` (
  `extraID` int(11) NOT NULL AUTO_INCREMENT,
  `employeeID` int(11) NOT NULL,
  `type` varchar(15) NOT NULL,
  `amount` float NOT NULL,
  `appliedDate` date NOT NULL,
  PRIMARY KEY (`extraID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary_extra`
--

LOCK TABLES `salary_extra` WRITE;
/*!40000 ALTER TABLE `salary_extra` DISABLE KEYS */;
INSERT INTO `salary_extra` VALUES (1,21231,'Allowance',4000,'2017-05-27'),(2,21231,'Bonus',4500,'2017-05-27'),(3,21231,'Bonus',4000,'2017-05-27'),(4,21231,'Allowance',4500,'2017-05-27'),(5,12456,'Allowance',100000,'2017-05-27'),(6,123,'Bonus',20000,'2017-05-27'),(12,21231,'Bonus',1000,'2017-06-22'),(13,1,'Bonus',500,'2017-05-27'),(14,6,'Bonus',12,'2017-05-26'),(15,21231,'Allowance',100,'2017-06-27'),(16,778,'Bonus',1000,'2017-06-27');
/*!40000 ALTER TABLE `salary_extra` ENABLE KEYS */;
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
