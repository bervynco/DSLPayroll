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
-- Table structure for table `payroll`
--

DROP TABLE IF EXISTS `payroll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payroll` (
  `payrollID` int(11) NOT NULL AUTO_INCREMENT,
  `employeeID` int(11) NOT NULL DEFAULT '0',
  `name` varchar(100) DEFAULT NULL,
  `rate` float DEFAULT '0',
  `sssDeduction` float DEFAULT '0',
  `pagibigDeduction` float DEFAULT '0',
  `philHealthDeduction` float DEFAULT '0',
  `bonus` float DEFAULT '0',
  `cashAdvance` float DEFAULT '0',
  `loan` float DEFAULT '0',
  `days` int(45) DEFAULT '0',
  `overTime` float DEFAULT '0',
  `taxDeduction` float DEFAULT '0',
  `totalSalary` float DEFAULT '0',
  `claimDate` datetime DEFAULT NULL,
  `claimed` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`payrollID`)
) ENGINE=InnoDB AUTO_INCREMENT=998 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payroll`
--

LOCK TABLES `payroll` WRITE;
/*!40000 ALTER TABLE `payroll` DISABLE KEYS */;
INSERT INTO `payroll` VALUES (952,21231,'Bervyn Co Co',0,0,0,0,17000,6100,8000,0,0,0,2900,NULL,0),(953,3,'Juan Santos',900,100,300,200,0,0,0,0,0,1000,-1600,NULL,0),(954,4,'Jose  Reyes',800,400,200,300,0,0,0,0,0,1500,-2400,NULL,0),(955,6,'Riche Liberes',500,1,1,1,12,0,0,0,0,1,8,NULL,0),(956,760,'Ñina Angeles',300,1,1,1,0,10,0,13,0,1,3886,NULL,1),(957,761,'Joseph Candazo',0,0,0,0,0,0,0,6,0,0,0,NULL,0),(958,762,'Ronnie Caravana',0,0,0,0,0,0,0,13,0,0,0,NULL,0),(959,763,'Riche Libres',0,0,0,0,0,0,0,14,0,0,0,NULL,0),(960,764,'Diane June Mabiog',0,0,0,0,0,0,0,7,0,0,0,NULL,0),(961,765,'Sarah Mae Mariño',0,0,0,0,0,0,0,14,0,0,0,NULL,0),(962,766,'Carissa Morales',0,0,0,0,0,0,0,14,0,0,0,NULL,0),(963,767,'Leo Ocana',0,0,0,0,0,0,0,12,0,0,0,NULL,0),(964,768,'Merlinda Orillo',0,0,0,0,0,0,0,14,0,0,0,NULL,0),(965,769,'Arlyn Santos',0,0,0,0,0,0,0,13,0,0,0,NULL,0),(966,770,'Renalyn Velonza',0,0,0,0,0,0,0,13,0,0,0,NULL,0),(967,771,'Jenny Balena',0,0,0,0,0,0,0,6,0,0,0,NULL,0),(968,772,'Arlene Jo Mangaliman',0,0,0,0,0,0,0,12,0,0,0,NULL,0),(969,773,'Rica May Antonino',0,0,0,0,0,0,0,2,0,0,0,NULL,0),(970,774,'Jessa Corre',0,0,0,0,0,0,0,14,0,0,0,NULL,0),(971,775,'Chrisette Santuile',0,0,0,0,0,0,0,12,0,0,0,NULL,0),(972,776,'Alberto Arpon',0,0,0,0,0,0,0,3,0,0,0,NULL,0),(973,777,'Francis Bonifacio',0,0,0,0,0,0,0,12,0,0,0,NULL,0),(974,778,'Hero Castillo',0,0,0,0,0,0,0,7,0,0,0,NULL,0),(975,779,'Roldan Cruz',0,0,0,0,0,0,0,12,0,0,0,NULL,0),(976,780,'April Dela Cruz',0,0,0,0,0,0,0,10,0,0,0,NULL,0),(977,781,'Edison Delos Santos',0,0,0,0,0,0,0,14,0,0,0,NULL,0),(978,782,'Roderic Garay',0,0,0,0,0,0,0,13,0,0,0,NULL,0),(979,783,'Joseph Garin',0,0,0,0,0,0,0,8,0,0,0,NULL,0),(980,784,'Rennel Gelongo',0,0,0,0,0,0,0,12,0,0,0,NULL,0),(981,785,'GIl Granadil',0,0,0,0,0,0,0,8,0,0,0,NULL,0),(982,786,'Sonny Boy Janda',0,0,0,0,0,0,0,13,0,0,0,NULL,0),(983,787,'Danilo Lara',0,0,0,0,0,0,0,13,0,0,0,NULL,0),(984,788,'Joven Magtana',0,0,0,0,0,0,0,13,0,0,0,NULL,0),(985,789,'Aaron Magdagasang',0,0,0,0,0,0,0,4,0,0,0,NULL,0),(986,790,'Bryelle Quitoga',0,0,0,0,0,0,0,14,0,0,0,NULL,0),(987,791,'Ryan Raborer',0,0,0,0,0,0,0,13,0,0,0,NULL,0),(988,792,'Michael Royo',0,0,0,0,0,0,0,13,0,0,0,NULL,0),(989,793,'Jiayian Villanueva',0,0,0,0,0,0,0,2,0,0,0,NULL,0),(990,794,'John Lord Erpelo',0,0,0,0,0,0,0,13,0,0,0,NULL,0),(991,795,'Arlene Dozon',0,0,0,0,0,0,0,14,0,0,0,NULL,0),(992,796,'Alfredo Seldo',0,0,0,0,0,0,0,4,0,0,0,NULL,0),(993,797,'Christian Gila',0,0,0,0,0,0,0,12,0,0,0,NULL,0),(994,798,'Alejandro Arpon',0,0,0,0,0,0,0,13,0,0,0,NULL,0),(995,799,'Josephrey Alag',0,0,0,0,0,0,0,14,0,0,0,NULL,0),(996,800,'Jeffrey Realino',0,0,0,0,0,0,0,14,0,0,0,NULL,0),(997,1,'Sarah Labati',300,1,1,1,500,0,0,0,0,1,496,NULL,0);
/*!40000 ALTER TABLE `payroll` ENABLE KEYS */;
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
