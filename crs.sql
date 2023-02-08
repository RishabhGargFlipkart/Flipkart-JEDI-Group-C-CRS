-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (x86_64)
--
-- Host: localhost    Database: crs_database
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `userid` varchar(45) NOT NULL,
  PRIMARY KEY (`userid`),
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('Admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card` (
  `refId` int NOT NULL,
  `cardNo` varchar(45) NOT NULL,
  `cardType` varchar(45) NOT NULL,
  `cvv` int NOT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`refId`),
  CONSTRAINT `refCard` FOREIGN KEY (`refId`) REFERENCES `payment` (`refId`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (9,'123','debit',123,NULL),(23,'12345','Credit',324,NULL),(27,'1233','credit',123,NULL),(29,'1234567890123456','credit',123,'2024-06-01 00:00:00');
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cheque`
--

DROP TABLE IF EXISTS `cheque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cheque` (
  `refId` int NOT NULL,
  `chequeNo` int NOT NULL,
  PRIMARY KEY (`refId`),
  CONSTRAINT `referenceId` FOREIGN KEY (`refId`) REFERENCES `payment` (`refId`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cheque`
--

LOCK TABLES `cheque` WRITE;
/*!40000 ALTER TABLE `cheque` DISABLE KEYS */;
INSERT INTO `cheque` VALUES (0,12324),(18,23421453);
/*!40000 ALTER TABLE `cheque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `courseCode` varchar(45) NOT NULL,
  `courseName` varchar(45) NOT NULL,
  `profId` varchar(45) DEFAULT NULL,
  `seats` int NOT NULL,
  `fee` decimal(10,2) NOT NULL,
  PRIMARY KEY (`courseCode`),
  KEY `profIdCourse` (`profId`),
  CONSTRAINT `profIdCourse` FOREIGN KEY (`profId`) REFERENCES `professor` (`profId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('1','DSA','5',7,100.00),('2','OS','5',7,110.00),('3','CN','6',7,120.00),('4','DBMS','5',9,100.00),('5','MUP','5',7,10.00),('6','DAA','5',9,150.00),('7','SD','5',8,200.00),('8','Testing','5',8,100.00);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `netbanking`
--

DROP TABLE IF EXISTS `netbanking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `netbanking` (
  `refId` int NOT NULL,
  `accountNo` int NOT NULL,
  `IFSC` varchar(45) NOT NULL,
  PRIMARY KEY (`refId`),
  CONSTRAINT `refNet` FOREIGN KEY (`refId`) REFERENCES `payment` (`refId`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `netbanking`
--

LOCK TABLES `netbanking` WRITE;
/*!40000 ALTER TABLE `netbanking` DISABLE KEYS */;
INSERT INTO `netbanking` VALUES (7,5234235,'1241353'),(13,123441,'f343qw');
/*!40000 ALTER TABLE `netbanking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `notificationId` int NOT NULL,
  `message` varchar(100) NOT NULL,
  `refId` int NOT NULL,
  PRIMARY KEY (`notificationId`),
  KEY `refId_idx` (`refId`),
  CONSTRAINT `refId` FOREIGN KEY (`refId`) REFERENCES `payment` (`refId`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (0,'Payment Successful',27),(15,'Payment Successful',9),(17,'Payment Successful',7),(20,'Payment Successful',3),(23,'Payment Successful',13),(25,'Payment Successful',17),(27,'Payment Successful',23),(29,'Payment Successful',29);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `refId` int NOT NULL,
  `studentId` varchar(45) NOT NULL,
  `amount` varchar(45) NOT NULL,
  `typeOfPayment` varchar(45) NOT NULL,
  `bank` varchar(45) NOT NULL,
  PRIMARY KEY (`refId`),
  KEY `studentNotification_idx` (`studentId`),
  CONSTRAINT `studentNotification` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (0,'123','610.0','CHEQUE','sbi'),(3,'s1','640.0','CARD','sbi'),(4,'123','610.0','CHEQUE','sbi'),(7,'123','610.0','NET_BANKING','SBI'),(9,'123','590.0','CARD','sbi'),(13,'123','610.0','NET_BANKING','SBI'),(17,'S1','640.0','UPI','SBI'),(18,'123','610.0','CHEQUE','sbi'),(23,'123','610.0','CARD','SBI'),(27,'S1','640.0','CARD','sbi'),(29,'S1','640.0','CARD','sbi');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professor` (
  `profId` varchar(45) NOT NULL,
  `department` varchar(45) NOT NULL,
  PRIMARY KEY (`profId`),
  CONSTRAINT `profId` FOREIGN KEY (`profId`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES ('5','cs'),('6','CS');
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `roles` varchar(45) NOT NULL,
  PRIMARY KEY (`roles`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('Admin'),('Professor'),('Student');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semregistration`
--

DROP TABLE IF EXISTS `semregistration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semregistration` (
  `studentId` varchar(45) NOT NULL,
  `courseCode` varchar(45) NOT NULL,
  `grade` varchar(45) DEFAULT NULL,
  `semester` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`studentId`,`courseCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semregistration`
--

LOCK TABLES `semregistration` WRITE;
/*!40000 ALTER TABLE `semregistration` DISABLE KEYS */;
INSERT INTO `semregistration` VALUES ('123','1','NA','1'),('123','2','NA','1'),('123','3','NA','1'),('123','4','NA','1'),('123','5','NA','1'),('123','6','NA','1'),('S1','1','NA','1'),('S1','2','A','1'),('S1','3','NA','1'),('S1','5','NA','1'),('S1','7','NA','1'),('S1','8','NA','1');
/*!40000 ALTER TABLE `semregistration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `studentId` varchar(45) NOT NULL,
  `batch` int NOT NULL,
  `branchName` varchar(45) NOT NULL,
  `loginApproved` tinyint(1) NOT NULL,
  `gradeCardApproved` tinyint(1) NOT NULL,
  `regApproved` tinyint(1) NOT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `isPaid` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`studentId`),
  CONSTRAINT `studentId` FOREIGN KEY (`studentId`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('123',2019,'ECE',1,0,0,'Male','NA',0),('234',2019,'ECE',1,1,0,'Male','NAD',0),('S1',2019,'CS',1,1,1,'Male','INDIA',1);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upi`
--

DROP TABLE IF EXISTS `upi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `upi` (
  `refid` int NOT NULL,
  `upiId` varchar(45) NOT NULL,
  `serviceProvider` varchar(45) NOT NULL,
  PRIMARY KEY (`refid`),
  CONSTRAINT `RefUPI` FOREIGN KEY (`refid`) REFERENCES `payment` (`refId`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upi`
--

LOCK TABLES `upi` WRITE;
/*!40000 ALTER TABLE `upi` DISABLE KEYS */;
INSERT INTO `upi` VALUES (4,'1234','gpay'),(17,'3456V','GPAY');
/*!40000 ALTER TABLE `upi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userid` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`userid`),
  KEY `role_idx` (`role`),
  CONSTRAINT `role` FOREIGN KEY (`role`) REFERENCES `roles` (`roles`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('123','Rishabh','Student','123'),('234','Rishabh','Student','123'),('5','Bruh','Professor','123'),('6','Hello','professor','123'),('Admin','Admin','Admin','Admin123'),('S1','PRANEET','Student','PRANEET123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-08 17:19:47
