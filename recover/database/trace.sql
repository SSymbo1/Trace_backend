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

 Date: 24/07/2024 19:39:22
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'admin', 'dcd4cd4ba7f9c08199e721cdd30a3c08', 1, 0, 0, NULL);

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

-- ----------------------------
-- Table structure for enterprise
-- ----------------------------
DROP TABLE IF EXISTS `enterprise`;
CREATE TABLE `enterprise`  (
  `eid` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `legal_person` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tel` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `social_code` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `zip_code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`eid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of enterprise
-- ----------------------------
INSERT INTO `enterprise` VALUES (1, '无所属公司', '无法人', '无', '无', '无', '无');

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
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `menue` VALUES (33, '用户权限管理', '/system/account', 'User', 'none', 10, NULL, 0);
INSERT INTO `menue` VALUES (34, '用户管理', '/system/account/user', 'User', 'none', 33, NULL, 0);
INSERT INTO `menue` VALUES (35, '权限管理', '/system/account/role', 'User', 'none', 33, NULL, 0);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `rid` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `memo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `del` smallint(0) NULL DEFAULT 0,
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '系统管理员', '开发人员', 0);

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
INSERT INTO `role_menue_contrast` VALUES (1, 4);
INSERT INTO `role_menue_contrast` VALUES (1, 7);
INSERT INTO `role_menue_contrast` VALUES (1, 8);
INSERT INTO `role_menue_contrast` VALUES (1, 10);

SET FOREIGN_KEY_CHECKS = 1;
