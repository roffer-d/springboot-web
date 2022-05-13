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

 Date: 14/05/2022 00:49:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for basic_dict
-- ----------------------------
DROP TABLE IF EXISTS `basic_dict`;
CREATE TABLE `basic_dict` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '值',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '分类',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典';

-- ----------------------------
-- Records of basic_dict
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for basic_log
-- ----------------------------
DROP TABLE IF EXISTS `basic_log`;
CREATE TABLE `basic_log` (
  `id` varchar(255) NOT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '操作描述',
  `user_id` varchar(255) DEFAULT NULL COMMENT '操作用户id',
  `user_name` varchar(20) DEFAULT NULL COMMENT '操作用户',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志';

-- ----------------------------
-- Records of basic_log
-- ----------------------------
BEGIN;
INSERT INTO `basic_log` VALUES ('1524589410115436545', '更新菜单', '1523492347122360321', '维护管理员', '2022-05-12 11:16:56');
INSERT INTO `basic_log` VALUES ('1524589514373251074', '更新角色', '1523492347122360321', '维护管理员', '2022-05-12 11:17:20');
INSERT INTO `basic_log` VALUES ('1524592267136897026', '保存角色权限', '1523492347122360321', '维护管理员', '2022-05-12 11:28:17');
INSERT INTO `basic_log` VALUES ('1524652330325147650', '更新菜单', '1523492347122360321', '维护管理员', '2022-05-12 15:26:57');
INSERT INTO `basic_log` VALUES ('1524652354807300098', '更新菜单', '1523492347122360321', '维护管理员', '2022-05-12 15:27:03');
INSERT INTO `basic_log` VALUES ('1524652380245753858', '更新菜单', '1523492347122360321', '维护管理员', '2022-05-12 15:27:09');
INSERT INTO `basic_log` VALUES ('1524652402672697346', '更新菜单', '1523492347122360321', '维护管理员', '2022-05-12 15:27:14');
INSERT INTO `basic_log` VALUES ('1524652418883682306', '更新菜单', '1523492347122360321', '维护管理员', '2022-05-12 15:27:18');
INSERT INTO `basic_log` VALUES ('1524668319477657601', '保存角色权限', '1523492347122360321', '维护管理员', '2022-05-12 16:30:29');
INSERT INTO `basic_log` VALUES ('1524668441414463490', '保存角色权限', '1523492347122360321', '维护管理员', '2022-05-12 16:30:58');
INSERT INTO `basic_log` VALUES ('1524668895225573377', '保存角色权限', '1523492347122360321', '维护管理员', '2022-05-12 16:32:46');
INSERT INTO `basic_log` VALUES ('1524668979807907841', '保存角色权限', '1523492347122360321', '维护管理员', '2022-05-12 16:33:06');
INSERT INTO `basic_log` VALUES ('1524669058652434433', '更新角色', '1523492347122360321', '维护管理员', '2022-05-12 16:33:25');
INSERT INTO `basic_log` VALUES ('1524669367600672769', '保存角色权限', '1523492347122360321', '维护管理员', '2022-05-12 16:34:39');
INSERT INTO `basic_log` VALUES ('1524669535183974402', '保存角色权限', '1523492347122360321', '维护管理员', '2022-05-12 16:35:19');
INSERT INTO `basic_log` VALUES ('1524670307929960449', '保存角色权限', '1523492347122360321', '维护管理员', '2022-05-12 16:38:23');
INSERT INTO `basic_log` VALUES ('1524670433993961474', '保存角色权限', '1523492347122360321', '维护管理员', '2022-05-12 16:38:53');
INSERT INTO `basic_log` VALUES ('1524670562339663873', '保存角色权限', '1523492347122360321', '维护管理员', '2022-05-12 16:39:24');
INSERT INTO `basic_log` VALUES ('1524670812483760129', '添加字典', '1523492347122360321', '维护管理员', '2022-05-12 16:40:23');
INSERT INTO `basic_log` VALUES ('1524670890472648706', '删除字典', '1523492347122360321', '维护管理员', '2022-05-12 16:40:42');
INSERT INTO `basic_log` VALUES ('1524677534942359554', '更新用户', '1516296092063412225', '超级管理员', '2022-05-12 17:07:06');
INSERT INTO `basic_log` VALUES ('1524677788274126850', '更新用户', '1523492347122360321', '维护管理员', '2022-05-12 17:08:07');
INSERT INTO `basic_log` VALUES ('1524677854086950913', '更新用户', '1523492347122360321', '维护管理员', '2022-05-12 17:08:22');
INSERT INTO `basic_log` VALUES ('1524677901524529153', '更新用户', '1523492347122360321', '维护管理员', '2022-05-12 17:08:34');
INSERT INTO `basic_log` VALUES ('1525068870493171714', '添加菜单', '1516296092063412225', '超级管理员', '2022-05-13 19:02:08');
INSERT INTO `basic_log` VALUES ('1525069037116092417', '菜单权限配置', '1516296092063412225', '超级管理员', '2022-05-13 19:02:48');
INSERT INTO `basic_log` VALUES ('1525069296672206850', '保存角色权限', '1516296092063412225', '超级管理员', '2022-05-13 19:03:49');
INSERT INTO `basic_log` VALUES ('1525069321817059330', '保存角色权限', '1516296092063412225', '超级管理员', '2022-05-13 19:03:55');
INSERT INTO `basic_log` VALUES ('1525069974069108737', '菜单权限配置', '1516296092063412225', '超级管理员', '2022-05-13 19:06:31');
INSERT INTO `basic_log` VALUES ('1525070052703920130', '保存角色权限', '1516296092063412225', '超级管理员', '2022-05-13 19:06:50');
INSERT INTO `basic_log` VALUES ('1525070072790441985', '保存角色权限', '1516296092063412225', '超级管理员', '2022-05-13 19:06:54');
INSERT INTO `basic_log` VALUES ('1525073302471602177', '更新用户', '1516296092063412225', '超级管理员', '2022-05-13 19:19:44');
INSERT INTO `basic_log` VALUES ('1525098465380552706', '保存角色权限', '1516296092063412225', '超级管理员', '2022-05-13 20:59:44');
INSERT INTO `basic_log` VALUES ('1525117170059636738', '保存角色权限', '1516296092063412225', '超级管理员', '2022-05-13 22:14:03');
INSERT INTO `basic_log` VALUES ('1525117282244685825', '保存角色权限', '1516296092063412225', '超级管理员', '2022-05-13 22:14:30');
INSERT INTO `basic_log` VALUES ('1525117343179534337', '保存角色权限', '1516296092063412225', '超级管理员', '2022-05-13 22:14:45');
INSERT INTO `basic_log` VALUES ('1525117477300793346', '保存角色权限', '1516296092063412225', '超级管理员', '2022-05-13 22:15:17');
INSERT INTO `basic_log` VALUES ('1525117542048264193', '更新菜单', '1516296092063412225', '超级管理员', '2022-05-13 22:15:32');
INSERT INTO `basic_log` VALUES ('1525145404788715521', '添加用户', '1516296092063412225', '超级管理员', '2022-05-14 00:06:15');
INSERT INTO `basic_log` VALUES ('1525145508874563586', '添加角色', '1516296092063412225', '超级管理员', '2022-05-14 00:06:40');
INSERT INTO `basic_log` VALUES ('1525145633055322113', '删除角色', '1516296092063412225', '超级管理员', '2022-05-14 00:07:09');
INSERT INTO `basic_log` VALUES ('1525145691909795841', '添加角色', '1516296092063412225', '超级管理员', '2022-05-14 00:07:23');
INSERT INTO `basic_log` VALUES ('1525145849749843969', '删除角色', '1516296092063412225', '超级管理员', '2022-05-14 00:08:01');
INSERT INTO `basic_log` VALUES ('1525146026858524673', '添加角色', '1516296092063412225', '超级管理员', '2022-05-14 00:08:43');
INSERT INTO `basic_log` VALUES ('1525146065869746178', '保存角色权限', '1516296092063412225', '超级管理员', '2022-05-14 00:08:53');
INSERT INTO `basic_log` VALUES ('1525146122975195138', '保存用户角色', '1516296092063412225', '超级管理员', '2022-05-14 00:09:06');
COMMIT;

-- ----------------------------
-- Table structure for basic_menu
-- ----------------------------
DROP TABLE IF EXISTS `basic_menu`;
CREATE TABLE `basic_menu` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
  `pid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '直属父级id',
  `pname` varchar(255) DEFAULT NULL COMMENT '直属父级菜单名称',
  `pids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '所有父级id',
  `pnames` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '所有父级名称',
  `route` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '路由',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '权限标识',
  `auth` varchar(255) DEFAULT NULL COMMENT '拥有权限',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `sort` int DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单';

-- ----------------------------
-- Records of basic_menu
-- ----------------------------
BEGIN;
INSERT INTO `basic_menu` VALUES ('1518483664025964545', '系统管理', '0', NULL, '', '', '/', NULL, NULL, '', 1, '系统管理', '2022-04-25 14:54:52', '2022-04-26 13:48:39');
INSERT INTO `basic_menu` VALUES ('1518497082179170305', '用户管理', '1518483664025964545', '系统管理', '1518483664025964545', '系统管理', '/sys/basicUser', 'user', '[{\"code\":\"query\",\"name\":\"查询\"},{\"code\":\"add\",\"name\":\"添加\"},{\"code\":\"edit\",\"name\":\"编辑\"},{\"code\":\"delete\",\"name\":\"删除\"},{\"code\":\"enable\",\"name\":\"启用\"},{\"code\":\"disable\",\"name\":\"停用\"},{\"code\":\"role_config\",\"name\":\"角色配置\"}]', 'icon-user', 2, '用户管理', '2022-04-25 15:48:11', '2022-05-12 15:27:18');
INSERT INTO `basic_menu` VALUES ('1518500816003063809', '角色管理', '1518483664025964545', '系统管理', '1518483664025964545', '系统管理', '/sys/basicRole', 'role', '[{\"code\":\"query\",\"name\":\"查询\"},{\"code\":\"add\",\"name\":\"添加\"},{\"code\":\"edit\",\"name\":\"编辑\"},{\"code\":\"delete\",\"name\":\"删除\"},{\"code\":\"menu_auth\",\"name\":\"菜单权限\"}]', 'icon-role', 3, '角色管理', '2022-04-25 16:03:02', '2022-05-12 15:27:14');
INSERT INTO `basic_menu` VALUES ('1518501003178074113', '菜单管理', '1518483664025964545', '系统管理', '1518483664025964545', '系统管理', '/sys/basicMenu', 'menu', '[{\"code\":\"query\",\"name\":\"查询\"},{\"code\":\"add\",\"name\":\"添加\"},{\"code\":\"edit\",\"name\":\"编辑\"},{\"code\":\"delete\",\"name\":\"删除\"},{\"code\":\"auth_config\",\"name\":\"权限配置\"}]', 'icon-menu', 4, '菜单管理', '2022-04-25 16:03:46', '2022-05-12 15:27:09');
INSERT INTO `basic_menu` VALUES ('1519595770762874881', '日志查询', '1518483664025964545', '系统管理', '1518483664025964545', '系统管理', '/sys/basicLog', 'log', '[{\"code\":\"query\",\"name\":\"查询\"}]', 'icon-rizhi', 5, '日志查询', '2022-04-28 16:33:59', '2022-05-12 15:27:03');
INSERT INTO `basic_menu` VALUES ('1522388749701369857', '字典管理', '1518483664025964545', '系统管理', '1518483664025964545', '系统管理', '/sys/basicDict', 'dict', '[{\"code\":\"query\",\"name\":\"查询\"},{\"code\":\"add\",\"name\":\"添加\"},{\"code\":\"edit\",\"name\":\"编辑\"},{\"code\":\"delete\",\"name\":\"删除\"}]', 'icon-rizhi', 6, '字典管理', '2022-05-06 09:32:17', '2022-05-12 15:26:57');
INSERT INTO `basic_menu` VALUES ('1525068870602223617', '在线用户', '1518483664025964545', '系统管理', '1518483664025964545', '系统管理', '/sys/onlineUser', 'onlineUser', '[{\"code\":\"query\",\"name\":\"查询\"},{\"code\":\"offLine\",\"name\":\"下线\"}]', 'icon-role', 7, '在线用户列表', '2022-05-13 19:02:08', '2022-05-13 22:15:32');
COMMIT;

-- ----------------------------
-- Table structure for basic_role
-- ----------------------------
DROP TABLE IF EXISTS `basic_role`;
CREATE TABLE `basic_role` (
  `id` varchar(255) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色';

-- ----------------------------
-- Records of basic_role
-- ----------------------------
BEGIN;
INSERT INTO `basic_role` VALUES ('1518424205647986689', '超级管理员', '2022-04-25 10:58:36', '2022-05-06 16:12:35');
INSERT INTO `basic_role` VALUES ('1523493741627744258', '维护管理员', '2022-05-09 10:43:08', '2022-05-12 16:33:25');
INSERT INTO `basic_role` VALUES ('1525146026875301890', '日志查看员', '2022-05-14 00:08:43', NULL);
COMMIT;

-- ----------------------------
-- Table structure for basic_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `basic_role_menu`;
CREATE TABLE `basic_role_menu` (
  `id` varchar(255) NOT NULL,
  `role_id` varchar(255) DEFAULT NULL COMMENT '角色id',
  `menu_id` varchar(255) DEFAULT NULL COMMENT '菜单id',
  `authority_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '权限类型(query、查询，add、新增，edit、编辑，delete、删除，import、导入，export、导出，examine、审核)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色菜单';

-- ----------------------------
-- Records of basic_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `basic_role_menu` VALUES ('1525070072815607810', '1518424205647986689', '1522388749701369857', 'query,add,edit,delete');
INSERT INTO `basic_role_menu` VALUES ('1525070072819802114', '1518424205647986689', '1519595770762874881', 'query');
INSERT INTO `basic_role_menu` VALUES ('1525070072828190721', '1518424205647986689', '1518501003178074113', 'query,add,edit,delete,auth_config');
INSERT INTO `basic_role_menu` VALUES ('1525070072832385026', '1518424205647986689', '1518500816003063809', 'query,add,edit,delete,menu_auth');
INSERT INTO `basic_role_menu` VALUES ('1525070072840773634', '1518424205647986689', '1518497082179170305', 'query,add,edit,delete,enable,disable,role_config');
INSERT INTO `basic_role_menu` VALUES ('1525070072844967938', '1518424205647986689', '1525068870602223617', 'query,offLine');
INSERT INTO `basic_role_menu` VALUES ('1525117477321764866', '1523493741627744258', '1522388749701369857', 'query,add,edit,delete');
INSERT INTO `basic_role_menu` VALUES ('1525117477325959170', '1523493741627744258', '1518501003178074113', 'query,add,edit,delete,auth_config');
INSERT INTO `basic_role_menu` VALUES ('1525117477334347777', '1523493741627744258', '1518500816003063809', 'query,add,edit,delete,menu_auth');
INSERT INTO `basic_role_menu` VALUES ('1525117477342736386', '1523493741627744258', '1518497082179170305', 'query,add,edit,delete,enable,disable,role_config');
INSERT INTO `basic_role_menu` VALUES ('1525146065903300610', '1525146026875301890', '1519595770762874881', 'query');
COMMIT;

-- ----------------------------
-- Table structure for basic_user
-- ----------------------------
DROP TABLE IF EXISTS `basic_user`;
CREATE TABLE `basic_user` (
  `id` varchar(255) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '真实姓名',
  `account` varchar(255) NOT NULL COMMENT '登录账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '用户状态（0禁用，1启用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户';

-- ----------------------------
-- Records of basic_user
-- ----------------------------
BEGIN;
INSERT INTO `basic_user` VALUES ('1516296092063412225', '超级管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'admin@qq.com', '2022-05-14 00:26:22', '2022-04-19 14:02:14', '2022-05-14 00:26:22', '1');
INSERT INTO `basic_user` VALUES ('1523492347122360321', '维护管理员', 'wh', 'e10adc3949ba59abbe56e057f20f883e', 'wh@qq.com', '2022-05-14 00:20:10', '2022-05-09 10:37:35', '2022-05-14 00:20:10', '1');
INSERT INTO `basic_user` VALUES ('1525145404818075649', '日志查看员', 'rizhi', 'e10adc3949ba59abbe56e057f20f883e', 'rizhi@qq.com', '2022-05-14 00:45:28', '2022-05-14 00:06:15', '2022-05-14 00:45:28', '1');
COMMIT;

-- ----------------------------
-- Table structure for basic_user_role
-- ----------------------------
DROP TABLE IF EXISTS `basic_user_role`;
CREATE TABLE `basic_user_role` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL COMMENT '用户id',
  `role_id` varchar(255) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户角色';

-- ----------------------------
-- Records of basic_user_role
-- ----------------------------
BEGIN;
INSERT INTO `basic_user_role` VALUES ('1522026681097838594', '1516296092063412225', '1518424205647986689');
INSERT INTO `basic_user_role` VALUES ('1523493776906035202', '1523492347122360321', '1523493741627744258');
INSERT INTO `basic_user_role` VALUES ('1525146123004555266', '1525145404818075649', '1525146026875301890');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
