-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: trace
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `aid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `rid` int NOT NULL,
  `del` smallint NOT NULL DEFAULT '0',
  `ban` smallint NOT NULL DEFAULT '0',
  `memo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  PRIMARY KEY (`aid`) USING BTREE,
  KEY `rid` (`rid`) USING BTREE,
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'admin','0aeef548aa12f11f8ab83facc27a8179',1,0,0,NULL),(3,'test1','0b3f05baf34ee47287713733c47fd426',10,0,0,NULL),(4,'test2','0b3f05baf34ee47287713733c47fd426',9,0,0,NULL),(5,'test3','0b3f05baf34ee47287713733c47fd426',8,0,0,NULL),(6,'debugger','0aeef548aa12f11f8ab83facc27a8179',1,0,0,'接口文档使用的用户，不要删除');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_info`
--

DROP TABLE IF EXISTS `account_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_info` (
  `aid` int NOT NULL,
  `eid` int DEFAULT NULL,
  `rid` int NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gander` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tel` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'default.png',
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `address` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `zip_code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`aid`) USING BTREE,
  KEY `rid` (`rid`) USING BTREE,
  KEY `eid` (`eid`) USING BTREE,
  CONSTRAINT `account_info_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `account_info_ibfk_2` FOREIGN KEY (`eid`) REFERENCES `enterprise` (`eid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `account_info_ibfk_3` FOREIGN KEY (`aid`) REFERENCES `account` (`aid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_info`
--

LOCK TABLES `account_info` WRITE;
/*!40000 ALTER TABLE `account_info` DISABLE KEYS */;
INSERT INTO `account_info` VALUES (1,1,1,'SSymbol','男','13612345611','admin.jpg','1277820742@qq.com','黑龙江省佳木斯市','100861'),(3,1,10,'Jon Stewart Doe','女','60195213251','18b7e2ad-8b3c-4f88-b9e4-4d68410453bb_user1.png','test@example.us','黑龙江省佳木斯市','94043'),(4,1,9,'qwe','女','12345678901','bf7db45d-72b6-4dc0-b1da-d2720ab28f28_89f5d904-d2d0-4889-a096-91ac35165375_user6.jpg','test@mail.com','address','123112'),(5,1,8,'qwerty','男','12345678901','e3564293-aafc-43f4-b004-7bcb178d89fb_user5.JPG','test@test.com','address','131536'),(6,1,1,'debugger','男','10086100861','default.png','test@test.com','address','10086');
/*!40000 ALTER TABLE `account_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_operate`
--

DROP TABLE IF EXISTS `account_operate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_operate` (
  `iid` int NOT NULL AUTO_INCREMENT,
  `oid` int NOT NULL,
  `aid` int NOT NULL,
  `operate` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `operate_time` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`iid`) USING BTREE,
  KEY `oid` (`oid`) USING BTREE,
  KEY `aid` (`aid`) USING BTREE,
  CONSTRAINT `account_operate_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `account` (`aid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `account_operate_ibfk_2` FOREIGN KEY (`aid`) REFERENCES `account` (`aid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_operate`
--

LOCK TABLES `account_operate` WRITE;
/*!40000 ALTER TABLE `account_operate` DISABLE KEYS */;
INSERT INTO `account_operate` VALUES (3,1,3,'修改账号状态','2024-07-26 10:41:14'),(4,1,1,'创建账号失败','2024-07-26 11:17:28'),(5,1,4,'创建账号','2024-07-26 11:17:34'),(6,1,1,'创建账号失败','2024-07-26 11:24:40'),(7,1,5,'创建账号','2024-07-26 11:24:46'),(8,1,4,'修改账号信息','2024-07-26 01:38:54'),(9,1,4,'修改账号状态','2024-07-26 01:39:03'),(10,1,4,'修改账号状态','2024-07-26 13:39:12'),(11,1,4,'修改账号信息','2024-07-26 13:39:38'),(12,1,4,'修改账号信息','2024-07-26 13:42:05'),(13,1,1,'修改账号信息','2024-07-26 13:50:26'),(14,1,5,'修改账号状态','2024-07-26 15:29:44'),(15,1,5,'修改账号状态','2024-07-26 15:29:56'),(16,1,4,'请求解码密码','2024-07-26 18:03:08'),(17,1,4,'请求解码密码','2024-07-26 18:03:20'),(18,1,3,'修改账号状态','2024-07-27 08:17:13'),(19,1,3,'修改账号状态','2024-07-27 08:17:24'),(20,1,3,'请求解码密码','2024-07-27 08:17:36'),(21,1,3,'请求解码密码','2024-07-27 08:17:41'),(22,1,3,'请求解码密码','2024-07-27 08:17:52'),(23,1,3,'请求解码密码','2024-07-27 08:18:09'),(24,1,3,'修改账号信息','2024-07-27 08:18:37'),(25,1,1,'禁用所有账户','2024-07-29 10:18:27'),(26,1,1,'禁用所有账户','2024-07-29 10:19:22'),(27,1,1,'禁用所有账户','2024-07-29 10:20:25'),(28,1,3,'请求解码密码','2024-07-29 10:35:44'),(29,1,3,'请求解码密码','2024-07-29 10:35:53'),(30,1,3,'修改账号信息','2024-07-29 11:02:02'),(31,1,1,'禁用所有账户','2024-07-29 11:02:09'),(32,1,4,'修改账号信息','2024-07-29 11:02:22'),(33,1,5,'修改账号信息','2024-07-29 11:02:28'),(34,1,1,'禁用所有账户','2024-07-29 11:02:36'),(35,1,1,'启用所有账户','2024-07-29 11:02:46'),(36,1,4,'修改账号信息','2024-07-29 17:42:04'),(37,1,3,'修改账号信息','2024-07-29 17:42:09'),(38,1,5,'修改账号信息','2024-07-29 17:42:13'),(39,1,5,'修改账号信息','2024-07-30 09:01:17'),(40,1,4,'修改账号信息','2024-07-30 09:02:05'),(41,1,3,'修改账号信息','2024-07-30 09:02:52'),(42,1,5,'修改账号状态','2024-07-30 10:24:16'),(43,1,5,'修改账号信息','2024-07-30 10:24:38'),(44,1,5,'修改账号状态','2024-07-30 10:24:45'),(45,1,5,'修改账号信息','2024-07-30 10:24:49'),(46,1,5,'修改账号状态','2024-07-30 10:27:48'),(47,1,5,'修改账号状态','2024-07-30 10:31:30'),(48,1,5,'修改账号信息','2024-07-30 10:37:59'),(49,1,5,'修改账号状态','2024-07-30 10:38:23'),(50,1,1,'禁用所有账户','2024-07-30 12:55:27'),(51,1,1,'启用所有账户','2024-07-30 12:55:39'),(52,1,3,'修改账号状态','2024-07-30 13:08:54'),(53,1,4,'修改账号状态','2024-07-30 13:08:59'),(54,1,5,'修改账号状态','2024-07-30 13:09:02'),(55,1,3,'修改账号信息','2024-07-30 13:09:08'),(56,1,1,'启用所有账户','2024-07-30 13:13:32'),(57,1,1,'启用所有账户','2024-07-30 13:33:55'),(58,1,1,'启用所有账户','2024-07-30 13:34:26'),(59,1,5,'修改账号状态','2024-08-04 11:24:47'),(60,1,5,'修改账号状态','2024-08-04 11:25:04'),(61,1,1,'修改账号信息','2024-08-06 13:19:51'),(62,1,6,'创建账号','2024-11-21 17:01:54'),(63,6,6,'修改账号状态','2024-11-22 14:18:33'),(64,6,6,'修改账号状态','2024-11-22 14:18:37'),(65,6,6,'修改账号状态','2024-11-22 14:18:39'),(66,6,6,'修改账号状态','2024-11-22 14:18:40'),(67,6,6,'修改账号状态','2024-11-22 14:18:41'),(68,6,6,'修改账号状态','2024-11-22 14:20:53'),(69,6,6,'修改账号状态','2024-11-22 14:20:54'),(70,6,6,'修改账号状态','2024-11-22 14:20:59'),(71,6,6,'修改账号状态','2024-11-22 14:21:01'),(72,6,6,'修改账号状态','2024-11-22 14:21:17'),(73,6,6,'修改账号状态','2024-11-22 14:21:59');
/*!40000 ALTER TABLE `account_operate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `approach`
--

DROP TABLE IF EXISTS `approach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `approach` (
  `aid` int NOT NULL AUTO_INCREMENT,
  `eid` int NOT NULL,
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `code` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `batch` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `num` int NOT NULL,
  `unit` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `trace` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cid` int NOT NULL,
  `business_time` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`aid`) USING BTREE,
  KEY `eid` (`eid`) USING BTREE,
  KEY `cid` (`cid`) USING BTREE,
  CONSTRAINT `approach_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `enterprise` (`eid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `approach_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `classification` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `approach`
--

LOCK TABLES `approach` WRITE;
/*!40000 ALTER TABLE `approach` DISABLE KEYS */;
INSERT INTO `approach` VALUES (7,52,'新鲜草莓礼盒','FS001','bt0001',50,'件','Trace-66b9a5a9ad661bcc2db6d95e',5,'2024-08-12 22:19:45'),(8,53,'精选牛肉块','MB003','bt0002',50,'件','Trace-66b9a5a9ad661bcc2db6d95f',5,'2024-08-12 22:19:45'),(9,54,'野生蓝莓200g','WB004','bt0003',50,'件','Trace-66b9a5a9ad661bcc2db6d960',5,'2024-08-12 22:19:45'),(10,55,'农家土鸡蛋12枚','FJ005','bt0004',50,'件','Trace-66b9a5a9ad661bcc2db6d961',5,'2024-08-12 22:19:45'),(11,56,'鲜榨橙汁1L','OJ006','bt0005',50,'件','Trace-66b9a5a9ad661bcc2db6d962',5,'2024-08-12 22:19:45'),(12,57,'深海鳕鱼片','CF007','bt0006',50,'件','Trace-66b9a5a9ad661bcc2db6d963',5,'2024-08-12 22:19:45');
/*!40000 ALTER TABLE `approach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classification`
--

DROP TABLE IF EXISTS `classification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classification` (
  `cid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `parent` int NOT NULL DEFAULT '0',
  `memo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `del` smallint NOT NULL DEFAULT '0',
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classification`
--

LOCK TABLES `classification` WRITE;
/*!40000 ALTER TABLE `classification` DISABLE KEYS */;
INSERT INTO `classification` VALUES (1,'生鲜食品',0,NULL,0),(2,'加工食品',0,NULL,0),(3,'饮料与酒水',0,NULL,0),(4,'食品杂货',0,NULL,0),(5,'水果与蔬菜',1,NULL,0),(6,'肉类与禽类',1,NULL,0),(7,'海鲜与水产',1,NULL,0),(8,'罐头食品',2,NULL,0),(9,'冷冻食品',2,NULL,0),(10,'糖果与巧克力',2,NULL,0),(11,'碳酸饮料',3,NULL,0),(12,'果汁与饮料',3,NULL,0),(13,'咖啡与茶类',3,NULL,0),(14,'酒类',3,NULL,0),(15,'面食与米面类',4,NULL,0),(16,'调味品与调料',4,NULL,0),(17,'油脂与沙拉酱',4,NULL,0),(18,'食品添加剂与调理品',4,NULL,0);
/*!40000 ALTER TABLE `classification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enterprise`
--

DROP TABLE IF EXISTS `enterprise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enterprise` (
  `eid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `legal_person` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tel` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `social_code` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `zip_code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `del` int NOT NULL DEFAULT '0',
  `type` int NOT NULL,
  PRIMARY KEY (`eid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=353 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enterprise`
--

LOCK TABLES `enterprise` WRITE;
/*!40000 ALTER TABLE `enterprise` DISABLE KEYS */;
INSERT INTO `enterprise` VALUES (1,'无所属公司','无法人','无','无','无','无',0,0),(52,'Sem Egestas Blandit LLC','Dong','002-334-4915','4936 9364 5699 8489','安徽省 合肥市 庐阳区长江中路 庐阳佳 123号','528286',0,7),(53,'Mauris Sapien Consulting','Qiu','035-354-3217','675925787857444288','北京市 朝阳区 酒仙桥路 朝阳新城 456号','841998',0,5),(54,'Blandit Nam Associates','Quon','027-113-5356','4905592532713935361','福建省 厦门市 思明区 厦禾路 思明花园 789号','352662',0,3),(55,'Neque Pellentesque Foundation','Biming','011-964-9518','6706 7642 3555 7829','甘肃省 兰州市 城关区 天水路 兰州大厦 101号','366255',0,1),(56,'Penatibus Et Inc.','Hui','064-166-1462','676722 2782442891','广东省 广州市 天河区 体育西路 天河城广场 202号','268340',0,2),(57,'Donec Nibh LLC','Hop','028-227-4862','402625 8639387717','贵州省 贵阳市 云岩区 中华北路 金筑大厦 303号','682238',0,4),(58,'Auctor Non LLC','Qing','024-886-2631','514451 4363527249','海南省 海口市 龙华区 海秀中路 龙华商城 404号','135492',0,6),(59,'Urna Et Incorporated','Jinhai','086-858-8627','3654 484161 54755','河北省 石家庄市 长安区 中山东路 长安花园 505号','775267',0,0),(60,'Diam Sed Diam LLC','Cheng','054-253-9084','4911 3744 7392 3257','河南省 郑州市 金水区 花园路 金水花园 606号','868668',0,0),(61,'Lobortis Tellus Industries','Shoi-ming','015-159-8323','4532 2295 5215 1163','黑龙江省 哈尔滨市 南岗区 东大直街 南岗大厦 707号','771721',0,0),(62,'Fringilla Purus Institute','Biming','054-672-5120','6333466121371999','湖北省 武汉市 江汉区 建设大道 江汉新城 808号','824262',0,0),(63,'Eu Tempor LLP','Chun','068-709-7674','4539856987287','湖南省 长沙市 岳麓区 岳麓大道 岳麓山庄 909号','635136',0,0),(64,'Scelerisque Neque Ltd','Mingyu','063-366-2602','6767595755492393','吉林省 长春市 南关区 人民大街 长春大厦 1010号','564273',0,0),(65,'Egestas Consulting','Hao','055-572-3882','450833 7256942553','江苏省 南京市 玄武区 中央路 玄武花园 1111号','754428',0,0),(66,'Lobortis LLC','Ping','095-251-2774','3046 473665 38628','江西省 南昌市 西湖区 井冈山大道 西湖家园 1212号','626655',0,0),(67,'Lorem Tristique Corp.','Sying','048-686-4234','6574975348825734','辽宁省 大连市 甘井子区 华北路 甘井子广场 1313号','822742',0,0),(68,'Risus Nulla Eget LLC','Lei','082-645-3416','2014 382658 89153','内蒙古自治区 呼和浩特市 新城区 新华大街 新城花园 1414号','325747',0,0),(69,'Quis Pede Associates','Chao','043-738-8734','6304825557552283667','宁夏回族自治区 银川市 兴庆区 丽景北街 银川大厦 1515号','951415',0,0),(70,'Mauris Rhoncus Id Company','Biming','061-471-4673','6767493616869621','青海省 西宁市 城西区 五四大街 城西广场 1616号','362904',0,0),(71,'Auctor Quis Tristique Limited','Zihao','071-943-0255','4929 6938 3797 3520','山东省 青岛市 市南区 香港西路 青岛花园 1717号','478454',0,0),(72,'Phasellus Elit Pede Consulting','Bai','046-476-8327','4936376586761457675','山西省 太原市 小店区 长风街 小店广场 1818号','684273',0,0),(73,'Accumsan Neque Inc.','Li qiang','001-384-1762','67596228222779437','陕西省 西安市 雁塔区 小寨东路 雁塔花园 1919号','754702',0,0),(74,'Mauris Eu Corporation','Wang wei','091-641-6845','4916244457483443','上海市 徐汇区 漕溪北路 徐汇嘉园 2020号','251560',0,0),(75,'Sit Amet PC','Jig','083-073-8526','445455 465232 8773','四川省 成都市 青羊区 金沙江路 成都花园 2121号','676337',0,0),(76,'Integer Mollis Integer Institute','Tung','023-815-1764','5833 772456 47448','天津市 河西区 大沽南路 河西广场 2222号','277253',0,0),(77,'Fermentum Fermentum Arcu Company','Kang','067-382-1493','491 12625 25371 866','黑龙江省 哈尔滨市 南岗区 东大直街 南岗大厦 707号','317429',0,0),(78,'Dui Semper Consulting','Bo','037-107-6398','644 38247 98483 785','山东省 青岛市 市南区 香港西路 青岛花园 1717号','261547',0,0),(79,'Dui Quis Accumsan Limited','Wang wei','064-649-6853','534774 562591 1277','江苏省 南京市 玄武区 中央路 玄武花园 1111号','437488',0,0),(80,'Proin LLC','Guotin','073-783-6274','448573 6368622234','辽宁省 大连市 甘井子区 华北路 甘井子广场 1313号','303847',0,0),(81,'Lectus Corporation','Yuanjun','097-555-1548','633354285273528254','河南省 郑州市 金水区 花园路 金水花园 606号','584981',0,0),(82,'Integer Incorporated','Ye','026-743-7117','647897 1735223226','贵州省 贵阳市 云岩区 中华北路 金筑大厦 303号','419668',0,0),(83,'Sem Pellentesque Associates','Yongrui','012-584-5226','2014 888166 83621','甘肃省 兰州市 城关区 天水路 兰州大厦 101号','470952',0,0),(84,'Cursus Integer Associates','Li jun','039-333-5294','6463826848187725','甘肃省 兰州市 城关区 天水路 兰州大厦 101号','325609',0,0),(85,'Posuere At Limited','Guotin','024-055-6237','6334744947957327','吉林省 长春市 南关区 人民大街 长春大厦 1010号','771341',0,0),(86,'Eget Varius LLC','Xin','026-819-8367','4844779856656716','青海省 西宁市 城西区 五四大街 城西广场 1616号','575317',0,0),(87,'Eu Consulting','Ying','063-145-5245','548685 7382865124','北京市 朝阳区 酒仙桥路 朝阳新城 456号','748753',0,0),(88,'Lacinia Vitae Foundation','Da','088-653-8261','6331108558169577','内蒙古自治区 呼和浩特市 新城区 新华大街 新城花园 1414号','249907',0,0),(89,'Semper Et Ltd','Sying','027-153-5231','4929 638 55 7724','山东省 青岛市 市南区 香港西路 青岛花园 1717号','703109',0,0),(90,'Faucibus Orci Luctus Associates','Kang','011-137-8373','448569 327469 5277','宁夏回族自治区 银川市 兴庆区 丽景北街 银川大厦 1515号','770353',0,0),(91,'Lorem Eu Corporation','Wang yong','011-121-7381','402400 7177694881','江苏省 南京市 玄武区 中央路 玄武花园 1111号','326793',0,0),(92,'Tristique Neque LLC','Wang lei','056-357-9699','2014 226453 37133','甘肃省 兰州市 城关区 天水路 兰州大厦 101号','315372',0,0),(93,'Orci Lobortis Augue Industries','Wang yong','058-276-5880','363577535353856','湖南省 长沙市 岳麓区 岳麓大道 岳麓山庄 909号','677695',0,0),(94,'Magna Tellus Faucibus Industries','Fu','077-668-4464','6333768485313420','吉林省 长春市 南关区 人民大街 长春大厦 1010号','936718',0,0),(95,'Enim Condimentum Eget Associates','Kang','045-187-2405','4936 1381 8539 8517','山东省 青岛市 市南区 香港西路 青岛花园 1717号','876924',0,0),(96,'Ac Tellus Corp.','Qiu','014-616-1565','493655 479466 9694','上海市 徐汇区 漕溪北路 徐汇嘉园 2020号','171086',0,0),(97,'Donec Ltd','Lie jie','054-415-3128','670626292563484523','吉林省 长春市 南关区 人民大街 长春大厦 1010号','769430',0,0),(98,'Tempor Diam Incorporated','Wang lei','011-808-6446','646 84772 62424 429','四川省 成都市 青羊区 金沙江路 成都花园 2121号','779077',0,0),(99,'Parturient Montes Consulting','Boqin','044-228-3587','503877 9989468149','安徽省合肥市庐阳区长江中路庐阳佳123号','598659',0,0),(100,'Fermentum Arcu Corp.','Chun','022-558-3464','3059 842525 48257','湖北省 武汉市 江汉区 建设大道 江汉新城 808号','776266',0,0),(101,'Purus Ac Associates','Hou','017-469-7485','6304623287334472925','上海市 徐汇区 漕溪北路 徐汇嘉园 2020号','421948',0,0),(102,'Diam Eu Dolor PC','Kun','053-921-5233','4905 3466 7969 8360','山东省 青岛市 市南区 香港西路 青岛花园 1717号','419767',0,0),(103,'Tempor Bibendum PC','Hao','072-460-2697','491744 947287 6245','湖北省 武汉市 江汉区 建设大道 江汉新城 808号','685526',0,0),(104,'Mauris Elit Associates','Wang wei','071-323-2375','2149 582215 66160','河南省 郑州市 金水区 花园路 金水花园 606号','275866',0,0),(105,'Cursus Luctus Inc.','You','047-653-8776','4532 927 68 2128','福建省 厦门市 思明区 厦禾路 思明花园 789号','664268',0,0),(106,'Turpis Aliquam Corporation','Shilin','068-251-4525','675 93655 75773 913','广东省 广州市 天河区 体育西路 天河城广场 202号','587676',0,0),(107,'Lacus Quisque Incorporated','Shoi-ming','053-336-4367','5592587688599496','福建省 厦门市 思明区 厦禾路 思明花园 789号','106585',0,0),(108,'Augue Malesuada Incorporated','Qi','046-215-0460','3481 657483 64330','山东省 青岛市 市南区 香港西路 青岛花园 1717号','688248',0,0),(109,'Fringilla Incorporated','Shilin','082-726-7844','300288656344213','宁夏回族自治区 银川市 兴庆区 丽景北街 银川大厦 1515号','636505',0,0),(110,'Neque Sed Incorporated','Hui','044-703-2338','5838 666656 8773','湖北省 武汉市 江汉区 建设大道 江汉新城 808号','271471',0,0),(111,'Ornare Libero Foundation','Ru','052-363-5773','201442375245272','辽宁省 大连市 甘井子区 华北路 甘井子广场 1313号','695639',0,0),(112,'Id Institute','Ping','011-703-7155','6589221576545725','河北省 石家庄市 长安区 中山东路 长安花园 505号','523816',0,0),(113,'Amet Nulla Donec Associates','Yi','008-456-3247','633110 86 8712 5637 387','内蒙古自治区 呼和浩特市 新城区 新华大街 新城花园 1414号','423192',0,0),(114,'Non Quam Inc.','Mingyu','048-336-2867','361448875489977','天津市 河西区 大沽南路 河西广场 2222号','602920',0,0),(115,'Nunc Id Enim Associates','Wang','084-933-2937','491 36824 34513 662','湖北省 武汉市 江汉区 建设大道 江汉新城 808号','141883',0,0),(116,'Tristique Ac Ltd','Li qiang','061-693-2635','5038 668498 4679','广东省 广州市 天河区 体育西路 天河城广场 202号','872093',0,0),(117,'Mauris Associates','Xue','084-425-2144','675996 224644 7743','北京市 朝阳区 酒仙桥路 朝阳新城 456号','718294',0,0),(118,'Sed Limited','Qiao','014-681-9455','4556785358521','天津市 河西区 大沽南路 河西广场 2222号','537803',0,0),(119,'Phasellus Corp.','Tu','045-421-1562','503821391463883','河南省 郑州市 金水区 花园路 金水花园 606号','504724',0,0),(120,'Consequat Consulting','Li qiang','005-256-8288','4916 9662 7936 7491','广东省 广州市 天河区 体育西路 天河城广场 202号','332680',0,0),(121,'Consequat Enim LLC','Chun','044-474-3787','514238 343674 4740','贵州省 贵阳市 云岩区 中华北路 金筑大厦 303号','162175',0,0),(122,'Justo Foundation','Peizhi','032-185-0446','346558231124355','湖北省 武汉市 江汉区 建设大道 江汉新城 808号','345426',0,0),(123,'Malesuada Vel LLP','Fu','008-351-4957','344321759223345','天津市 河西区 大沽南路 河西广场 2222号','330884',0,0),(124,'A Dui Consulting','Yingpei','033-014-9013','6763269369127','青海省 西宁市 城西区 五四大街 城西广场 1616号','369206',0,0),(125,'Facilisis Limited','Ping','091-744-3257','6455 4195 7648 4553','湖南省 长沙市 岳麓区 岳麓大道 岳麓山庄 909号','987789',0,0),(126,'Neque Nullam Nisl Corp.','Yongrui','053-488-0368','201468228443574','江西省 南昌市 西湖区 井冈山大道 西湖家园 1212号','275895',0,0),(127,'Scelerisque Neque Sed LLP','Jun','024-544-6488','670 68416 31749 638','河南省 郑州市 金水区 花园路 金水花园 606号','658138',0,0),(128,'Nec PC','Hop','088-886-5766','589355 468216 4848','天津市 河西区 大沽南路 河西广场 2222号','935757',0,0),(129,'Id Mollis Nec LLC','Xing','012-360-7688','670677 71 6865 4626 144','内蒙古自治区 呼和浩特市 新城区 新华大街 新城花园 1414号','553283',0,0),(130,'Donec Felis Orci LLP','Xin','018-807-8844','4508529726936260','河南省 郑州市 金水区 花园路 金水花园 606号','822797',0,0),(131,'Quis Pede Institute','Ling','075-322-6718','490585674452852963','江西省 南昌市 西湖区 井冈山大道 西湖家园 1212号','858523',0,0),(132,'Erat In Consectetuer Industries','Ping','031-407-8653','633496 7585684644','河南省 郑州市 金水区 花园路 金水花园 606号','473156',0,0),(133,'Leo PC','Yanlin','038-567-5568','6444 8262 2488 6928','北京市 朝阳区 酒仙桥路 朝阳新城 456号','468387',0,0),(134,'Risus Incorporated','Lei','078-999-1118','5361854546972532','陕西省 西安市 雁塔区 小寨东路 雁塔花园 1919号','462567',0,0),(135,'Donec Associates','Zhou','042-192-8465','349858776723380','天津市 河西区 大沽南路 河西广场 2222号','892408',0,0),(136,'A Malesuada PC','Kun','047-418-5187','493644 941474 6438','山东省 青岛市 市南区 香港西路 青岛花园 1717号','820337',0,0),(137,'Erat Vivamus Nisi Institute','Chang','097-157-0465','528559 882637 8356','安徽省合肥市庐阳区长江中路庐阳佳123号','827925',0,0),(138,'Bibendum Sed Foundation','Jinhai','023-158-7025','490539 6864413349','甘肃省 兰州市 城关区 天水路 兰州大厦 101号','737818',0,0),(139,'Sed Dui Foundation','Mingyu','042-383-7334','6762 575694 92365','陕西省 西安市 雁塔区 小寨东路 雁塔花园 1919号','764288',0,0),(140,'Turpis Nulla Inc.','Jig','081-317-1286','2149 297674 73681','北京市 朝阳区 酒仙桥路 朝阳新城 456号','649452',0,0),(141,'Arcu Institute','Qiao','005-104-2876','303384676795240','江西省 南昌市 西湖区 井冈山大道 西湖家园 1212号','606764',0,0),(142,'Ante Bibendum Associates','Yanlin','028-421-2269','417 50087 18745 569','四川省 成都市 青羊区 金沙江路 成都花园 2121号','843855',0,0),(143,'Aenean Eget Magna Corporation','Tai-hua','017-159-5516','3459 278758 78967','江西省 南昌市 西湖区 井冈山大道 西湖家园 1212号','563168',0,0),(144,'Odio Phasellus LLC','Bingwen','076-253-0172','367816844418653','吉林省 长春市 南关区 人民大街 长春大厦 1010号','534716',0,0),(145,'Class Aptent Taciti Corp.','Wenyan','030-443-4108','633 35871 75676 221','吉林省 长春市 南关区 人民大街 长春大厦 1010号','743460',0,0),(146,'Ac Feugiat Company','Ling','013-891-3885','676347822997720','甘肃省 兰州市 城关区 天水路 兰州大厦 101号','416288',0,0),(147,'Donec Consectetuer LLC','Zihao','096-447-2000','646 73362 74566 646','安徽省合肥市庐阳区长江中路庐阳佳123号','778895',0,0),(148,'Lacus Limited','Wen','026-491-5303','3034 885862 63973','甘肃省 兰州市 城关区 天水路 兰州大厦 101号','466494',0,0),(150,'Imperdiet Institute','Yanlin','053-858-6241','3777 574314 18755','福建省 厦门市 思明区 厦禾路 思明花园 789号','805468',0,0),(151,'Duis A Incorporated','Kuo','064-957-7575','4024007128181996','江西省 南昌市 西湖区 井冈山大道 西湖家园 1212号','383657',0,0),(152,'Eget Corporation','Gan','036-397-0153','491 34885 68286 483','陕西省 西安市 雁塔区 小寨东路 雁塔花园 1919号','845559',0,0),(153,'Faucibus Institute','Yong','097-983-5869','363423655957863','安徽省合肥市庐阳区长江中路庐阳佳123号','819420',0,0),(154,'Molestie Arcu Sed Incorporated','Weiyuan','099-695-2260','528559 521238 8320','湖北省 武汉市 江汉区 建设大道 江汉新城 808号','378249',0,0),(155,'Nullam Suscipit Ltd','An','010-376-2828','4485156573623317','山东省 青岛市 市南区 香港西路 青岛花园 1717号','522618',0,0),(156,'Phasellus Nulla Corp.','Deli','037-107-6613','471 63587 63256 822','四川省 成都市 青羊区 金沙江路 成都花园 2121号','670850',0,0),(157,'In Scelerisque Scelerisque PC','Bojing','038-203-7825','2014 365527 32821','天津市 河西区 大沽南路 河西广场 2222号','481687',0,0),(158,'Egestas A Associates','Wang lei','083-674-5582','5641821333893896587','湖北省 武汉市 江汉区 建设大道 江汉新城 808号','912533',0,0),(159,'Felis Company','Chaun','067-872-6397','6444887337255328','吉林省 长春市 南关区 人民大街 长春大厦 1010号','785552',0,0),(160,'Eget Nisi LLP','Cheng','037-233-6443','6709 3536 9287 4583','江西省 南昌市 西湖区 井冈山大道 西湖家园 1212号','151608',0,0),(161,'Donec Luctus Foundation','Ying','055-116-5742','491766 3874979890','青海省 西宁市 城西区 五四大街 城西广场 1616号','710097',0,0),(162,'Tellus Phasellus Corporation','Wen','071-266-3730','4485897794789','北京市 朝阳区 酒仙桥路 朝阳新城 456号','535254',0,0),(163,'Et Risus Institute','Ru','081-677-2661','450 88452 26647 938','天津市 河西区 大沽南路 河西广场 2222号','430245',0,0),(164,'Eget Laoreet Incorporated','Li jun','038-639-3341','6759 344628 5680','海南省 海口市 龙华区 海秀中路 龙华商城 404号','685155',0,0),(165,'Id Ante Limited','Wang yong','049-717-1712','558721 582466 5578','黑龙江省 哈尔滨市 南岗区 东大直街 南岗大厦 707号','623325',0,0),(166,'Sit Associates','Song','018-579-5210','6761424526713546946','河北省 石家庄市 长安区 中山东路 长安花园 505号','534257',0,0),(167,'Pretium Neque Morbi Corporation','Fu','080-377-6765','417 50015 62388 586','贵州省 贵阳市 云岩区 中华北路 金筑大厦 303号','836274',0,0),(168,'Donec Tempus Limited','Jia','056-773-6273','564265653829','天津市 河西区 大沽南路 河西广场 2222号','553023',0,0),(169,'Leo In Corporation','Kang','051-842-6130','3038 435726 92942','海南省 海口市 龙华区 海秀中路 龙华商城 404号','430139',0,0),(170,'Ut Sem Nulla Inc.','Qu','033-358-6384','402400 7115397480','山东省 青岛市 市南区 香港西路 青岛花园 1717号','299332',0,0),(171,'Nonummy Ac Feugiat LLC','Li wei','029-953-6609','4913 4577 7833 5224','四川省 成都市 青羊区 金沙江路 成都花园 2121号','276874',0,0),(172,'Donec Elementum Corp.','Chao','011-187-6388','343768782856437','辽宁省 大连市 甘井子区 华北路 甘井子广场 1313号','548314',0,0),(173,'Viverra Donec Incorporated','Shui','076-365-3565','5182 3325 7395 8895','宁夏回族自治区 银川市 兴庆区 丽景北街 银川大厦 1515号','310431',0,0),(174,'Non Associates','Shui','046-242-7153','490576 6442334342','湖北省 武汉市 江汉区 建设大道 江汉新城 808号','268184',0,0),(175,'Non Lobortis Institute','Shilin','076-825-1589','4556415739470','湖南省 长沙市 岳麓区 岳麓大道 岳麓山庄 909号','443105',0,0),(176,'Mollis LLP','Qu','013-331-5284','5283 3776 4683 2492','辽宁省 大连市 甘井子区 华北路 甘井子广场 1313号','781386',0,0),(177,'Duis Cursus Incorporated','Deming','088-383-5898','3008 689254 22849','辽宁省 大连市 甘井子区 华北路 甘井子广场 1313号','327428',0,0),(178,'Et Arcu Imperdiet Limited','Jia','002-100-6222','4508 8537 4658 5240','甘肃省 兰州市 城关区 天水路 兰州大厦 101号','183598',0,0),(179,'Etiam Ligula Institute','Kong','082-376-4376','675933 8738226962','河北省 石家庄市 长安区 中山东路 长安花园 505号','418225',0,0),(180,'Porttitor LLP','Song','015-568-7426','6334239533464523','甘肃省 兰州市 城关区 天水路 兰州大厦 101号','948531',0,0),(181,'Eu Eleifend Inc.','Zhong','058-695-5366','525952 918669 7145','宁夏回族自治区 银川市 兴庆区 丽景北街 银川大厦 1515号','157146',0,0),(182,'Fringilla LLC','Kuo','052-846-1474','6771896633753871','内蒙古自治区 呼和浩特市 新城区 新华大街 新城花园 1414号','428178',0,0),(183,'Mauris Ut Industries','Guang','056-661-4731','4929 518 26 5737','江苏省 南京市 玄武区 中央路 玄武花园 1111号','217226',0,0),(184,'Donec Consectetuer Mauris Inc.','Huan','038-453-2303','5893464883886','贵州省 贵阳市 云岩区 中华北路 金筑大厦 303号','433385',0,0),(185,'Arcu Et Pede Inc.','Manchu','063-411-8157','3037 464548 35846','江苏省 南京市 玄武区 中央路 玄武花园 1111号','623281',0,0),(186,'Donec Elementum Industries','Shilin','058-052-3858','676 77427 73442 832','安徽省合肥市庐阳区长江中路庐阳佳123号','277697',0,0),(187,'Nec Corporation','Tu','023-642-1201','4024007174796','湖南省 长沙市 岳麓区 岳麓大道 岳麓山庄 909号','791072',0,0),(188,'Dolor Quam Corporation','Jinhai','054-722-9674','645 28655 45655 637','上海市 徐汇区 漕溪北路 徐汇嘉园 2020号','355871',0,0),(189,'Iaculis Quis Foundation','Quon','095-515-4614','566222 53 5864 6593 742','宁夏回族自治区 银川市 兴庆区 丽景北街 银川大厦 1515号','344764',0,0),(190,'Id Magna Foundation','Bo','041-847-6824','4026674336375849','河北省 石家庄市 长安区 中山东路 长安花园 505号','446862',0,0),(191,'Tellus Suspendisse Company','Zhou','002-481-7855','633313 247747 9493','甘肃省 兰州市 城关区 天水路 兰州大厦 101号','931273',0,0),(192,'Id Industries','Peizhi','046-128-0648','6333 1247 3652 2569','黑龙江省 哈尔滨市 南岗区 东大直街 南岗大厦 707号','473563',0,0),(193,'Mattis Semper Ltd','Hong','045-724-5843','3443 855864 65841','贵州省 贵阳市 云岩区 中华北路 金筑大厦 303号','435330',0,0),(194,'Dolor Sit Foundation','An','072-102-9527','6767645727461838341','湖南省 长沙市 岳麓区 岳麓大道 岳麓山庄 909号','744345',0,0),(195,'Neque Non Quam Institute','Jin','013-744-7065','4716868249852','江苏省 南京市 玄武区 中央路 玄武花园 1111号','480529',0,0),(196,'Quam Vel Sapien LLC','Tai-hua','018-091-5678','670 64156 25874 395','山西省 太原市 小店区 长风街 小店广场 1818号','233461',0,0),(197,'Et Nunc Quisque PC','Shilin','069-041-5338','3656 198965 37343','甘肃省 兰州市 城关区 天水路 兰州大厦 101号','876493',0,0),(198,'In Consectetuer Foundation','Tu','064-373-4455','630432 45 2467 1849 386','福建省 厦门市 思明区 厦禾路 思明花园 789号','539322',0,0),(199,'Ante Ipsum Primis Consulting','Tung','042-018-4575','6767 2912 6317 6278','河北省 石家庄市 长安区 中山东路 长安花园 505号','779333',0,0),(200,'Interdum Feugiat Sed Inc.','Chung','049-192-8634','301834174755379','海南省 海口市 龙华区 海秀中路 龙华商城 404号','553127',0,0),(201,'Duis Volutpat LLP','Ning','001-573-0114','670 66884 27746 470','内蒙古自治区 呼和浩特市 新城区 新华大街 新城花园 1414号','512976',0,0),(202,'Pede Cras Limited','Bengt','053-597-8255','6762 839 32 8381','贵州省 贵阳市 云岩区 中华北路 金筑大厦 303号','379666',0,0),(203,'Gravida Praesent Associates','Bo','089-563-8374','4539597559853517','江苏省 南京市 玄武区 中央路 玄武花园 1111号','733965',0,0),(204,'Sollicitudin Adipiscing Incorporated','Shan','045-064-4885','6334842624756937','北京市 朝阳区 酒仙桥路 朝阳新城 456号','633003',0,0),(205,'Parturient Montes Corp.','He','077-811-7557','2014 889589 76130','河北省 石家庄市 长安区 中山东路 长安花园 505号','237124',0,0),(206,'Arcu Eu Institute','An','046-234-8235','5184344582314950','广东省 广州市 天河区 体育西路 天河城广场 202号','267172',0,0),(207,'Nisl Industries','Yingjie','045-542-4576','4911782852234542761','辽宁省 大连市 甘井子区 华北路 甘井子广场 1313号','325560',0,0),(208,'Metus Eu Erat Corporation','Weizhe','003-136-5738','417 50074 77468 876','山东省 青岛市 市南区 香港西路 青岛花园 1717号','747451',0,0),(209,'Ipsum Suspendisse Inc.','Hui','036-522-4243','5435 2455 6619 9571','广东省 广州市 天河区 体育西路 天河城广场 202号','862297',0,0),(210,'Eros Non Inc.','Kong','010-814-3474','2014 388358 47285','宁夏回族自治区 银川市 兴庆区 丽景北街 银川大厦 1515号','233182',0,0),(211,'Nulla Vulputate Ltd','Boqin','088-135-4311','6446451731259481','黑龙江省 哈尔滨市 南岗区 东大直街 南岗大厦 707号','774756',0,0),(212,'Torquent Per Inc.','Susu','045-726-4494','676363 4385192620','海南省 海口市 龙华区 海秀中路 龙华商城 404号','388168',0,0),(213,'Eu Neque Associates','Biming','023-647-2942','670922934845737878','江苏省 南京市 玄武区 中央路 玄武花园 1111号','388168',0,0),(214,'Vestibulum Accumsan Neque Inc.','Ping','077-734-1948','300651575299794','江苏省 南京市 玄武区 中央路 玄武花园 1111号','822654',0,0),(215,'Ut Eros Non Limited','Shilin','002-497-6752','574583833365647','陕西省 西安市 雁塔区 小寨东路 雁塔花园 1919号','591819',0,0),(216,'Cras Associates','Jinhai','096-476-4420','6763 8487 3114 3570','江苏省 南京市 玄武区 中央路 玄武花园 1111号','481179',0,0),(217,'Blandit At Institute','Deli','027-877-2144','4508338442746221','宁夏回族自治区 银川市 兴庆区 丽景北街 银川大厦 1515号','563468',0,0),(218,'Enim Corporation','Jin','023-226-2186','6465 1694 1136 8754','河北省 石家庄市 长安区 中山东路 长安花园 505号','337244',0,0),(219,'Aenean Massa Integer LLP','Li qiang','044-542-2203','3638 737266 54339','青海省 西宁市 城西区 五四大街 城西广场 1616号','248431',0,0),(220,'Vestibulum Massa Foundation','Ling','065-363-1519','5897 2554 4646 7834','黑龙江省 哈尔滨市 南岗区 东大直街 南岗大厦 707号','358549',0,0),(221,'Mi Enim Foundation','Deli','091-313-6994','3668 868544 28851','内蒙古自治区 呼和浩特市 新城区 新华大街 新城花园 1414号','221736',0,0),(222,'Porttitor Scelerisque PC','Changpu','091-016-6639','372673745876536','山东省 青岛市 市南区 香港西路 青岛花园 1717号','377443',0,0),(223,'Nibh Sit Associates','Shoi-ming','044-156-3176','3431 451967 75885','江苏省 南京市 玄武区 中央路 玄武花园 1111号','564734',0,0),(224,'Sed Malesuada LLP','Song','094-168-7913','589348693633','四川省 成都市 青羊区 金沙江路 成都花园 2121号','568577',0,0),(225,'Aliquam Fringilla Company','Tung','053-456-8451','361384288762421','河北省 石家庄市 长安区 中山东路 长安花园 505号','944148',0,0),(226,'Nam Ligula Corporation','Bo','004-762-6278','3484 724153 47725','吉林省 长春市 南关区 人民大街 长春大厦 1010号','853439',0,0),(227,'Vitae Semper Corporation','Bambang','082-862-1182','4916487853692','上海市 徐汇区 漕溪北路 徐汇嘉园 2020号','527757',0,0),(228,'Cras Dolor Incorporated','Bingwen','057-132-3418','589 37874 34346 947','河北省 石家庄市 长安区 中山东路 长安花园 505号','469929',0,0),(229,'Tincidunt Corporation','Susu','022-785-4873','2149 564862 45355','湖南省 长沙市 岳麓区 岳麓大道 岳麓山庄 909号','563654',0,0),(230,'Leo In Lobortis Corporation','Kong','067-108-7540','484 46383 84642 939','吉林省 长春市 南关区 人民大街 长春大厦 1010号','386451',0,0),(231,'Nullam Ltd','Qu','067-769-1656','2149 246572 51578','广东省 广州市 天河区 体育西路 天河城广场 202号','612148',0,0),(232,'Orci Luctus Company','Zhixin','063-537-5113','493627866475457853','四川省 成都市 青羊区 金沙江路 成都花园 2121号','436658',0,0),(233,'Eu Dolor LLP','Bingwen','007-182-0813','3053 454244 62456','江西省 南昌市 西湖区 井冈山大道 西湖家园 1212号','623243',0,0),(234,'Et Limited','Changpu','028-547-5587','658856 819924 4156','上海市 徐汇区 漕溪北路 徐汇嘉园 2020号','683337',0,0),(235,'Euismod Urna Associates','Bo','057-263-2684','648 13368 49782 472','河南省 郑州市 金水区 花园路 金水花园 606号','514718',0,0),(236,'Aliquam Gravida Mauris Foundation','Guotin','053-077-5892','453293 476654 2871','海南省 海口市 龙华区 海秀中路 龙华商城 404号','838742',0,0),(237,'Ultrices Corp.','Weiyuan','039-679-7377','527768 852782 3329','天津市 河西区 大沽南路 河西广场 2222号','781429',0,0),(238,'Egestas Limited','Qi','032-213-3453','630416 75 9397 6568 843','福建省 厦门市 思明区 厦禾路 思明花园 789号','312880',0,0),(239,'Sem Elit Corp.','Yi','055-761-8263','490 35558 57846 656','上海市 徐汇区 漕溪北路 徐汇嘉园 2020号','942516',0,0),(240,'Dui Suspendisse Limited','Hou','084-715-5266','6333 5162 9793 1214','河北省 石家庄市 长安区 中山东路 长安花园 505号','720185',0,0),(241,'Pellentesque Habitant Morbi Ltd','On','093-697-7858','417500 544477 4830','湖北省 武汉市 江汉区 建设大道 江汉新城 808号','944550',0,0),(242,'Orci Ut Semper Limited','Hao','051-171-5271','4026 3389 3789 9835','四川省 成都市 青羊区 金沙江路 成都花园 2121号','108403',0,0),(243,'Mi Company','Yingjie','066-711-4106','484451 469192 3470','河南省 郑州市 金水区 花园路 金水花园 606号','793873',0,0),(244,'Ac Turpis Egestas Ltd','Zhou','042-302-5723','633411 61 2715 3887 830','山西省 太原市 小店区 长风街 小店广场 1818号','884647',0,0),(245,'Purus Institute','Jig','052-423-2467','2014 243775 69719','广东省 广州市 天河区 体育西路 天河城广场 202号','866634',0,0),(246,'Blandit Viverra Institute','Zhong','034-046-8621','4913 8537 6223 2957','甘肃省 兰州市 城关区 天水路 兰州大厦 101号','378371',0,0),(247,'Lectus Ante Dictum LLC','Shan','029-487-4818','6463 6495 4973 8921','海南省 海口市 龙华区 海秀中路 龙华商城 404号','580472',0,0),(248,'Per Conubia Incorporated','Li jun','097-631-7782','633438 79 2332 3769 789','江西省 南昌市 西湖区 井冈山大道 西湖家园 1212号','719232',0,0),(249,'Ut Inc.','Lie jie','033-767-3146','6762337477235','江苏省 南京市 玄武区 中央路 玄武花园 1111号','303763',0,0),(250,'Pede Ltd','Bojing','016-898-7088','3467 652537 32334','北京市 朝阳区 酒仙桥路 朝阳新城 456号','314691',0,0),(251,'Ultrices Posuere Industries','Wang lei','076-236-3832','490558 2285725466','河北省 石家庄市 长安区 中山东路 长安花园 505号','976138',0,0),(252,'Arcu Vestibulum Limited','Hop','048-051-5152','3057 498265 82953','内蒙古自治区 呼和浩特市 新城区 新华大街 新城花园 1414号','733154',0,0),(253,'Tristique Consulting','Cheung','035-674-5785','2014 246247 92668','宁夏回族自治区 银川市 兴庆区 丽景北街 银川大厦 1515号','845054',0,0),(254,'Quis Diam LLP','Wei','096-328-5612','364662562527546','安徽省合肥市庐阳区长江中路庐阳佳123号','784266',0,0),(255,'Vitae Risus Limited','Yingpei','086-965-1850','516 62597 48388 866','福建省 厦门市 思明区 厦禾路 思明花园 789号','825724',0,0),(256,'Nulla Interdum LLP','Yingjie','031-348-2176','677189 49 8248 3143 549','四川省 成都市 青羊区 金沙江路 成都花园 2121号','676434',0,0),(257,'Cubilia Company','Chaun','072-934-9716','6334 3151 6917 5342','湖北省 武汉市 江汉区 建设大道 江汉新城 808号','884529',0,0),(258,'Morbi Non Corporation','Qing','018-859-2836','3486 182529 88753','青海省 西宁市 城西区 五四大街 城西广场 1616号','855731',0,0),(259,'Vivamus Euismod Institute','Feng','063-887-7720','3036 595738 14537','山西省 太原市 小店区 长风街 小店广场 1818号','447231',0,0),(260,'Hendrerit Donec Porttitor Institute','Huan','057-365-3658','3774 227566 35562','内蒙古自治区 呼和浩特市 新城区 新华大街 新城花园 1414号','285187',0,0),(261,'Egestas Ltd','Liang','047-751-1388','490546 836865 8784','宁夏回族自治区 银川市 兴庆区 丽景北街 银川大厦 1515号','818756',0,0),(262,'Luctus Ipsum Institute','Qiao','003-128-2984','503 84624 62686 129','福建省 厦门市 思明区 厦禾路 思明花园 789号','802675',0,0),(263,'Turpis Non Enim Institute','Song','083-331-9638','3654 728418 47635','贵州省 贵阳市 云岩区 中华北路 金筑大厦 303号','245552',0,0),(264,'Quam Vel Corp.','Biming','028-748-7176','490578893147848488','内蒙古自治区 呼和浩特市 新城区 新华大街 新城花园 1414号','476214',0,0),(265,'Sodales Mauris Consulting','Susu','012-978-8260','648922 579736 5780','江西省 南昌市 西湖区 井冈山大道 西湖家园 1212号','512841',0,0),(266,'Cursus In LLC','Xiang','068-288-4076','361843891727345','广东省 广州市 天河区 体育西路 天河城广场 202号','346876',0,0),(267,'Mauris Rhoncus LLP','Chao','053-338-7322','3754 283926 35736','贵州省 贵阳市 云岩区 中华北路 金筑大厦 303号','730888',0,0),(268,'Purus Associates','Mingyu','087-231-4339','503877 742348 7790','宁夏回族自治区 银川市 兴庆区 丽景北街 银川大厦 1515号','751582',0,0),(269,'Ornare Lectus LLC','Yi','063-447-2871','3004 644113 34563','江苏省 南京市 玄武区 中央路 玄武花园 1111号','685770',0,0),(270,'Lorem Luctus Ut PC','Jianyu','063-636-5839','589395528891758182','广东省 广州市 天河区 体育西路 天河城广场 202号','760582',0,0),(271,'Lacinia Company','Guotin','044-622-7282','6473 5628 4251 9674','山西省 太原市 小店区 长风街 小店广场 1818号','470511',0,0),(272,'At Velit Corporation','Liang','013-884-6761','3756 721444 43663','甘肃省 兰州市 城关区 天水路 兰州大厦 101号','723781',0,0),(273,'Vulputate Risus Institute','An','058-752-8677','4913 7778 7226 4388','山西省 太原市 小店区 长风街 小店广场 1818号','465823',0,0),(274,'Turpis Nulla Aliquet Industries','Mingli','059-044-4673','492 93356 82374 566','湖南省 长沙市 岳麓区 岳麓大道 岳麓山庄 909号','594236',0,0),(275,'Magna A Tortor Ltd','Jia','085-938-0924','601139 941213 7697','山东省 青岛市 市南区 香港西路 青岛花园 1717号','261052',0,0),(276,'Sed Libero Foundation','Peizhi','088-243-3921','652863 2772131959','山东省 青岛市 市南区 香港西路 青岛花园 1717号','325867',0,0),(277,'Netus Et Malesuada Ltd','Jig','022-935-1124','3475 552261 24688','黑龙江省 哈尔滨市 南岗区 东大直街 南岗大厦 707号','462633',0,0),(278,'Donec Porttitor Associates','On','061-463-4881','521 19813 52768 480','上海市 徐汇区 漕溪北路 徐汇嘉园 2020号','774371',0,0),(279,'Cras Lorem Lorem Incorporated','Park','075-164-1760','2149 342592 32512','海南省 海口市 龙华区 海秀中路 龙华商城 404号','369100',0,0),(280,'Justo Eu Consulting','Yi','048-374-2778','644467 161768 5366','四川省 成都市 青羊区 金沙江路 成都花园 2121号','296491',0,0),(281,'Mus Proin Institute','Zhixin','033-685-2875','564182886258475943','河北省 石家庄市 长安区 中山东路 长安花园 505号','214285',0,0),(282,'Amet Risus Inc.','Jin','075-572-7198','564182 5562731434','江苏省 南京市 玄武区 中央路 玄武花园 1111号','808582',0,0),(283,'Massa Mauris LLP','Wang wei','005-614-7834','491138 45 3741 5236 299','河北省 石家庄市 长安区 中山东路 长安花园 505号','238755',0,0),(284,'Lacus Vestibulum Limited','Ye','012-140-0840','63049852273897368','青海省 西宁市 城西区 五四大街 城西广场 1616号','298433',0,0),(285,'Odio A Incorporated','Tung','059-336-5339','5893844737643528','海南省 海口市 龙华区 海秀中路 龙华商城 404号','856321',0,0),(286,'Erat Etiam LLC','Chao','025-308-7784','303137246469484','湖南省 长沙市 岳麓区 岳麓大道 岳麓山庄 909号','686844',0,0),(287,'Nibh Aliquam Ornare Ltd','Kang','081-819-5869','374266347687480','天津市 河西区 大沽南路 河西广场 2222号','416481',0,0),(288,'Pede Nunc Limited','Chaun','054-776-4668','6474752417962461','北京市 朝阳区 酒仙桥路 朝阳新城 456号','188493',0,0),(289,'Et Foundation','Zhou','042-173-3228','2149 818353 45672','贵州省 贵阳市 云岩区 中华北路 金筑大厦 303号','773818',0,0),(290,'Vel Pede PC','Jinhai','032-474-6565','490583 17 7772 5392 777','天津市 河西区 大沽南路 河西广场 2222号','495683',0,0),(291,'Nunc Corporation','Hua','036-696-3091','670938 324657 7655','江西省 南昌市 西湖区 井冈山大道 西湖家园 1212号','544863',0,0),(292,'Condimentum Eget LLC','Zhixin','053-222-1103','491317 744168 1743','海南省 海口市 龙华区 海秀中路 龙华商城 404号','368587',0,0),(293,'Faucibus Associates','Song','020-788-1587','305127723248822','河南省 郑州市 金水区 花园路 金水花园 606号','646787',0,0),(294,'Ultricies Ligula Institute','Wenyan','066-454-8747','6759866822821470','黑龙江省 哈尔滨市 南岗区 东大直街 南岗大厦 707号','385012',0,0),(295,'Et Magnis Dis LLC','Bai','052-362-3313','630451 55 4358 3625 329','吉林省 长春市 南关区 人民大街 长春大厦 1010号','741066',0,0),(296,'Eu Turpis Foundation','You','086-315-8476','677164 9928646932','海南省 海口市 龙华区 海秀中路 龙华商城 404号','731847',0,0),(297,'Purus LLC','He','031-378-3778','453 94151 83533 621','内蒙古自治区 呼和浩特市 新城区 新华大街 新城花园 1414号','569635',0,0),(298,'Interdum Inc.','Li jun','092-030-4619','6767 3417 7434 2370','贵州省 贵阳市 云岩区 中华北路 金筑大厦 303号','662635',0,0),(299,'Lorem Ac Risus Corp.','Gan','097-596-9053','4917234787736882','青海省 西宁市 城西区 五四大街 城西广场 1616号','442164',0,0),(300,'Ipsum LLP','Ling','030-401-1072','3752 527261 67731','江苏省 南京市 玄武区 中央路 玄武花园 1111号','827465',0,0),(301,'Amet Industries','Mingli','050-643-9898','541643 3356966226','辽宁省 大连市 甘井子区 华北路 甘井子广场 1313号','184741',0,0),(302,'Quisque LLC','Park','063-346-7063','301485627234866','河南省 郑州市 金水区 花园路 金水花园 606号','516442',0,0),(303,'Ligula Donec Industries','Deli','041-872-6557','367814658868444','上海市 徐汇区 漕溪北路 徐汇嘉园 2020号','524111',0,0),(304,'Morbi LLP','Jun','077-052-7493','6465 8872 8565 6885','北京市 朝阳区 酒仙桥路 朝阳新城 456号','825062',0,0),(305,'In Consequat Enim Corp.','Kuo','041-392-2010','201473536881853','吉林省 长春市 南关区 人民大街 长春大厦 1010号','661308',0,0),(306,'Magna Et Incorporated','Yingjie','041-808-1386','5249319886693757','山东省 青岛市 市南区 香港西路 青岛花园 1717号','689823',0,0),(307,'Vel Venenatis Incorporated','Peizhi','083-572-2482','492997 117643 9968','海南省 海口市 龙华区 海秀中路 龙华商城 404号','318013',0,0),(308,'Tempus Scelerisque Corp.','On','053-668-3094','4817 6954 7588 4369','山东省 青岛市 市南区 香港西路 青岛花园 1717号','776643',0,0),(309,'Sapien Imperdiet Corporation','Duyi','014-850-2681','493657437715549223','福建省 厦门市 思明区 厦禾路 思明花园 789号','643959',0,0),(310,'Vivamus Associates','Qi','058-251-3882','490525 14 2276 6362 428','江苏省 南京市 玄武区 中央路 玄武花园 1111号','334854',0,0),(311,'Tincidunt Foundation','Jinhai','052-486-8286','3623 652868 66487','河南省 郑州市 金水区 花园路 金水花园 606号','318906',0,0),(312,'Lorem Ipsum Dolor Corporation','Ning','067-486-3858','450 86574 38443 984','青海省 西宁市 城西区 五四大街 城西广场 1616号','849123',0,0),(314,'Vulputate Lacus Inc.','Ru','078-322-0285','670644 41 1532 2584 885','河南省 郑州市 金水区 花园路 金水花园 606号','812384',0,0),(315,'Luctus Et Ultrices LLP','Hong','049-487-7761','5656 865 47 8360','山东省 青岛市 市南区 香港西路 青岛花园 1717号','828492',0,0),(316,'Purus Maecenas Libero Corp.','Jin','070-999-7238','6451 4374 3814 8772','广东省 广州市 天河区 体育西路 天河城广场 202号','418533',0,0),(317,'Ipsum Cursus Industries','Zhou','020-574-3279','448 53366 45942 685','辽宁省 大连市 甘井子区 华北路 甘井子广场 1313号','462323',0,0),(318,'Mattis Integer LLP','Hou','041-146-3702','3032 867318 44840','陕西省 西安市 雁塔区 小寨东路 雁塔花园 1919号','213108',0,0),(319,'Aliquam Arcu LLC','Liang','045-197-8871','6709385144168826','甘肃省 兰州市 城关区 天水路 兰州大厦 101号','400201',0,0),(320,'Odio Inc.','Taio','090-031-4475','6763 548853 4259','宁夏回族自治区 银川市 兴庆区 丽景北街 银川大厦 1515号','597492',0,0),(321,'Orci LLC','Wang','053-316-5833','373247735855633','河北省 石家庄市 长安区 中山东路 长安花园 505号','553898',0,0),(322,'Magna Incorporated','Hou','070-196-3476','2014 825778 77233','安徽省合肥市庐阳区长江中路庐阳佳123号','488461',0,0),(323,'Nec Imperdiet Nec LLP','Guotin','087-928-2133','676766527249531529','四川省 成都市 青羊区 金沙江路 成都花园 2121号','293771',0,0),(324,'Dis Limited','Tao','024-602-7624','523 97884 65719 229','甘肃省 兰州市 城关区 天水路 兰州大厦 101号','682037',0,0),(325,'Est Inc.','Qiao','073-982-8873','630442 3543242956','安徽省合肥市庐阳区长江中路庐阳佳123号','832858',0,0),(326,'Praesent Luctus Foundation','Qiu','072-259-6234','493647474716884336','海南省 海口市 龙华区 海秀中路 龙华商城 404号','106246',0,0),(327,'Nulla Ltd','Tung','068-054-8676','5038 2878 5589 2236','北京市 朝阳区 酒仙桥路 朝阳新城 456号','848735',0,0),(328,'Nascetur Ridiculus Ltd','Gui','076-122-5884','670633 22 8245 2673 428','黑龙江省 哈尔滨市 南岗区 东大直街 南岗大厦 707号','813620',0,0),(329,'Ante Ipsum Ltd','Mingyu','049-847-0001','6485 3767 6532 7528','四川省 成都市 青羊区 金沙江路 成都花园 2121号','875170',0,0),(330,'Facilisis Suspendisse LLC','Bingwen','082-813-6177','3788 655752 57696','海南省 海口市 龙华区 海秀中路 龙华商城 404号','820216',0,0),(331,'Eu Ltd','Kang','081-164-0871','416 55848 42895 498','广东省 广州市 天河区 体育西路 天河城广场 202号','615369',0,0),(332,'Nibh Donec Associates','Li qiang','028-406-7233','366488168647241','贵州省 贵阳市 云岩区 中华北路 金筑大厦 303号','737046',0,0),(333,'Euismod Urna Nullam Incorporated','Quon','053-746-9839','511 44973 68756 649','青海省 西宁市 城西区 五四大街 城西广场 1616号','769983',0,0),(334,'In Condimentum Donec Institute','Guang','013-748-3655','6471 8433 4387 8799','江西省 南昌市 西湖区 井冈山大道 西湖家园 1212号','233160',0,0),(335,'Lectus Justo LLP','Shoi-ming','085-860-8963','583449 827849 2480','湖南省 长沙市 岳麓区 岳麓大道 岳麓山庄 909号','428462',0,0),(336,'Laoreet Inc.','Wang lei','053-915-6344','303927784783753','江西省 南昌市 西湖区 井冈山大道 西湖家园 1212号','328566',0,0),(337,'Dui Cum LLC','Chang','037-847-0182','477887 7421593339','吉林省 长春市 南关区 人民大街 长春大厦 1010号','650225',0,0),(338,'Etiam Corp.','Wang lei','074-233-0248','214988343285688','安徽省合肥市庐阳区长江中路庐阳佳123号','859354',0,0),(339,'Phasellus At Augue PC','Zhixin','088-462-8612','455663 827429 3361','四川省 成都市 青羊区 金沙江路 成都花园 2121号','723587',0,0),(340,'Eros Nam Consequat Institute','Deli','056-228-2778','3648 229384 58673','北京市 朝阳区 酒仙桥路 朝阳新城 456号','827506',0,0),(341,'Duis Risus Odio Inc.','Taio','074-849-6252','2014 934272 66410','湖北省 武汉市 江汉区 建设大道 江汉新城 808号','661620',0,0),(342,'Ultrices Mauris Ipsum Inc.','Gen','039-581-6855','5472 8969 9662 3954','青海省 西宁市 城西区 五四大街 城西广场 1616号','768495',0,0),(343,'At Associates','Xue','082-787-9183','484 43537 43521 358','辽宁省 大连市 甘井子区 华北路 甘井子广场 1313号','762723',0,0),(344,'Egestas Nunc Corporation','Weizhe','003-371-1852','6763 564874 1687','山西省 太原市 小店区 长风街 小店广场 1818号','644577',0,0),(345,'Dictum Associates','Wang yong','043-716-1875','303741561534317','河南省 郑州市 金水区 花园路 金水花园 606号','605142',0,0),(346,'Ipsum Non Inc.','Jianyu','018-941-1499','402 40071 75744 761','辽宁省 大连市 甘井子区 华北路 甘井子广场 1313号','377784',0,0),(347,'Faucibus Industries','Heng','048-616-3693','676746 16 1223 3789 595','贵州省 贵阳市 云岩区 中华北路 金筑大厦 303号','513352',0,0),(348,'Egestas Industries','Bo','051-946-9695','453943 531862 5282','黑龙江省 哈尔滨市 南岗区 东大直街 南岗大厦 707号','698991',0,0),(349,'Pharetra Quisque Incorporated','Kong','066-589-5414','201417427888642','陕西省 西安市 雁塔区 小寨东路 雁塔花园 1919号','892988',0,0),(350,'Neque In Ltd','Hong','013-822-3678','543226 167156 4552','天津市 河西区 大沽南路 河西广场 2222号','822735',0,0),(351,'Urna Vivamus Molestie Associates','Taio','063-142-1354','305985668232865','福建省 厦门市 思明区 厦禾路 思明花园 789号','487037',0,0),(352,'Google','SSymbol','13634546175','112111','贵州省 贵阳市 云岩区 中华北路 金筑大厦 303号','30260',0,0);
/*!40000 ALTER TABLE `enterprise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enterprise_operate`
--

DROP TABLE IF EXISTS `enterprise_operate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enterprise_operate` (
  `iid` int NOT NULL AUTO_INCREMENT,
  `oid` int NOT NULL,
  `eid` int NOT NULL,
  `operate` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `operate_time` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`iid`) USING BTREE,
  KEY `oid` (`oid`) USING BTREE,
  KEY `eid` (`eid`) USING BTREE,
  CONSTRAINT `enterprise_operate_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `account` (`aid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `enterprise_operate_ibfk_2` FOREIGN KEY (`eid`) REFERENCES `enterprise` (`eid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enterprise_operate`
--

LOCK TABLES `enterprise_operate` WRITE;
/*!40000 ALTER TABLE `enterprise_operate` DISABLE KEYS */;
INSERT INTO `enterprise_operate` VALUES (5,1,52,'修改企业信息','2024-10-22 15:50:30'),(6,1,53,'修改企业信息','2024-10-22 15:50:35'),(7,1,54,'修改企业信息','2024-10-22 15:50:40'),(8,1,55,'修改企业信息','2024-10-23 16:47:48'),(9,1,56,'修改企业信息','2024-10-23 16:48:00'),(10,1,57,'修改企业信息','2024-10-23 16:48:06'),(11,1,58,'修改企业信息','2024-10-23 16:48:18');
/*!40000 ALTER TABLE `enterprise_operate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrance`
--

DROP TABLE IF EXISTS `entrance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrance` (
  `eid` int NOT NULL AUTO_INCREMENT,
  `bid` int NOT NULL,
  `business_time` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `code` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `batch` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `num` int NOT NULL,
  `unit` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `trace` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `buyer_type` smallint NOT NULL DEFAULT '0',
  `cid` int NOT NULL,
  PRIMARY KEY (`eid`) USING BTREE,
  KEY `cid` (`cid`) USING BTREE,
  KEY `bid` (`bid`) USING BTREE,
  CONSTRAINT `entrance_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `classification` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `entrance_ibfk_3` FOREIGN KEY (`bid`) REFERENCES `enterprise` (`eid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrance`
--

LOCK TABLES `entrance` WRITE;
/*!40000 ALTER TABLE `entrance` DISABLE KEYS */;
INSERT INTO `entrance` VALUES (7,352,'2024-08-12 14:03:20','新鲜草莓礼盒','FS001','bt0001',50,'件','Trace-66b9a5a9ad661bcc2db6d95e',1,5),(8,352,'2024-08-12 14:03:21','精选牛肉块','MB003','bt0002',50,'件','Trace-66b9a5a9ad661bcc2db6d95f',1,5),(9,352,'2024-08-12 14:03:21','野生蓝莓200g','WB004','bt0003',50,'件','Trace-66b9a5a9ad661bcc2db6d960',1,5),(10,352,'2024-08-12 14:03:21','农家土鸡蛋12枚','FJ005','bt0004',50,'件','Trace-66b9a5a9ad661bcc2db6d961',1,5),(11,352,'2024-08-12 14:03:21','鲜榨橙汁1L','OJ006','bt0005',50,'件','Trace-66b9a5a9ad661bcc2db6d962',1,5),(12,352,'2024-08-12 14:03:21','深海鳕鱼片','CF007','bt0006',50,'件','Trace-66b9a5a9ad661bcc2db6d963',1,5),(13,52,'2024-10-22 15:47:53','新鲜草莓礼盒','FS001','bt0001',50,'件','Trace-671758a9309d3479a53b0ec6',1,5),(14,52,'2024-10-22 15:47:53','精选牛肉块','MB003','bt0002',50,'件','Trace-671758a9309d3479a53b0ec7',1,5),(15,52,'2024-10-22 15:47:53','野生蓝莓200g','WB004','bt0003',50,'件','Trace-671758a9309d3479a53b0ec8',1,5),(16,52,'2024-10-22 15:47:53','农家土鸡蛋12枚','FJ005','bt0004',50,'件','Trace-671758a9309d3479a53b0ec9',1,5),(17,52,'2024-10-22 15:47:53','鲜榨橙汁1L','OJ006','bt0005',50,'件','Trace-671758a9309d3479a53b0eca',1,5),(18,52,'2024-10-22 15:47:53','深海鳕鱼片','CF007','bt0006',50,'件','Trace-671758a9309d3479a53b0ecb',1,5),(19,53,'2024-10-22 15:47:53','新鲜草莓礼盒','FS001','bt0001',50,'件','Trace-671758a9309d3479a53b0ecc',1,5),(20,53,'2024-10-22 15:47:53','精选牛肉块','MB003','bt0002',50,'件','Trace-671758a9309d3479a53b0ecd',1,5),(21,53,'2024-10-22 15:47:53','野生蓝莓200g','WB004','bt0003',50,'件','Trace-671758a9309d3479a53b0ece',1,5),(22,53,'2024-10-22 15:47:53','农家土鸡蛋12枚','FJ005','bt0004',50,'件','Trace-671758a9309d3479a53b0ecf',1,5),(23,53,'2024-10-22 15:47:53','鲜榨橙汁1L','OJ006','bt0005',50,'件','Trace-671758a9309d3479a53b0ed0',1,5),(24,53,'2024-10-22 15:47:53','深海鳕鱼片','CF007','bt0006',50,'件','Trace-671758a9309d3479a53b0ed1',1,5),(25,54,'2024-10-22 15:47:53','新鲜草莓礼盒','FS001','bt0001',50,'件','Trace-671758a9309d3479a53b0ed2',1,5),(26,54,'2024-10-22 15:47:53','精选牛肉块','MB003','bt0002',50,'件','Trace-671758a9309d3479a53b0ed3',1,5),(27,54,'2024-10-22 15:47:53','野生蓝莓200g','WB004','bt0003',50,'件','Trace-671758a9309d3479a53b0ed4',1,5),(28,54,'2024-10-22 15:47:53','农家土鸡蛋12枚','FJ005','bt0004',50,'件','Trace-671758a9309d3479a53b0ed5',1,5),(29,54,'2024-10-22 15:47:53','鲜榨橙汁1L','OJ006','bt0005',50,'件','Trace-671758a9309d3479a53b0ed6',1,5),(30,54,'2024-10-22 15:47:53','深海鳕鱼片','CF007','bt0006',50,'件','Trace-671758a9309d3479a53b0ed7',1,5),(31,55,'2024-10-23 16:54:55','新鲜草莓礼盒','FS001','bt0001',50,'件','Trace-6718b9e0309d1c15fca24e9f',1,5),(32,55,'2024-10-23 16:54:56','精选牛肉块','MB003','bt0002',50,'件','Trace-6718b9e0309d1c15fca24ea0',1,5),(33,55,'2024-10-23 16:54:56','野生蓝莓200g','WB004','bt0003',50,'件','Trace-6718b9e0309d1c15fca24ea1',1,5),(34,55,'2024-10-23 16:54:56','农家土鸡蛋12枚','FJ005','bt0004',50,'件','Trace-6718b9e0309d1c15fca24ea2',1,5),(35,55,'2024-10-23 16:54:56','鲜榨橙汁1L','OJ006','bt0005',50,'件','Trace-6718b9e0309d1c15fca24ea3',1,5),(36,55,'2024-10-23 16:54:56','深海鳕鱼片','CF007','bt0006',50,'件','Trace-6718b9e0309d1c15fca24ea4',1,5),(37,56,'2024-10-23 16:54:56','新鲜草莓礼盒','FS001','bt0001',50,'件','Trace-6718b9e0309d1c15fca24ea5',1,5),(38,56,'2024-10-23 16:54:56','精选牛肉块','MB003','bt0002',50,'件','Trace-6718b9e0309d1c15fca24ea6',1,5),(39,56,'2024-10-23 16:54:56','野生蓝莓200g','WB004','bt0003',50,'件','Trace-6718b9e0309d1c15fca24ea7',1,5),(40,56,'2024-10-23 16:54:56','农家土鸡蛋12枚','FJ005','bt0004',50,'件','Trace-6718b9e0309d1c15fca24ea8',1,5),(41,56,'2024-10-23 16:54:56','鲜榨橙汁1L','OJ006','bt0005',50,'件','Trace-6718b9e0309d1c15fca24ea9',1,5),(42,56,'2024-10-23 16:54:56','深海鳕鱼片','CF007','bt0006',50,'件','Trace-6718b9e0309d1c15fca24eaa',1,5),(43,57,'2024-10-23 16:54:56','新鲜草莓礼盒','FS001','bt0001',50,'件','Trace-6718b9e0309d1c15fca24eab',1,5),(44,57,'2024-10-23 16:54:56','精选牛肉块','MB003','bt0002',50,'件','Trace-6718b9e0309d1c15fca24eac',1,5),(45,57,'2024-10-23 16:54:56','野生蓝莓200g','WB004','bt0003',50,'件','Trace-6718b9e0309d1c15fca24ead',1,5),(46,57,'2024-10-23 16:54:56','农家土鸡蛋12枚','FJ005','bt0004',50,'件','Trace-6718b9e0309d1c15fca24eae',1,5),(47,57,'2024-10-23 16:54:56','鲜榨橙汁1L','OJ006','bt0005',50,'件','Trace-6718b9e0309d1c15fca24eaf',1,5),(48,57,'2024-10-23 16:54:56','深海鳕鱼片','CF007','bt0006',50,'件','Trace-6718b9e0309d1c15fca24eb0',1,5),(49,58,'2024-10-23 16:54:56','新鲜草莓礼盒','FS001','bt0001',50,'件','Trace-6718b9e0309d1c15fca24eb1',1,5),(50,58,'2024-10-23 16:54:56','精选牛肉块','MB003','bt0002',50,'件','Trace-6718b9e0309d1c15fca24eb2',1,5),(51,58,'2024-10-23 16:54:56','野生蓝莓200g','WB004','bt0003',50,'件','Trace-6718b9e0309d1c15fca24eb3',1,5),(52,58,'2024-10-23 16:54:56','农家土鸡蛋12枚','FJ005','bt0004',50,'件','Trace-6718b9e0309d1c15fca24eb4',1,5),(53,58,'2024-10-23 16:54:56','鲜榨橙汁1L','OJ006','bt0005',50,'件','Trace-6718b9e0309d1c15fca24eb5',1,5),(54,58,'2024-10-23 16:54:56','深海鳕鱼片','CF007','bt0006',50,'件','Trace-6718b9e0309d1c15fca24eb6',1,5);
/*!40000 ALTER TABLE `entrance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `important_enterprise`
--

DROP TABLE IF EXISTS `important_enterprise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `important_enterprise` (
  `eid` int NOT NULL,
  `oid` int NOT NULL,
  `date` varchar(25) NOT NULL,
  PRIMARY KEY (`eid`),
  KEY `oid` (`oid`),
  CONSTRAINT `important_enterprise_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `enterprise` (`eid`),
  CONSTRAINT `important_enterprise_ibfk_2` FOREIGN KEY (`oid`) REFERENCES `account` (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `important_enterprise`
--

LOCK TABLES `important_enterprise` WRITE;
/*!40000 ALTER TABLE `important_enterprise` DISABLE KEYS */;
INSERT INTO `important_enterprise` VALUES (52,1,'2024-10-30 15:14:38');
/*!40000 ALTER TABLE `important_enterprise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menue`
--

DROP TABLE IF EXISTS `menue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menue` (
  `mid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `icon` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `color` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `parent` int NOT NULL DEFAULT '0',
  `memo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `del` smallint NOT NULL DEFAULT '0',
  PRIMARY KEY (`mid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menue`
--

LOCK TABLES `menue` WRITE;
/*!40000 ALTER TABLE `menue` DISABLE KEYS */;
INSERT INTO `menue` VALUES (1,'追溯主体管理','/subject','TraceSubject.svg','#36a3f7',0,NULL,0),(4,'追溯数据分析','/analysis','TracingData.svg','#f4516c',0,NULL,0),(7,'运行监测管理','/monitor','OperationMonitoring.svg','#34bfa3',0,NULL,0),(8,'追溯环节管理','/segment','TraceabilityProcess.svg','#40c9c6',0,NULL,0),(10,'系统设置管理','/system','SystemSet.svg','#f4516c',0,NULL,0),(11,'环节管理','/segment/manage','Connection','none',8,NULL,0),(12,'超市出场','/segment/manage/entry','Connection','none',11,NULL,0),(13,'超市进场','/segment/manage/appears','Connection','none',11,NULL,0),(14,'运行监测管理','/monitor/manage','Monitor','none',7,NULL,0),(15,'采集数据汇总','/monitor/manage/summary','Monitor','none',14,NULL,0),(16,'环节类型数据','/monitor/manage/data','Monitor','none',14,NULL,0),(17,'市场运行分析','/analysis/market','ShoppingTrolley','none',4,NULL,0),(18,'市场运行分析','/analysis/market/operations','ShoppingTrolley','none',17,NULL,0),(19,'重点行业分析','/analysis/industry','PieChart','none',4,NULL,0),(20,'超市数据分析','/analysis/industry/market','PieChart','none',19,NULL,0),(21,'批发数据分析','/analysis/industry/wholesale','PieChart','none',19,NULL,0),(22,'加工数据分析','/analysis/industry/process','PieChart','none',19,NULL,0),(23,'行业结构分析','/analysis/industry/struct','PieChart','none',19,NULL,0),(24,'追溯分析报告','/analysis/report','DocumentCopy','none',4,NULL,0),(25,'主体信息备案','/subject/bulk','Notification','none',1,NULL,0),(26,'供应商备案','/subject/bulk/supplier','Service','none',25,NULL,0),(27,'供销商备案','/subject/bulk/vendors','User','none',25,NULL,0),(28,'产品信息备案','/subject/product','MessageBox','none',1,NULL,0),(29,'产品备案','/subject/product/filings','Edit','none',28,NULL,0),(30,'年度报告','/analysis/report/year','DocumentCopy','none',24,NULL,0),(31,'月度报告','/analysis/report/month','DocumentCopy','none',24,NULL,0),(32,'季度报告','/analysis/report/quarter','DocumentCopy','none',24,NULL,0),(33,'平台数据管理','/system/account','User','none',10,NULL,0),(34,'用户管理','/system/account/user','User','none',33,NULL,0),(35,'权限管理','/system/account/role','User','none',33,NULL,0),(36,'公司管理','/system/account/enterprise','User','none',33,NULL,0),(37,'敏感操作记录','/system/sensitive','View','none',10,NULL,0),(38,'账户敏感操作记录','/system/sensitive/account','View','none',37,NULL,0),(39,'企业敏感操作记录','/system/sensitive/enterprise','View','none',37,NULL,0),(40,'角色敏感操作记录','/system/sensitive/role','View','none',37,NULL,0),(41,'种植数据分析','/analysis/industry/plant','PieChart','none',19,NULL,0),(42,'屠宰数据分析','/analysis/industry/butch','PieChart','none',19,NULL,0),(43,'农贸数据分析','/analysis/industry/farm','PieChart','none',19,NULL,0),(44,'养殖数据分析','/analysis/industry/animal','PieChart','none',19,NULL,0);
/*!40000 ALTER TABLE `menue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `month_trace_report`
--

DROP TABLE IF EXISTS `month_trace_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `month_trace_report` (
  `rid` int NOT NULL AUTO_INCREMENT,
  `operate_date` varchar(25) NOT NULL,
  `date` varchar(25) NOT NULL,
  `market_approach` json NOT NULL,
  `market_entrance` json NOT NULL,
  `batch_approach` json NOT NULL,
  `batch_entrance` json NOT NULL,
  `butch_approach` json NOT NULL,
  `butch_entrance` json NOT NULL,
  `process_approach` json NOT NULL,
  `process_entrance` json NOT NULL,
  `plant_approach` json NOT NULL,
  `plant_entrance` json NOT NULL,
  `farm_approach` json NOT NULL,
  `farm_entrance` json NOT NULL,
  `animal_approach` json NOT NULL,
  `animal_entrance` json NOT NULL,
  `focus_enterprise_data` json NOT NULL,
  `area_data` json NOT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `month_trace_report`
--

LOCK TABLES `month_trace_report` WRITE;
/*!40000 ALTER TABLE `month_trace_report` DISABLE KEYS */;
INSERT INTO `month_trace_report` VALUES (1,'2024-11-18 17:14:38','2024-11','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"连锁超市\", \"total\": \"0\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"连锁超市\", \"total\": \"0\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"批发市场\", \"total\": \"0\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"批发市场\", \"total\": \"0\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"屠宰企业\", \"total\": \"0\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"屠宰企业\", \"total\": \"0\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"生产加工\", \"total\": \"0\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"生产加工\", \"total\": \"0\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"种植企业\", \"total\": \"0\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"种植企业\", \"total\": \"0\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"农贸市场\", \"total\": \"0\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"农贸市场\", \"total\": \"0\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"养殖企业\", \"total\": \"0\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"养殖企业\", \"total\": \"0\", \"popular\": null}','[{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"Sem Egestas Blandit LLC\", \"type\": \"7\", \"total\": \"0\"}]','[{\"name\": \"福建省\", \"total\": 0}, {\"name\": \"西藏自治区\", \"total\": 0}, {\"name\": \"贵州省\", \"total\": 0}, {\"name\": \"上海市\", \"total\": 0}, {\"name\": \"湖北省\", \"total\": 0}, {\"name\": \"湖南省\", \"total\": 0}, {\"name\": \"广东省\", \"total\": 0}, {\"name\": \"澳门特别行政区\", \"total\": 0}, {\"name\": \"香港特别行政区\", \"total\": 0}, {\"name\": \"安徽省\", \"total\": 0}, {\"name\": \"四川省\", \"total\": 0}, {\"name\": \"新疆维吾尔自治区\", \"total\": 0}, {\"name\": \"江苏省\", \"total\": 0}, {\"name\": \"吉林省\", \"total\": 0}, {\"name\": \"宁夏回族自治区\", \"total\": 0}, {\"name\": \"河北省\", \"total\": 0}, {\"name\": \"河南省\", \"total\": 0}, {\"name\": \"广西壮族自治区\", \"total\": 0}, {\"name\": \"海南省\", \"total\": 0}, {\"name\": \"江西省\", \"total\": 0}, {\"name\": \"重庆市\", \"total\": 0}, {\"name\": \"云南省\", \"total\": 0}, {\"name\": \"北京市\", \"total\": 0}, {\"name\": \"甘肃省\", \"total\": 0}, {\"name\": \"山东省\", \"total\": 0}, {\"name\": \"陕西省\", \"total\": 0}, {\"name\": \"浙江省\", \"total\": 0}, {\"name\": \"内蒙古自治区\", \"total\": 0}, {\"name\": \"青海省\", \"total\": 0}, {\"name\": \"天津市\", \"total\": 0}, {\"name\": \"辽宁省\", \"total\": 0}, {\"name\": \"台湾省\", \"total\": 0}, {\"name\": \"黑龙江省\", \"total\": 0}, {\"name\": \"山西省\", \"total\": 0}]');
/*!40000 ALTER TABLE `month_trace_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `platform_data`
--

DROP TABLE IF EXISTS `platform_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `platform_data` (
  `pid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `count` int NOT NULL,
  `date` varchar(25) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `platform_data`
--

LOCK TABLES `platform_data` WRITE;
/*!40000 ALTER TABLE `platform_data` DISABLE KEYS */;
INSERT INTO `platform_data` VALUES (2,'enterprise',299,'2024-12-02');
/*!40000 ALTER TABLE `platform_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `pid` int NOT NULL AUTO_INCREMENT,
  `cid` int NOT NULL,
  `eid` int NOT NULL,
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `code` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `unit` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `is_major` smallint NOT NULL DEFAULT '0',
  PRIMARY KEY (`pid`) USING BTREE,
  KEY `eid` (`eid`) USING BTREE,
  KEY `cid` (`cid`) USING BTREE,
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `enterprise` (`eid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `classification` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (32,5,352,'default.png','FS001','新鲜草莓礼盒','件',1),(33,5,352,'default.png','OG002','有机菠菜500g','件',0),(34,5,352,'default.png','MB003','精选牛肉块','件',0),(35,5,352,'default.png','WB004','野生蓝莓200g','件',0),(36,5,352,'default.png','FJ005','农家土鸡蛋12枚','件',0),(37,5,352,'default.png','OJ006','鲜榨橙汁1L','件',0),(38,5,352,'default.png','CF007','深海鳕鱼片','件',0),(39,5,352,'default.png','GV008','绿叶蔬菜混合包','件',0),(40,5,352,'default.png','OP009','有机黑猪肉','件',0),(41,5,352,'default.png','CT010','散养土鸡整只','件',0),(43,5,52,'default.png','FS001','新鲜草莓礼盒','件',0),(44,5,52,'default.png','MB003','精选牛肉块','件',0),(45,5,52,'default.png','WB004','野生蓝莓200g','件',0),(46,5,52,'default.png','FJ005','农家土鸡蛋12枚','件',0),(47,5,52,'default.png','OJ006','鲜榨橙汁1L','件',0),(48,5,52,'default.png','CF007','深海鳕鱼片','件',0),(49,5,53,'default.png','FS001','新鲜草莓礼盒','件',0),(50,5,53,'default.png','MB003','精选牛肉块','件',0),(51,5,53,'default.png','WB004','野生蓝莓200g','件',0),(52,5,53,'default.png','FJ005','农家土鸡蛋12枚','件',0),(53,5,53,'default.png','OJ006','鲜榨橙汁1L','件',0),(54,5,53,'default.png','CF007','深海鳕鱼片','件',0),(55,5,54,'default.png','FS001','新鲜草莓礼盒','件',0),(56,5,54,'default.png','MB003','精选牛肉块','件',0),(57,5,54,'default.png','WB004','野生蓝莓200g','件',0),(58,5,54,'default.png','FJ005','农家土鸡蛋12枚','件',0),(59,5,54,'default.png','OJ006','鲜榨橙汁1L','件',0),(60,5,54,'default.png','CF007','深海鳕鱼片','件',0),(61,5,55,'default.png','FS001','新鲜草莓礼盒','件',0),(62,5,55,'default.png','MB003','精选牛肉块','件',0),(63,5,55,'default.png','WB004','野生蓝莓200g','件',0),(64,5,55,'default.png','FJ005','农家土鸡蛋12枚','件',0),(65,5,55,'default.png','OJ006','鲜榨橙汁1L','件',0),(66,5,55,'default.png','CF007','深海鳕鱼片','件',0),(67,5,56,'default.png','FS001','新鲜草莓礼盒','件',0),(68,5,56,'default.png','MB003','精选牛肉块','件',0),(69,5,56,'default.png','WB004','野生蓝莓200g','件',0),(70,5,56,'default.png','FJ005','农家土鸡蛋12枚','件',0),(71,5,56,'default.png','OJ006','鲜榨橙汁1L','件',0),(72,5,56,'default.png','CF007','深海鳕鱼片','件',0),(73,5,57,'default.png','FS001','新鲜草莓礼盒','件',0),(74,5,57,'default.png','MB003','精选牛肉块','件',0),(75,5,57,'default.png','WB004','野生蓝莓200g','件',0),(76,5,57,'default.png','FJ005','农家土鸡蛋12枚','件',0),(77,5,57,'default.png','OJ006','鲜榨橙汁1L','件',0),(78,5,57,'default.png','CF007','深海鳕鱼片','件',0),(79,5,58,'default.png','FS001','新鲜草莓礼盒','件',0),(80,5,58,'default.png','MB003','精选牛肉块','件',0),(81,5,58,'default.png','WB004','野生蓝莓200g','件',0),(82,5,58,'default.png','FJ005','农家土鸡蛋12枚','件',0),(83,5,58,'default.png','OJ006','鲜榨橙汁1L','件',0),(84,5,58,'default.png','CF007','深海鳕鱼片','件',0);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_record`
--

DROP TABLE IF EXISTS `product_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_record` (
  `rid` int NOT NULL AUTO_INCREMENT,
  `pid` int NOT NULL,
  `aid` int NOT NULL,
  `num` int NOT NULL,
  `approver` int DEFAULT NULL,
  `process_time` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `insert_time` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `import_type` smallint NOT NULL DEFAULT '0',
  `statue` smallint NOT NULL DEFAULT '0',
  PRIMARY KEY (`rid`) USING BTREE,
  KEY `aid` (`aid`) USING BTREE,
  KEY `approver` (`approver`) USING BTREE,
  KEY `pid` (`pid`) USING BTREE,
  CONSTRAINT `product_record_ibfk_1` FOREIGN KEY (`aid`) REFERENCES `account` (`aid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_record_ibfk_2` FOREIGN KEY (`approver`) REFERENCES `account` (`aid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_record_ibfk_3` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_record`
--

LOCK TABLES `product_record` WRITE;
/*!40000 ALTER TABLE `product_record` DISABLE KEYS */;
INSERT INTO `product_record` VALUES (32,32,1,50,1,'2024-08-04 15:47:18','2024-08-04 11:20:07',0,1),(33,33,1,50,1,'2024-08-04 16:03:29','2024-08-04 11:01:22',0,2),(34,34,1,50,1,'2024-08-04 16:24:02','2024-08-04 11:01:22',0,1),(35,35,1,50,1,'2024-08-04 16:24:02','2024-08-04 11:01:22',0,1),(36,36,1,50,1,'2024-08-04 16:24:02','2024-08-04 11:01:22',0,1),(37,37,1,50,1,'2024-08-04 16:24:02','2024-08-04 11:01:22',0,1),(38,38,1,50,1,'2024-08-04 16:24:02','2024-08-04 11:01:22',0,1),(39,39,1,50,1,'2024-08-04 16:24:45','2024-08-04 11:01:22',0,2),(40,40,1,50,1,'2024-08-04 16:24:45','2024-08-04 11:01:22',0,2),(41,41,1,50,1,'2024-08-04 16:24:45','2024-08-04 11:01:22',0,2),(42,43,1,50,1,'2024-10-22 15:47:46','2024-10-22 15:47:34',0,1),(43,44,1,50,1,'2024-10-22 15:47:46','2024-10-22 15:47:34',0,1),(44,45,1,50,1,'2024-10-22 15:47:46','2024-10-22 15:47:34',0,1),(45,46,1,50,1,'2024-10-22 15:47:46','2024-10-22 15:47:34',0,1),(46,47,1,50,1,'2024-10-22 15:47:46','2024-10-22 15:47:34',0,1),(47,48,1,50,1,'2024-10-22 15:47:46','2024-10-22 15:47:34',0,1),(48,49,1,50,1,'2024-10-22 15:47:46','2024-10-22 15:47:34',0,1),(49,50,1,50,1,'2024-10-22 15:47:46','2024-10-22 15:47:34',0,1),(50,51,1,50,1,'2024-10-22 15:47:46','2024-10-22 15:47:34',0,1),(51,52,1,50,1,'2024-10-22 15:47:46','2024-10-22 15:47:34',0,1),(52,53,1,50,1,'2024-10-22 15:47:46','2024-10-22 15:47:34',0,1),(53,54,1,50,1,'2024-10-22 15:47:46','2024-10-22 15:47:34',0,1),(54,55,1,50,1,'2024-10-22 15:47:46','2024-10-22 15:47:34',0,1),(55,56,1,50,1,'2024-10-22 15:47:46','2024-10-22 15:47:34',0,1),(56,57,1,50,1,'2024-10-22 15:47:46','2024-10-22 15:47:34',0,1),(57,58,1,50,1,'2024-10-22 15:47:46','2024-10-22 15:47:34',0,1),(58,59,1,50,1,'2024-10-22 15:47:46','2024-10-22 15:47:34',0,1),(59,60,1,50,1,'2024-10-22 15:47:46','2024-10-22 15:47:34',0,1),(60,61,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(61,62,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(62,63,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(63,64,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(64,65,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(65,66,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(66,67,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(67,68,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(68,69,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(69,70,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(70,71,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(71,72,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(72,73,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(73,74,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(74,75,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(75,76,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(76,77,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(77,78,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(78,79,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(79,80,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(80,81,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(81,82,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(82,83,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1),(83,84,1,50,1,'2024-10-23 16:53:05','2024-10-23 16:52:53',0,1);
/*!40000 ALTER TABLE `product_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quarter_trace_report`
--

DROP TABLE IF EXISTS `quarter_trace_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quarter_trace_report` (
  `rid` int NOT NULL AUTO_INCREMENT,
  `operate_date` varchar(25) NOT NULL,
  `date` varchar(25) NOT NULL,
  `market_approach` json NOT NULL,
  `market_entrance` json NOT NULL,
  `batch_approach` json NOT NULL,
  `batch_entrance` json NOT NULL,
  `butch_approach` json NOT NULL,
  `butch_entrance` json NOT NULL,
  `process_approach` json NOT NULL,
  `process_entrance` json NOT NULL,
  `plant_approach` json NOT NULL,
  `plant_entrance` json NOT NULL,
  `farm_approach` json NOT NULL,
  `farm_entrance` json NOT NULL,
  `animal_approach` json NOT NULL,
  `animal_entrance` json NOT NULL,
  `focus_enterprise_data` json NOT NULL,
  `area_data` json NOT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quarter_trace_report`
--

LOCK TABLES `quarter_trace_report` WRITE;
/*!40000 ALTER TABLE `quarter_trace_report` DISABLE KEYS */;
INSERT INTO `quarter_trace_report` VALUES (1,'2024-11-19 17:03:01','2024-11','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"连锁超市\", \"total\": \"0\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"连锁超市\", \"total\": \"6\", \"popular\": \"Sem Egestas Blandit LLC\"}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"批发市场\", \"total\": \"0\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"批发市场\", \"total\": \"6\", \"popular\": \"Mauris Sapien Consulting\"}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"屠宰企业\", \"total\": \"0\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"屠宰企业\", \"total\": \"6\", \"popular\": \"Donec Nibh LLC\"}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"生产加工\", \"total\": \"0\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"生产加工\", \"total\": \"6\", \"popular\": \"Blandit Nam Associates\"}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"种植企业\", \"total\": \"0\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"种植企业\", \"total\": \"6\", \"popular\": \"Neque Pellentesque Foundation\"}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"农贸市场\", \"total\": \"0\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"农贸市场\", \"total\": \"6\", \"popular\": \"Auctor Non LLC\"}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"养殖企业\", \"total\": \"0\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"养殖企业\", \"total\": \"6\", \"popular\": \"Penatibus Et Inc.\"}','[{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"Sem Egestas Blandit LLC\", \"type\": \"7\", \"total\": \"18\"}]','[{\"name\": \"福建省\", \"total\": 6}, {\"name\": \"西藏自治区\", \"total\": 0}, {\"name\": \"贵州省\", \"total\": 6}, {\"name\": \"上海市\", \"total\": 0}, {\"name\": \"湖北省\", \"total\": 0}, {\"name\": \"湖南省\", \"total\": 0}, {\"name\": \"广东省\", \"total\": 6}, {\"name\": \"澳门特别行政区\", \"total\": 0}, {\"name\": \"香港特别行政区\", \"total\": 0}, {\"name\": \"安徽省\", \"total\": 6}, {\"name\": \"四川省\", \"total\": 0}, {\"name\": \"新疆维吾尔自治区\", \"total\": 0}, {\"name\": \"江苏省\", \"total\": 0}, {\"name\": \"吉林省\", \"total\": 0}, {\"name\": \"宁夏回族自治区\", \"total\": 0}, {\"name\": \"河北省\", \"total\": 0}, {\"name\": \"河南省\", \"total\": 0}, {\"name\": \"广西壮族自治区\", \"total\": 0}, {\"name\": \"海南省\", \"total\": 6}, {\"name\": \"江西省\", \"total\": 0}, {\"name\": \"重庆市\", \"total\": 0}, {\"name\": \"云南省\", \"total\": 0}, {\"name\": \"北京市\", \"total\": 6}, {\"name\": \"甘肃省\", \"total\": 6}, {\"name\": \"山东省\", \"total\": 0}, {\"name\": \"陕西省\", \"total\": 0}, {\"name\": \"浙江省\", \"total\": 0}, {\"name\": \"内蒙古自治区\", \"total\": 0}, {\"name\": \"青海省\", \"total\": 0}, {\"name\": \"天津市\", \"total\": 0}, {\"name\": \"辽宁省\", \"total\": 0}, {\"name\": \"台湾省\", \"total\": 0}, {\"name\": \"黑龙江省\", \"total\": 0}, {\"name\": \"山西省\", \"total\": 0}]');
/*!40000 ALTER TABLE `quarter_trace_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `rid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `memo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `del` smallint DEFAULT '0',
  `ban` smallint DEFAULT '0',
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'超级系统管理员','开发人员',0,0),(8,'企业用户','普通用户',0,0),(9,'系统管理员','维护溯源系统人员',0,0),(10,'监管人员','分析追溯数据人员',0,0);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_menue_contrast`
--

DROP TABLE IF EXISTS `role_menue_contrast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_menue_contrast` (
  `rid` int NOT NULL,
  `mid` int NOT NULL,
  PRIMARY KEY (`rid`,`mid`) USING BTREE,
  KEY `mid` (`mid`) USING BTREE,
  CONSTRAINT `role_menue_contrast_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_menue_contrast_ibfk_2` FOREIGN KEY (`mid`) REFERENCES `menue` (`mid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_menue_contrast`
--

LOCK TABLES `role_menue_contrast` WRITE;
/*!40000 ALTER TABLE `role_menue_contrast` DISABLE KEYS */;
INSERT INTO `role_menue_contrast` VALUES (1,1),(8,1),(1,4),(10,4),(1,7),(8,7),(10,7),(1,8),(10,8),(1,10),(9,10),(1,11),(10,11),(1,12),(10,12),(1,13),(10,13),(1,14),(8,14),(10,14),(1,15),(8,15),(10,15),(1,16),(8,16),(10,16),(1,17),(10,17),(1,18),(10,18),(1,19),(10,19),(1,20),(10,20),(1,21),(10,21),(1,22),(10,22),(1,23),(10,23),(1,24),(10,24),(1,25),(8,25),(1,26),(8,26),(1,27),(8,27),(1,28),(8,28),(1,29),(8,29),(1,30),(10,30),(1,31),(10,31),(1,32),(10,32),(1,33),(9,33),(1,34),(1,35),(9,35),(1,36),(9,36),(1,37),(9,37),(1,38),(9,38),(1,39),(9,39),(1,40),(9,40),(1,41),(1,42),(1,43),(1,44);
/*!40000 ALTER TABLE `role_menue_contrast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_operate`
--

DROP TABLE IF EXISTS `role_operate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_operate` (
  `iid` int NOT NULL AUTO_INCREMENT,
  `oid` int NOT NULL,
  `rid` int NOT NULL,
  `operate` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `operate_time` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`iid`) USING BTREE,
  KEY `oid` (`oid`) USING BTREE,
  KEY `rid` (`rid`) USING BTREE,
  CONSTRAINT `role_operate_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `account` (`aid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_operate_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_operate`
--

LOCK TABLES `role_operate` WRITE;
/*!40000 ALTER TABLE `role_operate` DISABLE KEYS */;
INSERT INTO `role_operate` VALUES (1,1,8,'创建角色','2024-07-29 19:36:29'),(2,1,9,'创建角色','2024-07-30 09:01:50'),(3,1,10,'创建角色','2024-07-30 09:02:41'),(4,1,8,'修改角色状态','2024-07-30 10:24:08'),(5,1,8,'修改角色状态','2024-07-30 10:25:23'),(6,1,8,'修改角色状态','2024-07-30 10:27:07'),(7,1,8,'修改角色状态','2024-07-30 10:29:42'),(8,1,8,'修改角色状态','2024-07-30 10:29:58'),(9,1,8,'修改角色状态','2024-07-30 10:30:02'),(10,1,8,'修改角色状态','2024-07-30 10:31:21'),(11,1,8,'修改角色状态','2024-07-30 10:36:19'),(12,1,8,'修改角色状态','2024-07-30 10:38:17'),(13,1,8,'修改角色信息','2024-07-30 12:20:31'),(14,1,8,'修改角色信息','2024-07-30 12:20:51'),(15,1,1,'禁用所有角色','2024-07-30 13:08:46'),(16,1,1,'启用所有角色','2024-07-30 13:09:21'),(17,1,1,'启用所有角色','2024-07-30 13:10:07'),(18,1,1,'启用所有角色','2024-07-30 13:10:35'),(19,1,8,'修改角色状态','2024-07-30 13:13:10'),(20,1,9,'修改角色状态','2024-07-30 13:13:12'),(21,1,10,'修改角色状态','2024-07-30 13:13:16'),(22,1,1,'启用所有角色','2024-07-30 13:13:23'),(23,1,8,'修改角色状态','2024-07-30 13:33:43'),(24,1,8,'修改角色状态','2024-07-30 13:34:12'),(25,1,9,'修改角色信息','2024-07-30 14:08:38'),(26,1,8,'修改角色状态','2024-08-04 11:24:41'),(27,1,8,'修改角色状态','2024-08-04 11:24:58');
/*!40000 ALTER TABLE `role_operate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `struct_report`
--

DROP TABLE IF EXISTS `struct_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `struct_report` (
  `sid` int NOT NULL AUTO_INCREMENT,
  `generate` varchar(25) NOT NULL,
  `date` varchar(25) NOT NULL,
  `plant_total` int NOT NULL DEFAULT '0',
  `farm_total` int NOT NULL DEFAULT '0',
  `batch_total` int NOT NULL DEFAULT '0',
  `butch_total` int NOT NULL DEFAULT '0',
  `animal_total` int NOT NULL DEFAULT '0',
  `process_total` int NOT NULL DEFAULT '0',
  `market_total` int NOT NULL DEFAULT '0',
  `plant_point` int NOT NULL DEFAULT '0',
  `farm_point` int NOT NULL DEFAULT '0',
  `batch_point` int NOT NULL DEFAULT '0',
  `butch_point` int NOT NULL DEFAULT '0',
  `animal_point` int NOT NULL DEFAULT '0',
  `process_point` int NOT NULL DEFAULT '0',
  `market_point` int NOT NULL DEFAULT '0',
  `point_struct` json DEFAULT NULL,
  `point_focus` json DEFAULT NULL,
  `point_rate` json DEFAULT NULL,
  `type` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `struct_report`
--

LOCK TABLES `struct_report` WRITE;
/*!40000 ALTER TABLE `struct_report` DISABLE KEYS */;
INSERT INTO `struct_report` VALUES (18,'2024-11-11 08:51:44','2024-10',1,1,1,1,1,1,1,0,0,0,0,0,0,1,'[{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"Sem Egestas Blandit LLC\", \"type\": \"7\", \"total\": \"18\"}]','[{\"name\": \"Sem Egestas Blandit LLC\", \"type\": \"7\", \"total\": \"11.39%\"}]','[{\"name\": \"Sem Egestas Blandit LLC\", \"type\": \"7\", \"total\": \"100%\"}]',1);
/*!40000 ALTER TABLE `struct_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `sid` int NOT NULL AUTO_INCREMENT,
  `eid` int NOT NULL,
  `code` varchar(26) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` smallint NOT NULL DEFAULT '0',
  `generate` smallint NOT NULL DEFAULT '0',
  `is_trace` smallint NOT NULL DEFAULT '0',
  `del` smallint DEFAULT '0',
  PRIMARY KEY (`sid`) USING BTREE,
  KEY `eid` (`eid`) USING BTREE,
  CONSTRAINT `supplier_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `enterprise` (`eid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=623 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (323,1,'66b1b4cb37265bde76e247c6',0,0,0,0),(324,52,'66b1b4cb37265bde76e247c7',0,0,0,0),(325,53,'66b1b4cb37265bde76e247c8',0,0,0,0),(326,54,'66b1b4cb37265bde76e247c9',1,0,0,0),(327,55,'66b1b4cb37265bde76e247ca',2,0,0,0),(328,56,'66b1b4cb37265bde76e247cb',1,0,0,0),(329,57,'66b1b4cb37265bde76e247cc',2,0,0,0),(330,58,'66b1b4cb37265bde76e247cd',2,0,0,0),(331,59,'66b1b4cb37265bde76e247ce',2,0,0,0),(332,60,'66b1b4cb37265bde76e247cf',2,0,0,0),(333,61,'66b1b4cb37265bde76e247d0',2,0,0,0),(334,62,'66b1b4cb37265bde76e247d1',1,0,0,0),(335,63,'66b1b4cb37265bde76e247d2',2,0,0,0),(336,64,'66b1b4cb37265bde76e247d3',1,0,0,0),(337,65,'66b1b4cb37265bde76e247d4',2,0,0,0),(338,66,'66b1b4cb37265bde76e247d5',2,0,0,0),(339,67,'66b1b4cb37265bde76e247d6',1,0,0,0),(340,68,'66b1b4cb37265bde76e247d7',2,0,0,0),(341,69,'66b1b4cb37265bde76e247d8',2,0,0,0),(342,70,'66b1b4cb37265bde76e247d9',1,0,0,0),(343,71,'66b1b4cb37265bde76e247da',0,0,0,0),(344,72,'66b1b4cb37265bde76e247db',1,0,0,0),(345,73,'66b1b4cb37265bde76e247dc',0,0,0,0),(346,74,'66b1b4cb37265bde76e247dd',2,0,0,0),(347,75,'66b1b4cb37265bde76e247de',0,0,0,0),(348,76,'66b1b4cb37265bde76e247df',0,0,0,0),(349,77,'66b1b4cb37265bde76e247e0',1,0,0,0),(350,78,'66b1b4cb37265bde76e247e1',1,0,0,0),(351,79,'66b1b4cb37265bde76e247e2',1,0,0,0),(352,80,'66b1b4cb37265bde76e247e3',1,0,0,0),(353,81,'66b1b4cb37265bde76e247e4',2,0,0,0),(354,82,'66b1b4cb37265bde76e247e5',1,0,0,0),(355,83,'66b1b4cb37265bde76e247e6',0,0,0,0),(356,84,'66b1b4cb37265bde76e247e7',1,0,0,0),(357,85,'66b1b4cb37265bde76e247e8',2,0,0,0),(358,86,'66b1b4cb37265bde76e247e9',1,0,0,0),(359,87,'66b1b4cb37265bde76e247ea',2,0,0,0),(360,88,'66b1b4cb37265bde76e247eb',2,0,0,0),(361,89,'66b1b4cb37265bde76e247ec',2,0,0,0),(362,90,'66b1b4cb37265bde76e247ed',1,0,0,0),(363,91,'66b1b4cb37265bde76e247ee',0,0,0,0),(364,92,'66b1b4cb37265bde76e247ef',2,0,0,0),(365,93,'66b1b4cb37265bde76e247f0',0,0,0,0),(366,94,'66b1b4cb37265bde76e247f1',2,0,0,0),(367,95,'66b1b4cb37265bde76e247f2',2,0,0,0),(368,96,'66b1b4cb37265bde76e247f3',0,0,0,0),(369,97,'66b1b4cb37265bde76e247f4',1,0,0,0),(370,98,'66b1b4cb37265bde76e247f5',1,0,0,0),(371,99,'66b1b4cb37265bde76e247f6',0,0,0,0),(372,100,'66b1b4cb37265bde76e247f7',2,0,0,0),(373,101,'66b1b4cb37265bde76e247f8',2,0,0,0),(374,102,'66b1b4cb37265bde76e247f9',1,0,0,0),(375,103,'66b1b4cb37265bde76e247fa',2,0,0,0),(376,104,'66b1b4cb37265bde76e247fb',1,0,0,0),(377,105,'66b1b4cb37265bde76e247fc',1,0,0,0),(378,106,'66b1b4cb37265bde76e247fd',1,0,0,0),(379,107,'66b1b4cb37265bde76e247fe',0,0,0,0),(380,108,'66b1b4cb37265bde76e247ff',1,0,0,0),(381,109,'66b1b4cb37265bde76e24800',2,0,0,0),(382,110,'66b1b4cb37265bde76e24801',2,0,0,0),(383,111,'66b1b4cb37265bde76e24802',0,0,0,0),(384,112,'66b1b4cb37265bde76e24803',0,0,0,0),(385,113,'66b1b4cb37265bde76e24804',2,0,0,0),(386,114,'66b1b4cb37265bde76e24805',0,0,0,0),(387,115,'66b1b4cb37265bde76e24806',1,0,0,0),(388,116,'66b1b4cb37265bde76e24807',0,0,0,0),(389,117,'66b1b4cb37265bde76e24808',1,0,0,0),(390,118,'66b1b4cb37265bde76e24809',2,0,0,0),(391,119,'66b1b4cb37265bde76e2480a',2,0,0,0),(392,120,'66b1b4cb37265bde76e2480b',1,0,0,0),(393,121,'66b1b4cb37265bde76e2480c',2,0,0,0),(394,122,'66b1b4cb37265bde76e2480d',0,0,0,0),(395,123,'66b1b4cb37265bde76e2480e',0,0,0,0),(396,124,'66b1b4cb37265bde76e2480f',2,0,0,0),(397,125,'66b1b4cb37265bde76e24810',0,0,0,0),(398,126,'66b1b4cb37265bde76e24811',2,0,0,0),(399,127,'66b1b4cb37265bde76e24812',1,0,0,0),(400,128,'66b1b4cb37265bde76e24813',2,0,0,0),(401,129,'66b1b4cb37265bde76e24814',2,0,0,0),(402,130,'66b1b4cb37265bde76e24815',2,0,0,0),(403,131,'66b1b4cb37265bde76e24816',0,0,0,0),(404,132,'66b1b4cb37265bde76e24817',0,0,0,0),(405,133,'66b1b4cb37265bde76e24818',2,0,0,0),(406,134,'66b1b4cb37265bde76e24819',2,0,0,0),(407,135,'66b1b4cb37265bde76e2481a',1,0,0,0),(408,136,'66b1b4cb37265bde76e2481b',2,0,0,0),(409,137,'66b1b4cb37265bde76e2481c',1,0,0,0),(410,138,'66b1b4cb37265bde76e2481d',0,0,0,0),(411,139,'66b1b4cb37265bde76e2481e',0,0,0,0),(412,140,'66b1b4cb37265bde76e2481f',0,0,0,0),(413,141,'66b1b4cb37265bde76e24820',1,0,0,0),(414,142,'66b1b4cb37265bde76e24821',2,0,0,0),(415,143,'66b1b4cb37265bde76e24822',2,0,0,0),(416,144,'66b1b4cb37265bde76e24823',2,0,0,0),(417,145,'66b1b4cb37265bde76e24824',0,0,0,0),(418,146,'66b1b4cb37265bde76e24825',2,0,0,0),(419,147,'66b1b4cb37265bde76e24826',0,0,0,0),(420,148,'66b1b4cb37265bde76e24827',0,0,0,0),(421,150,'66b1b4cb37265bde76e24828',2,0,0,0),(422,151,'66b1b4cb37265bde76e24829',0,0,0,0),(423,152,'66b1b4cb37265bde76e2482a',2,0,0,0),(424,153,'66b1b4cb37265bde76e2482b',1,0,0,0),(425,154,'66b1b4cb37265bde76e2482c',1,0,0,0),(426,155,'66b1b4cb37265bde76e2482d',2,0,0,0),(427,156,'66b1b4cb37265bde76e2482e',1,0,0,0),(428,157,'66b1b4cb37265bde76e2482f',1,0,0,0),(429,158,'66b1b4cb37265bde76e24830',0,0,0,0),(430,159,'66b1b4cb37265bde76e24831',1,0,0,0),(431,160,'66b1b4cb37265bde76e24832',0,0,0,0),(432,161,'66b1b4cb37265bde76e24833',0,0,0,0),(433,162,'66b1b4cb37265bde76e24834',0,0,0,0),(434,163,'66b1b4cb37265bde76e24835',0,0,0,0),(435,164,'66b1b4cb37265bde76e24836',2,0,0,0),(436,165,'66b1b4cb37265bde76e24837',0,0,0,0),(437,166,'66b1b4cb37265bde76e24838',0,0,0,0),(438,167,'66b1b4cb37265bde76e24839',2,0,0,0),(439,168,'66b1b4cb37265bde76e2483a',1,0,0,0),(440,169,'66b1b4cb37265bde76e2483b',1,0,0,0),(441,170,'66b1b4cb37265bde76e2483c',0,0,0,0),(442,171,'66b1b4cb37265bde76e2483d',0,0,0,0),(443,172,'66b1b4cb37265bde76e2483e',2,0,0,0),(444,173,'66b1b4cb37265bde76e2483f',0,0,0,0),(445,174,'66b1b4cb37265bde76e24840',1,0,0,0),(446,175,'66b1b4cb37265bde76e24841',2,0,0,0),(447,176,'66b1b4cb37265bde76e24842',0,0,0,0),(448,177,'66b1b4cb37265bde76e24843',0,0,0,0),(449,178,'66b1b4cb37265bde76e24844',0,0,0,0),(450,179,'66b1b4cb37265bde76e24845',0,0,0,0),(451,180,'66b1b4cb37265bde76e24846',2,0,0,0),(452,181,'66b1b4cb37265bde76e24847',1,0,0,0),(453,182,'66b1b4cb37265bde76e24848',0,0,0,0),(454,183,'66b1b4cb37265bde76e24849',2,0,0,0),(455,184,'66b1b4cb37265bde76e2484a',1,0,0,0),(456,185,'66b1b4cb37265bde76e2484b',1,0,0,0),(457,186,'66b1b4cb37265bde76e2484c',1,0,0,0),(458,187,'66b1b4cb37265bde76e2484d',2,0,0,0),(459,188,'66b1b4cb37265bde76e2484e',1,0,0,0),(460,189,'66b1b4cb37265bde76e2484f',1,0,0,0),(461,190,'66b1b4cb37265bde76e24850',1,0,0,0),(462,191,'66b1b4cb37265bde76e24851',0,0,0,0),(463,192,'66b1b4cb37265bde76e24852',1,0,0,0),(464,193,'66b1b4cb37265bde76e24853',2,0,0,0),(465,194,'66b1b4cb37265bde76e24854',0,0,0,0),(466,195,'66b1b4cb37265bde76e24855',0,0,0,0),(467,196,'66b1b4cb37265bde76e24856',0,0,0,0),(468,197,'66b1b4cb37265bde76e24857',1,0,0,0),(469,198,'66b1b4cb37265bde76e24858',2,0,0,0),(470,199,'66b1b4cb37265bde76e24859',0,0,0,0),(471,200,'66b1b4cb37265bde76e2485a',0,0,0,0),(472,201,'66b1b4cb37265bde76e2485b',2,0,0,0),(473,202,'66b1b4cb37265bde76e2485c',0,0,0,0),(474,203,'66b1b4cb37265bde76e2485d',2,0,0,0),(475,204,'66b1b4cb37265bde76e2485e',0,0,0,0),(476,205,'66b1b4cb37265bde76e2485f',2,0,0,0),(477,206,'66b1b4cb37265bde76e24860',0,0,0,0),(478,207,'66b1b4cb37265bde76e24861',2,0,0,0),(479,208,'66b1b4cb37265bde76e24862',0,0,0,0),(480,209,'66b1b4cb37265bde76e24863',0,0,0,0),(481,210,'66b1b4cb37265bde76e24864',1,0,0,0),(482,211,'66b1b4cb37265bde76e24865',0,0,0,0),(483,212,'66b1b4cb37265bde76e24866',2,0,0,0),(484,213,'66b1b4cb37265bde76e24867',2,0,0,0),(485,214,'66b1b4cb37265bde76e24868',2,0,0,0),(486,215,'66b1b4cb37265bde76e24869',1,0,0,0),(487,216,'66b1b4cb37265bde76e2486a',0,0,0,0),(488,217,'66b1b4cb37265bde76e2486b',0,0,0,0),(489,218,'66b1b4cb37265bde76e2486c',0,0,0,0),(490,219,'66b1b4cb37265bde76e2486d',2,0,0,0),(491,220,'66b1b4cb37265bde76e2486e',1,0,0,0),(492,221,'66b1b4cb37265bde76e2486f',1,0,0,0),(493,222,'66b1b4cb37265bde76e24870',1,0,0,0),(494,223,'66b1b4cb37265bde76e24871',1,0,0,0),(495,224,'66b1b4cb37265bde76e24872',1,0,0,0),(496,225,'66b1b4cb37265bde76e24873',2,0,0,0),(497,226,'66b1b4cb37265bde76e24874',2,0,0,0),(498,227,'66b1b4cb37265bde76e24875',2,0,0,0),(499,228,'66b1b4cb37265bde76e24876',1,0,0,0),(500,229,'66b1b4cb37265bde76e24877',1,0,0,0),(501,230,'66b1b4cb37265bde76e24878',1,0,0,0),(502,231,'66b1b4cb37265bde76e24879',0,0,0,0),(503,232,'66b1b4cb37265bde76e2487a',2,0,0,0),(504,233,'66b1b4cb37265bde76e2487b',2,0,0,0),(505,234,'66b1b4cb37265bde76e2487c',2,0,0,0),(506,235,'66b1b4cb37265bde76e2487d',0,0,0,0),(507,236,'66b1b4cb37265bde76e2487e',2,0,0,0),(508,237,'66b1b4cb37265bde76e2487f',1,0,0,0),(509,238,'66b1b4cb37265bde76e24880',0,0,0,0),(510,239,'66b1b4cb37265bde76e24881',0,0,0,0),(511,240,'66b1b4cb37265bde76e24882',0,0,0,0),(512,241,'66b1b4cb37265bde76e24883',1,0,0,0),(513,242,'66b1b4cb37265bde76e24884',1,0,0,0),(514,243,'66b1b4cb37265bde76e24885',1,0,0,0),(515,244,'66b1b4cb37265bde76e24886',1,0,0,0),(516,245,'66b1b4cb37265bde76e24887',1,0,0,0),(517,246,'66b1b4cb37265bde76e24888',0,0,0,0),(518,247,'66b1b4cb37265bde76e24889',2,0,0,0),(519,248,'66b1b4cb37265bde76e2488a',1,0,0,0),(520,249,'66b1b4cb37265bde76e2488b',1,0,0,0),(521,250,'66b1b4cb37265bde76e2488c',0,0,0,0),(522,251,'66b1b4cb37265bde76e2488d',0,0,0,0),(523,252,'66b1b4cb37265bde76e2488e',2,0,0,0),(524,253,'66b1b4cb37265bde76e2488f',2,0,0,0),(525,254,'66b1b4cb37265bde76e24890',0,0,0,0),(526,255,'66b1b4cb37265bde76e24891',1,0,0,0),(527,256,'66b1b4cb37265bde76e24892',1,0,0,0),(528,257,'66b1b4cb37265bde76e24893',1,0,0,0),(529,258,'66b1b4cb37265bde76e24894',0,0,0,0),(530,259,'66b1b4cb37265bde76e24895',2,0,0,0),(531,260,'66b1b4cb37265bde76e24896',1,0,0,0),(532,261,'66b1b4cb37265bde76e24897',1,0,0,0),(533,262,'66b1b4cb37265bde76e24898',1,0,0,0),(534,263,'66b1b4cb37265bde76e24899',2,0,0,0),(535,264,'66b1b4cb37265bde76e2489a',1,0,0,0),(536,265,'66b1b4cb37265bde76e2489b',0,0,0,0),(537,266,'66b1b4cb37265bde76e2489c',0,0,0,0),(538,267,'66b1b4cb37265bde76e2489d',0,0,0,0),(539,268,'66b1b4cb37265bde76e2489e',2,0,0,0),(540,269,'66b1b4cb37265bde76e2489f',2,0,0,0),(541,270,'66b1b4cb37265bde76e248a0',0,0,0,0),(542,271,'66b1b4cb37265bde76e248a1',1,0,0,0),(543,272,'66b1b4cb37265bde76e248a2',1,0,0,0),(544,273,'66b1b4cb37265bde76e248a3',1,0,0,0),(545,274,'66b1b4cb37265bde76e248a4',2,0,0,0),(546,275,'66b1b4cb37265bde76e248a5',0,0,0,0),(547,276,'66b1b4cb37265bde76e248a6',0,0,0,0),(548,277,'66b1b4cb37265bde76e248a7',1,0,0,0),(549,278,'66b1b4cb37265bde76e248a8',0,0,0,0),(550,279,'66b1b4cb37265bde76e248a9',0,0,0,0),(551,280,'66b1b4cb37265bde76e248aa',2,0,0,0),(552,281,'66b1b4cb37265bde76e248ab',0,0,0,0),(553,282,'66b1b4cb37265bde76e248ac',2,0,0,0),(554,283,'66b1b4cb37265bde76e248ad',2,0,0,0),(555,284,'66b1b4cb37265bde76e248ae',2,0,0,0),(556,285,'66b1b4cb37265bde76e248af',1,0,0,0),(557,286,'66b1b4cb37265bde76e248b0',2,0,0,0),(558,287,'66b1b4cb37265bde76e248b1',2,0,0,0),(559,288,'66b1b4cb37265bde76e248b2',2,0,0,0),(560,289,'66b1b4cb37265bde76e248b3',2,0,0,0),(561,290,'66b1b4cb37265bde76e248b4',1,0,0,0),(562,291,'66b1b4cb37265bde76e248b5',2,0,0,0),(563,292,'66b1b4cb37265bde76e248b6',1,0,0,0),(564,293,'66b1b4cb37265bde76e248b7',2,0,0,0),(565,294,'66b1b4cb37265bde76e248b8',0,0,0,0),(566,295,'66b1b4cb37265bde76e248b9',0,0,0,0),(567,296,'66b1b4cb37265bde76e248ba',1,0,0,0),(568,297,'66b1b4cb37265bde76e248bb',2,0,0,0),(569,298,'66b1b4cb37265bde76e248bc',1,0,0,0),(570,299,'66b1b4cb37265bde76e248bd',1,0,0,0),(571,300,'66b1b4cb37265bde76e248be',1,0,0,0),(572,301,'66b1b4cb37265bde76e248bf',2,0,0,0),(573,302,'66b1b4cb37265bde76e248c0',1,0,0,0),(574,303,'66b1b4cb37265bde76e248c1',1,0,0,0),(575,304,'66b1b4cb37265bde76e248c2',0,0,0,0),(576,305,'66b1b4cb37265bde76e248c3',0,0,0,0),(577,306,'66b1b4cb37265bde76e248c4',0,0,0,0),(578,307,'66b1b4cb37265bde76e248c5',2,0,0,0),(579,308,'66b1b4cb37265bde76e248c6',0,0,0,0),(580,309,'66b1b4cb37265bde76e248c7',1,0,0,0),(581,310,'66b1b4cb37265bde76e248c8',2,0,0,0),(582,311,'66b1b4cb37265bde76e248c9',1,0,0,0),(583,312,'66b1b4cb37265bde76e248ca',2,0,0,0),(584,314,'66b1b4cb37265bde76e248cb',1,0,0,0),(585,315,'66b1b4cb37265bde76e248cc',2,0,0,0),(586,316,'66b1b4cb37265bde76e248cd',2,0,0,0),(587,317,'66b1b4cb37265bde76e248ce',2,0,0,0),(588,318,'66b1b4cb37265bde76e248cf',1,0,0,0),(589,319,'66b1b4cb37265bde76e248d0',2,0,0,0),(590,320,'66b1b4cb37265bde76e248d1',1,0,0,0),(591,321,'66b1b4cb37265bde76e248d2',2,0,0,0),(592,322,'66b1b4cb37265bde76e248d3',1,0,0,0),(593,323,'66b1b4cb37265bde76e248d4',2,0,0,0),(594,324,'66b1b4cb37265bde76e248d5',2,0,0,0),(595,325,'66b1b4cb37265bde76e248d6',1,0,0,0),(596,326,'66b1b4cb37265bde76e248d7',1,0,0,0),(597,327,'66b1b4cb37265bde76e248d8',2,0,0,0),(598,328,'66b1b4cb37265bde76e248d9',2,0,0,0),(599,329,'66b1b4cb37265bde76e248da',2,0,0,0),(600,330,'66b1b4cb37265bde76e248db',1,0,0,0),(601,331,'66b1b4cb37265bde76e248dc',0,0,0,0),(602,332,'66b1b4cb37265bde76e248dd',1,0,0,0),(603,333,'66b1b4cb37265bde76e248de',0,0,0,0),(604,334,'66b1b4cb37265bde76e248df',2,0,0,0),(605,335,'66b1b4cb37265bde76e248e0',1,0,0,0),(606,336,'66b1b4cb37265bde76e248e1',2,0,0,0),(607,337,'66b1b4cb37265bde76e248e2',0,0,0,0),(608,338,'66b1b4cb37265bde76e248e3',1,0,0,0),(609,339,'66b1b4cb37265bde76e248e4',2,0,0,0),(610,340,'66b1b4cb37265bde76e248e5',1,0,0,0),(611,341,'66b1b4cb37265bde76e248e6',2,0,0,0),(612,342,'66b1b4cb37265bde76e248e7',0,0,0,0),(613,343,'66b1b4cb37265bde76e248e8',0,0,0,0),(614,344,'66b1b4cb37265bde76e248e9',2,0,0,0),(615,345,'66b1b4cb37265bde76e248ea',1,0,0,0),(616,346,'66b1b4cb37265bde76e248eb',0,0,0,0),(617,347,'66b1b4cb37265bde76e248ec',2,0,0,0),(618,348,'66b1b4cb37265bde76e248ed',1,0,0,0),(619,349,'66b1b4cb37265bde76e248ee',2,0,0,0),(620,350,'66b1b4cb37265bde76e248ef',0,0,0,0),(621,351,'66b1b4cb37265bde76e248f0',0,0,0,0),(622,352,'66b1b4cb37265bde76e248f1',2,0,0,0);
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `year_trace_report`
--

DROP TABLE IF EXISTS `year_trace_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `year_trace_report` (
  `rid` int NOT NULL AUTO_INCREMENT,
  `operate_date` varchar(25) NOT NULL,
  `date` varchar(25) NOT NULL,
  `market_approach` json NOT NULL,
  `market_entrance` json NOT NULL,
  `batch_approach` json NOT NULL,
  `batch_entrance` json NOT NULL,
  `butch_approach` json NOT NULL,
  `butch_entrance` json NOT NULL,
  `process_approach` json NOT NULL,
  `process_entrance` json NOT NULL,
  `plant_approach` json NOT NULL,
  `plant_entrance` json NOT NULL,
  `farm_approach` json NOT NULL,
  `farm_entrance` json NOT NULL,
  `animal_approach` json NOT NULL,
  `animal_entrance` json NOT NULL,
  `focus_enterprise_data` json NOT NULL,
  `area_data` json NOT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `year_trace_report`
--

LOCK TABLES `year_trace_report` WRITE;
/*!40000 ALTER TABLE `year_trace_report` DISABLE KEYS */;
INSERT INTO `year_trace_report` VALUES (4,'2024-11-18 14:56:50','2024','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"连锁超市\", \"total\": \"1\", \"popular\": \"Sem Egestas Blandit LLC\"}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"连锁超市\", \"total\": \"6\", \"popular\": \"Sem Egestas Blandit LLC\"}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"批发市场\", \"total\": \"1\", \"popular\": \"Mauris Sapien Consulting\"}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"批发市场\", \"total\": \"6\", \"popular\": \"Mauris Sapien Consulting\"}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"屠宰企业\", \"total\": \"1\", \"popular\": \"Donec Nibh LLC\"}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"屠宰企业\", \"total\": \"6\", \"popular\": \"Donec Nibh LLC\"}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"生产加工\", \"total\": \"1\", \"popular\": \"Blandit Nam Associates\"}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"生产加工\", \"total\": \"6\", \"popular\": \"Blandit Nam Associates\"}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"种植企业\", \"total\": \"1\", \"popular\": \"Neque Pellentesque Foundation\"}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"种植企业\", \"total\": \"6\", \"popular\": \"Neque Pellentesque Foundation\"}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"农贸市场\", \"total\": \"0\", \"popular\": \"Auctor Non LLC\"}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"农贸市场\", \"total\": \"6\", \"popular\": null}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"养殖企业\", \"total\": \"1\", \"popular\": \"Penatibus Et Inc.\"}','{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"养殖企业\", \"total\": \"6\", \"popular\": \"Penatibus Et Inc.\"}','[{\"qoq\": \"0\", \"yoy\": \"0\", \"name\": \"Sem Egestas Blandit LLC\", \"type\": \"7\", \"total\": \"19\"}]','[{\"name\": \"福建省\", \"total\": 7}, {\"name\": \"西藏自治区\", \"total\": 0}, {\"name\": \"贵州省\", \"total\": 13}, {\"name\": \"上海市\", \"total\": 0}, {\"name\": \"湖北省\", \"total\": 0}, {\"name\": \"湖南省\", \"total\": 0}, {\"name\": \"广东省\", \"total\": 7}, {\"name\": \"澳门特别行政区\", \"total\": 0}, {\"name\": \"香港特别行政区\", \"total\": 0}, {\"name\": \"安徽省\", \"total\": 7}, {\"name\": \"四川省\", \"total\": 0}, {\"name\": \"新疆维吾尔自治区\", \"total\": 0}, {\"name\": \"江苏省\", \"total\": 0}, {\"name\": \"吉林省\", \"total\": 0}, {\"name\": \"宁夏回族自治区\", \"total\": 0}, {\"name\": \"河北省\", \"total\": 0}, {\"name\": \"河南省\", \"total\": 0}, {\"name\": \"广西壮族自治区\", \"total\": 0}, {\"name\": \"海南省\", \"total\": 6}, {\"name\": \"江西省\", \"total\": 0}, {\"name\": \"重庆市\", \"total\": 0}, {\"name\": \"云南省\", \"total\": 0}, {\"name\": \"北京市\", \"total\": 7}, {\"name\": \"甘肃省\", \"total\": 7}, {\"name\": \"山东省\", \"total\": 0}, {\"name\": \"陕西省\", \"total\": 0}, {\"name\": \"浙江省\", \"total\": 0}, {\"name\": \"内蒙古自治区\", \"total\": 0}, {\"name\": \"青海省\", \"total\": 0}, {\"name\": \"天津市\", \"total\": 0}, {\"name\": \"辽宁省\", \"total\": 0}, {\"name\": \"台湾省\", \"total\": 0}, {\"name\": \"黑龙江省\", \"total\": 0}, {\"name\": \"山西省\", \"total\": 0}]');
/*!40000 ALTER TABLE `year_trace_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'trace'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-03  9:30:13
