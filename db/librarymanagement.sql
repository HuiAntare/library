/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : librarymanagement

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 05/01/2023 00:55:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系方式',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `createtime` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NULL DEFAULT NULL,
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_key`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (7, 'zhang', '18798540990', 'zhang@qq.com', 'a8e2501db8a47631019cbac34bc4a784', '2023-01-04 00:50:21', NULL, 1);
INSERT INTO `admin` VALUES (9, 'admin1', '18798540990', 'admin1@qq.com', 'a8e2501db8a47631019cbac34bc4a784', '2023-01-04 00:51:48', NULL, 1);
INSERT INTO `admin` VALUES (11, 'admin', '18798540990', 'admin@qq.com', 'a8e2501db8a47631019cbac34bc4a784', '2023-01-04 01:02:22', NULL, 1);
INSERT INTO `admin` VALUES (12, 'admin123', '18798540990', 'admin123@qq.com', 'a8e2501db8a47631019cbac34bc4a784', '2023-01-04 01:02:37', NULL, 1);
INSERT INTO `admin` VALUES (13, 'root', '18798540990', 'root@qq.com', 'a8e2501db8a47631019cbac34bc4a784', '2023-01-04 14:36:02', NULL, 1);
INSERT INTO `admin` VALUES (14, 'root1', '18798540990', 'root1@qq.com', 'a8e2501db8a47631019cbac34bc4a784', '2023-01-04 14:37:20', '2023-01-04 23:53:22', 1);

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `publish_date` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '出版日期',
  `author` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '作者',
  `publisher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '出版社',
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类',
  `book_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标准码',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面',
  `createtime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `score` int NULL DEFAULT NULL COMMENT '积分',
  `nums` int NULL DEFAULT 0 COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `book_no_index`(`book_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '西游记幼儿美绘本（全10册）', '中国四大名著之一，西游记3-8岁版，彩绘注音，有声伴读。49款夸张角色，610张精美插图， 29个西游问答，10大智力挑战，以一种超有趣的方式亲近名著经典。', '2022-10-17', '吕进1', '少年儿童出版社', '历史文化 > 唐朝文化', 'A-123', 'http://img3m7.ddimg.cn/10/30/25276987-1_w_44.jpg', '2022-10-17 21:35:14', '2022-10-19 00:00:00', 10, 1);
INSERT INTO `book` VALUES (6, '十万个为什么', '2222', '2022-10-06', '222', '2', '历史文化 > 唐朝文化', 'A-132', 'http://img3m7.ddimg.cn/10/30/25276987-1_w_44.jpg', '2022-10-17 22:30:01', '2022-10-25 00:00:00', 20, 100);
INSERT INTO `book` VALUES (7, '百科全书', '3333', '2022-10-12', '33', '333', '科技 > 高新科技 > 微分子科技', 'A-131', 'http://img3m7.ddimg.cn/10/30/25276987-1_w_44.jpg', '2022-10-17 22:30:59', '2022-10-25 00:00:00', 10, 98);
INSERT INTO `book` VALUES (8, '童话故事大全', '345', '2022-10-05', '55', '5', '科技 > 生物科技', 'F-543', 'http://localhost:9090/api/book/file/download/1666707290390?&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwiZXhwIjoxNjY4MDAzMjkwfQ.FtPUrLjNRTbfzauHObyGwy0LWyrqD4VSLylW9Ef7FW8&play=1', '2022-10-17 22:32:16', '2022-10-25 00:00:00', 30, 92);
INSERT INTO `book` VALUES (10, '钢铁是怎样炼成的', '2214124', NULL, '77', '123', '历史文化 > 现代文化', 'F-123', 'http://localhost:9090/api/book/file/download/1666706911257?&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwiZXhwIjoxNjY4MDAyOTExfQ.DLB80S9v-eWBECESJiigVZooV7ks58D43KUQ2ByJQrI&play=1', '2022-10-25 22:09:08', NULL, 20, 100);
INSERT INTO `book` VALUES (11, '水浒传', '中国四大名著之一', '2023-01-02', '吕蒙', '少年儿童出版社', '历史文化 > 唐朝文化', 'G-123', 'http://localhost:9090/api/book/file/download/1666706911257?&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwiZXhwIjoxNjY4MDAyOTExfQ.DLB80S9v-eWBECESJiigVZooV7ks58D43KUQ2ByJQrI&play=1', '2023-01-05 00:19:16', '2023-01-05 00:00:00', 10, 99);

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图书名称',
  `book_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图书标准码',
  `user_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户学号',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名称',
  `user_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户联系方式',
  `createtime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `score` int NULL DEFAULT NULL COMMENT '借书积分',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '已借出' COMMENT '借书状态',
  `days` int NULL DEFAULT 1 COMMENT '借书天数',
  `return_date` datetime NULL DEFAULT NULL COMMENT '归还日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of borrow
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `pid` int NULL DEFAULT NULL COMMENT '父级id',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (3, '历史文化', '历史文化', NULL, '2022-10-12 20:42:53', NULL);
INSERT INTO `category` VALUES (4, '唐朝文化', '唐朝文化', 3, '2022-10-12 20:43:21', NULL);
INSERT INTO `category` VALUES (5, '宋朝文化', NULL, 3, '2022-10-12 20:46:29', NULL);
INSERT INTO `category` VALUES (9, '现代文化', '现代文化', 3, '2022-10-12 21:00:41', NULL);
INSERT INTO `category` VALUES (10, '科技', '科技', NULL, '2022-10-12 21:50:08', NULL);
INSERT INTO `category` VALUES (12, '生物科技', '生物科技', 10, '2022-10-12 21:50:22', NULL);
INSERT INTO `category` VALUES (13, '量子物理', '量子物理', 10, '2022-10-17 22:11:44', '2023-01-05 00:00:00');
INSERT INTO `category` VALUES (14, '微分子科技', '微分子科技', 10, '2023-01-05 00:15:19', NULL);

-- ----------------------------
-- Table structure for retur
-- ----------------------------
DROP TABLE IF EXISTS `retur`;
CREATE TABLE `retur`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图书名称',
  `book_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图书标准码',
  `user_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户会员码',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名称',
  `user_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户联系方式',
  `createtime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `score` int NULL DEFAULT NULL COMMENT '借书积分',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '已借出' COMMENT '借书状态',
  `days` int NULL DEFAULT 1 COMMENT '借书天数',
  `return_date` datetime NULL DEFAULT NULL COMMENT '归还日期',
  `real_date` datetime NULL DEFAULT NULL COMMENT '实际归还日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of retur
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学号',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `sex` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系方式',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `createtime` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NULL DEFAULT NULL,
  `account` int NULL DEFAULT 0 COMMENT '账户余额',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '禁用状态 1不禁用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_index`(`uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (19, '苗洋洋', '2022201042Y129', 18, '男', '13988990099', '合肥', '2023-01-03 22:10:36', '2022-10-25 19:32:13', 140, 1);
INSERT INTO `user` VALUES (20, '李芸', '2022201042Y128', 24, '女', '13899900998', '合肥', '2023-01-03 22:16:03', NULL, 20, 1);
INSERT INTO `user` VALUES (21, '落布', '2022201042Y127', 20, '男', '13456789765', '南京', '2023-01-03 22:17:06', '2022-10-25 19:31:20', 140, 1);
INSERT INTO `user` VALUES (22, '卡森', '2022201042Y126', 22, '男', '13988990066', '上海', '2023-01-03 22:34:48', '2023-01-04 23:53:14', 100, 1);
INSERT INTO `user` VALUES (23, '杰克', '2022201042Y125', 33, '男', '13977889988', '重庆', '2023-01-03 22:35:35', '2023-01-05 00:10:56', -40, 0);
INSERT INTO `user` VALUES (24, '王斌', '2023201042Y110', 10, '男', '18798540990', '北京', '2023-01-04 23:58:15', '2023-01-05 00:10:54', 82, 1);

SET FOREIGN_KEY_CHECKS = 1;
