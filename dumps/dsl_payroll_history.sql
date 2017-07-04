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
-- Table structure for table `payroll_history`
--

DROP TABLE IF EXISTS `payroll_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payroll_history` (
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
  `claimDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `claimed` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`payrollID`)
) ENGINE=InnoDB AUTO_INCREMENT=1382 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payroll_history`
--

LOCK TABLES `payroll_history` WRITE;
/*!40000 ALTER TABLE `payroll_history` DISABLE KEYS */;
INSERT INTO `payroll_history` VALUES (1244,21231,'Bervyn Co Co',0,0,0,0,0,0,0,0,0,0,0,'2017-06-27 12:17:45',1),(1245,3,'Juan Santos',900,100,300,200,0,0,0,-1,0,1000,-1600,'2017-06-27 12:17:45',0),(1246,4,'Jose  Reyes',800,400,200,300,0,0,0,-1,0,1500,-2400,'2017-06-27 12:17:45',0),(1247,6,'Riche Liberes',500,1,1,1,12,0,0,-1,0,1,8,'2017-06-27 12:17:45',0),(1248,760,'Ñina Angeles',300,1,1,1,0,10,0,13,0,1,3886,'2017-06-27 12:17:45',1),(1249,761,'Joseph Candazo',0,0,0,0,0,0,0,5,0,0,0,'2017-06-27 12:17:45',0),(1250,762,'Ronnie Caravana',0,0,0,0,0,0,0,12,0,0,0,'2017-06-27 12:17:45',0),(1251,763,'Riche Libres',0,0,0,0,0,0,0,13,0,0,0,'2017-06-27 12:17:45',0),(1252,764,'Diane June Mabiog',0,0,0,0,0,0,0,6,0,0,0,'2017-06-27 12:17:46',0),(1253,765,'Sarah Mae Mariño',0,0,0,0,0,0,0,13,0,0,0,'2017-06-27 12:17:46',0),(1254,766,'Carissa Morales',0,0,0,0,0,0,0,13,0,0,0,'2017-06-27 12:17:46',0),(1255,767,'Leo Ocana',0,0,0,0,0,0,0,11,0,0,0,'2017-06-27 12:17:46',0),(1256,768,'Merlinda Orillo',0,0,0,0,0,0,0,13,0,0,0,'2017-06-27 12:17:46',0),(1257,769,'Arlyn Santos',0,0,0,0,0,0,0,12,0,0,0,'2017-06-27 12:17:46',0),(1258,770,'Renalyn Velonza',0,0,0,0,0,0,0,12,0,0,0,'2017-06-27 12:17:46',0),(1259,771,'Jenny Balena',0,0,0,0,0,0,0,5,0,0,0,'2017-06-27 12:17:46',0),(1260,772,'Arlene Jo Mangaliman',0,0,0,0,0,0,0,11,0,0,0,'2017-06-27 12:17:46',0),(1261,773,'Rica May Antonino',0,0,0,0,0,0,0,0,0,0,0,'2017-06-27 12:17:46',1),(1262,774,'Jessa Corre',0,0,0,0,0,0,0,0,0,0,0,'2017-06-27 12:17:46',1),(1263,775,'Chrisette Santuile',0,0,0,0,0,0,0,11,0,0,0,'2017-06-27 12:17:46',0),(1264,776,'Alberto Arpon',0,0,0,0,0,0,0,0,0,0,0,'2017-06-27 12:17:46',1),(1265,777,'Francis Bonifacio',0,0,0,0,0,0,0,11,0,0,0,'2017-06-27 12:17:47',0),(1266,778,'Hero Castillo',0,0,0,0,0,0,0,6,0,0,0,'2017-06-27 12:17:47',0),(1267,779,'Roldan Cruz',0,0,0,0,0,0,0,11,0,0,0,'2017-06-27 12:17:47',0),(1268,780,'April Dela Cruz',0,0,0,0,0,0,0,9,0,0,0,'2017-06-27 12:17:47',0),(1269,781,'Edison Delos Santos',0,0,0,0,0,0,0,13,0,0,0,'2017-06-27 12:17:47',0),(1270,782,'Roderic Garay',0,0,0,0,0,0,0,12,0,0,0,'2017-06-27 12:17:47',0),(1271,783,'Joseph Garin',0,0,0,0,0,0,0,7,0,0,0,'2017-06-27 12:17:47',0),(1272,784,'Rennel Gelongo',0,0,0,0,0,0,0,11,0,0,0,'2017-06-27 12:17:47',0),(1273,785,'GIl Granadil',0,0,0,0,0,0,0,7,0,0,0,'2017-06-27 12:17:47',0),(1274,786,'Sonny Boy Janda',0,0,0,0,0,0,0,12,0,0,0,'2017-06-27 12:17:47',0),(1275,787,'Danilo Lara',0,0,0,0,0,0,0,12,0,0,0,'2017-06-27 12:17:47',0),(1276,788,'Joven Magtana',0,0,0,0,0,0,0,12,0,0,0,'2017-06-27 12:17:47',0),(1277,789,'Aaron Magdagasang',0,0,0,0,0,0,0,3,0,0,0,'2017-06-27 12:17:48',0),(1278,790,'Bryelle Quitoga',0,0,0,0,0,0,0,13,0,0,0,'2017-06-27 12:17:48',0),(1279,791,'Ryan Raborer',0,0,0,0,0,0,0,12,0,0,0,'2017-06-27 12:17:48',0),(1280,792,'Michael Royo',0,0,0,0,0,0,0,12,0,0,0,'2017-06-27 12:17:48',0),(1281,793,'Jiayian Villanueva',0,0,0,0,0,0,0,1,0,0,0,'2017-06-27 12:17:48',0),(1282,794,'John Lord Erpelo',0,0,0,0,0,0,0,12,0,0,0,'2017-06-27 12:17:48',0),(1283,795,'Arlene Dozon',0,0,0,0,0,0,0,13,0,0,0,'2017-06-27 12:17:48',0),(1284,796,'Alfredo Seldo',0,0,0,0,0,0,0,0,0,0,0,'2017-06-27 12:17:48',1),(1285,797,'Christian Gila',0,0,0,0,0,0,0,0,0,0,0,'2017-06-27 12:17:48',1),(1286,798,'Alejandro Arpon',0,0,0,0,0,0,0,0,0,0,0,'2017-06-27 12:17:48',1),(1287,799,'Josephrey Alag',0,0,0,0,0,0,0,0,0,0,0,'2017-06-27 12:17:48',1),(1288,800,'Jeffrey Realino',0,0,0,0,0,0,0,0,0,0,0,'2017-06-27 12:17:48',1),(1289,1,'Sarah Labati',300,1,1,1,500,0,0,-1,0,1,496,'2017-06-27 12:17:48',0),(1290,21231,'Bervyn Co Co',0,0,0,0,0,0,0,0,0,0,0,'2017-06-27 13:21:20',1),(1291,3,'Juan Santos',900,100,300,200,0,0,0,-1,0,1000,-1600,'2017-06-27 13:21:20',0),(1292,4,'Jose  Reyes',800,400,200,300,0,0,0,-1,0,1500,-2400,'2017-06-27 13:21:21',0),(1293,6,'Riche Liberes',500,1,1,1,12,0,0,-1,0,1,8,'2017-06-27 13:21:21',0),(1294,760,'Ñina Angeles',300,1,1,1,0,10,0,13,0,1,3886,'2017-06-27 13:21:21',1),(1295,761,'Joseph Candazo',0,0,0,0,0,0,0,5,0,0,0,'2017-06-27 13:21:21',0),(1296,762,'Ronnie Caravana',0,0,0,0,0,0,0,12,0,0,0,'2017-06-27 13:21:21',0),(1297,763,'Riche Libres',0,0,0,0,0,0,0,13,0,0,0,'2017-06-27 13:21:21',0),(1298,764,'Diane June Mabiog',0,0,0,0,0,0,0,6,0,0,0,'2017-06-27 13:21:21',0),(1299,765,'Sarah Mae Mariño',0,0,0,0,0,0,0,13,0,0,0,'2017-06-27 13:21:21',0),(1300,766,'Carissa Morales',0,0,0,0,0,0,0,13,0,0,0,'2017-06-27 13:21:22',0),(1301,767,'Leo Ocana',0,0,0,0,0,0,0,11,0,0,0,'2017-06-27 13:21:22',0),(1302,768,'Merlinda Orillo',0,0,0,0,0,0,0,13,0,0,0,'2017-06-27 13:21:22',0),(1303,769,'Arlyn Santos',0,0,0,0,0,0,0,12,0,0,0,'2017-06-27 13:21:22',0),(1304,770,'Renalyn Velonza',0,0,0,0,0,0,0,12,0,0,0,'2017-06-27 13:21:22',0),(1305,771,'Jenny Balena',0,0,0,0,0,0,0,5,0,0,0,'2017-06-27 13:21:22',0),(1306,772,'Arlene Jo Mangaliman',0,0,0,0,0,0,0,11,0,0,0,'2017-06-27 13:21:22',0),(1307,773,'Rica May Antonino',0,0,0,0,0,0,0,0,0,0,0,'2017-06-27 13:21:22',1),(1308,774,'Jessa Corre',0,0,0,0,0,0,0,0,0,0,0,'2017-06-27 13:21:23',1),(1309,775,'Chrisette Santuile',0,0,0,0,0,0,0,11,0,0,0,'2017-06-27 13:21:23',0),(1310,776,'Alberto Arpon',0,0,0,0,0,0,0,0,0,0,0,'2017-06-27 13:21:23',1),(1311,777,'Francis Bonifacio',0,0,0,0,0,0,0,11,0,0,0,'2017-06-27 13:21:23',0),(1312,778,'Hero Castillo',0,0,0,0,0,0,0,6,0,0,0,'2017-06-27 13:21:23',0),(1313,779,'Roldan Cruz',0,0,0,0,0,0,0,11,0,0,0,'2017-06-27 13:21:23',0),(1314,780,'April Dela Cruz',0,0,0,0,0,0,0,9,0,0,0,'2017-06-27 13:21:23',0),(1315,781,'Edison Delos Santos',0,0,0,0,0,0,0,13,0,0,0,'2017-06-27 13:21:23',0),(1316,782,'Roderic Garay',0,0,0,0,0,0,0,12,0,0,0,'2017-06-27 13:21:23',0),(1317,783,'Joseph Garin',0,0,0,0,0,0,0,7,0,0,0,'2017-06-27 13:21:23',0),(1318,784,'Rennel Gelongo',0,0,0,0,0,0,0,11,0,0,0,'2017-06-27 13:21:24',0),(1319,785,'GIl Granadil',0,0,0,0,0,0,0,7,0,0,0,'2017-06-27 13:21:24',0),(1320,786,'Sonny Boy Janda',0,0,0,0,0,0,0,12,0,0,0,'2017-06-27 13:21:24',0),(1321,787,'Danilo Lara',0,0,0,0,0,0,0,12,0,0,0,'2017-06-27 13:21:24',0),(1322,788,'Joven Magtana',0,0,0,0,0,0,0,12,0,0,0,'2017-06-27 13:21:24',0),(1323,789,'Aaron Magdagasang',0,0,0,0,0,0,0,3,0,0,0,'2017-06-27 13:21:24',0),(1324,790,'Bryelle Quitoga',0,0,0,0,0,0,0,13,0,0,0,'2017-06-27 13:21:24',0),(1325,791,'Ryan Raborer',0,0,0,0,0,0,0,12,0,0,0,'2017-06-27 13:21:24',0),(1326,792,'Michael Royo',0,0,0,0,0,0,0,12,0,0,0,'2017-06-27 13:21:24',0),(1327,793,'Jiayian Villanueva',0,0,0,0,0,0,0,1,0,0,0,'2017-06-27 13:21:25',0),(1328,794,'John Lord Erpelo',0,0,0,0,0,0,0,12,0,0,0,'2017-06-27 13:21:25',0),(1329,795,'Arlene Dozon',0,0,0,0,0,0,0,13,0,0,0,'2017-06-27 13:21:25',0),(1330,796,'Alfredo Seldo',0,0,0,0,0,0,0,0,0,0,0,'2017-06-27 13:21:25',1),(1331,797,'Christian Gila',0,0,0,0,0,0,0,0,0,0,0,'2017-06-27 13:21:25',1),(1332,798,'Alejandro Arpon',0,0,0,0,0,0,0,0,0,0,0,'2017-06-27 13:21:25',1),(1333,799,'Josephrey Alag',0,0,0,0,0,0,0,0,0,0,0,'2017-06-27 13:21:25',1),(1334,800,'Jeffrey Realino',0,0,0,0,0,0,0,0,0,0,0,'2017-06-27 13:21:25',1),(1335,1,'Sarah Labati',300,1,1,1,500,0,0,-1,0,1,496,'2017-06-27 13:21:25',0),(1336,21231,'Bervyn Co Co',0,0,0,0,17000,6100,8000,-1,0,0,2900,'2017-07-03 14:29:46',0),(1337,3,'Juan Santos',900,100,300,200,0,0,0,-1,0,1000,-1600,'2017-07-03 14:29:46',0),(1338,4,'Jose  Reyes',800,400,200,300,0,0,0,-1,0,1500,-2400,'2017-07-03 14:29:46',0),(1339,6,'Riche Liberes',500,1,1,1,12,0,0,-1,0,1,8,'2017-07-03 14:29:47',0),(1340,760,'Ñina Angeles',300,1,1,1,0,10,0,13,0,1,3886,'2017-07-03 14:29:47',1),(1341,761,'Joseph Candazo',0,0,0,0,0,0,0,5,0,0,0,'2017-07-03 14:29:47',0),(1342,762,'Ronnie Caravana',0,0,0,0,0,0,0,12,0,0,0,'2017-07-03 14:29:47',0),(1343,763,'Riche Libres',0,0,0,0,0,0,0,13,0,0,0,'2017-07-03 14:29:47',0),(1344,764,'Diane June Mabiog',0,0,0,0,0,0,0,6,0,0,0,'2017-07-03 14:29:47',0),(1345,765,'Sarah Mae Mariño',0,0,0,0,0,0,0,13,0,0,0,'2017-07-03 14:29:47',0),(1346,766,'Carissa Morales',0,0,0,0,0,0,0,13,0,0,0,'2017-07-03 14:29:47',0),(1347,767,'Leo Ocana',0,0,0,0,0,0,0,11,0,0,0,'2017-07-03 14:29:47',0),(1348,768,'Merlinda Orillo',0,0,0,0,0,0,0,13,0,0,0,'2017-07-03 14:29:47',0),(1349,769,'Arlyn Santos',0,0,0,0,0,0,0,12,0,0,0,'2017-07-03 14:29:47',0),(1350,770,'Renalyn Velonza',0,0,0,0,0,0,0,12,0,0,0,'2017-07-03 14:29:47',0),(1351,771,'Jenny Balena',0,0,0,0,0,0,0,5,0,0,0,'2017-07-03 14:29:48',0),(1352,772,'Arlene Jo Mangaliman',0,0,0,0,0,0,0,11,0,0,0,'2017-07-03 14:29:48',0),(1353,773,'Rica May Antonino',0,0,0,0,0,0,0,1,0,0,0,'2017-07-03 14:29:48',0),(1354,774,'Jessa Corre',0,0,0,0,0,0,0,13,0,0,0,'2017-07-03 14:29:48',0),(1355,775,'Chrisette Santuile',0,0,0,0,0,0,0,11,0,0,0,'2017-07-03 14:29:48',0),(1356,776,'Alberto Arpon',0,0,0,0,0,0,0,2,0,0,0,'2017-07-03 14:29:48',0),(1357,777,'Francis Bonifacio',0,0,0,0,0,0,0,11,0,0,0,'2017-07-03 14:29:48',0),(1358,778,'Hero Castillo',0,0,0,0,0,0,0,6,0,0,0,'2017-07-03 14:29:48',0),(1359,779,'Roldan Cruz',0,0,0,0,0,0,0,11,0,0,0,'2017-07-03 14:29:48',0),(1360,780,'April Dela Cruz',0,0,0,0,0,0,0,9,0,0,0,'2017-07-03 14:29:48',0),(1361,781,'Edison Delos Santos',0,0,0,0,0,0,0,13,0,0,0,'2017-07-03 14:29:48',0),(1362,782,'Roderic Garay',0,0,0,0,0,0,0,12,0,0,0,'2017-07-03 14:29:48',0),(1363,783,'Joseph Garin',0,0,0,0,0,0,0,7,0,0,0,'2017-07-03 14:29:48',0),(1364,784,'Rennel Gelongo',0,0,0,0,0,0,0,11,0,0,0,'2017-07-03 14:29:49',0),(1365,785,'GIl Granadil',0,0,0,0,0,0,0,7,0,0,0,'2017-07-03 14:29:49',0),(1366,786,'Sonny Boy Janda',0,0,0,0,0,0,0,12,0,0,0,'2017-07-03 14:29:49',0),(1367,787,'Danilo Lara',0,0,0,0,0,0,0,12,0,0,0,'2017-07-03 14:29:49',0),(1368,788,'Joven Magtana',0,0,0,0,0,0,0,12,0,0,0,'2017-07-03 14:29:49',0),(1369,789,'Aaron Magdagasang',0,0,0,0,0,0,0,3,0,0,0,'2017-07-03 14:29:49',0),(1370,790,'Bryelle Quitoga',0,0,0,0,0,0,0,13,0,0,0,'2017-07-03 14:29:49',0),(1371,791,'Ryan Raborer',0,0,0,0,0,0,0,12,0,0,0,'2017-07-03 14:29:49',0),(1372,792,'Michael Royo',0,0,0,0,0,0,0,12,0,0,0,'2017-07-03 14:29:49',0),(1373,793,'Jiayian Villanueva',0,0,0,0,0,0,0,1,0,0,0,'2017-07-03 14:29:49',0),(1374,794,'John Lord Erpelo',0,0,0,0,0,0,0,12,0,0,0,'2017-07-03 14:29:49',0),(1375,795,'Arlene Dozon',0,0,0,0,0,0,0,13,0,0,0,'2017-07-03 14:29:49',0),(1376,796,'Alfredo Seldo',0,0,0,0,0,0,0,3,0,0,0,'2017-07-03 14:29:49',0),(1377,797,'Christian Gila',0,0,0,0,0,0,0,11,0,0,0,'2017-07-03 14:29:49',0),(1378,798,'Alejandro Arpon',0,0,0,0,0,0,0,12,0,0,0,'2017-07-03 14:29:50',0),(1379,799,'Josephrey Alag',0,0,0,0,0,0,0,13,0,0,0,'2017-07-03 14:29:50',0),(1380,800,'Jeffrey Realino',0,0,0,0,0,0,0,13,0,0,0,'2017-07-03 14:29:50',0),(1381,1,'Sarah Labati',300,1,1,1,500,0,0,-1,0,1,496,'2017-07-03 14:29:50',0);
/*!40000 ALTER TABLE `payroll_history` ENABLE KEYS */;
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