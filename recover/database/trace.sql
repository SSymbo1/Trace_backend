/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : trace

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 04/08/2024 16:32:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `aid` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `rid` int(0) NOT NULL,
  `del` smallint(0) NOT NULL DEFAULT 0,
  `ban` smallint(0) NOT NULL DEFAULT 0,
  `memo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`aid`) USING BTREE,
  INDEX `rid`(`rid`) USING BTREE,
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'admin', '0aeef548aa12f11f8ab83facc27a8179', 1, 0, 0, NULL);
INSERT INTO `account` VALUES (3, 'test1', '0b3f05baf34ee47287713733c47fd426', 10, 0, 0, NULL);
INSERT INTO `account` VALUES (4, 'test2', '0b3f05baf34ee47287713733c47fd426', 9, 0, 0, NULL);
INSERT INTO `account` VALUES (5, 'test3', '0b3f05baf34ee47287713733c47fd426', 8, 0, 0, NULL);

-- ----------------------------
-- Table structure for account_info
-- ----------------------------
DROP TABLE IF EXISTS `account_info`;
CREATE TABLE `account_info`  (
  `aid` int(0) NOT NULL,
  `eid` int(0) NULL DEFAULT NULL,
  `rid` int(0) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gander` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tel` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'default.png',
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `zip_code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`aid`) USING BTREE,
  INDEX `rid`(`rid`) USING BTREE,
  INDEX `eid`(`eid`) USING BTREE,
  CONSTRAINT `account_info_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `account_info_ibfk_2` FOREIGN KEY (`eid`) REFERENCES `enterprise` (`eid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `account_info_ibfk_3` FOREIGN KEY (`aid`) REFERENCES `account` (`aid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account_info
-- ----------------------------
INSERT INTO `account_info` VALUES (1, 22, 1, 'SSymbol', '男', '13612345611', 'admin.jpg', '1277820742@qq.com', '黑龙江省佳木斯市', '100861');
INSERT INTO `account_info` VALUES (3, 1, 10, 'Jon Stewart Doe', '女', '60195213251', '18b7e2ad-8b3c-4f88-b9e4-4d68410453bb_user1.png', 'test@example.us', '黑龙江省佳木斯市', '94043');
INSERT INTO `account_info` VALUES (4, 1, 9, 'qwe', '女', '12345678901', 'bf7db45d-72b6-4dc0-b1da-d2720ab28f28_89f5d904-d2d0-4889-a096-91ac35165375_user6.jpg', 'test@mail.com', 'address', '123112');
INSERT INTO `account_info` VALUES (5, 1, 8, 'qwerty', '男', '12345678901', 'e3564293-aafc-43f4-b004-7bcb178d89fb_user5.JPG', 'test@test.com', 'address', '131536');

-- ----------------------------
-- Table structure for account_operate
-- ----------------------------
DROP TABLE IF EXISTS `account_operate`;
CREATE TABLE `account_operate`  (
  `iid` int(0) NOT NULL AUTO_INCREMENT,
  `oid` int(0) NOT NULL,
  `aid` int(0) NOT NULL,
  `operate` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `operate_time` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`iid`) USING BTREE,
  INDEX `oid`(`oid`) USING BTREE,
  INDEX `aid`(`aid`) USING BTREE,
  CONSTRAINT `account_operate_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `account` (`aid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `account_operate_ibfk_2` FOREIGN KEY (`aid`) REFERENCES `account` (`aid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account_operate
-- ----------------------------
INSERT INTO `account_operate` VALUES (3, 1, 3, '修改账号状态', '2024-07-26 10:41:14');
INSERT INTO `account_operate` VALUES (4, 1, 1, '创建账号失败', '2024-07-26 11:17:28');
INSERT INTO `account_operate` VALUES (5, 1, 4, '创建账号', '2024-07-26 11:17:34');
INSERT INTO `account_operate` VALUES (6, 1, 1, '创建账号失败', '2024-07-26 11:24:40');
INSERT INTO `account_operate` VALUES (7, 1, 5, '创建账号', '2024-07-26 11:24:46');
INSERT INTO `account_operate` VALUES (8, 1, 4, '修改账号信息', '2024-07-26 01:38:54');
INSERT INTO `account_operate` VALUES (9, 1, 4, '修改账号状态', '2024-07-26 01:39:03');
INSERT INTO `account_operate` VALUES (10, 1, 4, '修改账号状态', '2024-07-26 13:39:12');
INSERT INTO `account_operate` VALUES (11, 1, 4, '修改账号信息', '2024-07-26 13:39:38');
INSERT INTO `account_operate` VALUES (12, 1, 4, '修改账号信息', '2024-07-26 13:42:05');
INSERT INTO `account_operate` VALUES (13, 1, 1, '修改账号信息', '2024-07-26 13:50:26');
INSERT INTO `account_operate` VALUES (14, 1, 5, '修改账号状态', '2024-07-26 15:29:44');
INSERT INTO `account_operate` VALUES (15, 1, 5, '修改账号状态', '2024-07-26 15:29:56');
INSERT INTO `account_operate` VALUES (16, 1, 4, '请求解码密码', '2024-07-26 18:03:08');
INSERT INTO `account_operate` VALUES (17, 1, 4, '请求解码密码', '2024-07-26 18:03:20');
INSERT INTO `account_operate` VALUES (18, 1, 3, '修改账号状态', '2024-07-27 08:17:13');
INSERT INTO `account_operate` VALUES (19, 1, 3, '修改账号状态', '2024-07-27 08:17:24');
INSERT INTO `account_operate` VALUES (20, 1, 3, '请求解码密码', '2024-07-27 08:17:36');
INSERT INTO `account_operate` VALUES (21, 1, 3, '请求解码密码', '2024-07-27 08:17:41');
INSERT INTO `account_operate` VALUES (22, 1, 3, '请求解码密码', '2024-07-27 08:17:52');
INSERT INTO `account_operate` VALUES (23, 1, 3, '请求解码密码', '2024-07-27 08:18:09');
INSERT INTO `account_operate` VALUES (24, 1, 3, '修改账号信息', '2024-07-27 08:18:37');
INSERT INTO `account_operate` VALUES (25, 1, 1, '禁用所有账户', '2024-07-29 10:18:27');
INSERT INTO `account_operate` VALUES (26, 1, 1, '禁用所有账户', '2024-07-29 10:19:22');
INSERT INTO `account_operate` VALUES (27, 1, 1, '禁用所有账户', '2024-07-29 10:20:25');
INSERT INTO `account_operate` VALUES (28, 1, 3, '请求解码密码', '2024-07-29 10:35:44');
INSERT INTO `account_operate` VALUES (29, 1, 3, '请求解码密码', '2024-07-29 10:35:53');
INSERT INTO `account_operate` VALUES (30, 1, 3, '修改账号信息', '2024-07-29 11:02:02');
INSERT INTO `account_operate` VALUES (31, 1, 1, '禁用所有账户', '2024-07-29 11:02:09');
INSERT INTO `account_operate` VALUES (32, 1, 4, '修改账号信息', '2024-07-29 11:02:22');
INSERT INTO `account_operate` VALUES (33, 1, 5, '修改账号信息', '2024-07-29 11:02:28');
INSERT INTO `account_operate` VALUES (34, 1, 1, '禁用所有账户', '2024-07-29 11:02:36');
INSERT INTO `account_operate` VALUES (35, 1, 1, '启用所有账户', '2024-07-29 11:02:46');
INSERT INTO `account_operate` VALUES (36, 1, 4, '修改账号信息', '2024-07-29 17:42:04');
INSERT INTO `account_operate` VALUES (37, 1, 3, '修改账号信息', '2024-07-29 17:42:09');
INSERT INTO `account_operate` VALUES (38, 1, 5, '修改账号信息', '2024-07-29 17:42:13');
INSERT INTO `account_operate` VALUES (39, 1, 5, '修改账号信息', '2024-07-30 09:01:17');
INSERT INTO `account_operate` VALUES (40, 1, 4, '修改账号信息', '2024-07-30 09:02:05');
INSERT INTO `account_operate` VALUES (41, 1, 3, '修改账号信息', '2024-07-30 09:02:52');
INSERT INTO `account_operate` VALUES (42, 1, 5, '修改账号状态', '2024-07-30 10:24:16');
INSERT INTO `account_operate` VALUES (43, 1, 5, '修改账号信息', '2024-07-30 10:24:38');
INSERT INTO `account_operate` VALUES (44, 1, 5, '修改账号状态', '2024-07-30 10:24:45');
INSERT INTO `account_operate` VALUES (45, 1, 5, '修改账号信息', '2024-07-30 10:24:49');
INSERT INTO `account_operate` VALUES (46, 1, 5, '修改账号状态', '2024-07-30 10:27:48');
INSERT INTO `account_operate` VALUES (47, 1, 5, '修改账号状态', '2024-07-30 10:31:30');
INSERT INTO `account_operate` VALUES (48, 1, 5, '修改账号信息', '2024-07-30 10:37:59');
INSERT INTO `account_operate` VALUES (49, 1, 5, '修改账号状态', '2024-07-30 10:38:23');
INSERT INTO `account_operate` VALUES (50, 1, 1, '禁用所有账户', '2024-07-30 12:55:27');
INSERT INTO `account_operate` VALUES (51, 1, 1, '启用所有账户', '2024-07-30 12:55:39');
INSERT INTO `account_operate` VALUES (52, 1, 3, '修改账号状态', '2024-07-30 13:08:54');
INSERT INTO `account_operate` VALUES (53, 1, 4, '修改账号状态', '2024-07-30 13:08:59');
INSERT INTO `account_operate` VALUES (54, 1, 5, '修改账号状态', '2024-07-30 13:09:02');
INSERT INTO `account_operate` VALUES (55, 1, 3, '修改账号信息', '2024-07-30 13:09:08');
INSERT INTO `account_operate` VALUES (56, 1, 1, '启用所有账户', '2024-07-30 13:13:32');
INSERT INTO `account_operate` VALUES (57, 1, 1, '启用所有账户', '2024-07-30 13:33:55');
INSERT INTO `account_operate` VALUES (58, 1, 1, '启用所有账户', '2024-07-30 13:34:26');
INSERT INTO `account_operate` VALUES (59, 1, 5, '修改账号状态', '2024-08-04 11:24:47');
INSERT INTO `account_operate` VALUES (60, 1, 5, '修改账号状态', '2024-08-04 11:25:04');

-- ----------------------------
-- Table structure for classification
-- ----------------------------
DROP TABLE IF EXISTS `classification`;
CREATE TABLE `classification`  (
  `cid` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `parent` int(0) NOT NULL DEFAULT 0,
  `memo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `del` smallint(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classification
-- ----------------------------
INSERT INTO `classification` VALUES (1, '生鲜食品', 0, NULL, 0);
INSERT INTO `classification` VALUES (2, '加工食品', 0, NULL, 0);
INSERT INTO `classification` VALUES (3, '饮料与酒水', 0, NULL, 0);
INSERT INTO `classification` VALUES (4, '食品杂货', 0, NULL, 0);
INSERT INTO `classification` VALUES (5, '水果与蔬菜', 1, NULL, 0);
INSERT INTO `classification` VALUES (6, '肉类与禽类', 1, NULL, 0);
INSERT INTO `classification` VALUES (7, '海鲜与水产', 1, NULL, 0);
INSERT INTO `classification` VALUES (8, '罐头食品', 2, NULL, 0);
INSERT INTO `classification` VALUES (9, '冷冻食品', 2, NULL, 0);
INSERT INTO `classification` VALUES (10, '糖果与巧克力', 2, NULL, 0);
INSERT INTO `classification` VALUES (11, '碳酸饮料', 3, NULL, 0);
INSERT INTO `classification` VALUES (12, '果汁与饮料', 3, NULL, 0);
INSERT INTO `classification` VALUES (13, '咖啡与茶类', 3, NULL, 0);
INSERT INTO `classification` VALUES (14, '酒类', 3, NULL, 0);
INSERT INTO `classification` VALUES (15, '面食与米面类', 4, NULL, 0);
INSERT INTO `classification` VALUES (16, '调味品与调料', 4, NULL, 0);
INSERT INTO `classification` VALUES (17, '油脂与沙拉酱', 4, NULL, 0);
INSERT INTO `classification` VALUES (18, '食品添加剂与调理品', 4, NULL, 0);

-- ----------------------------
-- Table structure for enterprise
-- ----------------------------
DROP TABLE IF EXISTS `enterprise`;
CREATE TABLE `enterprise`  (
  `eid` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `legal_person` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tel` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `social_code` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `zip_code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `del` int(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`eid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 353 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of enterprise
-- ----------------------------
INSERT INTO `enterprise` VALUES (1, '无所属公司', '无法人', '无', '无', '无', '无', 0);
INSERT INTO `enterprise` VALUES (22, 'Sem Egestas Blandit LLC', 'Dong', '002-334-4915', '4936 9364 5699 8489', 'Heilongjiang', '528286', 0);
INSERT INTO `enterprise` VALUES (23, 'Mauris Sapien Consulting', 'Qiu', '035-354-3217', '675925787857444288', 'Jiangxi', '841998', 0);
INSERT INTO `enterprise` VALUES (24, 'Blandit Nam Associates', 'Quon', '027-113-5356', '4905592532713935361', 'Shanxi', '352662', 0);
INSERT INTO `enterprise` VALUES (25, 'Neque Pellentesque Foundation', 'Biming', '011-964-9518', '6706 7642 3555 7829', 'Shanghai', '366255', 0);
INSERT INTO `enterprise` VALUES (26, 'Penatibus Et Inc.', 'Hui', '064-166-1462', '676722 2782442891', 'Yunnan', '268340', 0);
INSERT INTO `enterprise` VALUES (27, 'Donec Nibh LLC', 'Hop', '028-227-4862', '402625 8639387717', 'Zhejiang', '682238', 0);
INSERT INTO `enterprise` VALUES (28, 'Auctor Non LLC', 'Qing', '024-886-2631', '514451 4363527249', 'Shandong', '135492', 0);
INSERT INTO `enterprise` VALUES (29, 'Urna Et Incorporated', 'Jinhai', '086-858-8627', '3654 484161 54755', 'Xinjiang', '775267', 0);
INSERT INTO `enterprise` VALUES (30, 'Diam Sed Diam LLC', 'Cheng', '054-253-9084', '4911 3744 7392 3257', 'Hebei', '868668', 0);
INSERT INTO `enterprise` VALUES (31, 'Lobortis Tellus Industries', 'Shoi-ming', '015-159-8323', '4532 2295 5215 1163', 'Gansu', '771721', 0);
INSERT INTO `enterprise` VALUES (32, 'Fringilla Purus Institute', 'Biming', '054-672-5120', '6333466121371999', 'Fujian', '824262', 0);
INSERT INTO `enterprise` VALUES (33, 'Eu Tempor LLP', 'Chun', '068-709-7674', '4539856987287', 'Inner Mongolia', '635136', 0);
INSERT INTO `enterprise` VALUES (34, 'Scelerisque Neque Ltd', 'Mingyu', '063-366-2602', '6767595755492393', 'Anhui', '564273', 0);
INSERT INTO `enterprise` VALUES (35, 'Egestas Consulting', 'Hao', '055-572-3882', '450833 7256942553', 'Yunnan', '754428', 0);
INSERT INTO `enterprise` VALUES (36, 'Lobortis LLC', 'Ping', '095-251-2774', '3046 473665 38628', 'Hubei', '626655', 0);
INSERT INTO `enterprise` VALUES (37, 'Lorem Tristique Corp.', 'Sying', '048-686-4234', '6574975348825734', 'Sichuan', '822742', 0);
INSERT INTO `enterprise` VALUES (38, 'Risus Nulla Eget LLC', 'Lei', '082-645-3416', '2014 382658 89153', 'Ningxia', '325747', 0);
INSERT INTO `enterprise` VALUES (39, 'Quis Pede Associates', 'Chao', '043-738-8734', '6304825557552283667', 'Fujian', '951415', 0);
INSERT INTO `enterprise` VALUES (40, 'Mauris Rhoncus Id Company', 'Biming', '061-471-4673', '6767493616869621', 'Shandong', '362904', 0);
INSERT INTO `enterprise` VALUES (41, 'Auctor Quis Tristique Limited', 'Zihao', '071-943-0255', '4929 6938 3797 3520', 'Ningxia', '478454', 0);
INSERT INTO `enterprise` VALUES (52, 'Sem Egestas Blandit LLC', 'Dong', '002-334-4915', '4936 9364 5699 8489', 'Hong Kong', '528286', 0);
INSERT INTO `enterprise` VALUES (53, 'Mauris Sapien Consulting', 'Qiu', '035-354-3217', '675925787857444288', 'Jiangxi', '841998', 0);
INSERT INTO `enterprise` VALUES (54, 'Blandit Nam Associates', 'Quon', '027-113-5356', '4905592532713935361', 'Shanxi', '352662', 0);
INSERT INTO `enterprise` VALUES (55, 'Neque Pellentesque Foundation', 'Biming', '011-964-9518', '6706 7642 3555 7829', 'Shanghai', '366255', 0);
INSERT INTO `enterprise` VALUES (56, 'Penatibus Et Inc.', 'Hui', '064-166-1462', '676722 2782442891', 'Yunnan', '268340', 0);
INSERT INTO `enterprise` VALUES (57, 'Donec Nibh LLC', 'Hop', '028-227-4862', '402625 8639387717', 'Zhejiang', '682238', 0);
INSERT INTO `enterprise` VALUES (58, 'Auctor Non LLC', 'Qing', '024-886-2631', '514451 4363527249', 'Shandong', '135492', 0);
INSERT INTO `enterprise` VALUES (59, 'Urna Et Incorporated', 'Jinhai', '086-858-8627', '3654 484161 54755', 'Xinjiang', '775267', 0);
INSERT INTO `enterprise` VALUES (60, 'Diam Sed Diam LLC', 'Cheng', '054-253-9084', '4911 3744 7392 3257', 'Hebei', '868668', 0);
INSERT INTO `enterprise` VALUES (61, 'Lobortis Tellus Industries', 'Shoi-ming', '015-159-8323', '4532 2295 5215 1163', 'Gansu', '771721', 0);
INSERT INTO `enterprise` VALUES (62, 'Fringilla Purus Institute', 'Biming', '054-672-5120', '6333466121371999', 'Fujian', '824262', 0);
INSERT INTO `enterprise` VALUES (63, 'Eu Tempor LLP', 'Chun', '068-709-7674', '4539856987287', 'Inner Mongolia', '635136', 0);
INSERT INTO `enterprise` VALUES (64, 'Scelerisque Neque Ltd', 'Mingyu', '063-366-2602', '6767595755492393', 'Anhui', '564273', 0);
INSERT INTO `enterprise` VALUES (65, 'Egestas Consulting', 'Hao', '055-572-3882', '450833 7256942553', 'Yunnan', '754428', 0);
INSERT INTO `enterprise` VALUES (66, 'Lobortis LLC', 'Ping', '095-251-2774', '3046 473665 38628', 'Hubei', '626655', 0);
INSERT INTO `enterprise` VALUES (67, 'Lorem Tristique Corp.', 'Sying', '048-686-4234', '6574975348825734', 'Sichuan', '822742', 0);
INSERT INTO `enterprise` VALUES (68, 'Risus Nulla Eget LLC', 'Lei', '082-645-3416', '2014 382658 89153', 'Ningxia', '325747', 0);
INSERT INTO `enterprise` VALUES (69, 'Quis Pede Associates', 'Chao', '043-738-8734', '6304825557552283667', 'Fujian', '951415', 0);
INSERT INTO `enterprise` VALUES (70, 'Mauris Rhoncus Id Company', 'Biming', '061-471-4673', '6767493616869621', 'Shandong', '362904', 0);
INSERT INTO `enterprise` VALUES (71, 'Auctor Quis Tristique Limited', 'Zihao', '071-943-0255', '4929 6938 3797 3520', 'Ningxia', '478454', 0);
INSERT INTO `enterprise` VALUES (72, 'Phasellus Elit Pede Consulting', 'Bai', '046-476-8327', '4936376586761457675', 'Shanghai', '684273', 0);
INSERT INTO `enterprise` VALUES (73, 'Accumsan Neque Inc.', 'Li qiang', '001-384-1762', '67596228222779437', 'Sichuan', '754702', 0);
INSERT INTO `enterprise` VALUES (74, 'Mauris Eu Corporation', 'Wang wei', '091-641-6845', '4916244457483443', 'Shaanxi', '251560', 0);
INSERT INTO `enterprise` VALUES (75, 'Sit Amet PC', 'Jig', '083-073-8526', '445455 465232 8773', 'Tianjin', '676337', 0);
INSERT INTO `enterprise` VALUES (76, 'Integer Mollis Integer Institute', 'Tung', '023-815-1764', '5833 772456 47448', 'Inner Mongolia', '277253', 0);
INSERT INTO `enterprise` VALUES (77, 'Fermentum Fermentum Arcu Company', 'Kang', '067-382-1493', '491 12625 25371 866', 'Inner Mongolia', '317429', 0);
INSERT INTO `enterprise` VALUES (78, 'Dui Semper Consulting', 'Bo', '037-107-6398', '644 38247 98483 785', 'Qinghai', '261547', 0);
INSERT INTO `enterprise` VALUES (79, 'Dui Quis Accumsan Limited', 'Wang wei', '064-649-6853', '534774 562591 1277', 'Guangxi', '437488', 0);
INSERT INTO `enterprise` VALUES (80, 'Proin LLC', 'Guotin', '073-783-6274', '448573 6368622234', 'Henan', '303847', 0);
INSERT INTO `enterprise` VALUES (81, 'Lectus Corporation', 'Yuanjun', '097-555-1548', '633354285273528254', 'Shanxi', '584981', 0);
INSERT INTO `enterprise` VALUES (82, 'Integer Incorporated', 'Ye', '026-743-7117', '647897 1735223226', 'Heilongjiang', '419668', 0);
INSERT INTO `enterprise` VALUES (83, 'Sem Pellentesque Associates', 'Yongrui', '012-584-5226', '2014 888166 83621', 'Chongqing', '470952', 0);
INSERT INTO `enterprise` VALUES (84, 'Cursus Integer Associates', 'Li jun', '039-333-5294', '6463826848187725', 'Heilongjiang', '325609', 0);
INSERT INTO `enterprise` VALUES (85, 'Posuere At Limited', 'Guotin', '024-055-6237', '6334744947957327', 'Qinghai', '771341', 0);
INSERT INTO `enterprise` VALUES (86, 'Eget Varius LLC', 'Xin', '026-819-8367', '4844779856656716', 'Shaanxi', '575317', 0);
INSERT INTO `enterprise` VALUES (87, 'Eu Consulting', 'Ying', '063-145-5245', '548685 7382865124', 'Shaanxi', '748753', 0);
INSERT INTO `enterprise` VALUES (88, 'Lacinia Vitae Foundation', 'Da', '088-653-8261', '6331108558169577', 'Shanxi', '249907', 0);
INSERT INTO `enterprise` VALUES (89, 'Semper Et Ltd', 'Sying', '027-153-5231', '4929 638 55 7724', 'Ningxia', '703109', 0);
INSERT INTO `enterprise` VALUES (90, 'Faucibus Orci Luctus Associates', 'Kang', '011-137-8373', '448569 327469 5277', 'Inner Mongolia', '770353', 0);
INSERT INTO `enterprise` VALUES (91, 'Lorem Eu Corporation', 'Wang yong', '011-121-7381', '402400 7177694881', 'Xinjiang', '326793', 0);
INSERT INTO `enterprise` VALUES (92, 'Tristique Neque LLC', 'Wang lei', '056-357-9699', '2014 226453 37133', 'Gansu', '315372', 0);
INSERT INTO `enterprise` VALUES (93, 'Orci Lobortis Augue Industries', 'Wang yong', '058-276-5880', '363577535353856', 'Qinghai', '677695', 0);
INSERT INTO `enterprise` VALUES (94, 'Magna Tellus Faucibus Industries', 'Fu', '077-668-4464', '6333768485313420', 'Zhejiang', '936718', 0);
INSERT INTO `enterprise` VALUES (95, 'Enim Condimentum Eget Associates', 'Kang', '045-187-2405', '4936 1381 8539 8517', 'Heilongjiang', '876924', 0);
INSERT INTO `enterprise` VALUES (96, 'Ac Tellus Corp.', 'Qiu', '014-616-1565', '493655 479466 9694', 'Shanxi', '171086', 0);
INSERT INTO `enterprise` VALUES (97, 'Donec Ltd', 'Lie jie', '054-415-3128', '670626292563484523', 'Jiangxi', '769430', 0);
INSERT INTO `enterprise` VALUES (98, 'Tempor Diam Incorporated', 'Wang lei', '011-808-6446', '646 84772 62424 429', 'Shanxi', '779077', 0);
INSERT INTO `enterprise` VALUES (99, 'Parturient Montes Consulting', 'Boqin', '044-228-3587', '503877 9989468149', 'Xinjiang', '598659', 0);
INSERT INTO `enterprise` VALUES (100, 'Fermentum Arcu Corp.', 'Chun', '022-558-3464', '3059 842525 48257', 'Shaanxi', '776266', 0);
INSERT INTO `enterprise` VALUES (101, 'Purus Ac Associates', 'Hou', '017-469-7485', '6304623287334472925', 'Hebei', '421948', 0);
INSERT INTO `enterprise` VALUES (102, 'Diam Eu Dolor PC', 'Kun', '053-921-5233', '4905 3466 7969 8360', 'Jilin', '419767', 0);
INSERT INTO `enterprise` VALUES (103, 'Tempor Bibendum PC', 'Hao', '072-460-2697', '491744 947287 6245', 'Chongqing', '685526', 0);
INSERT INTO `enterprise` VALUES (104, 'Mauris Elit Associates', 'Wang wei', '071-323-2375', '2149 582215 66160', 'Liaoning', '275866', 0);
INSERT INTO `enterprise` VALUES (105, 'Cursus Luctus Inc.', 'You', '047-653-8776', '4532 927 68 2128', 'Heilongjiang', '664268', 0);
INSERT INTO `enterprise` VALUES (106, 'Turpis Aliquam Corporation', 'Shilin', '068-251-4525', '675 93655 75773 913', 'Hubei', '587676', 0);
INSERT INTO `enterprise` VALUES (107, 'Lacus Quisque Incorporated', 'Shoi-ming', '053-336-4367', '5592587688599496', 'Hong Kong', '106585', 0);
INSERT INTO `enterprise` VALUES (108, 'Augue Malesuada Incorporated', 'Qi', '046-215-0460', '3481 657483 64330', 'Yunnan', '688248', 0);
INSERT INTO `enterprise` VALUES (109, 'Fringilla Incorporated', 'Shilin', '082-726-7844', '300288656344213', 'Fujian', '636505', 0);
INSERT INTO `enterprise` VALUES (110, 'Neque Sed Incorporated', 'Hui', '044-703-2338', '5838 666656 8773', 'Xinjiang', '271471', 0);
INSERT INTO `enterprise` VALUES (111, 'Ornare Libero Foundation', 'Ru', '052-363-5773', '201442375245272', 'Jilin', '695639', 0);
INSERT INTO `enterprise` VALUES (112, 'Id Institute', 'Ping', '011-703-7155', '6589221576545725', 'Shandong', '523816', 0);
INSERT INTO `enterprise` VALUES (113, 'Amet Nulla Donec Associates', 'Yi', '008-456-3247', '633110 86 8712 5637 387', 'Shaanxi', '423192', 0);
INSERT INTO `enterprise` VALUES (114, 'Non Quam Inc.', 'Mingyu', '048-336-2867', '361448875489977', 'Ningxia', '602920', 0);
INSERT INTO `enterprise` VALUES (115, 'Nunc Id Enim Associates', 'Wang', '084-933-2937', '491 36824 34513 662', 'Xinjiang', '141883', 0);
INSERT INTO `enterprise` VALUES (116, 'Tristique Ac Ltd', 'Li qiang', '061-693-2635', '5038 668498 4679', 'Guizhou', '872093', 0);
INSERT INTO `enterprise` VALUES (117, 'Mauris Associates', 'Xue', '084-425-2144', '675996 224644 7743', 'Beijing', '718294', 0);
INSERT INTO `enterprise` VALUES (118, 'Sed Limited', 'Qiao', '014-681-9455', '4556785358521', 'Shanxi', '537803', 0);
INSERT INTO `enterprise` VALUES (119, 'Phasellus Corp.', 'Tu', '045-421-1562', '503821391463883', 'Tibet', '504724', 0);
INSERT INTO `enterprise` VALUES (120, 'Consequat Consulting', 'Li qiang', '005-256-8288', '4916 9662 7936 7491', 'Ningxia', '332680', 0);
INSERT INTO `enterprise` VALUES (121, 'Consequat Enim LLC', 'Chun', '044-474-3787', '514238 343674 4740', 'Yunnan', '162175', 0);
INSERT INTO `enterprise` VALUES (122, 'Justo Foundation', 'Peizhi', '032-185-0446', '346558231124355', 'Tianjin', '345426', 0);
INSERT INTO `enterprise` VALUES (123, 'Malesuada Vel LLP', 'Fu', '008-351-4957', '344321759223345', 'Chongqing', '330884', 0);
INSERT INTO `enterprise` VALUES (124, 'A Dui Consulting', 'Yingpei', '033-014-9013', '6763269369127', 'Liaoning', '369206', 0);
INSERT INTO `enterprise` VALUES (125, 'Facilisis Limited', 'Ping', '091-744-3257', '6455 4195 7648 4553', 'Hebei', '987789', 0);
INSERT INTO `enterprise` VALUES (126, 'Neque Nullam Nisl Corp.', 'Yongrui', '053-488-0368', '201468228443574', 'Shanxi', '275895', 0);
INSERT INTO `enterprise` VALUES (127, 'Scelerisque Neque Sed LLP', 'Jun', '024-544-6488', '670 68416 31749 638', 'Gansu', '658138', 0);
INSERT INTO `enterprise` VALUES (128, 'Nec PC', 'Hop', '088-886-5766', '589355 468216 4848', 'Xinjiang', '935757', 0);
INSERT INTO `enterprise` VALUES (129, 'Id Mollis Nec LLC', 'Xing', '012-360-7688', '670677 71 6865 4626 144', 'Liaoning', '553283', 0);
INSERT INTO `enterprise` VALUES (130, 'Donec Felis Orci LLP', 'Xin', '018-807-8844', '4508529726936260', 'Chongqing', '822797', 0);
INSERT INTO `enterprise` VALUES (131, 'Quis Pede Institute', 'Ling', '075-322-6718', '490585674452852963', 'Jiangsu', '858523', 0);
INSERT INTO `enterprise` VALUES (132, 'Erat In Consectetuer Industries', 'Ping', '031-407-8653', '633496 7585684644', 'Ningxia', '473156', 0);
INSERT INTO `enterprise` VALUES (133, 'Leo PC', 'Yanlin', '038-567-5568', '6444 8262 2488 6928', 'Liaoning', '468387', 0);
INSERT INTO `enterprise` VALUES (134, 'Risus Incorporated', 'Lei', '078-999-1118', '5361854546972532', 'Chongqing', '462567', 0);
INSERT INTO `enterprise` VALUES (135, 'Donec Associates', 'Zhou', '042-192-8465', '349858776723380', 'Liaoning', '892408', 0);
INSERT INTO `enterprise` VALUES (136, 'A Malesuada PC', 'Kun', '047-418-5187', '493644 941474 6438', 'Xinjiang', '820337', 0);
INSERT INTO `enterprise` VALUES (137, 'Erat Vivamus Nisi Institute', 'Chang', '097-157-0465', '528559 882637 8356', 'Heilongjiang', '827925', 0);
INSERT INTO `enterprise` VALUES (138, 'Bibendum Sed Foundation', 'Jinhai', '023-158-7025', '490539 6864413349', 'Chongqing', '737818', 0);
INSERT INTO `enterprise` VALUES (139, 'Sed Dui Foundation', 'Mingyu', '042-383-7334', '6762 575694 92365', 'Yunnan', '764288', 0);
INSERT INTO `enterprise` VALUES (140, 'Turpis Nulla Inc.', 'Jig', '081-317-1286', '2149 297674 73681', 'Heilongjiang', '649452', 0);
INSERT INTO `enterprise` VALUES (141, 'Arcu Institute', 'Qiao', '005-104-2876', '303384676795240', 'Hubei', '606764', 0);
INSERT INTO `enterprise` VALUES (142, 'Ante Bibendum Associates', 'Yanlin', '028-421-2269', '417 50087 18745 569', 'Heilongjiang', '843855', 0);
INSERT INTO `enterprise` VALUES (143, 'Aenean Eget Magna Corporation', 'Tai-hua', '017-159-5516', '3459 278758 78967', 'Jilin', '563168', 0);
INSERT INTO `enterprise` VALUES (144, 'Odio Phasellus LLC', 'Bingwen', '076-253-0172', '367816844418653', 'Zhejiang', '534716', 0);
INSERT INTO `enterprise` VALUES (145, 'Class Aptent Taciti Corp.', 'Wenyan', '030-443-4108', '633 35871 75676 221', 'Inner Mongolia', '743460', 0);
INSERT INTO `enterprise` VALUES (146, 'Ac Feugiat Company', 'Ling', '013-891-3885', '676347822997720', 'Guangxi', '416288', 0);
INSERT INTO `enterprise` VALUES (147, 'Donec Consectetuer LLC', 'Zihao', '096-447-2000', '646 73362 74566 646', 'Inner Mongolia', '778895', 0);
INSERT INTO `enterprise` VALUES (148, 'Lacus Limited', 'Wen', '026-491-5303', '3034 885862 63973', 'Hebei', '466494', 0);
INSERT INTO `enterprise` VALUES (149, 'Dictum Associates', 'Ling', '073-887-5115', '5568 8927 7278 5682', 'Tibet', '164368', 0);
INSERT INTO `enterprise` VALUES (150, 'Imperdiet Institute', 'Yanlin', '053-858-6241', '3777 574314 18755', 'Heilongjiang', '805468', 0);
INSERT INTO `enterprise` VALUES (151, 'Duis A Incorporated', 'Kuo', '064-957-7575', '4024007128181996', 'Jilin', '383657', 0);
INSERT INTO `enterprise` VALUES (152, 'Eget Corporation', 'Gan', '036-397-0153', '491 34885 68286 483', 'Ningxia', '845559', 0);
INSERT INTO `enterprise` VALUES (153, 'Faucibus Institute', 'Yong', '097-983-5869', '363423655957863', 'Jilin', '819420', 0);
INSERT INTO `enterprise` VALUES (154, 'Molestie Arcu Sed Incorporated', 'Weiyuan', '099-695-2260', '528559 521238 8320', 'Jilin', '378249', 0);
INSERT INTO `enterprise` VALUES (155, 'Nullam Suscipit Ltd', 'An', '010-376-2828', '4485156573623317', 'Xinjiang', '522618', 0);
INSERT INTO `enterprise` VALUES (156, 'Phasellus Nulla Corp.', 'Deli', '037-107-6613', '471 63587 63256 822', 'Qinghai', '670850', 0);
INSERT INTO `enterprise` VALUES (157, 'In Scelerisque Scelerisque PC', 'Bojing', '038-203-7825', '2014 365527 32821', 'Chongqing', '481687', 0);
INSERT INTO `enterprise` VALUES (158, 'Egestas A Associates', 'Wang lei', '083-674-5582', '5641821333893896587', 'Shaanxi', '912533', 0);
INSERT INTO `enterprise` VALUES (159, 'Felis Company', 'Chaun', '067-872-6397', '6444887337255328', 'Ningxia', '785552', 0);
INSERT INTO `enterprise` VALUES (160, 'Eget Nisi LLP', 'Cheng', '037-233-6443', '6709 3536 9287 4583', 'Jilin', '151608', 0);
INSERT INTO `enterprise` VALUES (161, 'Donec Luctus Foundation', 'Ying', '055-116-5742', '491766 3874979890', 'Jilin', '710097', 0);
INSERT INTO `enterprise` VALUES (162, 'Tellus Phasellus Corporation', 'Wen', '071-266-3730', '4485897794789', 'Shanxi', '535254', 0);
INSERT INTO `enterprise` VALUES (163, 'Et Risus Institute', 'Ru', '081-677-2661', '450 88452 26647 938', 'Jilin', '430245', 0);
INSERT INTO `enterprise` VALUES (164, 'Eget Laoreet Incorporated', 'Li jun', '038-639-3341', '6759 344628 5680', 'Shanghai', '685155', 0);
INSERT INTO `enterprise` VALUES (165, 'Id Ante Limited', 'Wang yong', '049-717-1712', '558721 582466 5578', 'Sichuan', '623325', 0);
INSERT INTO `enterprise` VALUES (166, 'Sit Associates', 'Song', '018-579-5210', '6761424526713546946', 'Anhui', '534257', 0);
INSERT INTO `enterprise` VALUES (167, 'Pretium Neque Morbi Corporation', 'Fu', '080-377-6765', '417 50015 62388 586', 'Shanxi', '836274', 0);
INSERT INTO `enterprise` VALUES (168, 'Donec Tempus Limited', 'Jia', '056-773-6273', '564265653829', 'Shanghai', '553023', 0);
INSERT INTO `enterprise` VALUES (169, 'Leo In Corporation', 'Kang', '051-842-6130', '3038 435726 92942', 'Jilin', '430139', 0);
INSERT INTO `enterprise` VALUES (170, 'Ut Sem Nulla Inc.', 'Qu', '033-358-6384', '402400 7115397480', 'Yunnan', '299332', 0);
INSERT INTO `enterprise` VALUES (171, 'Nonummy Ac Feugiat LLC', 'Li wei', '029-953-6609', '4913 4577 7833 5224', 'Heilongjiang', '276874', 0);
INSERT INTO `enterprise` VALUES (172, 'Donec Elementum Corp.', 'Chao', '011-187-6388', '343768782856437', 'Gansu', '548314', 0);
INSERT INTO `enterprise` VALUES (173, 'Viverra Donec Incorporated', 'Shui', '076-365-3565', '5182 3325 7395 8895', 'Shaanxi', '310431', 0);
INSERT INTO `enterprise` VALUES (174, 'Non Associates', 'Shui', '046-242-7153', '490576 6442334342', 'Beijing', '268184', 0);
INSERT INTO `enterprise` VALUES (175, 'Non Lobortis Institute', 'Shilin', '076-825-1589', '4556415739470', 'Guizhou', '443105', 0);
INSERT INTO `enterprise` VALUES (176, 'Mollis LLP', 'Qu', '013-331-5284', '5283 3776 4683 2492', 'Shaanxi', '781386', 0);
INSERT INTO `enterprise` VALUES (177, 'Duis Cursus Incorporated', 'Deming', '088-383-5898', '3008 689254 22849', 'Jilin', '327428', 0);
INSERT INTO `enterprise` VALUES (178, 'Et Arcu Imperdiet Limited', 'Jia', '002-100-6222', '4508 8537 4658 5240', 'Macau', '183598', 0);
INSERT INTO `enterprise` VALUES (179, 'Etiam Ligula Institute', 'Kong', '082-376-4376', '675933 8738226962', 'Guizhou', '418225', 0);
INSERT INTO `enterprise` VALUES (180, 'Porttitor LLP', 'Song', '015-568-7426', '6334239533464523', 'Qinghai', '948531', 0);
INSERT INTO `enterprise` VALUES (181, 'Eu Eleifend Inc.', 'Zhong', '058-695-5366', '525952 918669 7145', 'Liaoning', '157146', 0);
INSERT INTO `enterprise` VALUES (182, 'Fringilla LLC', 'Kuo', '052-846-1474', '6771896633753871', 'Xinjiang', '428178', 0);
INSERT INTO `enterprise` VALUES (183, 'Mauris Ut Industries', 'Guang', '056-661-4731', '4929 518 26 5737', 'Anhui', '217226', 0);
INSERT INTO `enterprise` VALUES (184, 'Donec Consectetuer Mauris Inc.', 'Huan', '038-453-2303', '5893464883886', 'Hebei', '433385', 0);
INSERT INTO `enterprise` VALUES (185, 'Arcu Et Pede Inc.', 'Manchu', '063-411-8157', '3037 464548 35846', 'Beijing', '623281', 0);
INSERT INTO `enterprise` VALUES (186, 'Donec Elementum Industries', 'Shilin', '058-052-3858', '676 77427 73442 832', 'Hong Kong', '277697', 0);
INSERT INTO `enterprise` VALUES (187, 'Nec Corporation', 'Tu', '023-642-1201', '4024007174796', 'Guizhou', '791072', 0);
INSERT INTO `enterprise` VALUES (188, 'Dolor Quam Corporation', 'Jinhai', '054-722-9674', '645 28655 45655 637', 'Jilin', '355871', 0);
INSERT INTO `enterprise` VALUES (189, 'Iaculis Quis Foundation', 'Quon', '095-515-4614', '566222 53 5864 6593 742', 'Liaoning', '344764', 0);
INSERT INTO `enterprise` VALUES (190, 'Id Magna Foundation', 'Bo', '041-847-6824', '4026674336375849', 'Jiangsu', '446862', 0);
INSERT INTO `enterprise` VALUES (191, 'Tellus Suspendisse Company', 'Zhou', '002-481-7855', '633313 247747 9493', 'Beijing', '931273', 0);
INSERT INTO `enterprise` VALUES (192, 'Id Industries', 'Peizhi', '046-128-0648', '6333 1247 3652 2569', 'Inner Mongolia', '473563', 0);
INSERT INTO `enterprise` VALUES (193, 'Mattis Semper Ltd', 'Hong', '045-724-5843', '3443 855864 65841', 'Zhejiang', '435330', 0);
INSERT INTO `enterprise` VALUES (194, 'Dolor Sit Foundation', 'An', '072-102-9527', '6767645727461838341', 'Hunan', '744345', 0);
INSERT INTO `enterprise` VALUES (195, 'Neque Non Quam Institute', 'Jin', '013-744-7065', '4716868249852', 'Tibet', '480529', 0);
INSERT INTO `enterprise` VALUES (196, 'Quam Vel Sapien LLC', 'Tai-hua', '018-091-5678', '670 64156 25874 395', 'Liaoning', '233461', 0);
INSERT INTO `enterprise` VALUES (197, 'Et Nunc Quisque PC', 'Shilin', '069-041-5338', '3656 198965 37343', 'Tibet', '876493', 0);
INSERT INTO `enterprise` VALUES (198, 'In Consectetuer Foundation', 'Tu', '064-373-4455', '630432 45 2467 1849 386', 'Jiangxi', '539322', 0);
INSERT INTO `enterprise` VALUES (199, 'Ante Ipsum Primis Consulting', 'Tung', '042-018-4575', '6767 2912 6317 6278', 'Chongqing', '779333', 0);
INSERT INTO `enterprise` VALUES (200, 'Interdum Feugiat Sed Inc.', 'Chung', '049-192-8634', '301834174755379', 'Henan', '553127', 0);
INSERT INTO `enterprise` VALUES (201, 'Duis Volutpat LLP', 'Ning', '001-573-0114', '670 66884 27746 470', 'Jiangsu', '512976', 0);
INSERT INTO `enterprise` VALUES (202, 'Pede Cras Limited', 'Bengt', '053-597-8255', '6762 839 32 8381', 'Qinghai', '379666', 0);
INSERT INTO `enterprise` VALUES (203, 'Gravida Praesent Associates', 'Bo', '089-563-8374', '4539597559853517', 'Ningxia', '733965', 0);
INSERT INTO `enterprise` VALUES (204, 'Sollicitudin Adipiscing Incorporated', 'Shan', '045-064-4885', '6334842624756937', 'Heilongjiang', '633003', 0);
INSERT INTO `enterprise` VALUES (205, 'Parturient Montes Corp.', 'He', '077-811-7557', '2014 889589 76130', 'Heilongjiang', '237124', 0);
INSERT INTO `enterprise` VALUES (206, 'Arcu Eu Institute', 'An', '046-234-8235', '5184344582314950', 'Xinjiang', '267172', 0);
INSERT INTO `enterprise` VALUES (207, 'Nisl Industries', 'Yingjie', '045-542-4576', '4911782852234542761', 'Yunnan', '325560', 0);
INSERT INTO `enterprise` VALUES (208, 'Metus Eu Erat Corporation', 'Weizhe', '003-136-5738', '417 50074 77468 876', 'Guizhou', '747451', 0);
INSERT INTO `enterprise` VALUES (209, 'Ipsum Suspendisse Inc.', 'Hui', '036-522-4243', '5435 2455 6619 9571', 'Qinghai', '862297', 0);
INSERT INTO `enterprise` VALUES (210, 'Eros Non Inc.', 'Kong', '010-814-3474', '2014 388358 47285', 'Shaanxi', '233182', 0);
INSERT INTO `enterprise` VALUES (211, 'Nulla Vulputate Ltd', 'Boqin', '088-135-4311', '6446451731259481', 'Macau', '774756', 0);
INSERT INTO `enterprise` VALUES (212, 'Torquent Per Inc.', 'Susu', '045-726-4494', '676363 4385192620', 'Sichuan', '388168', 0);
INSERT INTO `enterprise` VALUES (213, 'Eu Neque Associates', 'Biming', '023-647-2942', '670922934845737878', 'Hunan', '388168', 0);
INSERT INTO `enterprise` VALUES (214, 'Vestibulum Accumsan Neque Inc.', 'Ping', '077-734-1948', '300651575299794', 'Guizhou', '822654', 0);
INSERT INTO `enterprise` VALUES (215, 'Ut Eros Non Limited', 'Shilin', '002-497-6752', '574583833365647', 'Liaoning', '591819', 0);
INSERT INTO `enterprise` VALUES (216, 'Cras Associates', 'Jinhai', '096-476-4420', '6763 8487 3114 3570', 'Shaanxi', '481179', 0);
INSERT INTO `enterprise` VALUES (217, 'Blandit At Institute', 'Deli', '027-877-2144', '4508338442746221', 'Yunnan', '563468', 0);
INSERT INTO `enterprise` VALUES (218, 'Enim Corporation', 'Jin', '023-226-2186', '6465 1694 1136 8754', 'Anhui', '337244', 0);
INSERT INTO `enterprise` VALUES (219, 'Aenean Massa Integer LLP', 'Li qiang', '044-542-2203', '3638 737266 54339', 'Anhui', '248431', 0);
INSERT INTO `enterprise` VALUES (220, 'Vestibulum Massa Foundation', 'Ling', '065-363-1519', '5897 2554 4646 7834', 'Liaoning', '358549', 0);
INSERT INTO `enterprise` VALUES (221, 'Mi Enim Foundation', 'Deli', '091-313-6994', '3668 868544 28851', 'Hainan', '221736', 0);
INSERT INTO `enterprise` VALUES (222, 'Porttitor Scelerisque PC', 'Changpu', '091-016-6639', '372673745876536', 'Beijing', '377443', 0);
INSERT INTO `enterprise` VALUES (223, 'Nibh Sit Associates', 'Shoi-ming', '044-156-3176', '3431 451967 75885', 'Shaanxi', '564734', 0);
INSERT INTO `enterprise` VALUES (224, 'Sed Malesuada LLP', 'Song', '094-168-7913', '589348693633', 'Shanghai', '568577', 0);
INSERT INTO `enterprise` VALUES (225, 'Aliquam Fringilla Company', 'Tung', '053-456-8451', '361384288762421', 'Tibet', '944148', 0);
INSERT INTO `enterprise` VALUES (226, 'Nam Ligula Corporation', 'Bo', '004-762-6278', '3484 724153 47725', 'Heilongjiang', '853439', 0);
INSERT INTO `enterprise` VALUES (227, 'Vitae Semper Corporation', 'Bambang', '082-862-1182', '4916487853692', 'Tibet', '527757', 0);
INSERT INTO `enterprise` VALUES (228, 'Cras Dolor Incorporated', 'Bingwen', '057-132-3418', '589 37874 34346 947', 'Shandong', '469929', 0);
INSERT INTO `enterprise` VALUES (229, 'Tincidunt Corporation', 'Susu', '022-785-4873', '2149 564862 45355', 'Henan', '563654', 0);
INSERT INTO `enterprise` VALUES (230, 'Leo In Lobortis Corporation', 'Kong', '067-108-7540', '484 46383 84642 939', 'Hebei', '386451', 0);
INSERT INTO `enterprise` VALUES (231, 'Nullam Ltd', 'Qu', '067-769-1656', '2149 246572 51578', 'Jilin', '612148', 0);
INSERT INTO `enterprise` VALUES (232, 'Orci Luctus Company', 'Zhixin', '063-537-5113', '493627866475457853', 'Ningxia', '436658', 0);
INSERT INTO `enterprise` VALUES (233, 'Eu Dolor LLP', 'Bingwen', '007-182-0813', '3053 454244 62456', 'Jilin', '623243', 0);
INSERT INTO `enterprise` VALUES (234, 'Et Limited', 'Changpu', '028-547-5587', '658856 819924 4156', 'Shanxi', '683337', 0);
INSERT INTO `enterprise` VALUES (235, 'Euismod Urna Associates', 'Bo', '057-263-2684', '648 13368 49782 472', 'Tianjin', '514718', 0);
INSERT INTO `enterprise` VALUES (236, 'Aliquam Gravida Mauris Foundation', 'Guotin', '053-077-5892', '453293 476654 2871', 'Sichuan', '838742', 0);
INSERT INTO `enterprise` VALUES (237, 'Ultrices Corp.', 'Weiyuan', '039-679-7377', '527768 852782 3329', 'Inner Mongolia', '781429', 0);
INSERT INTO `enterprise` VALUES (238, 'Egestas Limited', 'Qi', '032-213-3453', '630416 75 9397 6568 843', 'Tibet', '312880', 0);
INSERT INTO `enterprise` VALUES (239, 'Sem Elit Corp.', 'Yi', '055-761-8263', '490 35558 57846 656', 'Guizhou', '942516', 0);
INSERT INTO `enterprise` VALUES (240, 'Dui Suspendisse Limited', 'Hou', '084-715-5266', '6333 5162 9793 1214', 'Tianjin', '720185', 0);
INSERT INTO `enterprise` VALUES (241, 'Pellentesque Habitant Morbi Ltd', 'On', '093-697-7858', '417500 544477 4830', 'Hainan', '944550', 0);
INSERT INTO `enterprise` VALUES (242, 'Orci Ut Semper Limited', 'Hao', '051-171-5271', '4026 3389 3789 9835', 'Gansu', '108403', 0);
INSERT INTO `enterprise` VALUES (243, 'Mi Company', 'Yingjie', '066-711-4106', '484451 469192 3470', 'Shanxi', '793873', 0);
INSERT INTO `enterprise` VALUES (244, 'Ac Turpis Egestas Ltd', 'Zhou', '042-302-5723', '633411 61 2715 3887 830', 'Jiangxi', '884647', 0);
INSERT INTO `enterprise` VALUES (245, 'Purus Institute', 'Jig', '052-423-2467', '2014 243775 69719', 'Hebei', '866634', 0);
INSERT INTO `enterprise` VALUES (246, 'Blandit Viverra Institute', 'Zhong', '034-046-8621', '4913 8537 6223 2957', 'Guangdong', '378371', 0);
INSERT INTO `enterprise` VALUES (247, 'Lectus Ante Dictum LLC', 'Shan', '029-487-4818', '6463 6495 4973 8921', 'Jilin', '580472', 0);
INSERT INTO `enterprise` VALUES (248, 'Per Conubia Incorporated', 'Li jun', '097-631-7782', '633438 79 2332 3769 789', 'Heilongjiang', '719232', 0);
INSERT INTO `enterprise` VALUES (249, 'Ut Inc.', 'Lie jie', '033-767-3146', '6762337477235', 'Qinghai', '303763', 0);
INSERT INTO `enterprise` VALUES (250, 'Pede Ltd', 'Bojing', '016-898-7088', '3467 652537 32334', 'Ningxia', '314691', 0);
INSERT INTO `enterprise` VALUES (251, 'Ultrices Posuere Industries', 'Wang lei', '076-236-3832', '490558 2285725466', 'Hebei', '976138', 0);
INSERT INTO `enterprise` VALUES (252, 'Arcu Vestibulum Limited', 'Hop', '048-051-5152', '3057 498265 82953', 'Hubei', '733154', 0);
INSERT INTO `enterprise` VALUES (253, 'Tristique Consulting', 'Cheung', '035-674-5785', '2014 246247 92668', 'Guangxi', '845054', 0);
INSERT INTO `enterprise` VALUES (254, 'Quis Diam LLP', 'Wei', '096-328-5612', '364662562527546', 'Inner Mongolia', '784266', 0);
INSERT INTO `enterprise` VALUES (255, 'Vitae Risus Limited', 'Yingpei', '086-965-1850', '516 62597 48388 866', 'Jiangxi', '825724', 0);
INSERT INTO `enterprise` VALUES (256, 'Nulla Interdum LLP', 'Yingjie', '031-348-2176', '677189 49 8248 3143 549', 'Tibet', '676434', 0);
INSERT INTO `enterprise` VALUES (257, 'Cubilia Company', 'Chaun', '072-934-9716', '6334 3151 6917 5342', 'Sichuan', '884529', 0);
INSERT INTO `enterprise` VALUES (258, 'Morbi Non Corporation', 'Qing', '018-859-2836', '3486 182529 88753', 'Jilin', '855731', 0);
INSERT INTO `enterprise` VALUES (259, 'Vivamus Euismod Institute', 'Feng', '063-887-7720', '3036 595738 14537', 'Heilongjiang', '447231', 0);
INSERT INTO `enterprise` VALUES (260, 'Hendrerit Donec Porttitor Institute', 'Huan', '057-365-3658', '3774 227566 35562', 'Fujian', '285187', 0);
INSERT INTO `enterprise` VALUES (261, 'Egestas Ltd', 'Liang', '047-751-1388', '490546 836865 8784', 'Heilongjiang', '818756', 0);
INSERT INTO `enterprise` VALUES (262, 'Luctus Ipsum Institute', 'Qiao', '003-128-2984', '503 84624 62686 129', 'Gansu', '802675', 0);
INSERT INTO `enterprise` VALUES (263, 'Turpis Non Enim Institute', 'Song', '083-331-9638', '3654 728418 47635', 'Anhui', '245552', 0);
INSERT INTO `enterprise` VALUES (264, 'Quam Vel Corp.', 'Biming', '028-748-7176', '490578893147848488', 'Jilin', '476214', 0);
INSERT INTO `enterprise` VALUES (265, 'Sodales Mauris Consulting', 'Susu', '012-978-8260', '648922 579736 5780', 'Qinghai', '512841', 0);
INSERT INTO `enterprise` VALUES (266, 'Cursus In LLC', 'Xiang', '068-288-4076', '361843891727345', 'Hebei', '346876', 0);
INSERT INTO `enterprise` VALUES (267, 'Mauris Rhoncus LLP', 'Chao', '053-338-7322', '3754 283926 35736', 'Chongqing', '730888', 0);
INSERT INTO `enterprise` VALUES (268, 'Purus Associates', 'Mingyu', '087-231-4339', '503877 742348 7790', 'Yunnan', '751582', 0);
INSERT INTO `enterprise` VALUES (269, 'Ornare Lectus LLC', 'Yi', '063-447-2871', '3004 644113 34563', 'Jilin', '685770', 0);
INSERT INTO `enterprise` VALUES (270, 'Lorem Luctus Ut PC', 'Jianyu', '063-636-5839', '589395528891758182', 'Chongqing', '760582', 0);
INSERT INTO `enterprise` VALUES (271, 'Lacinia Company', 'Guotin', '044-622-7282', '6473 5628 4251 9674', 'Gansu', '470511', 0);
INSERT INTO `enterprise` VALUES (272, 'At Velit Corporation', 'Liang', '013-884-6761', '3756 721444 43663', 'Hunan', '723781', 0);
INSERT INTO `enterprise` VALUES (273, 'Vulputate Risus Institute', 'An', '058-752-8677', '4913 7778 7226 4388', 'Shaanxi', '465823', 0);
INSERT INTO `enterprise` VALUES (274, 'Turpis Nulla Aliquet Industries', 'Mingli', '059-044-4673', '492 93356 82374 566', 'Inner Mongolia', '594236', 0);
INSERT INTO `enterprise` VALUES (275, 'Magna A Tortor Ltd', 'Jia', '085-938-0924', '601139 941213 7697', 'Hebei', '261052', 0);
INSERT INTO `enterprise` VALUES (276, 'Sed Libero Foundation', 'Peizhi', '088-243-3921', '652863 2772131959', 'Zhejiang', '325867', 0);
INSERT INTO `enterprise` VALUES (277, 'Netus Et Malesuada Ltd', 'Jig', '022-935-1124', '3475 552261 24688', 'Hebei', '462633', 0);
INSERT INTO `enterprise` VALUES (278, 'Donec Porttitor Associates', 'On', '061-463-4881', '521 19813 52768 480', 'Inner Mongolia', '774371', 0);
INSERT INTO `enterprise` VALUES (279, 'Cras Lorem Lorem Incorporated', 'Park', '075-164-1760', '2149 342592 32512', 'Hebei', '369100', 0);
INSERT INTO `enterprise` VALUES (280, 'Justo Eu Consulting', 'Yi', '048-374-2778', '644467 161768 5366', 'Tianjin', '296491', 0);
INSERT INTO `enterprise` VALUES (281, 'Mus Proin Institute', 'Zhixin', '033-685-2875', '564182886258475943', 'Liaoning', '214285', 0);
INSERT INTO `enterprise` VALUES (282, 'Amet Risus Inc.', 'Jin', '075-572-7198', '564182 5562731434', 'Gansu', '808582', 0);
INSERT INTO `enterprise` VALUES (283, 'Massa Mauris LLP', 'Wang wei', '005-614-7834', '491138 45 3741 5236 299', 'Jiangsu', '238755', 0);
INSERT INTO `enterprise` VALUES (284, 'Lacus Vestibulum Limited', 'Ye', '012-140-0840', '63049852273897368', 'Xinjiang', '298433', 0);
INSERT INTO `enterprise` VALUES (285, 'Odio A Incorporated', 'Tung', '059-336-5339', '5893844737643528', 'Sichuan', '856321', 0);
INSERT INTO `enterprise` VALUES (286, 'Erat Etiam LLC', 'Chao', '025-308-7784', '303137246469484', 'Ningxia', '686844', 0);
INSERT INTO `enterprise` VALUES (287, 'Nibh Aliquam Ornare Ltd', 'Kang', '081-819-5869', '374266347687480', 'Jilin', '416481', 0);
INSERT INTO `enterprise` VALUES (288, 'Pede Nunc Limited', 'Chaun', '054-776-4668', '6474752417962461', 'Beijing', '188493', 0);
INSERT INTO `enterprise` VALUES (289, 'Et Foundation', 'Zhou', '042-173-3228', '2149 818353 45672', 'Qinghai', '773818', 0);
INSERT INTO `enterprise` VALUES (290, 'Vel Pede PC', 'Jinhai', '032-474-6565', '490583 17 7772 5392 777', 'Jiangsu', '495683', 0);
INSERT INTO `enterprise` VALUES (291, 'Nunc Corporation', 'Hua', '036-696-3091', '670938 324657 7655', 'Jiangxi', '544863', 0);
INSERT INTO `enterprise` VALUES (292, 'Condimentum Eget LLC', 'Zhixin', '053-222-1103', '491317 744168 1743', 'Heilongjiang', '368587', 0);
INSERT INTO `enterprise` VALUES (293, 'Faucibus Associates', 'Song', '020-788-1587', '305127723248822', 'Tibet', '646787', 0);
INSERT INTO `enterprise` VALUES (294, 'Ultricies Ligula Institute', 'Wenyan', '066-454-8747', '6759866822821470', 'Beijing', '385012', 0);
INSERT INTO `enterprise` VALUES (295, 'Et Magnis Dis LLC', 'Bai', '052-362-3313', '630451 55 4358 3625 329', 'Inner Mongolia', '741066', 0);
INSERT INTO `enterprise` VALUES (296, 'Eu Turpis Foundation', 'You', '086-315-8476', '677164 9928646932', 'Guangxi', '731847', 0);
INSERT INTO `enterprise` VALUES (297, 'Purus LLC', 'He', '031-378-3778', '453 94151 83533 621', 'Chongqing', '569635', 0);
INSERT INTO `enterprise` VALUES (298, 'Interdum Inc.', 'Li jun', '092-030-4619', '6767 3417 7434 2370', 'Beijing', '662635', 0);
INSERT INTO `enterprise` VALUES (299, 'Lorem Ac Risus Corp.', 'Gan', '097-596-9053', '4917234787736882', 'Shandong', '442164', 0);
INSERT INTO `enterprise` VALUES (300, 'Ipsum LLP', 'Ling', '030-401-1072', '3752 527261 67731', 'Heilongjiang', '827465', 0);
INSERT INTO `enterprise` VALUES (301, 'Amet Industries', 'Mingli', '050-643-9898', '541643 3356966226', 'Tibet', '184741', 0);
INSERT INTO `enterprise` VALUES (302, 'Quisque LLC', 'Park', '063-346-7063', '301485627234866', 'Beijing', '516442', 0);
INSERT INTO `enterprise` VALUES (303, 'Ligula Donec Industries', 'Deli', '041-872-6557', '367814658868444', 'Macau', '524111', 0);
INSERT INTO `enterprise` VALUES (304, 'Morbi LLP', 'Jun', '077-052-7493', '6465 8872 8565 6885', 'Ningxia', '825062', 0);
INSERT INTO `enterprise` VALUES (305, 'In Consequat Enim Corp.', 'Kuo', '041-392-2010', '201473536881853', 'Liaoning', '661308', 0);
INSERT INTO `enterprise` VALUES (306, 'Magna Et Incorporated', 'Yingjie', '041-808-1386', '5249319886693757', 'Chongqing', '689823', 0);
INSERT INTO `enterprise` VALUES (307, 'Vel Venenatis Incorporated', 'Peizhi', '083-572-2482', '492997 117643 9968', 'Gansu', '318013', 0);
INSERT INTO `enterprise` VALUES (308, 'Tempus Scelerisque Corp.', 'On', '053-668-3094', '4817 6954 7588 4369', 'Guangdong', '776643', 0);
INSERT INTO `enterprise` VALUES (309, 'Sapien Imperdiet Corporation', 'Duyi', '014-850-2681', '493657437715549223', 'Macau', '643959', 0);
INSERT INTO `enterprise` VALUES (310, 'Vivamus Associates', 'Qi', '058-251-3882', '490525 14 2276 6362 428', 'Ningxia', '334854', 0);
INSERT INTO `enterprise` VALUES (311, 'Tincidunt Foundation', 'Jinhai', '052-486-8286', '3623 652868 66487', 'Shanghai', '318906', 0);
INSERT INTO `enterprise` VALUES (312, 'Lorem Ipsum Dolor Corporation', 'Ning', '067-486-3858', '450 86574 38443 984', 'Macau', '849123', 0);
INSERT INTO `enterprise` VALUES (313, 'Cursus In LLC', 'Chen', '073-053-5246', '300415652811586', 'Hebei', '415492', 0);
INSERT INTO `enterprise` VALUES (314, 'Vulputate Lacus Inc.', 'Ru', '078-322-0285', '670644 41 1532 2584 885', 'Shanxi', '812384', 0);
INSERT INTO `enterprise` VALUES (315, 'Luctus Et Ultrices LLP', 'Hong', '049-487-7761', '5656 865 47 8360', 'Hainan', '828492', 0);
INSERT INTO `enterprise` VALUES (316, 'Purus Maecenas Libero Corp.', 'Jin', '070-999-7238', '6451 4374 3814 8772', 'Tianjin', '418533', 0);
INSERT INTO `enterprise` VALUES (317, 'Ipsum Cursus Industries', 'Zhou', '020-574-3279', '448 53366 45942 685', 'Qinghai', '462323', 0);
INSERT INTO `enterprise` VALUES (318, 'Mattis Integer LLP', 'Hou', '041-146-3702', '3032 867318 44840', 'Hainan', '213108', 0);
INSERT INTO `enterprise` VALUES (319, 'Aliquam Arcu LLC', 'Liang', '045-197-8871', '6709385144168826', 'Xinjiang', '400201', 0);
INSERT INTO `enterprise` VALUES (320, 'Odio Inc.', 'Taio', '090-031-4475', '6763 548853 4259', 'Qinghai', '597492', 0);
INSERT INTO `enterprise` VALUES (321, 'Orci LLC', 'Wang', '053-316-5833', '373247735855633', 'Beijing', '553898', 0);
INSERT INTO `enterprise` VALUES (322, 'Magna Incorporated', 'Hou', '070-196-3476', '2014 825778 77233', 'Macau', '488461', 0);
INSERT INTO `enterprise` VALUES (323, 'Nec Imperdiet Nec LLP', 'Guotin', '087-928-2133', '676766527249531529', 'Xinjiang', '293771', 0);
INSERT INTO `enterprise` VALUES (324, 'Dis Limited', 'Tao', '024-602-7624', '523 97884 65719 229', 'Shaanxi', '682037', 0);
INSERT INTO `enterprise` VALUES (325, 'Est Inc.', 'Qiao', '073-982-8873', '630442 3543242956', 'Ningxia', '832858', 0);
INSERT INTO `enterprise` VALUES (326, 'Praesent Luctus Foundation', 'Qiu', '072-259-6234', '493647474716884336', 'Tibet', '106246', 0);
INSERT INTO `enterprise` VALUES (327, 'Nulla Ltd', 'Tung', '068-054-8676', '5038 2878 5589 2236', 'Liaoning', '848735', 0);
INSERT INTO `enterprise` VALUES (328, 'Nascetur Ridiculus Ltd', 'Gui', '076-122-5884', '670633 22 8245 2673 428', 'Shanghai', '813620', 0);
INSERT INTO `enterprise` VALUES (329, 'Ante Ipsum Ltd', 'Mingyu', '049-847-0001', '6485 3767 6532 7528', 'Tianjin', '875170', 0);
INSERT INTO `enterprise` VALUES (330, 'Facilisis Suspendisse LLC', 'Bingwen', '082-813-6177', '3788 655752 57696', 'Hubei', '820216', 0);
INSERT INTO `enterprise` VALUES (331, 'Eu Ltd', 'Kang', '081-164-0871', '416 55848 42895 498', 'Jiangxi', '615369', 0);
INSERT INTO `enterprise` VALUES (332, 'Nibh Donec Associates', 'Li qiang', '028-406-7233', '366488168647241', 'Tibet', '737046', 0);
INSERT INTO `enterprise` VALUES (333, 'Euismod Urna Nullam Incorporated', 'Quon', '053-746-9839', '511 44973 68756 649', 'Liaoning', '769983', 0);
INSERT INTO `enterprise` VALUES (334, 'In Condimentum Donec Institute', 'Guang', '013-748-3655', '6471 8433 4387 8799', 'Anhui', '233160', 0);
INSERT INTO `enterprise` VALUES (335, 'Lectus Justo LLP', 'Shoi-ming', '085-860-8963', '583449 827849 2480', 'Hong Kong', '428462', 0);
INSERT INTO `enterprise` VALUES (336, 'Laoreet Inc.', 'Wang lei', '053-915-6344', '303927784783753', 'Zhejiang', '328566', 0);
INSERT INTO `enterprise` VALUES (337, 'Dui Cum LLC', 'Chang', '037-847-0182', '477887 7421593339', 'Heilongjiang', '650225', 0);
INSERT INTO `enterprise` VALUES (338, 'Etiam Corp.', 'Wang lei', '074-233-0248', '214988343285688', 'Xinjiang', '859354', 0);
INSERT INTO `enterprise` VALUES (339, 'Phasellus At Augue PC', 'Zhixin', '088-462-8612', '455663 827429 3361', 'Jilin', '723587', 0);
INSERT INTO `enterprise` VALUES (340, 'Eros Nam Consequat Institute', 'Deli', '056-228-2778', '3648 229384 58673', 'Heilongjiang', '827506', 0);
INSERT INTO `enterprise` VALUES (341, 'Duis Risus Odio Inc.', 'Taio', '074-849-6252', '2014 934272 66410', 'Qinghai', '661620', 0);
INSERT INTO `enterprise` VALUES (342, 'Ultrices Mauris Ipsum Inc.', 'Gen', '039-581-6855', '5472 8969 9662 3954', 'Gansu', '768495', 0);
INSERT INTO `enterprise` VALUES (343, 'At Associates', 'Xue', '082-787-9183', '484 43537 43521 358', 'Guangdong', '762723', 0);
INSERT INTO `enterprise` VALUES (344, 'Egestas Nunc Corporation', 'Weizhe', '003-371-1852', '6763 564874 1687', 'Liaoning', '644577', 0);
INSERT INTO `enterprise` VALUES (345, 'Dictum Associates', 'Wang yong', '043-716-1875', '303741561534317', 'Hubei', '605142', 0);
INSERT INTO `enterprise` VALUES (346, 'Ipsum Non Inc.', 'Jianyu', '018-941-1499', '402 40071 75744 761', 'Sichuan', '377784', 0);
INSERT INTO `enterprise` VALUES (347, 'Faucibus Industries', 'Heng', '048-616-3693', '676746 16 1223 3789 595', 'Guangdong', '513352', 0);
INSERT INTO `enterprise` VALUES (348, 'Egestas Industries', 'Bo', '051-946-9695', '453943 531862 5282', 'Yunnan', '698991', 0);
INSERT INTO `enterprise` VALUES (349, 'Pharetra Quisque Incorporated', 'Kong', '066-589-5414', '201417427888642', 'Liaoning', '892988', 0);
INSERT INTO `enterprise` VALUES (350, 'Neque In Ltd', 'Hong', '013-822-3678', '543226 167156 4552', 'Inner Mongolia', '822735', 0);
INSERT INTO `enterprise` VALUES (351, 'Urna Vivamus Molestie Associates', 'Taio', '063-142-1354', '305985668232865', 'Qinghai', '487037', 0);
INSERT INTO `enterprise` VALUES (352, 'Google', 'SSymbol', '13634546175', '112111', 'Av. dos Andradas, 3000', '30260', 0);

-- ----------------------------
-- Table structure for enterprise_operate
-- ----------------------------
DROP TABLE IF EXISTS `enterprise_operate`;
CREATE TABLE `enterprise_operate`  (
  `iid` int(0) NOT NULL AUTO_INCREMENT,
  `oid` int(0) NOT NULL,
  `eid` int(0) NOT NULL,
  `operate` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `operate_time` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`iid`) USING BTREE,
  INDEX `oid`(`oid`) USING BTREE,
  INDEX `eid`(`eid`) USING BTREE,
  CONSTRAINT `enterprise_operate_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `account` (`aid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `enterprise_operate_ibfk_2` FOREIGN KEY (`eid`) REFERENCES `enterprise` (`eid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of enterprise_operate
-- ----------------------------
INSERT INTO `enterprise_operate` VALUES (1, 1, 352, '创建企业', '2024-07-27 11:26:52');
INSERT INTO `enterprise_operate` VALUES (2, 1, 352, '修改企业信息', '2024-07-28 09:51:16');
INSERT INTO `enterprise_operate` VALUES (3, 1, 22, '修改企业信息', '2024-07-28 09:59:32');
INSERT INTO `enterprise_operate` VALUES (4, 1, 22, '修改企业信息', '2024-07-28 09:59:55');
INSERT INTO `enterprise_operate` VALUES (5, 1, 24, '修改企业信息', '2024-08-01 10:26:18');

-- ----------------------------
-- Table structure for menue
-- ----------------------------
DROP TABLE IF EXISTS `menue`;
CREATE TABLE `menue`  (
  `mid` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `icon` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `color` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `parent` int(0) NOT NULL DEFAULT 0,
  `memo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `del` smallint(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`mid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menue
-- ----------------------------
INSERT INTO `menue` VALUES (1, '追溯主体管理', '/subject', 'TraceSubject.svg', '#36a3f7', 0, NULL, 0);
INSERT INTO `menue` VALUES (4, '追溯数据分析', '/analysis', 'TracingData.svg', '#f4516c', 0, NULL, 0);
INSERT INTO `menue` VALUES (7, '运行监测管理', '/monitor', 'OperationMonitoring.svg', '#34bfa3', 0, NULL, 0);
INSERT INTO `menue` VALUES (8, '追溯环节管理', '/segment', 'TraceabilityProcess.svg', '#40c9c6', 0, NULL, 0);
INSERT INTO `menue` VALUES (10, '系统设置管理', '/system', 'SystemSet.svg', '#f4516c', 0, NULL, 0);
INSERT INTO `menue` VALUES (11, '环节管理', '/segment/manage', 'Connection', 'none', 8, NULL, 0);
INSERT INTO `menue` VALUES (12, '超市进场', '/segment/manage/entry', 'Connection', 'none', 11, NULL, 0);
INSERT INTO `menue` VALUES (13, '超市出场', '/segment/manage/appears', 'Connection', 'none', 11, NULL, 0);
INSERT INTO `menue` VALUES (14, '运行监测管理', '/monitor/manage', 'Monitor', 'none', 7, NULL, 0);
INSERT INTO `menue` VALUES (15, '采集数据汇总', '/monitor/manage/summary', 'Monitor', 'none', 14, NULL, 0);
INSERT INTO `menue` VALUES (16, '环节类型数据', '/monitor/manage/data', 'Monitor', 'none', 14, NULL, 0);
INSERT INTO `menue` VALUES (17, '市场运行分析', '/analysis/market', 'ShoppingTrolley', 'none', 4, NULL, 0);
INSERT INTO `menue` VALUES (18, '市场运行分析', '/analysis/market/operations', 'ShoppingTrolley', 'none', 17, NULL, 0);
INSERT INTO `menue` VALUES (19, '重点行业分析', '/analysis/industry', 'PieChart', 'none', 4, NULL, 0);
INSERT INTO `menue` VALUES (20, '超市数据分析', '/analysis/industry/market', 'PieChart', 'none', 19, NULL, 0);
INSERT INTO `menue` VALUES (21, '批发数据分析', '/analysis/industry/wholesale', 'PieChart', 'none', 19, NULL, 0);
INSERT INTO `menue` VALUES (22, '加工数据分析', '/analysis/industry/process', 'PieChart', 'none', 19, NULL, 0);
INSERT INTO `menue` VALUES (23, '行业结构分析', '/analysis/industry/struct', 'PieChart', 'none', 19, NULL, 0);
INSERT INTO `menue` VALUES (24, '追溯分析报告', '/analysis/report', 'DocumentCopy', 'none', 4, NULL, 0);
INSERT INTO `menue` VALUES (25, '主体信息备案', '/subject/bulk', 'Notification', 'none', 1, NULL, 0);
INSERT INTO `menue` VALUES (26, '供应商备案', '/subject/bulk/supplier', 'Service', 'none', 25, NULL, 0);
INSERT INTO `menue` VALUES (27, '供销商备案', '/subject/bulk/vendors', 'User', 'none', 25, NULL, 0);
INSERT INTO `menue` VALUES (28, '产品信息备案', '/subject/product', 'MessageBox', 'none', 1, NULL, 0);
INSERT INTO `menue` VALUES (29, '产品备案', '/subject/product/filings', 'Edit', 'none', 28, NULL, 0);
INSERT INTO `menue` VALUES (30, '年度报告', '/analysis/report/year', 'DocumentCopy', 'none', 24, NULL, 0);
INSERT INTO `menue` VALUES (31, '月度报告', '/analysis/report/month', 'DocumentCopy', 'none', 24, NULL, 0);
INSERT INTO `menue` VALUES (32, '季度报告', '/analysis/report/quarter', 'DocumentCopy', 'none', 24, NULL, 0);
INSERT INTO `menue` VALUES (33, '平台数据管理', '/system/account', 'User', 'none', 10, NULL, 0);
INSERT INTO `menue` VALUES (34, '用户管理', '/system/account/user', 'User', 'none', 33, NULL, 0);
INSERT INTO `menue` VALUES (35, '权限管理', '/system/account/role', 'User', 'none', 33, NULL, 0);
INSERT INTO `menue` VALUES (36, '公司管理', '/system/account/enterprise', 'User', 'none', 33, NULL, 0);
INSERT INTO `menue` VALUES (37, '敏感操作记录', '/system/sensitive', 'View', 'none', 10, NULL, 0);
INSERT INTO `menue` VALUES (38, '账户敏感操作记录', '/system/sensitive/account', 'View', 'none', 37, NULL, 0);
INSERT INTO `menue` VALUES (39, '企业敏感操作记录', '/system/sensitive/enterprise', 'View', 'none', 37, NULL, 0);
INSERT INTO `menue` VALUES (40, '角色敏感操作记录', '/system/sensitive/role', 'View', 'none', 37, NULL, 0);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `pid` int(0) NOT NULL AUTO_INCREMENT,
  `cid` int(0) NOT NULL,
  `eid` int(0) NOT NULL,
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `code` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `unit` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `is_major` smallint(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `eid`(`eid`) USING BTREE,
  INDEX `cid`(`cid`) USING BTREE,
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `enterprise` (`eid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `classification` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (32, 5, 352, 'default.png', 'FS001', '新鲜草莓礼盒', '件', 1);
INSERT INTO `product` VALUES (33, 5, 352, 'default.png', 'OG002', '有机菠菜500g', '件', 0);
INSERT INTO `product` VALUES (34, 5, 352, 'default.png', 'MB003', '精选牛肉块', '件', 0);
INSERT INTO `product` VALUES (35, 5, 352, 'default.png', 'WB004', '野生蓝莓200g', '件', 0);
INSERT INTO `product` VALUES (36, 5, 352, 'default.png', 'FJ005', '农家土鸡蛋12枚', '件', 0);
INSERT INTO `product` VALUES (37, 5, 352, 'default.png', 'OJ006', '鲜榨橙汁1L', '件', 0);
INSERT INTO `product` VALUES (38, 5, 352, 'default.png', 'CF007', '深海鳕鱼片', '件', 0);
INSERT INTO `product` VALUES (39, 5, 352, 'default.png', 'GV008', '绿叶蔬菜混合包', '件', 0);
INSERT INTO `product` VALUES (40, 5, 352, 'default.png', 'OP009', '有机黑猪肉', '件', 0);
INSERT INTO `product` VALUES (41, 5, 352, 'default.png', 'CT010', '散养土鸡整只', '件', 0);

-- ----------------------------
-- Table structure for product_record
-- ----------------------------
DROP TABLE IF EXISTS `product_record`;
CREATE TABLE `product_record`  (
  `rid` int(0) NOT NULL AUTO_INCREMENT,
  `pid` int(0) NOT NULL,
  `aid` int(0) NOT NULL,
  `num` int(0) NOT NULL,
  `approver` int(0) NULL DEFAULT NULL,
  `process_time` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `insert_time` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `import_type` smallint(0) NOT NULL DEFAULT 0,
  `statue` smallint(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`rid`) USING BTREE,
  INDEX `aid`(`aid`) USING BTREE,
  INDEX `approver`(`approver`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  CONSTRAINT `product_record_ibfk_1` FOREIGN KEY (`aid`) REFERENCES `account` (`aid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_record_ibfk_2` FOREIGN KEY (`approver`) REFERENCES `account` (`aid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_record_ibfk_3` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_record
-- ----------------------------
INSERT INTO `product_record` VALUES (32, 32, 1, 50, 1, '2024-08-04 15:47:18', '2024-08-04 11:20:07', 0, 1);
INSERT INTO `product_record` VALUES (33, 33, 1, 50, 1, '2024-08-04 16:03:29', '2024-08-04 11:01:22', 0, 2);
INSERT INTO `product_record` VALUES (34, 34, 1, 50, 1, '2024-08-04 16:24:02', '2024-08-04 11:01:22', 0, 1);
INSERT INTO `product_record` VALUES (35, 35, 1, 50, 1, '2024-08-04 16:24:02', '2024-08-04 11:01:22', 0, 1);
INSERT INTO `product_record` VALUES (36, 36, 1, 50, 1, '2024-08-04 16:24:02', '2024-08-04 11:01:22', 0, 1);
INSERT INTO `product_record` VALUES (37, 37, 1, 50, 1, '2024-08-04 16:24:02', '2024-08-04 11:01:22', 0, 1);
INSERT INTO `product_record` VALUES (38, 38, 1, 50, 1, '2024-08-04 16:24:02', '2024-08-04 11:01:22', 0, 1);
INSERT INTO `product_record` VALUES (39, 39, 1, 50, 1, '2024-08-04 16:24:45', '2024-08-04 11:01:22', 0, 2);
INSERT INTO `product_record` VALUES (40, 40, 1, 50, 1, '2024-08-04 16:24:45', '2024-08-04 11:01:22', 0, 2);
INSERT INTO `product_record` VALUES (41, 41, 1, 50, 1, '2024-08-04 16:24:45', '2024-08-04 11:01:22', 0, 2);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `rid` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `memo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `del` smallint(0) NULL DEFAULT 0,
  `ban` smallint(0) NULL DEFAULT 0,
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级系统管理员', '开发人员', 0, 0);
INSERT INTO `role` VALUES (8, '企业用户', '普通用户', 0, 0);
INSERT INTO `role` VALUES (9, '系统管理员', '维护溯源系统人员', 0, 0);
INSERT INTO `role` VALUES (10, '监管人员', '分析追溯数据人员', 0, 0);

-- ----------------------------
-- Table structure for role_menue_contrast
-- ----------------------------
DROP TABLE IF EXISTS `role_menue_contrast`;
CREATE TABLE `role_menue_contrast`  (
  `rid` int(0) NOT NULL,
  `mid` int(0) NOT NULL,
  PRIMARY KEY (`rid`, `mid`) USING BTREE,
  INDEX `mid`(`mid`) USING BTREE,
  CONSTRAINT `role_menue_contrast_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_menue_contrast_ibfk_2` FOREIGN KEY (`mid`) REFERENCES `menue` (`mid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menue_contrast
-- ----------------------------
INSERT INTO `role_menue_contrast` VALUES (1, 1);
INSERT INTO `role_menue_contrast` VALUES (8, 1);
INSERT INTO `role_menue_contrast` VALUES (1, 4);
INSERT INTO `role_menue_contrast` VALUES (10, 4);
INSERT INTO `role_menue_contrast` VALUES (1, 7);
INSERT INTO `role_menue_contrast` VALUES (8, 7);
INSERT INTO `role_menue_contrast` VALUES (10, 7);
INSERT INTO `role_menue_contrast` VALUES (1, 8);
INSERT INTO `role_menue_contrast` VALUES (10, 8);
INSERT INTO `role_menue_contrast` VALUES (1, 10);
INSERT INTO `role_menue_contrast` VALUES (9, 10);
INSERT INTO `role_menue_contrast` VALUES (1, 11);
INSERT INTO `role_menue_contrast` VALUES (10, 11);
INSERT INTO `role_menue_contrast` VALUES (1, 12);
INSERT INTO `role_menue_contrast` VALUES (10, 12);
INSERT INTO `role_menue_contrast` VALUES (1, 13);
INSERT INTO `role_menue_contrast` VALUES (10, 13);
INSERT INTO `role_menue_contrast` VALUES (1, 14);
INSERT INTO `role_menue_contrast` VALUES (8, 14);
INSERT INTO `role_menue_contrast` VALUES (10, 14);
INSERT INTO `role_menue_contrast` VALUES (1, 15);
INSERT INTO `role_menue_contrast` VALUES (8, 15);
INSERT INTO `role_menue_contrast` VALUES (10, 15);
INSERT INTO `role_menue_contrast` VALUES (1, 16);
INSERT INTO `role_menue_contrast` VALUES (8, 16);
INSERT INTO `role_menue_contrast` VALUES (10, 16);
INSERT INTO `role_menue_contrast` VALUES (1, 17);
INSERT INTO `role_menue_contrast` VALUES (10, 17);
INSERT INTO `role_menue_contrast` VALUES (1, 18);
INSERT INTO `role_menue_contrast` VALUES (10, 18);
INSERT INTO `role_menue_contrast` VALUES (1, 19);
INSERT INTO `role_menue_contrast` VALUES (10, 19);
INSERT INTO `role_menue_contrast` VALUES (1, 20);
INSERT INTO `role_menue_contrast` VALUES (10, 20);
INSERT INTO `role_menue_contrast` VALUES (1, 21);
INSERT INTO `role_menue_contrast` VALUES (10, 21);
INSERT INTO `role_menue_contrast` VALUES (1, 22);
INSERT INTO `role_menue_contrast` VALUES (10, 22);
INSERT INTO `role_menue_contrast` VALUES (1, 23);
INSERT INTO `role_menue_contrast` VALUES (10, 23);
INSERT INTO `role_menue_contrast` VALUES (1, 24);
INSERT INTO `role_menue_contrast` VALUES (10, 24);
INSERT INTO `role_menue_contrast` VALUES (1, 25);
INSERT INTO `role_menue_contrast` VALUES (8, 25);
INSERT INTO `role_menue_contrast` VALUES (1, 26);
INSERT INTO `role_menue_contrast` VALUES (8, 26);
INSERT INTO `role_menue_contrast` VALUES (1, 27);
INSERT INTO `role_menue_contrast` VALUES (8, 27);
INSERT INTO `role_menue_contrast` VALUES (1, 28);
INSERT INTO `role_menue_contrast` VALUES (8, 28);
INSERT INTO `role_menue_contrast` VALUES (1, 29);
INSERT INTO `role_menue_contrast` VALUES (8, 29);
INSERT INTO `role_menue_contrast` VALUES (1, 30);
INSERT INTO `role_menue_contrast` VALUES (10, 30);
INSERT INTO `role_menue_contrast` VALUES (1, 31);
INSERT INTO `role_menue_contrast` VALUES (10, 31);
INSERT INTO `role_menue_contrast` VALUES (1, 32);
INSERT INTO `role_menue_contrast` VALUES (10, 32);
INSERT INTO `role_menue_contrast` VALUES (1, 33);
INSERT INTO `role_menue_contrast` VALUES (9, 33);
INSERT INTO `role_menue_contrast` VALUES (1, 34);
INSERT INTO `role_menue_contrast` VALUES (1, 35);
INSERT INTO `role_menue_contrast` VALUES (9, 35);
INSERT INTO `role_menue_contrast` VALUES (1, 36);
INSERT INTO `role_menue_contrast` VALUES (9, 36);
INSERT INTO `role_menue_contrast` VALUES (1, 37);
INSERT INTO `role_menue_contrast` VALUES (9, 37);
INSERT INTO `role_menue_contrast` VALUES (1, 38);
INSERT INTO `role_menue_contrast` VALUES (9, 38);
INSERT INTO `role_menue_contrast` VALUES (1, 39);
INSERT INTO `role_menue_contrast` VALUES (9, 39);
INSERT INTO `role_menue_contrast` VALUES (1, 40);
INSERT INTO `role_menue_contrast` VALUES (9, 40);

-- ----------------------------
-- Table structure for role_operate
-- ----------------------------
DROP TABLE IF EXISTS `role_operate`;
CREATE TABLE `role_operate`  (
  `iid` int(0) NOT NULL AUTO_INCREMENT,
  `oid` int(0) NOT NULL,
  `rid` int(0) NOT NULL,
  `operate` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `operate_time` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`iid`) USING BTREE,
  INDEX `oid`(`oid`) USING BTREE,
  INDEX `rid`(`rid`) USING BTREE,
  CONSTRAINT `role_operate_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `account` (`aid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_operate_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_operate
-- ----------------------------
INSERT INTO `role_operate` VALUES (1, 1, 8, '创建角色', '2024-07-29 19:36:29');
INSERT INTO `role_operate` VALUES (2, 1, 9, '创建角色', '2024-07-30 09:01:50');
INSERT INTO `role_operate` VALUES (3, 1, 10, '创建角色', '2024-07-30 09:02:41');
INSERT INTO `role_operate` VALUES (4, 1, 8, '修改角色状态', '2024-07-30 10:24:08');
INSERT INTO `role_operate` VALUES (5, 1, 8, '修改角色状态', '2024-07-30 10:25:23');
INSERT INTO `role_operate` VALUES (6, 1, 8, '修改角色状态', '2024-07-30 10:27:07');
INSERT INTO `role_operate` VALUES (7, 1, 8, '修改角色状态', '2024-07-30 10:29:42');
INSERT INTO `role_operate` VALUES (8, 1, 8, '修改角色状态', '2024-07-30 10:29:58');
INSERT INTO `role_operate` VALUES (9, 1, 8, '修改角色状态', '2024-07-30 10:30:02');
INSERT INTO `role_operate` VALUES (10, 1, 8, '修改角色状态', '2024-07-30 10:31:21');
INSERT INTO `role_operate` VALUES (11, 1, 8, '修改角色状态', '2024-07-30 10:36:19');
INSERT INTO `role_operate` VALUES (12, 1, 8, '修改角色状态', '2024-07-30 10:38:17');
INSERT INTO `role_operate` VALUES (13, 1, 8, '修改角色信息', '2024-07-30 12:20:31');
INSERT INTO `role_operate` VALUES (14, 1, 8, '修改角色信息', '2024-07-30 12:20:51');
INSERT INTO `role_operate` VALUES (15, 1, 1, '禁用所有角色', '2024-07-30 13:08:46');
INSERT INTO `role_operate` VALUES (16, 1, 1, '启用所有角色', '2024-07-30 13:09:21');
INSERT INTO `role_operate` VALUES (17, 1, 1, '启用所有角色', '2024-07-30 13:10:07');
INSERT INTO `role_operate` VALUES (18, 1, 1, '启用所有角色', '2024-07-30 13:10:35');
INSERT INTO `role_operate` VALUES (19, 1, 8, '修改角色状态', '2024-07-30 13:13:10');
INSERT INTO `role_operate` VALUES (20, 1, 9, '修改角色状态', '2024-07-30 13:13:12');
INSERT INTO `role_operate` VALUES (21, 1, 10, '修改角色状态', '2024-07-30 13:13:16');
INSERT INTO `role_operate` VALUES (22, 1, 1, '启用所有角色', '2024-07-30 13:13:23');
INSERT INTO `role_operate` VALUES (23, 1, 8, '修改角色状态', '2024-07-30 13:33:43');
INSERT INTO `role_operate` VALUES (24, 1, 8, '修改角色状态', '2024-07-30 13:34:12');
INSERT INTO `role_operate` VALUES (25, 1, 9, '修改角色信息', '2024-07-30 14:08:38');
INSERT INTO `role_operate` VALUES (26, 1, 8, '修改角色状态', '2024-08-04 11:24:41');
INSERT INTO `role_operate` VALUES (27, 1, 8, '修改角色状态', '2024-08-04 11:24:58');

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `sid` int(0) NOT NULL AUTO_INCREMENT,
  `eid` int(0) NOT NULL,
  `code` varchar(26) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` smallint(0) NOT NULL DEFAULT 0,
  `generate` smallint(0) NOT NULL DEFAULT 0,
  `is_trace` smallint(0) NOT NULL DEFAULT 0,
  `del` smallint(0) NULL DEFAULT 0,
  PRIMARY KEY (`sid`) USING BTREE,
  INDEX `eid`(`eid`) USING BTREE,
  CONSTRAINT `supplier_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `enterprise` (`eid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 323 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES (1, 1, '66aaeecfad66b14d35536ffe', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (2, 22, '66aaeecfad66b14d35536fff', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (3, 23, '66aaeecfad66b14d35537000', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (4, 24, '66aaeecfad66b14d35537001', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (5, 25, '66aaeecfad66b14d35537002', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (6, 26, '66aaeecfad66b14d35537003', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (7, 27, '66aaeecfad66b14d35537004', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (8, 28, '66aaeecfad66b14d35537005', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (9, 29, '66aaeecfad66b14d35537006', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (10, 30, '66aaeecfad66b14d35537007', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (11, 31, '66aaeecfad66b14d35537008', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (12, 32, '66aaeecfad66b14d35537009', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (13, 33, '66aaeecfad66b14d3553700a', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (14, 34, '66aaeecfad66b14d3553700b', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (15, 35, '66aaeecfad66b14d3553700c', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (16, 36, '66aaeecfad66b14d3553700d', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (17, 37, '66aaeecfad66b14d3553700e', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (18, 38, '66aaeecfad66b14d3553700f', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (19, 39, '66aaeecfad66b14d35537010', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (20, 40, '66aaeecfad66b14d35537011', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (21, 41, '66aaeecfad66b14d35537012', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (22, 52, '66aaeecfad66b14d35537013', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (23, 53, '66aaeecfad66b14d35537014', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (24, 54, '66aaeecfad66b14d35537015', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (25, 55, '66aaeecfad66b14d35537016', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (26, 56, '66aaeecfad66b14d35537017', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (27, 57, '66aaeecfad66b14d35537018', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (28, 58, '66aaeecfad66b14d35537019', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (29, 59, '66aaeecfad66b14d3553701a', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (30, 60, '66aaeecfad66b14d3553701b', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (31, 61, '66aaeecfad66b14d3553701c', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (32, 62, '66aaeecfad66b14d3553701d', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (33, 63, '66aaeecfad66b14d3553701e', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (34, 64, '66aaeecfad66b14d3553701f', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (35, 65, '66aaeecfad66b14d35537020', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (36, 66, '66aaeecfad66b14d35537021', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (37, 67, '66aaeecfad66b14d35537022', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (38, 68, '66aaeecfad66b14d35537023', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (39, 69, '66aaeecfad66b14d35537024', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (40, 70, '66aaeecfad66b14d35537025', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (41, 71, '66aaeecfad66b14d35537026', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (42, 72, '66aaeecfad66b14d35537027', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (43, 73, '66aaeecfad66b14d35537028', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (44, 74, '66aaeecfad66b14d35537029', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (45, 75, '66aaeecfad66b14d3553702a', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (46, 76, '66aaeecfad66b14d3553702b', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (47, 77, '66aaeecfad66b14d3553702c', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (48, 78, '66aaeecfad66b14d3553702d', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (49, 79, '66aaeecfad66b14d3553702e', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (50, 80, '66aaeecfad66b14d3553702f', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (51, 81, '66aaeecfad66b14d35537030', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (52, 82, '66aaeecfad66b14d35537031', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (53, 83, '66aaeecfad66b14d35537032', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (54, 84, '66aaeecfad66b14d35537033', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (55, 85, '66aaeecfad66b14d35537034', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (56, 86, '66aaeecfad66b14d35537035', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (57, 87, '66aaeecfad66b14d35537036', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (58, 88, '66aaeecfad66b14d35537037', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (59, 89, '66aaeecfad66b14d35537038', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (60, 90, '66aaeecfad66b14d35537039', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (61, 91, '66aaeecfad66b14d3553703a', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (62, 92, '66aaeecfad66b14d3553703b', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (63, 93, '66aaeecfad66b14d3553703c', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (64, 94, '66aaeecfad66b14d3553703d', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (65, 95, '66aaeecfad66b14d3553703e', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (66, 96, '66aaeecfad66b14d3553703f', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (67, 97, '66aaeecfad66b14d35537040', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (68, 98, '66aaeecfad66b14d35537041', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (69, 99, '66aaeecfad66b14d35537042', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (70, 100, '66aaeecfad66b14d35537043', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (71, 101, '66aaeecfad66b14d35537044', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (72, 102, '66aaeecfad66b14d35537045', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (73, 103, '66aaeecfad66b14d35537046', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (74, 104, '66aaeecfad66b14d35537047', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (75, 105, '66aaeecfad66b14d35537048', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (76, 106, '66aaeecfad66b14d35537049', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (77, 107, '66aaeecfad66b14d3553704a', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (78, 108, '66aaeecfad66b14d3553704b', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (79, 109, '66aaeecfad66b14d3553704c', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (80, 110, '66aaeecfad66b14d3553704d', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (81, 111, '66aaeecfad66b14d3553704e', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (82, 112, '66aaeecfad66b14d3553704f', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (83, 113, '66aaeecfad66b14d35537050', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (84, 114, '66aaeecfad66b14d35537051', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (85, 115, '66aaeecfad66b14d35537052', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (86, 116, '66aaeecfad66b14d35537053', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (87, 117, '66aaeecfad66b14d35537054', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (88, 118, '66aaeecfad66b14d35537055', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (89, 119, '66aaeecfad66b14d35537056', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (90, 120, '66aaeecfad66b14d35537057', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (91, 121, '66aaeecfad66b14d35537058', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (92, 122, '66aaeecfad66b14d35537059', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (93, 123, '66aaeecfad66b14d3553705a', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (94, 124, '66aaeecfad66b14d3553705b', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (95, 125, '66aaeecfad66b14d3553705c', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (96, 126, '66aaeecfad66b14d3553705d', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (97, 127, '66aaeecfad66b14d3553705e', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (98, 128, '66aaeecfad66b14d3553705f', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (99, 129, '66aaeecfad66b14d35537060', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (100, 130, '66aaeecfad66b14d35537061', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (101, 131, '66aaeecfad66b14d35537062', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (102, 132, '66aaeecfad66b14d35537063', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (103, 133, '66aaeecfad66b14d35537064', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (104, 134, '66aaeecfad66b14d35537065', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (105, 135, '66aaeecfad66b14d35537066', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (106, 136, '66aaeecfad66b14d35537067', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (107, 137, '66aaeecfad66b14d35537068', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (108, 138, '66aaeecfad66b14d35537069', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (109, 139, '66aaeecfad66b14d3553706a', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (110, 140, '66aaeed0ad66b14d3553706b', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (111, 141, '66aaeed0ad66b14d3553706c', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (112, 142, '66aaeed0ad66b14d3553706d', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (113, 143, '66aaeed0ad66b14d3553706e', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (114, 144, '66aaeed0ad66b14d3553706f', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (115, 145, '66aaeed0ad66b14d35537070', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (116, 146, '66aaeed0ad66b14d35537071', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (117, 147, '66aaeed0ad66b14d35537072', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (118, 148, '66aaeed0ad66b14d35537073', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (119, 149, '66aaeed0ad66b14d35537074', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (120, 150, '66aaeed0ad66b14d35537075', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (121, 151, '66aaeed0ad66b14d35537076', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (122, 152, '66aaeed0ad66b14d35537077', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (123, 153, '66aaeed0ad66b14d35537078', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (124, 154, '66aaeed0ad66b14d35537079', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (125, 155, '66aaeed0ad66b14d3553707a', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (126, 156, '66aaeed0ad66b14d3553707b', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (127, 157, '66aaeed0ad66b14d3553707c', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (128, 158, '66aaeed0ad66b14d3553707d', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (129, 159, '66aaeed0ad66b14d3553707e', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (130, 160, '66aaeed0ad66b14d3553707f', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (131, 161, '66aaeed0ad66b14d35537080', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (132, 162, '66aaeed0ad66b14d35537081', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (133, 163, '66aaeed0ad66b14d35537082', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (134, 164, '66aaeed0ad66b14d35537083', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (135, 165, '66aaeed0ad66b14d35537084', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (136, 166, '66aaeed0ad66b14d35537085', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (137, 167, '66aaeed0ad66b14d35537086', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (138, 168, '66aaeed0ad66b14d35537087', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (139, 169, '66aaeed0ad66b14d35537088', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (140, 170, '66aaeed0ad66b14d35537089', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (141, 171, '66aaeed0ad66b14d3553708a', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (142, 172, '66aaeed0ad66b14d3553708b', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (143, 173, '66aaeed0ad66b14d3553708c', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (144, 174, '66aaeed0ad66b14d3553708d', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (145, 175, '66aaeed0ad66b14d3553708e', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (146, 176, '66aaeed0ad66b14d3553708f', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (147, 177, '66aaeed0ad66b14d35537090', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (148, 178, '66aaeed0ad66b14d35537091', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (149, 179, '66aaeed0ad66b14d35537092', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (150, 180, '66aaeed0ad66b14d35537093', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (151, 181, '66aaeed0ad66b14d35537094', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (152, 182, '66aaeed0ad66b14d35537095', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (153, 183, '66aaeed0ad66b14d35537096', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (154, 184, '66aaeed0ad66b14d35537097', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (155, 185, '66aaeed0ad66b14d35537098', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (156, 186, '66aaeed0ad66b14d35537099', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (157, 187, '66aaeed0ad66b14d3553709a', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (158, 188, '66aaeed0ad66b14d3553709b', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (159, 189, '66aaeed0ad66b14d3553709c', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (160, 190, '66aaeed0ad66b14d3553709d', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (161, 191, '66aaeed0ad66b14d3553709e', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (162, 192, '66aaeed0ad66b14d3553709f', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (163, 193, '66aaeed0ad66b14d355370a0', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (164, 194, '66aaeed0ad66b14d355370a1', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (165, 195, '66aaeed0ad66b14d355370a2', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (166, 196, '66aaeed0ad66b14d355370a3', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (167, 197, '66aaeed0ad66b14d355370a4', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (168, 198, '66aaeed0ad66b14d355370a5', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (169, 199, '66aaeed0ad66b14d355370a6', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (170, 200, '66aaeed0ad66b14d355370a7', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (171, 201, '66aaeed0ad66b14d355370a8', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (172, 202, '66aaeed0ad66b14d355370a9', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (173, 203, '66aaeed0ad66b14d355370aa', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (174, 204, '66aaeed0ad66b14d355370ab', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (175, 205, '66aaeed0ad66b14d355370ac', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (176, 206, '66aaeed0ad66b14d355370ad', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (177, 207, '66aaeed0ad66b14d355370ae', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (178, 208, '66aaeed0ad66b14d355370af', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (179, 209, '66aaeed0ad66b14d355370b0', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (180, 210, '66aaeed0ad66b14d355370b1', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (181, 211, '66aaeed0ad66b14d355370b2', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (182, 212, '66aaeed0ad66b14d355370b3', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (183, 213, '66aaeed0ad66b14d355370b4', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (184, 214, '66aaeed0ad66b14d355370b5', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (185, 215, '66aaeed0ad66b14d355370b6', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (186, 216, '66aaeed0ad66b14d355370b7', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (187, 217, '66aaeed0ad66b14d355370b8', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (188, 218, '66aaeed0ad66b14d355370b9', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (189, 219, '66aaeed0ad66b14d355370ba', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (190, 220, '66aaeed0ad66b14d355370bb', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (191, 221, '66aaeed0ad66b14d355370bc', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (192, 222, '66aaeed0ad66b14d355370bd', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (193, 223, '66aaeed0ad66b14d355370be', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (194, 224, '66aaeed0ad66b14d355370bf', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (195, 225, '66aaeed0ad66b14d355370c0', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (196, 226, '66aaeed0ad66b14d355370c1', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (197, 227, '66aaeed0ad66b14d355370c2', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (198, 228, '66aaeed0ad66b14d355370c3', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (199, 229, '66aaeed0ad66b14d355370c4', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (200, 230, '66aaeed0ad66b14d355370c5', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (201, 231, '66aaeed0ad66b14d355370c6', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (202, 232, '66aaeed0ad66b14d355370c7', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (203, 233, '66aaeed0ad66b14d355370c8', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (204, 234, '66aaeed0ad66b14d355370c9', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (205, 235, '66aaeed0ad66b14d355370ca', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (206, 236, '66aaeed0ad66b14d355370cb', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (207, 237, '66aaeed0ad66b14d355370cc', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (208, 238, '66aaeed0ad66b14d355370cd', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (209, 239, '66aaeed0ad66b14d355370ce', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (210, 240, '66aaeed0ad66b14d355370cf', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (211, 241, '66aaeed0ad66b14d355370d0', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (212, 242, '66aaeed0ad66b14d355370d1', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (213, 243, '66aaeed0ad66b14d355370d2', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (214, 244, '66aaeed0ad66b14d355370d3', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (215, 245, '66aaeed0ad66b14d355370d4', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (216, 246, '66aaeed0ad66b14d355370d5', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (217, 247, '66aaeed0ad66b14d355370d6', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (218, 248, '66aaeed0ad66b14d355370d7', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (219, 249, '66aaeed0ad66b14d355370d8', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (220, 250, '66aaeed0ad66b14d355370d9', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (221, 251, '66aaeed0ad66b14d355370da', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (222, 252, '66aaeed0ad66b14d355370db', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (223, 253, '66aaeed0ad66b14d355370dc', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (224, 254, '66aaeed0ad66b14d355370dd', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (225, 255, '66aaeed0ad66b14d355370de', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (226, 256, '66aaeed0ad66b14d355370df', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (227, 257, '66aaeed0ad66b14d355370e0', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (228, 258, '66aaeed0ad66b14d355370e1', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (229, 259, '66aaeed0ad66b14d355370e2', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (230, 260, '66aaeed0ad66b14d355370e3', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (231, 261, '66aaeed0ad66b14d355370e4', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (232, 262, '66aaeed0ad66b14d355370e5', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (233, 263, '66aaeed0ad66b14d355370e6', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (234, 264, '66aaeed0ad66b14d355370e7', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (235, 265, '66aaeed0ad66b14d355370e8', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (236, 266, '66aaeed0ad66b14d355370e9', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (237, 267, '66aaeed0ad66b14d355370ea', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (238, 268, '66aaeed0ad66b14d355370eb', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (239, 269, '66aaeed0ad66b14d355370ec', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (240, 270, '66aaeed0ad66b14d355370ed', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (241, 271, '66aaeed0ad66b14d355370ee', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (242, 272, '66aaeed0ad66b14d355370ef', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (243, 273, '66aaeed0ad66b14d355370f0', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (244, 274, '66aaeed0ad66b14d355370f1', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (245, 275, '66aaeed0ad66b14d355370f2', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (246, 276, '66aaeed0ad66b14d355370f3', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (247, 277, '66aaeed0ad66b14d355370f4', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (248, 278, '66aaeed0ad66b14d355370f5', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (249, 279, '66aaeed0ad66b14d355370f6', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (250, 280, '66aaeed0ad66b14d355370f7', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (251, 281, '66aaeed0ad66b14d355370f8', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (252, 282, '66aaeed0ad66b14d355370f9', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (253, 283, '66aaeed0ad66b14d355370fa', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (254, 284, '66aaeed0ad66b14d355370fb', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (255, 285, '66aaeed0ad66b14d355370fc', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (256, 286, '66aaeed0ad66b14d355370fd', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (257, 287, '66aaeed0ad66b14d355370fe', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (258, 288, '66aaeed0ad66b14d355370ff', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (259, 289, '66aaeed0ad66b14d35537100', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (260, 290, '66aaeed0ad66b14d35537101', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (261, 291, '66aaeed0ad66b14d35537102', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (262, 292, '66aaeed0ad66b14d35537103', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (263, 293, '66aaeed0ad66b14d35537104', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (264, 294, '66aaeed0ad66b14d35537105', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (265, 295, '66aaeed0ad66b14d35537106', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (266, 296, '66aaeed0ad66b14d35537107', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (267, 297, '66aaeed0ad66b14d35537108', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (268, 298, '66aaeed0ad66b14d35537109', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (269, 299, '66aaeed0ad66b14d3553710a', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (270, 300, '66aaeed0ad66b14d3553710b', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (271, 301, '66aaeed0ad66b14d3553710c', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (272, 302, '66aaeed0ad66b14d3553710d', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (273, 303, '66aaeed0ad66b14d3553710e', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (274, 304, '66aaeed0ad66b14d3553710f', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (275, 305, '66aaeed0ad66b14d35537110', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (276, 306, '66aaeed0ad66b14d35537111', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (277, 307, '66aaeed0ad66b14d35537112', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (278, 308, '66aaeed0ad66b14d35537113', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (279, 309, '66aaeed0ad66b14d35537114', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (280, 310, '66aaeed0ad66b14d35537115', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (281, 311, '66aaeed0ad66b14d35537116', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (282, 312, '66aaeed0ad66b14d35537117', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (283, 313, '66aaeed0ad66b14d35537118', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (284, 314, '66aaeed0ad66b14d35537119', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (285, 315, '66aaeed0ad66b14d3553711a', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (286, 316, '66aaeed0ad66b14d3553711b', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (287, 317, '66aaeed0ad66b14d3553711c', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (288, 318, '66aaeed0ad66b14d3553711d', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (289, 319, '66aaeed0ad66b14d3553711e', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (290, 320, '66aaeed0ad66b14d3553711f', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (291, 321, '66aaeed0ad66b14d35537120', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (292, 322, '66aaeed0ad66b14d35537121', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (293, 323, '66aaeed0ad66b14d35537122', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (294, 324, '66aaeed0ad66b14d35537123', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (295, 325, '66aaeed0ad66b14d35537124', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (296, 326, '66aaeed0ad66b14d35537125', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (297, 327, '66aaeed0ad66b14d35537126', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (298, 328, '66aaeed0ad66b14d35537127', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (299, 329, '66aaeed0ad66b14d35537128', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (300, 330, '66aaeed0ad66b14d35537129', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (301, 331, '66aaeed0ad66b14d3553712a', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (302, 332, '66aaeed0ad66b14d3553712b', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (303, 333, '66aaeed0ad66b14d3553712c', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (304, 334, '66aaeed0ad66b14d3553712d', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (305, 335, '66aaeed0ad66b14d3553712e', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (306, 336, '66aaeed0ad66b14d3553712f', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (307, 337, '66aaeed0ad66b14d35537130', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (308, 338, '66aaeed0ad66b14d35537131', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (309, 339, '66aaeed0ad66b14d35537132', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (310, 340, '66aaeed0ad66b14d35537133', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (311, 341, '66aaeed0ad66b14d35537134', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (312, 342, '66aaeed0ad66b14d35537135', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (313, 343, '66aaeed0ad66b14d35537136', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (314, 344, '66aaeed0ad66b14d35537137', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (315, 345, '66aaeed0ad66b14d35537138', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (316, 346, '66aaeed0ad66b14d35537139', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (317, 347, '66aaeed0ad66b14d3553713a', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (318, 348, '66aaeed0ad66b14d3553713b', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (319, 349, '66aaeed0ad66b14d3553713c', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (320, 350, '66aaeed0ad66b14d3553713d', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (321, 351, '66aaeed0ad66b14d3553713e', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (322, 352, '66aaeed0ad66b14d3553713f', 2, 0, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
