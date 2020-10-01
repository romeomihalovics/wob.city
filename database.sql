-- MySQL dump 10.13  Distrib 8.0.21, for Linux (x86_64)
--
-- Host: localhost    Database: city
-- ------------------------------------------------------
-- Server version	8.0.21-0ubuntu0.20.04.4

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
-- Table structure for table `available_food`
--

DROP TABLE IF EXISTS `available_food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `available_food` (
  `id` int NOT NULL AUTO_INCREMENT,
  `food_name` text NOT NULL,
  `amount` double NOT NULL,
  `city` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_available_food_food_name` (`food_name`(100)),
  KEY `idx_available_food_amount` (`amount`),
  KEY `idx_available_food_food_name_amount` (`food_name`(100),`amount`),
  KEY `idx_available_food_city` (`city`(100)),
  KEY `idx_available_food_food_name_amount_city` (`food_name`(100),`amount`,`city`(100))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `consumption_news`
--

DROP TABLE IF EXISTS `consumption_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consumption_news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `city` text NOT NULL,
  `type` text NOT NULL,
  `amount` double NOT NULL,
  `reported` tinyint DEFAULT '0',
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_consumption_news_location` (`city`(100)),
  KEY `idx_consumption_news_reported` (`reported`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `disaster_history`
--

DROP TABLE IF EXISTS `disaster_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `disaster_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `city` text NOT NULL,
  `type` text NOT NULL,
  `destroyed_buildings` int NOT NULL,
  `died_families` int NOT NULL,
  `died_people` int NOT NULL,
  `date` datetime NOT NULL,
  `event` text NOT NULL,
  `reported` tinyint DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_disaster_history_city` (`city`(100)),
  KEY `idx_disaster_history_type` (`type`(100)),
  KEY `idx_disaster_history_reported` (`reported`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `person_history`
--

DROP TABLE IF EXISTS `person_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `city` text NOT NULL,
  `full_name` text NOT NULL,
  `event` text NOT NULL,
  `date` datetime NOT NULL,
  `reported` tinyint DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_person_history_fullname` (`full_name`(100)),
  KEY `idx_person_history_city` (`city`(100)),
  KEY `idx_person_history_reported` (`reported`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `person_news`
--

DROP TABLE IF EXISTS `person_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person_news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category` text NOT NULL,
  `type` text NOT NULL,
  `full_name` text NOT NULL,
  `age` int NOT NULL,
  `weight` int NOT NULL,
  `height` int NOT NULL,
  `city` text NOT NULL,
  `energy` text NOT NULL,
  `last_food` text,
  `reported` tinyint DEFAULT '0',
  `involved_person` text,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_person_news_reported` (`reported`),
  KEY `idx_person_news_location` (`city`(100)),
  KEY `idx_person_news_category` (`category`(100)),
  KEY `idx_person_news_involved_person` (`involved_person`(100))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `record_temperature`
--

DROP TABLE IF EXISTS `record_temperature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `record_temperature` (
  `id` int NOT NULL AUTO_INCREMENT,
  `city` text NOT NULL,
  `type` text NOT NULL,
  `temperature` double NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temperature_report`
--

DROP TABLE IF EXISTS `temperature_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `temperature_report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `city` text NOT NULL,
  `part_of_day` text NOT NULL,
  `temperature` double NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-01 16:45:01
