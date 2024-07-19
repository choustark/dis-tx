/*
 Navicat Premium Data Transfer

 Source Server         : centos115
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 192.168.0.115:3306
 Source Schema         : order_module

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 27/08/2023 18:21:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for commodity_order
-- ----------------------------
DROP TABLE IF EXISTS `commodity_order`;
CREATE TABLE `commodity_order`  (
  `id` bigint(0) NOT NULL COMMENT 'id',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '客户id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `amount` int(0) NULL DEFAULT NULL COMMENT '金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mq_msg_log
-- ----------------------------
DROP TABLE IF EXISTS `mq_msg_log`;
CREATE TABLE `mq_msg_log`  (
  `id` bigint(0) NOT NULL COMMENT 'id',
  `object_id` bigint(0) NULL DEFAULT NULL COMMENT '存储的对象id',
  `exchange_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '交换机名称',
  `routing_key_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由键名称',
  `state` tinyint(0) NULL DEFAULT NULL COMMENT '状态消息(0-未确认,1-已确认,-1-发送消息异常)',
  `body` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '消息主题 json 格式',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `next_time` datetime(0) NULL DEFAULT NULL COMMENT '下一次执行时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '消息备份表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
