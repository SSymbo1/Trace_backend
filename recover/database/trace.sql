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

 Date: 11/08/2024 14:27:38
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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `account_info` VALUES (1, 1, 1, 'SSymbol', '男', '13612345611', 'admin.jpg', '1277820742@qq.com', '黑龙江省佳木斯市', '100861');
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
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `account_operate` VALUES (61, 1, 1, '修改账号信息', '2024-08-06 13:19:51');

-- ----------------------------
-- Table structure for approach
-- ----------------------------
DROP TABLE IF EXISTS `approach`;
CREATE TABLE `approach`  (
  `aid` int(0) NOT NULL AUTO_INCREMENT,
  `eid` int(0) NOT NULL,
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `code` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `batch` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `num` int(0) NOT NULL,
  `unit` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `trace` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cid` int(0) NOT NULL,
  `sid` int(0) NOT NULL,
  `business_time` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`aid`) USING BTREE,
  INDEX `eid`(`eid`) USING BTREE,
  INDEX `cid`(`cid`) USING BTREE,
  INDEX `sid`(`sid`) USING BTREE,
  CONSTRAINT `approach_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `enterprise` (`eid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `approach_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `classification` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `approach_ibfk_3` FOREIGN KEY (`sid`) REFERENCES `supplier` (`eid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of approach
-- ----------------------------
INSERT INTO `approach` VALUES (1, 352, '新鲜草莓礼盒', 'FS001', 'bc001', 50, '件', 'trace-66b1b6a03726a542d854b215', 5, 58, '2024-08-06 13:40:33');
INSERT INTO `approach` VALUES (2, 352, '精选牛肉块', 'MB003', 'bc002', 50, '件', 'trace-66b1b6a03726a542d854b216', 5, 58, '2024-08-06 13:40:33');
INSERT INTO `approach` VALUES (3, 352, '野生蓝莓200g', 'WB004', 'bc003', 50, '件', 'trace-66b1b6a03726a542d854b217', 5, 58, '2024-08-06 13:40:33');
INSERT INTO `approach` VALUES (4, 352, '农家土鸡蛋12枚', 'FJ005', 'bc004', 50, '件', 'trace-66b1b6a03726a542d854b218', 5, 58, '2024-08-06 13:40:33');
INSERT INTO `approach` VALUES (5, 352, '鲜榨橙汁1L', 'OJ006', 'bc005', 50, '件', 'trace-66b1b6a03726a542d854b219', 5, 58, '2024-08-06 13:40:33');
INSERT INTO `approach` VALUES (6, 352, '深海鳕鱼片', 'CF007', 'bc006', 50, '件', 'trace-66b1b6a03726a542d854b21a', 5, 58, '2024-08-06 13:40:33');

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
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 352 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of enterprise
-- ----------------------------
INSERT INTO `enterprise` VALUES (1, '无所属公司', '无法人', '无', '无', '无', '无', 0);
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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of enterprise_operate
-- ----------------------------

-- ----------------------------
-- Table structure for entrance
-- ----------------------------
DROP TABLE IF EXISTS `entrance`;
CREATE TABLE `entrance`  (
  `eid` int(0) NOT NULL AUTO_INCREMENT,
  `bid` int(0) NOT NULL,
  `business_time` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `code` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `batch` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `num` int(0) NOT NULL,
  `unit` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `trace` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `buyer_type` smallint(0) NOT NULL DEFAULT 0,
  `cid` int(0) NOT NULL,
  `sid` int(0) NOT NULL,
  PRIMARY KEY (`eid`) USING BTREE,
  INDEX `cid`(`cid`) USING BTREE,
  INDEX `sid`(`sid`) USING BTREE,
  INDEX `bid`(`bid`) USING BTREE,
  CONSTRAINT `entrance_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `classification` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `entrance_ibfk_2` FOREIGN KEY (`sid`) REFERENCES `supplier` (`eid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `entrance_ibfk_3` FOREIGN KEY (`bid`) REFERENCES `enterprise` (`eid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of entrance
-- ----------------------------
INSERT INTO `entrance` VALUES (1, 352, '2024-08-06 13:37:36', '新鲜草莓礼盒', 'FS001', 'bc001', 50, '件', 'trace-66b1b6a03726a542d854b215', 1, 5, 58);
INSERT INTO `entrance` VALUES (2, 352, '2024-08-06 13:37:36', '精选牛肉块', 'MB003', 'bc002', 50, '件', 'trace-66b1b6a03726a542d854b216', 1, 5, 58);
INSERT INTO `entrance` VALUES (3, 352, '2024-08-06 13:37:36', '野生蓝莓200g', 'WB004', 'bc003', 50, '件', 'trace-66b1b6a03726a542d854b217', 1, 5, 58);
INSERT INTO `entrance` VALUES (4, 352, '2024-08-06 13:37:36', '农家土鸡蛋12枚', 'FJ005', 'bc004', 50, '件', 'trace-66b1b6a03726a542d854b218', 1, 5, 58);
INSERT INTO `entrance` VALUES (5, 352, '2024-08-06 13:37:36', '鲜榨橙汁1L', 'OJ006', 'bc005', 50, '件', 'trace-66b1b6a03726a542d854b219', 1, 5, 58);
INSERT INTO `entrance` VALUES (6, 352, '2024-08-06 13:37:36', '深海鳕鱼片', 'CF007', 'bc006', 50, '件', 'trace-66b1b6a03726a542d854b21a', 1, 5, 58);

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
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menue
-- ----------------------------
INSERT INTO `menue` VALUES (1, '追溯主体管理', '/subject', 'TraceSubject.svg', '#36a3f7', 0, NULL, 0);
INSERT INTO `menue` VALUES (4, '追溯数据分析', '/analysis', 'TracingData.svg', '#f4516c', 0, NULL, 0);
INSERT INTO `menue` VALUES (7, '运行监测管理', '/monitor', 'OperationMonitoring.svg', '#34bfa3', 0, NULL, 0);
INSERT INTO `menue` VALUES (8, '追溯环节管理', '/segment', 'TraceabilityProcess.svg', '#40c9c6', 0, NULL, 0);
INSERT INTO `menue` VALUES (10, '系统设置管理', '/system', 'SystemSet.svg', '#f4516c', 0, NULL, 0);
INSERT INTO `menue` VALUES (11, '环节管理', '/segment/manage', 'Connection', 'none', 8, NULL, 0);
INSERT INTO `menue` VALUES (12, '超市出场', '/segment/manage/entry', 'Connection', 'none', 11, NULL, 0);
INSERT INTO `menue` VALUES (13, '超市进场', '/segment/manage/appears', 'Connection', 'none', 11, NULL, 0);
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
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 622 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES (323, 1, '66b1b4cb37265bde76e247c6', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (324, 52, '66b1b4cb37265bde76e247c7', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (325, 53, '66b1b4cb37265bde76e247c8', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (326, 54, '66b1b4cb37265bde76e247c9', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (327, 55, '66b1b4cb37265bde76e247ca', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (328, 56, '66b1b4cb37265bde76e247cb', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (329, 57, '66b1b4cb37265bde76e247cc', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (330, 58, '66b1b4cb37265bde76e247cd', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (331, 59, '66b1b4cb37265bde76e247ce', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (332, 60, '66b1b4cb37265bde76e247cf', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (333, 61, '66b1b4cb37265bde76e247d0', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (334, 62, '66b1b4cb37265bde76e247d1', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (335, 63, '66b1b4cb37265bde76e247d2', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (336, 64, '66b1b4cb37265bde76e247d3', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (337, 65, '66b1b4cb37265bde76e247d4', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (338, 66, '66b1b4cb37265bde76e247d5', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (339, 67, '66b1b4cb37265bde76e247d6', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (340, 68, '66b1b4cb37265bde76e247d7', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (341, 69, '66b1b4cb37265bde76e247d8', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (342, 70, '66b1b4cb37265bde76e247d9', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (343, 71, '66b1b4cb37265bde76e247da', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (344, 72, '66b1b4cb37265bde76e247db', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (345, 73, '66b1b4cb37265bde76e247dc', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (346, 74, '66b1b4cb37265bde76e247dd', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (347, 75, '66b1b4cb37265bde76e247de', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (348, 76, '66b1b4cb37265bde76e247df', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (349, 77, '66b1b4cb37265bde76e247e0', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (350, 78, '66b1b4cb37265bde76e247e1', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (351, 79, '66b1b4cb37265bde76e247e2', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (352, 80, '66b1b4cb37265bde76e247e3', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (353, 81, '66b1b4cb37265bde76e247e4', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (354, 82, '66b1b4cb37265bde76e247e5', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (355, 83, '66b1b4cb37265bde76e247e6', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (356, 84, '66b1b4cb37265bde76e247e7', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (357, 85, '66b1b4cb37265bde76e247e8', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (358, 86, '66b1b4cb37265bde76e247e9', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (359, 87, '66b1b4cb37265bde76e247ea', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (360, 88, '66b1b4cb37265bde76e247eb', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (361, 89, '66b1b4cb37265bde76e247ec', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (362, 90, '66b1b4cb37265bde76e247ed', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (363, 91, '66b1b4cb37265bde76e247ee', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (364, 92, '66b1b4cb37265bde76e247ef', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (365, 93, '66b1b4cb37265bde76e247f0', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (366, 94, '66b1b4cb37265bde76e247f1', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (367, 95, '66b1b4cb37265bde76e247f2', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (368, 96, '66b1b4cb37265bde76e247f3', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (369, 97, '66b1b4cb37265bde76e247f4', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (370, 98, '66b1b4cb37265bde76e247f5', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (371, 99, '66b1b4cb37265bde76e247f6', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (372, 100, '66b1b4cb37265bde76e247f7', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (373, 101, '66b1b4cb37265bde76e247f8', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (374, 102, '66b1b4cb37265bde76e247f9', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (375, 103, '66b1b4cb37265bde76e247fa', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (376, 104, '66b1b4cb37265bde76e247fb', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (377, 105, '66b1b4cb37265bde76e247fc', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (378, 106, '66b1b4cb37265bde76e247fd', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (379, 107, '66b1b4cb37265bde76e247fe', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (380, 108, '66b1b4cb37265bde76e247ff', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (381, 109, '66b1b4cb37265bde76e24800', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (382, 110, '66b1b4cb37265bde76e24801', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (383, 111, '66b1b4cb37265bde76e24802', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (384, 112, '66b1b4cb37265bde76e24803', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (385, 113, '66b1b4cb37265bde76e24804', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (386, 114, '66b1b4cb37265bde76e24805', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (387, 115, '66b1b4cb37265bde76e24806', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (388, 116, '66b1b4cb37265bde76e24807', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (389, 117, '66b1b4cb37265bde76e24808', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (390, 118, '66b1b4cb37265bde76e24809', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (391, 119, '66b1b4cb37265bde76e2480a', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (392, 120, '66b1b4cb37265bde76e2480b', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (393, 121, '66b1b4cb37265bde76e2480c', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (394, 122, '66b1b4cb37265bde76e2480d', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (395, 123, '66b1b4cb37265bde76e2480e', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (396, 124, '66b1b4cb37265bde76e2480f', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (397, 125, '66b1b4cb37265bde76e24810', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (398, 126, '66b1b4cb37265bde76e24811', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (399, 127, '66b1b4cb37265bde76e24812', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (400, 128, '66b1b4cb37265bde76e24813', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (401, 129, '66b1b4cb37265bde76e24814', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (402, 130, '66b1b4cb37265bde76e24815', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (403, 131, '66b1b4cb37265bde76e24816', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (404, 132, '66b1b4cb37265bde76e24817', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (405, 133, '66b1b4cb37265bde76e24818', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (406, 134, '66b1b4cb37265bde76e24819', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (407, 135, '66b1b4cb37265bde76e2481a', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (408, 136, '66b1b4cb37265bde76e2481b', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (409, 137, '66b1b4cb37265bde76e2481c', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (410, 138, '66b1b4cb37265bde76e2481d', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (411, 139, '66b1b4cb37265bde76e2481e', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (412, 140, '66b1b4cb37265bde76e2481f', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (413, 141, '66b1b4cb37265bde76e24820', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (414, 142, '66b1b4cb37265bde76e24821', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (415, 143, '66b1b4cb37265bde76e24822', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (416, 144, '66b1b4cb37265bde76e24823', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (417, 145, '66b1b4cb37265bde76e24824', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (418, 146, '66b1b4cb37265bde76e24825', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (419, 147, '66b1b4cb37265bde76e24826', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (420, 148, '66b1b4cb37265bde76e24827', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (421, 150, '66b1b4cb37265bde76e24828', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (422, 151, '66b1b4cb37265bde76e24829', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (423, 152, '66b1b4cb37265bde76e2482a', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (424, 153, '66b1b4cb37265bde76e2482b', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (425, 154, '66b1b4cb37265bde76e2482c', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (426, 155, '66b1b4cb37265bde76e2482d', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (427, 156, '66b1b4cb37265bde76e2482e', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (428, 157, '66b1b4cb37265bde76e2482f', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (429, 158, '66b1b4cb37265bde76e24830', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (430, 159, '66b1b4cb37265bde76e24831', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (431, 160, '66b1b4cb37265bde76e24832', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (432, 161, '66b1b4cb37265bde76e24833', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (433, 162, '66b1b4cb37265bde76e24834', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (434, 163, '66b1b4cb37265bde76e24835', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (435, 164, '66b1b4cb37265bde76e24836', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (436, 165, '66b1b4cb37265bde76e24837', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (437, 166, '66b1b4cb37265bde76e24838', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (438, 167, '66b1b4cb37265bde76e24839', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (439, 168, '66b1b4cb37265bde76e2483a', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (440, 169, '66b1b4cb37265bde76e2483b', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (441, 170, '66b1b4cb37265bde76e2483c', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (442, 171, '66b1b4cb37265bde76e2483d', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (443, 172, '66b1b4cb37265bde76e2483e', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (444, 173, '66b1b4cb37265bde76e2483f', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (445, 174, '66b1b4cb37265bde76e24840', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (446, 175, '66b1b4cb37265bde76e24841', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (447, 176, '66b1b4cb37265bde76e24842', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (448, 177, '66b1b4cb37265bde76e24843', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (449, 178, '66b1b4cb37265bde76e24844', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (450, 179, '66b1b4cb37265bde76e24845', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (451, 180, '66b1b4cb37265bde76e24846', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (452, 181, '66b1b4cb37265bde76e24847', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (453, 182, '66b1b4cb37265bde76e24848', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (454, 183, '66b1b4cb37265bde76e24849', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (455, 184, '66b1b4cb37265bde76e2484a', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (456, 185, '66b1b4cb37265bde76e2484b', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (457, 186, '66b1b4cb37265bde76e2484c', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (458, 187, '66b1b4cb37265bde76e2484d', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (459, 188, '66b1b4cb37265bde76e2484e', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (460, 189, '66b1b4cb37265bde76e2484f', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (461, 190, '66b1b4cb37265bde76e24850', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (462, 191, '66b1b4cb37265bde76e24851', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (463, 192, '66b1b4cb37265bde76e24852', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (464, 193, '66b1b4cb37265bde76e24853', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (465, 194, '66b1b4cb37265bde76e24854', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (466, 195, '66b1b4cb37265bde76e24855', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (467, 196, '66b1b4cb37265bde76e24856', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (468, 197, '66b1b4cb37265bde76e24857', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (469, 198, '66b1b4cb37265bde76e24858', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (470, 199, '66b1b4cb37265bde76e24859', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (471, 200, '66b1b4cb37265bde76e2485a', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (472, 201, '66b1b4cb37265bde76e2485b', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (473, 202, '66b1b4cb37265bde76e2485c', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (474, 203, '66b1b4cb37265bde76e2485d', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (475, 204, '66b1b4cb37265bde76e2485e', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (476, 205, '66b1b4cb37265bde76e2485f', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (477, 206, '66b1b4cb37265bde76e24860', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (478, 207, '66b1b4cb37265bde76e24861', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (479, 208, '66b1b4cb37265bde76e24862', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (480, 209, '66b1b4cb37265bde76e24863', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (481, 210, '66b1b4cb37265bde76e24864', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (482, 211, '66b1b4cb37265bde76e24865', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (483, 212, '66b1b4cb37265bde76e24866', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (484, 213, '66b1b4cb37265bde76e24867', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (485, 214, '66b1b4cb37265bde76e24868', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (486, 215, '66b1b4cb37265bde76e24869', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (487, 216, '66b1b4cb37265bde76e2486a', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (488, 217, '66b1b4cb37265bde76e2486b', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (489, 218, '66b1b4cb37265bde76e2486c', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (490, 219, '66b1b4cb37265bde76e2486d', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (491, 220, '66b1b4cb37265bde76e2486e', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (492, 221, '66b1b4cb37265bde76e2486f', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (493, 222, '66b1b4cb37265bde76e24870', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (494, 223, '66b1b4cb37265bde76e24871', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (495, 224, '66b1b4cb37265bde76e24872', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (496, 225, '66b1b4cb37265bde76e24873', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (497, 226, '66b1b4cb37265bde76e24874', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (498, 227, '66b1b4cb37265bde76e24875', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (499, 228, '66b1b4cb37265bde76e24876', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (500, 229, '66b1b4cb37265bde76e24877', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (501, 230, '66b1b4cb37265bde76e24878', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (502, 231, '66b1b4cb37265bde76e24879', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (503, 232, '66b1b4cb37265bde76e2487a', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (504, 233, '66b1b4cb37265bde76e2487b', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (505, 234, '66b1b4cb37265bde76e2487c', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (506, 235, '66b1b4cb37265bde76e2487d', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (507, 236, '66b1b4cb37265bde76e2487e', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (508, 237, '66b1b4cb37265bde76e2487f', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (509, 238, '66b1b4cb37265bde76e24880', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (510, 239, '66b1b4cb37265bde76e24881', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (511, 240, '66b1b4cb37265bde76e24882', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (512, 241, '66b1b4cb37265bde76e24883', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (513, 242, '66b1b4cb37265bde76e24884', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (514, 243, '66b1b4cb37265bde76e24885', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (515, 244, '66b1b4cb37265bde76e24886', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (516, 245, '66b1b4cb37265bde76e24887', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (517, 246, '66b1b4cb37265bde76e24888', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (518, 247, '66b1b4cb37265bde76e24889', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (519, 248, '66b1b4cb37265bde76e2488a', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (520, 249, '66b1b4cb37265bde76e2488b', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (521, 250, '66b1b4cb37265bde76e2488c', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (522, 251, '66b1b4cb37265bde76e2488d', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (523, 252, '66b1b4cb37265bde76e2488e', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (524, 253, '66b1b4cb37265bde76e2488f', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (525, 254, '66b1b4cb37265bde76e24890', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (526, 255, '66b1b4cb37265bde76e24891', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (527, 256, '66b1b4cb37265bde76e24892', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (528, 257, '66b1b4cb37265bde76e24893', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (529, 258, '66b1b4cb37265bde76e24894', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (530, 259, '66b1b4cb37265bde76e24895', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (531, 260, '66b1b4cb37265bde76e24896', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (532, 261, '66b1b4cb37265bde76e24897', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (533, 262, '66b1b4cb37265bde76e24898', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (534, 263, '66b1b4cb37265bde76e24899', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (535, 264, '66b1b4cb37265bde76e2489a', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (536, 265, '66b1b4cb37265bde76e2489b', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (537, 266, '66b1b4cb37265bde76e2489c', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (538, 267, '66b1b4cb37265bde76e2489d', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (539, 268, '66b1b4cb37265bde76e2489e', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (540, 269, '66b1b4cb37265bde76e2489f', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (541, 270, '66b1b4cb37265bde76e248a0', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (542, 271, '66b1b4cb37265bde76e248a1', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (543, 272, '66b1b4cb37265bde76e248a2', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (544, 273, '66b1b4cb37265bde76e248a3', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (545, 274, '66b1b4cb37265bde76e248a4', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (546, 275, '66b1b4cb37265bde76e248a5', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (547, 276, '66b1b4cb37265bde76e248a6', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (548, 277, '66b1b4cb37265bde76e248a7', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (549, 278, '66b1b4cb37265bde76e248a8', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (550, 279, '66b1b4cb37265bde76e248a9', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (551, 280, '66b1b4cb37265bde76e248aa', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (552, 281, '66b1b4cb37265bde76e248ab', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (553, 282, '66b1b4cb37265bde76e248ac', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (554, 283, '66b1b4cb37265bde76e248ad', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (555, 284, '66b1b4cb37265bde76e248ae', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (556, 285, '66b1b4cb37265bde76e248af', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (557, 286, '66b1b4cb37265bde76e248b0', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (558, 287, '66b1b4cb37265bde76e248b1', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (559, 288, '66b1b4cb37265bde76e248b2', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (560, 289, '66b1b4cb37265bde76e248b3', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (561, 290, '66b1b4cb37265bde76e248b4', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (562, 291, '66b1b4cb37265bde76e248b5', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (563, 292, '66b1b4cb37265bde76e248b6', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (564, 293, '66b1b4cb37265bde76e248b7', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (565, 294, '66b1b4cb37265bde76e248b8', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (566, 295, '66b1b4cb37265bde76e248b9', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (567, 296, '66b1b4cb37265bde76e248ba', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (568, 297, '66b1b4cb37265bde76e248bb', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (569, 298, '66b1b4cb37265bde76e248bc', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (570, 299, '66b1b4cb37265bde76e248bd', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (571, 300, '66b1b4cb37265bde76e248be', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (572, 301, '66b1b4cb37265bde76e248bf', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (573, 302, '66b1b4cb37265bde76e248c0', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (574, 303, '66b1b4cb37265bde76e248c1', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (575, 304, '66b1b4cb37265bde76e248c2', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (576, 305, '66b1b4cb37265bde76e248c3', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (577, 306, '66b1b4cb37265bde76e248c4', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (578, 307, '66b1b4cb37265bde76e248c5', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (579, 308, '66b1b4cb37265bde76e248c6', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (580, 309, '66b1b4cb37265bde76e248c7', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (581, 310, '66b1b4cb37265bde76e248c8', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (582, 311, '66b1b4cb37265bde76e248c9', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (583, 312, '66b1b4cb37265bde76e248ca', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (584, 314, '66b1b4cb37265bde76e248cb', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (585, 315, '66b1b4cb37265bde76e248cc', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (586, 316, '66b1b4cb37265bde76e248cd', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (587, 317, '66b1b4cb37265bde76e248ce', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (588, 318, '66b1b4cb37265bde76e248cf', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (589, 319, '66b1b4cb37265bde76e248d0', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (590, 320, '66b1b4cb37265bde76e248d1', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (591, 321, '66b1b4cb37265bde76e248d2', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (592, 322, '66b1b4cb37265bde76e248d3', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (593, 323, '66b1b4cb37265bde76e248d4', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (594, 324, '66b1b4cb37265bde76e248d5', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (595, 325, '66b1b4cb37265bde76e248d6', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (596, 326, '66b1b4cb37265bde76e248d7', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (597, 327, '66b1b4cb37265bde76e248d8', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (598, 328, '66b1b4cb37265bde76e248d9', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (599, 329, '66b1b4cb37265bde76e248da', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (600, 330, '66b1b4cb37265bde76e248db', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (601, 331, '66b1b4cb37265bde76e248dc', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (602, 332, '66b1b4cb37265bde76e248dd', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (603, 333, '66b1b4cb37265bde76e248de', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (604, 334, '66b1b4cb37265bde76e248df', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (605, 335, '66b1b4cb37265bde76e248e0', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (606, 336, '66b1b4cb37265bde76e248e1', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (607, 337, '66b1b4cb37265bde76e248e2', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (608, 338, '66b1b4cb37265bde76e248e3', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (609, 339, '66b1b4cb37265bde76e248e4', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (610, 340, '66b1b4cb37265bde76e248e5', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (611, 341, '66b1b4cb37265bde76e248e6', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (612, 342, '66b1b4cb37265bde76e248e7', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (613, 343, '66b1b4cb37265bde76e248e8', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (614, 344, '66b1b4cb37265bde76e248e9', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (615, 345, '66b1b4cb37265bde76e248ea', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (616, 346, '66b1b4cb37265bde76e248eb', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (617, 347, '66b1b4cb37265bde76e248ec', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (618, 348, '66b1b4cb37265bde76e248ed', 1, 0, 0, 0);
INSERT INTO `supplier` VALUES (619, 349, '66b1b4cb37265bde76e248ee', 2, 0, 0, 0);
INSERT INTO `supplier` VALUES (620, 350, '66b1b4cb37265bde76e248ef', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (621, 351, '66b1b4cb37265bde76e248f0', 0, 0, 0, 0);
INSERT INTO `supplier` VALUES (622, 352, '66b1b4cb37265bde76e248f1', 2, 0, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
