-- MySQL dump 10.13  Distrib 5.7.32, for Win64 (x86_64)
--
-- Host: localhost    Database: classroom
-- ------------------------------------------------------
-- Server version	5.7.32-log

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
-- Table structure for table `building`
--

DROP TABLE IF EXISTS `building`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `building` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `full_name` varchar(50) NOT NULL COMMENT 'fullName',
  `alias_name` varchar(50) NOT NULL COMMENT 'aliasName',
  `department` varchar(50) NOT NULL COMMENT 'department',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='building';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `building`
--

LOCK TABLES `building` WRITE;
/*!40000 ALTER TABLE `building` DISABLE KEYS */;
INSERT INTO `building` VALUES (1,'博学楼','1号楼','教学主楼'),(2,'明德楼','2号楼','教学主楼'),(3,'博观楼','3号楼','土木，化工'),(4,'明信楼','4号楼','管理，机械'),(5,'博识楼','5号楼','软件，经法，外语'),(6,'明贤楼','6号楼','材冶，理学'),(7,'博采楼','7号楼','矿业'),(8,'博爱楼','9号楼','后勤，矿业'),(9,'博闻楼','11号楼','电信'),(10,'博艺楼','13号楼','艺术'),(11,'明睿楼','14号楼','研究生院'),(12,'明远楼','22号楼','高镁'),(13,'明礼楼','26号楼','东体育场东侧教学楼'),(14,'博智楼','27号楼','新世纪学校'),(15,'博远楼','29号楼','国际教育学校');
/*!40000 ALTER TABLE `building` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classroom` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT 'name',
  `capacity` int(11) NOT NULL COMMENT 'capacity',
  `building_id` int(11) NOT NULL COMMENT 'buildingId',
  `status` tinyint(3) DEFAULT '0' COMMENT 'status',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COMMENT='classroom';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` VALUES (1,'A101',200,1,1),(2,'A102',40,1,1),(3,'A103',50,1,0),(4,'A104',40,1,1),(5,'A105',40,1,1),(6,'A106',40,1,0),(7,'A107',40,1,0),(8,'A108',40,1,1),(9,'C211',40,2,1),(10,'C212',40,2,0),(11,'C213',40,2,1),(12,'C214',40,2,0),(13,'B421',50,5,0),(14,'B523',60,5,1),(15,'B622',120,5,1),(16,'B429',120,5,1),(17,'B529',120,5,0),(18,'B336',70,5,NULL);
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) NOT NULL COMMENT 'username',
  `password` varchar(100) NOT NULL COMMENT 'password',
  `avatar` varchar(100) NOT NULL COMMENT 'avatar',
  `nickname` varchar(50) NOT NULL COMMENT 'nickname',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='user';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','202cb962ac59075b964b07152d234b70','https://cdn.jsdelivr.net/gh/MaoLongLong/maolonglong.github.io/.vuepress/public/avatar.png','毛珑珑'),(2,'test','202cb962ac59075b964b07152d234b70','https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1973154360,2111956714&fm=26&gp=0.jpg','测试用户1'),(3,'root','202cb962ac59075b964b07152d234b70','https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1882351849,4029075716&fm=26&gp=0.jpg','测试用户2'),(4,'abc','202cb962ac59075b964b07152d234b70','https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2065688693,3914254592&fm=26&gp=0.jpg','测试用户3'),(5,'def','202cb962ac59075b964b07152d234b70','https://dss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1833258246,3430836162&fm=26&gp=0.jpg','测试用户4'),(6,'ghi','202cb962ac59075b964b07152d234b70','https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1719455770,2791752741&fm=26&gp=0.jpg','测试用户5');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'classroom'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-04 21:29:51
