/*
Navicat MySQL Data Transfer

Source Server         : localhost本地
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : david

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2022-06-02 09:34:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张三', '123');
INSERT INTO `user` VALUES ('2', '李四', '456');
INSERT INTO `user` VALUES ('3', '王五', '1654062351934');
