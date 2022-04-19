/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : basic

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 19/04/2022 16:26:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for basic_menu
-- ----------------------------
DROP TABLE IF EXISTS `basic_menu`;
CREATE TABLE `basic_menu` (
  `id` bigint NOT NULL,
  `parent_id` bigint DEFAULT NULL COMMENT '父级菜单id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `router` varchar(255) DEFAULT NULL COMMENT '路由',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `sort` int DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '菜单说明',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单';

-- ----------------------------
-- Records of basic_menu
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for basic_role
-- ----------------------------
DROP TABLE IF EXISTS `basic_role`;
CREATE TABLE `basic_role` (
  `id` bigint NOT NULL,
  `role` char(20) NOT NULL,
  `status` tinyint(1) NOT NULL COMMENT '是否启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色';

-- ----------------------------
-- Records of basic_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for basic_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `basic_role_menu`;
CREATE TABLE `basic_role_menu` (
  `id` bigint NOT NULL,
  `role_id` bigint DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint DEFAULT NULL COMMENT '菜单id',
  `authority_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '权限类型(1、新增，2、编辑，3、删除，4、查询，5、导出，6、导入)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色菜单';

-- ----------------------------
-- Records of basic_role_menu
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for basic_user
-- ----------------------------
DROP TABLE IF EXISTS `basic_user`;
CREATE TABLE `basic_user` (
  `id` varchar(255) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `account` varchar(255) NOT NULL COMMENT '登录账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '用户状态（0禁用，1启用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户';

-- ----------------------------
-- Records of basic_user
-- ----------------------------
BEGIN;
INSERT INTO `basic_user` VALUES ('1515945574279942145', '杜龙飞', 'roffer', '79b35c1882f7aba0899683330c7f0a99', '497664492@qq.com', '2022-04-18 14:49:24', '2022-04-19 14:45:29', '1');
INSERT INTO `basic_user` VALUES ('1516296092063412225', '管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'a@qq.com', '2022-04-19 14:02:14', '2022-04-19 15:57:52', '1');
COMMIT;

-- ----------------------------
-- Table structure for basic_user_role
-- ----------------------------
DROP TABLE IF EXISTS `basic_user_role`;
CREATE TABLE `basic_user_role` (
  `id` bigint NOT NULL,
  `user_id` int NOT NULL COMMENT '用户id',
  `role_id` int NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户角色';

-- ----------------------------
-- Records of basic_user_role
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
