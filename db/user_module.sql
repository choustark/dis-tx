/*
 Navicat Premium Data Transfer

 Source Server         : centos115
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 192.168.0.115:3306
 Source Schema         : user_module

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 27/08/2023 18:21:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tx_company_org
-- ----------------------------
DROP TABLE IF EXISTS `tx_company_org`;
CREATE TABLE `tx_company_org`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '组织名称',
  `org_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '组织编码',
  `sort_number` int(0) NULL DEFAULT NULL COMMENT '序号',
  `parent_id` bigint(0) NOT NULL COMMENT '父节点id',
  `full_path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '全路径（id）',
  `cn_full_path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '全路径（name）',
  `is_del` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除标识（0-否,1-是）',
  `company_id` bigint(0) NULL DEFAULT NULL COMMENT '公司id',
  `create_by` bigint(0) NOT NULL COMMENT '创建人',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` bigint(0) NOT NULL COMMENT '更新人',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL COMMENT 'id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `deposit` int(0) NULL DEFAULT NULL COMMENT '余额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
