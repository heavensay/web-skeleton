/*
 Navicat Premium Data Transfer

 Source Server         : mysql-localhost-3306
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : diy

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 16/05/2019 10:14:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for perms_resource
-- ----------------------------
DROP TABLE IF EXISTS `perms_resource`;
CREATE TABLE `perms_resource`  (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `pid` bigint(10) NULL DEFAULT NULL COMMENT '父id',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源代码',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `uri` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源唯一标识',
  `icon_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源图标',
  `type` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源类型(菜单:menu,操作:button,api:api)',
  `sortby` int(8) NULL DEFAULT NULL COMMENT '当前节点等级下排序优先级',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of perms_resource
-- ----------------------------
INSERT INTO `perms_resource` VALUES (1, NULL, NULL, '用户管理', 'user-manager', NULL, 'menu', 1, '2019-02-18 15:29:42', '2019-02-18 15:29:44');
INSERT INTO `perms_resource` VALUES (2, 1, NULL, '用户列表', 'userList', NULL, 'api', 1, '2019-02-18 15:29:42', '2019-02-18 15:29:44');
INSERT INTO `perms_resource` VALUES (3, 2, NULL, 'hello-test', 'getHappy', NULL, 'api', 1, '2019-02-18 16:29:59', '2019-02-18 16:30:02');

-- ----------------------------
-- Table structure for perms_role
-- ----------------------------
DROP TABLE IF EXISTS `perms_role`;
CREATE TABLE `perms_role`  (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `pid` bigint(10) NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of perms_role
-- ----------------------------
INSERT INTO `perms_role` VALUES (1, NULL, '测试角色', '测试使用', '2019-02-18 15:33:28', '2019-02-18 15:33:30');

-- ----------------------------
-- Table structure for perms_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `perms_role_resource`;
CREATE TABLE `perms_role_resource`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `role_id` int(12) NULL DEFAULT NULL COMMENT '角色主键',
  `resource_id` int(12) NULL DEFAULT NULL COMMENT '资源主键',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of perms_role_resource
-- ----------------------------
INSERT INTO `perms_role_resource` VALUES (1, 1, 1, '2019-02-18 15:33:43', '2019-02-18 15:33:45');
INSERT INTO `perms_role_resource` VALUES (2, 1, 2, '2019-02-18 16:53:57', '2019-02-18 16:54:00');
INSERT INTO `perms_role_resource` VALUES (3, 1, 3, '2019-02-18 16:54:04', '2019-02-18 16:54:01');

-- ----------------------------
-- Table structure for perms_user_role
-- ----------------------------
DROP TABLE IF EXISTS `perms_user_role`;
CREATE TABLE `perms_user_role`  (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(10) NULL DEFAULT NULL,
  `perms_role_id` bigint(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户-角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of perms_user_role
-- ----------------------------
INSERT INTO `perms_user_role` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐值',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'test1', '30c1e242e887ba5e5026e560c4d0941831b5a1db6d389733f6e410e6804cefa8', 'abcdefghigk', '2019-05-15 03:56:16', '2019-05-15 03:56:16');

SET FOREIGN_KEY_CHECKS = 1;
