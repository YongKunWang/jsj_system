/*
 Navicat Premium Data Transfer

 Source Server         : MYSQL
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : jsj

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 23/09/2018 03:54:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime(6) NOT NULL,
  `gmt_modified` datetime(6) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `realname` varchar(255) NOT NULL,
  `sex` tinyint(1) unsigned zerofill NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE COMMENT '用户名必须唯一'
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (1, '2018-09-19 00:00:00.000000', NULL, 'admin', '111111', '超级管理员', 1, '成都市武侯区西南民族大学');
INSERT INTO `t_user` VALUES (2, '2018-09-19 00:00:00.000000', NULL, 'user001', '111111', '用户001', 1, '成都市武侯区');
INSERT INTO `t_user` VALUES (4, '2018-09-19 00:00:00.000000', NULL, 'user002', '111111', '用户002', 1, '成都市武侯区');
INSERT INTO `t_user` VALUES (6, '2018-09-23 00:00:00.000000', '2018-09-23 00:00:00.000000', 'user003', '111111', '用户003', 0, '成都市双流机场');
INSERT INTO `t_user` VALUES (7, '2018-09-23 00:00:00.000000', NULL, 'user004', '111111', '用户004', 0, '成都市双流机场');
INSERT INTO `t_user` VALUES (8, '2018-09-23 00:00:00.000000', NULL, 'user005', '111111', '用户005', 0, '成都市双流机场');
INSERT INTO `t_user` VALUES (14, '2018-09-23 00:00:00.000000', '2018-09-23 00:00:00.000000', 'user006', '123456', '用户006', 0, '成都市双流机场');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
