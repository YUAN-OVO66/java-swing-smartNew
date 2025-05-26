/*
 Navicat Premium Dump SQL

 Source Server         : javaSwing
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30-cynos)
 Source Host           : sh-cynosdbmysql-grp-qf3gwiu4.sql.tencentcdb.com:22289
 Source Schema         : javaswing

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30-cynos)
 File Encoding         : 65001

 Date: 26/05/2025 11:07:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `abstractText` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `contentText` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 96 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (93, '这是一条test新闻', 'test', '这是一条测试新闻', 'yuan', '2025-05-16 16:21:32', '2025-05-16 16:21:32');
INSERT INTO `article` VALUES (94, '今天天气', '今天天气好啊', '今日天气甚佳', 'yuan', '2025-05-16 17:30:03', '2025-05-16 17:30:03');
INSERT INTO `article` VALUES (95, 'test', 'test', 'test', 'yuan', '2025-05-16 17:36:20', '2025-05-16 17:36:20');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '账号',
  `nickname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'yuan', 'yuan', '123456');
INSERT INTO `user` VALUES (2, 'admin123', 'yuan', '123456');
INSERT INTO `user` VALUES (3, 'LiFuNeng', 'LiFuNeng', '123456');
INSERT INTO `user` VALUES (4, 'kwj', 'kwj', '123456');
INSERT INTO `user` VALUES (6, 'xj', 'xj', '12345');

SET FOREIGN_KEY_CHECKS = 1;
