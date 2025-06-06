/*
 Navicat Premium Data Transfer

 Source Server         : root@localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : second-hand-trading-system

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 27/11/2024 12:29:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chat_message
-- ----------------------------
DROP TABLE IF EXISTS `chat_message`;
CREATE TABLE `chat_message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户Id',
  `chat_room_id` int(11) NULL DEFAULT NULL COMMENT '聊天房间Id',
  `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容(多行文本,5)',
  `status` int(11) NOT NULL COMMENT '状态(单选,1:已读,2:未读)',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 336 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '聊天消息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of chat_message
-- ----------------------------
INSERT INTO `chat_message` VALUES (177, 1, 8, '1', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (178, 1, 8, '2', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (179, 1, 8, '3', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (180, 1, 8, '4', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (181, 1, 8, '5', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (182, 1, 8, '6', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (183, 1, 8, '7', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (184, 1, 8, '8', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (185, 1, 8, '9', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (186, 1, 8, '10', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (187, 1, 8, '11', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (188, 1, 8, '12', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (189, 1, 8, '13', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (190, 1, 8, '14', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (191, 1, 8, '15', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (192, 1, 8, '16', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (193, 1, 8, '17', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (194, 1, 8, '18', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (195, 1, 8, '19', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (196, 1, 8, '20', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (197, 1, 8, '21', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (198, 1, 8, '22', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (199, 1, 8, '23', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (200, 1, 8, '24', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (201, 1, 7, '1', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (202, 1, 7, '2', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (203, 1, 7, '3', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (204, 1, 7, '4', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (205, 1, 7, '5', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (206, 1, 7, '6', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (207, 1, 7, '7', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (208, 1, 7, '8', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (209, 1, 7, '9', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (210, 1, 7, '10', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (211, 1, 7, '11', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (212, 1, 7, '12', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (213, 2, 8, '你好', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (214, 2, 8, '纳米是晒v吧', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (215, 2, 8, '是的', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (216, 2, 8, '烧饼', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (217, 2, 8, '哈哈哈', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (218, 2, 8, '你测i是', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (219, 2, 7, '666呀', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (220, 2, 7, '发展H恶魔多消息', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (221, 1, 7, '你也一样', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (222, 1, 7, '试试', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (223, 2, 7, '牛逼', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (224, 2, 7, '666', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (225, 2, 8, '311', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (226, 2, 8, '3131', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (227, 2, 8, '258275', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (228, 2, 8, '666', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (229, 2, 8, '3132132', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (230, 2, 8, '321321321', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (231, 2, 8, '1122222', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (232, 2, 8, '321123', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (233, 2, 8, '31232131231', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (234, 2, 8, '312312312', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (235, 2, 8, '312321', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (236, 2, 8, '方法士大夫士大夫但是', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (237, 2, 8, '法大师傅士大夫', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (238, 2, 8, '秀那个', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (239, 2, 8, '你与偶中', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (240, 2, 8, '牛逼呀', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (241, 2, 8, 'clas', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (242, 2, 8, 'syanbni heng', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (243, 2, 8, '你流弊', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (244, 2, 8, '傻逼', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (245, 2, 8, '王i有', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (246, 1, 7, '', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (247, 1, 7, '🙄', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (248, 1, 7, '可以', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (249, 1, 8, '非常的狂热i有', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (250, 1, 8, '抗日分分子', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (251, 1, 8, '', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (252, 1, 8, '', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (253, 1, 8, '', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (254, 1, 8, '', 0, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (255, 2, 11, '111', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (256, 2, 11, '你好呀', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (257, 2, 11, '鹦鹉个', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (258, 2, 11, '个满', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (259, 2, 11, '你好元', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (260, 2, 11, '你好', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (261, 2, 11, '非常号', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (262, 2, 7, '好呀好呀', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (263, 2, 7, '测试通过', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (264, 2, 7, '就可以了', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (265, 2, 7, '好滴', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (266, 2, 7, '非常好', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (267, 2, 7, '你非常不错', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (268, 2, 7, '聊天记录', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (269, 2, 7, '非常哈o你好', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (270, 2, 7, '打分欸', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (271, 1, 7, '星', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (272, 1, 7, '你有种', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (273, 2, 12, '你好呀', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (274, 2, 12, '非常好', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (275, 2, 12, '哈哈哈', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (276, 2, 12, '我i秀昂买', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (277, 1, 7, '新', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (278, 2, 7, '😇', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (279, 2, 7, '😝', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (280, 2, 7, '可以', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (281, 2, 7, '可以', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (282, 2, 7, '你好', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (283, 2, 7, '怎么了', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (284, 2, 7, '你不对劲', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (285, 2, 7, '🤗', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (286, 2, 12, '是会议', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (287, 2, 12, '关于爱情', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (288, 2, 12, '你屈辱秃笔', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (289, 2, 12, '你那么那挺', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (290, 2, 12, 'dddd', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (291, 2, 12, 'buiengn huxi', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (292, 2, 12, 'tabudong nid', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (293, 2, 12, 'de xing', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (294, 2, 12, '他不懂你的心', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (295, 2, 12, '呜呜呜、', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (296, 2, 12, '你好元', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (297, 2, 12, '31234214', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (298, 2, 12, '412412', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (299, 2, 12, '412421', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (300, 2, 12, '412412441', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (301, 1, 7, '好的', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (302, 2, 7, 'fdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdedsadasdasdasdsADAWSD', 2, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (303, 1, 14, '你好', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (304, 1, 14, '我想要', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (305, 2, 15, '我想要', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (306, 2, 15, '可以吗', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (307, 2, 15, '可以的', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (308, 2, 15, '可以的', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (309, 1, 15, '😙', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (310, 1, 15, '😏', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (311, 1, 15, '333', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (312, 1, 15, '333', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (313, 1, 15, '333', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (314, 1, 15, '336', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (315, 1, 15, '23212152', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (316, 2, 15, '3666', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (317, 2, 15, '666', 1, '2024-11-07 14:20:01');
INSERT INTO `chat_message` VALUES (318, 12, 17, '你好', 1, '2024-11-17 21:24:52');
INSERT INTO `chat_message` VALUES (319, 12, 14, '123', 2, '2024-11-17 21:27:52');
INSERT INTO `chat_message` VALUES (320, 12, 14, '123456', 2, '2024-11-17 21:28:06');
INSERT INTO `chat_message` VALUES (321, 12, 16, '123', 1, '2024-11-17 21:28:17');
INSERT INTO `chat_message` VALUES (322, 12, 15, '123', 2, '2024-11-17 21:28:26');
INSERT INTO `chat_message` VALUES (323, 12, 14, '😀', 2, '2024-11-17 21:29:57');
INSERT INTO `chat_message` VALUES (324, 12, 16, '😀', 1, '2024-11-17 21:30:06');
INSERT INTO `chat_message` VALUES (325, 12, 16, '😀😀😀😀😀😀😀😀', 1, '2024-11-17 21:30:10');
INSERT INTO `chat_message` VALUES (326, 12, 16, '4324234234234234324', 1, '2024-11-17 21:30:13');
INSERT INTO `chat_message` VALUES (327, 12, 16, '42343244234', 1, '2024-11-17 21:30:22');
INSERT INTO `chat_message` VALUES (328, 2, 17, '你要想吗', 1, '2024-11-26 17:56:29');
INSERT INTO `chat_message` VALUES (329, 12, 17, '是的，东西怎么样', 2, '2024-11-26 17:56:45');
INSERT INTO `chat_message` VALUES (330, 12, 17, '😃😃😃', 2, '2024-11-26 17:56:50');
INSERT INTO `chat_message` VALUES (331, 2, 17, '非常好的，赶紧拍', 1, '2024-11-26 17:57:03');
INSERT INTO `chat_message` VALUES (332, 12, 18, '我需要，还能便宜点吗', 1, '2024-11-26 18:36:22');
INSERT INTO `chat_message` VALUES (333, 2, 18, '当然可以', 1, '2024-11-26 18:36:59');
INSERT INTO `chat_message` VALUES (334, 12, 18, '嗯嗯', 2, '2024-11-26 18:37:35');
INSERT INTO `chat_message` VALUES (335, 12, 18, '😀😀😀 非常感谢', 2, '2024-11-26 18:37:43');

-- ----------------------------
-- Table structure for chat_room
-- ----------------------------
DROP TABLE IF EXISTS `chat_room`;
CREATE TABLE `chat_room`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `product_id` int(11) NULL DEFAULT NULL COMMENT '商品',
  `user1_id` int(11) NULL DEFAULT NULL COMMENT '用户1Id',
  `user2_id` int(11) NULL DEFAULT NULL COMMENT '用户2Id',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '聊天房间' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of chat_room
-- ----------------------------
INSERT INTO `chat_room` VALUES (14, NULL, 23, 1, 11, '2024-11-17 21:29:57', '2024-11-07 14:20:01');
INSERT INTO `chat_room` VALUES (15, NULL, 23, 2, 11, '2024-11-17 21:28:26', '2024-11-07 14:20:01');
INSERT INTO `chat_room` VALUES (16, NULL, 19, 12, 2, '2024-11-17 21:30:22', '2024-11-17 21:21:26');
INSERT INTO `chat_room` VALUES (17, NULL, 13, 12, 2, '2024-11-26 17:57:03', '2024-11-17 21:24:22');
INSERT INTO `chat_room` VALUES (18, NULL, 25, 12, 2, '2024-11-26 18:37:43', '2024-11-26 18:36:04');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '说明(多行文本,10)',
  `img` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '图片(图片)',
  `video` varchar(1010) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '视频(视频)',
  `product_classify_id` int(11) NULL DEFAULT NULL COMMENT '分类(下拉框,10:数码,20:电器)',
  `product_fineness_id` int(11) NULL DEFAULT NULL COMMENT '成色(下拉框,10:几乎全新,20:轻微使用痕迹)',
  `original_price` double NULL DEFAULT NULL COMMENT '原价',
  `price` double NULL DEFAULT NULL COMMENT '价格',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态(单选,1:上架,2:下架,3:已卖出)',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '发布人',
  `page_view` int(11) NULL DEFAULT NULL COMMENT '浏览量',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `want` int(11) NULL DEFAULT NULL COMMENT '想要',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (13, '麦岭电动自行车', '麦岭918，跑外卖首选，必须过户，现在上60v，电池筒参考深超远60v电池大小，暂时4000多公里，极速上85，有意者可以小刀，不带电池，不带电池', '[{\"name\":\"微信截图_20240707142156.png\",\"url\":\"http://localhost:1000/file/img/2024/07/07/53729ac5-597e-430b-92af-f3c290a889a7.png\"},{\"name\":\"微信截图_20240707142215.png\",\"url\":\"http://localhost:1000/file/img/2024/07/07/61e29586-3ec6-4ba2-86b1-1f326a84fbb7.png\"},{\"name\":\"微信截图_20240707142225.png\",\"url\":\"http://localhost:1000/file/img/2024/07/07/7935775d-885d-48e5-ab4f-7878efcc36d4.png\"},{\"name\":\"微信截图_20240707142235.png\",\"url\":\"http://localhost:1000/file/img/2024/07/07/e0be0364-f180-40f4-8d18-ee662ceac4ae.png\"},{\"name\":\"微信截图_20240707142244.png\",\"url\":\"http://localhost:1000/file/img/2024/07/07/61442a1f-26ec-47f9-959c-02a21eccb934.png\"},{\"name\":\"微信截图_20240707142253.png\",\"url\":\"http://localhost:1000/file/img/2024/07/07/57a65c9e-309a-456c-90aa-eb9cd0d3ab98.png\"},{\"name\":\"微信截图_20240707142307.png\",\"url\":\"http://localhost:1000/file/img/2024/07/07/b3bd1709-f3a1-4140-b276-943e0be58ff7.png\"},{\"name\":\"微信截图_20240707142315.png\",\"url\":\"http://localhost:1000/file/img/2024/07/07/71325830-3729-44c9-a094-b1c9bf09f29c.png\"}]', NULL, 8, 9, 2000, 1400, 3, 2, 32, '2024-11-07 14:20:01', 2);
INSERT INTO `product` VALUES (14, '升降电脑椅', '用了没多久，一切正常！计划搬家，带不走！自提一口', '[{\"name\":\"O1CN01DZnz771lJ4d1dXgUb_!!0-fleamarket.jpg_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/1f549adf-759c-4271-ad7d-b7db3c9fd916.webp\"},{\"name\":\"O1CN01fLFYO01lJ4d3Ba6lV_!!0-fleamarket.jpg_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/84cb8144-8a37-42f7-a7e2-1eceff7666cd.webp\"},{\"name\":\"O1CN01DiRyCL1lJ4cxmNHjt_!!0-fleamarket.jpg_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/5a9242ab-f1f6-4031-82c9-d01acbff936b.webp\"}]', NULL, 11, 9, 75, 25, 1, 2, 3, '2024-11-07 14:20:01', 0);
INSERT INTO `product` VALUES (15, '跑步机', '电动跑步机，搬家带不走！甩卖！', '[{\"name\":\"O1CN01nHQamk1lJ4e1RbW9l_!!0-fleamarket.jpg_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/ef55f3f3-0110-4ab3-821a-e65c2d20bf3f.webp\"},{\"name\":\"O1CN01nM8pXI1lJ4e1RZIxn_!!0-fleamarket.jpg_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/412c7b04-ab99-48c9-9dc9-38bb0ff2d4c4.webp\"},{\"name\":\"O1CN01O1RX3X1lJ4dxA6Ys4_!!0-fleamarket.jpg_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/6836daec-5b58-4ae9-be2d-61a66d5d4306.webp\"}]', '[{\"name\":\"5fa4dbce014a49718460f19c4897fc85.mp4_335606166476_mp4_264_ld_0_xianyu.aliyun.mp4\",\"url\":\"http://localhost:1000/file/img/2024/07/07/ca4d9003-5e8f-4d08-97d7-8861063c97ed.mp4\"}]', 14, 9, 1000, 100, 1, 11, 4, '2024-11-07 14:20:01', 0);
INSERT INTO `product` VALUES (16, '冰箱 志高BCD118A', '功能正常，只能只提', '[{\"name\":\"O1CN01Wtz30G1lJ4gACcnnj_!!0-fleamarket.jpg_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/0867d8f2-e6dc-4bc3-bca0-2eeee8dfcd64.webp\"},{\"name\":\"O1CN01ZJaYzH1lJ4gBQduGl_!!0-fleamarket.jpg_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/ac54bc9f-830f-436b-bf9c-19a6b9636bac.webp\"}]', NULL, 14, 10, 400, 120, 1, 11, 2, '2024-11-07 14:20:01', 0);
INSERT INTO `product` VALUES (17, '华为B311As-853', '华为B311As-853  4G插卡无线路由器！功能完好！', '[{\"name\":\"O1CN015IjWXc1lJ4j60c6jV_!!0-fleamarket.jpg_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/5992b0f7-0671-4870-b81f-1d4afed17c8a.webp\"},{\"name\":\"O1CN01u9ETSH1lJ4jAH7heM_!!0-fleamarket.jpg_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/5cf9dc9b-3ad0-4f92-8e6d-1364a133b633.webp\"},{\"name\":\"O1CN01DkgqNh1lJ4jF8wMlK_!!0-fleamarket.jpg_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/b68538b4-9cbe-489e-a470-cbd37902cda3.webp\"}]', NULL, 7, 9, 300, 130, 1, 11, 3, '2024-11-07 14:20:01', 0);
INSERT INTO `product` VALUES (18, '小牛电动车电池', '出个小牛蓝电（48V26A），无拆无修无磕碰，功能一切正常，循环145次左右（之前在同事小牛车上看过，保证真实循环），已解绑！\n带原装4A充电器！不要充电器可以少50！其余不刀！\n北京天安门自提，附近可送货！不邮寄！\n009 A7 A8 都可以用（一个转接线就行）', '[{\"name\":\"O1CN01Iwp5zR1lJ4lyuECs6_!!53-fleamarket.heic_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/ad45934e-d8eb-4d47-adec-8c5e64400863.webp\"},{\"name\":\"O1CN01ofwj2W1lJ4m7rqDd5_!!53-fleamarket.heic_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/4984bffe-3ed2-4b69-9135-97aca367e2c9.webp\"},{\"name\":\"O1CN01ZgfPdv1lJ4mD1TNFF_!!53-fleamarket.heic_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/9fd4ab8f-ad07-49ac-9091-7d7307273046.webp\"},{\"name\":\"O1CN01zXxcRX1lJ4m4qLcQQ_!!53-fleamarket.heic_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/bfce806a-0ab6-4851-b710-06e663c5f265.webp\"}]', NULL, 8, 10, 1500, 800, 1, 11, 6, '2024-11-07 14:20:01', 0);
INSERT INTO `product` VALUES (19, '手抛式速开帐篷', '手抛式速开帐篷（3-4人款），买了一年，只用了两次！收缩有个卡扣断了，不影响正常使用，只是收缩的时候要用点力，其他功能正常！如需寄件，到付邮费！\n货物详情见京东的原购货链接：\nhttps://item.m.jd.com/product/2657990.html?gx=RnFtkTYLamCNydRP--tzCQb9QKbfXHPaRg5W&ad_od=share&utm_source=androidapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=CopyURL\n', '[{\"name\":\"O1CN01WZcwXq1lJ4m8jN7IM_!!53-fleamarket.heic_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/f1840c6f-2d49-4f4a-be71-f204d56ef04e.webp\"},{\"name\":\"O1CN01kiSADq1lJ4mOeYRep_!!53-fleamarket.heic_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/f9b5750d-f048-4c0d-8f03-4363f42e3021.webp\"}]', NULL, 11, 8, 120, 50, 1, 2, 19, '2024-11-07 14:20:01', 1);
INSERT INTO `product` VALUES (20, '萤石N1互联网NVR硬盘录像机', '萤石N1互联网NVR硬盘录像机 CS-N1P-208/1T！\n支持8路萤石摄像头！自带POE供电！支持手机远程观看！带原装48v电源，不带硬盘！已恢复出厂设置，萤石云已解绑！\n成色如图，功能一切正常！升级监控换下的！\n广州市天河区自提（附近可送），外地邮寄需要另外协商运费！', '[{\"name\":\"O1CN01J2zvQY1lJ4pajuENL_!!53-fleamarket.heic_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/b63edb77-6b55-431c-b112-50192639d50e.webp\"},{\"name\":\"O1CN01lwYY3u1lJ4pWdt54B_!!53-fleamarket.heic_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/016f29a3-e125-4192-943d-3f4ab9ee29ea.webp\"},{\"name\":\"O1CN01mk0Lnv1lJ4pWcw4jy_!!53-fleamarket.heic_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/4ddf3c90-1e90-44d5-afcb-cbd60b6d52ee.webp\"},{\"name\":\"O1CN01uPMrGY1lJ4pYCkbMZ_!!53-fleamarket.heic_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/26ebf275-db71-4cc4-95dd-476c5dbd56b1.webp\"},{\"name\":\"O1CN01z7zMY41lJ4pYCmHJ3_!!53-fleamarket.heic_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/17653833-0b8f-46d4-a6ba-338ae6354eb8.webp\"},{\"name\":\"O1CN014Ylpfe1lJ4pTAwgl6_!!53-fleamarket.heic_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/87a624eb-dac9-405c-878c-f38ff9c382b2.webp\"}]', NULL, 10, 8, 500, 64, 1, 2, 12, '2024-11-07 14:20:01', 0);
INSERT INTO `product` VALUES (21, '锐捷RG-ES208GC 千兆8口云管理交换机', '锐捷RG-ES208GC 千兆8口云管理交换机\n支持锐捷锐易APP\n本来准备装监控用的，结果买多了，所以出掉\n\n几乎全新！就拿回来测试一下就没用了！原包装原电源！无任何使用痕迹！保修还有300多天！\n包邮非偏远地区', '[{\"name\":\"O1CN01CEcQBz1lJ4qDpJua5_!!0-fleamarket.jpg_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/f7946f7d-80e2-4149-a9be-2af5c53f1bd8.webp\"},{\"name\":\"O1CN01VlXfEI1lJ4qBjV9lj_!!0-fleamarket.jpg_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/a00dfd1c-29c2-4147-8a61-2b042d6753a8.webp\"}]', NULL, 10, 7, 320, 135, 1, 2, 4, '2024-11-07 14:20:01', 0);
INSERT INTO `product` VALUES (22, '姿椅人体工学椅', '跪椅矫正坐姿椅人体工学椅电脑椅家用办公椅成人学生座椅休闲摇椅！人体工程学跪椅，用了几个月，功能一切正常，送皮质垫子（替换用的）！换了更高级的人体工学椅，所以出了！只自提不邮寄！', '[{\"name\":\"O1CN01djjEf21lJ4odS7cFK_!!53-fleamarket.heic_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/73560ae8-b663-4956-a47d-7659ca9a922a.webp\"},{\"name\":\"O1CN01gXg17V1lJ4ohBrX6b_!!53-fleamarket.heic_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/7de2464f-094c-477f-945c-403d2f05be05.webp\"},{\"name\":\"O1CN01Rvtel71lJ4oh9rOZg_!!53-fleamarket.heic_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/620ff0f4-0d14-42e2-858f-88ec7387372e.webp\"},{\"name\":\"O1CN01aFIYfL1lJ4okGMZX5_!!53-fleamarket.heic_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/4422d72a-3fb0-4853-bda3-c65040c0c81e.webp\"}]', NULL, 11, 9, 200, 37, 3, 11, 12, '2024-11-07 14:20:01', 0);
INSERT INTO `product` VALUES (23, '漫步者音响  R101V', '漫步者音响  R101V！功能一切正常！升级音响所以换下了！ 天河黄村自提！不邮寄', '[{\"name\":\"O1CN01NUDvOF1lJ4rSHBPsY_!!0-fleamarket.jpg_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/a335f76f-8e8d-4bb7-ba3c-eb7f10cb0963.webp\"},{\"name\":\"O1CN01z5q2i31lJ4rR2Rzyn_!!0-fleamarket.jpg_790x10000q50.jpg_.webp\",\"url\":\"http://localhost:1000/file/img/2024/07/07/8d048ccc-19f6-49c4-bd70-49a60b5d3c84.webp\"}]', NULL, 10, 9, 120, 35, 3, 11, 20, '2024-11-07 14:20:01', 2);
INSERT INTO `product` VALUES (25, '路由器', '刚买的路由器，现在不需要了，需要的联系我', '[{\"name\":\"图1.png\",\"url\":\"http://localhost:1000/file/img/2024/11/26/727dfc9d-34ee-4ce2-bb4d-a6dee6e7d04c.png\"},{\"name\":\"图2.png\",\"url\":\"http://localhost:1000/file/img/2024/11/26/10516091-bac8-4cff-aecb-0a8afb3fa8e9.png\"}]', NULL, 14, 8, 100, 60, 3, 2, 4, '2024-11-26 18:35:21', 1);

-- ----------------------------
-- Table structure for product_classify
-- ----------------------------
DROP TABLE IF EXISTS `product_classify`;
CREATE TABLE `product_classify`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `remark` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注(多行文本,5)',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品分类' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_classify
-- ----------------------------
INSERT INTO `product_classify` VALUES (7, '手机', '手机', '2024-11-07 14:20:01');
INSERT INTO `product_classify` VALUES (8, '车品', '车品', '2024-11-07 14:20:01');
INSERT INTO `product_classify` VALUES (9, '食品', '食品', '2024-11-07 14:20:01');
INSERT INTO `product_classify` VALUES (10, '数码', '数码', '2024-11-07 14:20:01');
INSERT INTO `product_classify` VALUES (11, '家装', '家装', '2024-11-07 14:20:01');
INSERT INTO `product_classify` VALUES (12, '男装', '男装', '2024-11-07 14:20:01');
INSERT INTO `product_classify` VALUES (13, '女装', '	\n女装', '2024-11-07 14:20:01');
INSERT INTO `product_classify` VALUES (14, '电器', '电器', '2024-11-07 14:20:01');

-- ----------------------------
-- Table structure for product_favorite
-- ----------------------------
DROP TABLE IF EXISTS `product_favorite`;
CREATE TABLE `product_favorite`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户',
  `product_id` int(11) NULL DEFAULT NULL COMMENT '商品',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品收藏' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_favorite
-- ----------------------------
INSERT INTO `product_favorite` VALUES (23, 2, 23, '2024-11-07 14:20:01');
INSERT INTO `product_favorite` VALUES (24, 12, 13, '2024-11-13 21:07:36');
INSERT INTO `product_favorite` VALUES (25, 12, 25, '2024-11-26 18:35:44');

-- ----------------------------
-- Table structure for product_fineness
-- ----------------------------
DROP TABLE IF EXISTS `product_fineness`;
CREATE TABLE `product_fineness`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `remark` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注(多行文本,5)',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品成色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_fineness
-- ----------------------------
INSERT INTO `product_fineness` VALUES (7, '全新', '全新', '2024-11-07 14:20:01');
INSERT INTO `product_fineness` VALUES (8, '几乎全新', '几乎全新', '2024-11-07 14:20:01');
INSERT INTO `product_fineness` VALUES (9, '轻微使用痕迹', '轻微使用痕迹', '2024-11-07 14:20:01');
INSERT INTO `product_fineness` VALUES (10, '明显使用痕迹', '明显使用痕迹', '2024-11-07 14:20:01');

-- ----------------------------
-- Table structure for product_history
-- ----------------------------
DROP TABLE IF EXISTS `product_history`;
CREATE TABLE `product_history`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户',
  `product_id` int(11) NULL DEFAULT NULL COMMENT '商品',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 308 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品历史' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_history
-- ----------------------------
INSERT INTO `product_history` VALUES (184, 11, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (185, 11, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (186, 11, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (187, 1, 14, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (188, 1, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (189, 1, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (190, 1, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (191, 1, 14, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (192, 2, 19, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (193, 2, 15, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (194, 2, 22, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (195, 1, 23, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (196, 1, 23, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (197, 1, 23, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (198, 2, 23, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (199, 2, 23, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (200, 2, 23, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (201, 2, 23, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (202, 2, 23, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (203, 2, 18, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (204, 2, 23, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (205, 2, 23, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (206, 1, 23, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (207, 1, 23, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (208, 2, 23, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (209, 2, 15, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (210, 2, 19, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (211, 2, 19, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (212, 2, 18, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (213, 2, 14, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (214, 2, 19, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (215, 2, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (216, 2, 23, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (217, 1, 21, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (218, 1, 20, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (219, 1, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (220, 1, 22, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (221, 1, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (222, 1, 19, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (223, 1, 19, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (224, 1, 19, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (225, 1, 19, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (226, 1, 20, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (227, 1, 19, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (228, 1, 19, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (229, 1, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (230, 1, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (231, 1, 20, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (232, 1, 22, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (233, 2, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (234, 2, 20, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (235, 2, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (236, 2, 22, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (237, 2, 15, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (238, 2, 18, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (239, 2, 19, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (240, 2, 20, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (241, 2, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (242, 2, 18, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (243, 2, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (244, 2, 20, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (245, 2, 21, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (246, 2, 18, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (247, 2, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (248, 1, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (249, 1, 16, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (250, 1, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (251, 1, 22, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (252, 1, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (253, 2, 19, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (254, 2, 23, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (255, 2, 23, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (256, 2, 23, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (257, 2, 23, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (258, 2, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (259, 2, 23, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (260, 2, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (261, 1, 23, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (262, 2, 19, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (263, 2, 20, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (264, 2, 19, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (265, 2, 20, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (266, 2, 20, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (267, 2, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (268, 2, 20, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (269, 1, 20, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (270, 1, 16, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (271, 1, 22, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (272, 1, 13, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (273, 1, 21, '2024-11-07 14:20:01');
INSERT INTO `product_history` VALUES (274, 12, 13, '2024-11-13 21:06:47');
INSERT INTO `product_history` VALUES (275, 12, 14, '2024-11-13 21:06:53');
INSERT INTO `product_history` VALUES (276, 12, 19, '2024-11-13 21:06:58');
INSERT INTO `product_history` VALUES (277, 12, 13, '2024-11-13 21:07:34');
INSERT INTO `product_history` VALUES (278, 12, 13, '2024-11-13 21:07:36');
INSERT INTO `product_history` VALUES (279, 12, 13, '2024-11-13 21:08:04');
INSERT INTO `product_history` VALUES (280, 12, 21, '2024-11-17 21:21:16');
INSERT INTO `product_history` VALUES (281, 12, 19, '2024-11-17 21:21:24');
INSERT INTO `product_history` VALUES (282, 12, 13, '2024-11-17 21:24:21');
INSERT INTO `product_history` VALUES (283, 12, 13, '2024-11-17 21:28:09');
INSERT INTO `product_history` VALUES (284, 12, 13, '2024-11-17 21:28:30');
INSERT INTO `product_history` VALUES (285, 12, 19, '2024-11-17 21:29:08');
INSERT INTO `product_history` VALUES (286, 12, 19, '2024-11-17 21:30:00');
INSERT INTO `product_history` VALUES (287, 12, 22, '2024-11-17 21:31:41');
INSERT INTO `product_history` VALUES (288, 12, 22, '2024-11-17 21:32:24');
INSERT INTO `product_history` VALUES (289, 12, 22, '2024-11-17 21:32:34');
INSERT INTO `product_history` VALUES (290, 12, 22, '2024-11-17 21:32:43');
INSERT INTO `product_history` VALUES (291, 12, 22, '2024-11-17 21:32:50');
INSERT INTO `product_history` VALUES (292, 12, 22, '2024-11-17 21:33:45');
INSERT INTO `product_history` VALUES (293, 12, 13, '2024-11-26 17:54:33');
INSERT INTO `product_history` VALUES (294, 12, 19, '2024-11-26 18:01:11');
INSERT INTO `product_history` VALUES (295, 12, 20, '2024-11-26 18:01:21');
INSERT INTO `product_history` VALUES (296, 12, 18, '2024-11-26 18:01:32');
INSERT INTO `product_history` VALUES (297, 12, 14, '2024-11-26 18:02:38');
INSERT INTO `product_history` VALUES (298, 12, 17, '2024-11-26 18:02:56');
INSERT INTO `product_history` VALUES (299, 12, 15, '2024-11-26 18:19:24');
INSERT INTO `product_history` VALUES (300, 12, 17, '2024-11-26 18:19:44');
INSERT INTO `product_history` VALUES (301, 12, 17, '2024-11-26 18:22:19');
INSERT INTO `product_history` VALUES (302, 12, 24, '2024-11-26 18:22:33');
INSERT INTO `product_history` VALUES (303, 12, 13, '2024-11-26 18:32:03');
INSERT INTO `product_history` VALUES (304, 12, 25, '2024-11-26 18:35:41');
INSERT INTO `product_history` VALUES (305, 12, 25, '2024-11-26 18:35:44');
INSERT INTO `product_history` VALUES (306, 12, 25, '2024-11-26 18:37:48');
INSERT INTO `product_history` VALUES (307, 12, 25, '2024-11-26 18:38:13');

-- ----------------------------
-- Table structure for product_leave_message
-- ----------------------------
DROP TABLE IF EXISTS `product_leave_message`;
CREATE TABLE `product_leave_message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '发布人',
  `product_id` int(11) NULL DEFAULT NULL COMMENT '商品',
  `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容(多行文本,5)',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品留言' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_leave_message
-- ----------------------------
INSERT INTO `product_leave_message` VALUES (9, 1, 16, '你好', '2024-11-07 14:20:01');
INSERT INTO `product_leave_message` VALUES (10, 12, 25, '我需要，可以便宜点吗', '2024-11-26 18:35:59');

-- ----------------------------
-- Table structure for shipping_address
-- ----------------------------
DROP TABLE IF EXISTS `shipping_address`;
CREATE TABLE `shipping_address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户',
  `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '收货人',
  `tel` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `address` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '地址(多行文本,10)',
  `is_default` int(11) NOT NULL COMMENT '默认地址(单选,1:是,0:否)',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '收货地址' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shipping_address
-- ----------------------------
INSERT INTO `shipping_address` VALUES (7, 1, '张三', '0151710228361', '湖北省武汉市洪山区xx街道xx号', 1, '2024-11-07 14:20:01');
INSERT INTO `shipping_address` VALUES (8, 1, '王二', '1234567', '湖北省武汉市洪山区xx街道xx号', 0, '2024-11-07 14:20:01');
INSERT INTO `shipping_address` VALUES (9, 2, '王大', '15666', '5216546595', 1, '2024-11-07 14:20:01');
INSERT INTO `shipping_address` VALUES (10, 12, '张三', '15566776677', '张三张三张三张三', 1, '2024-11-17 21:32:20');

-- ----------------------------
-- Table structure for sys_inform
-- ----------------------------
DROP TABLE IF EXISTS `sys_inform`;
CREATE TABLE `sys_inform`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容(多行文本,5)',
  `win_top` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '置顶(单选,是,否)',
  `status` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态(单选,启用,禁用)',
  `user_id` int(11) NOT NULL COMMENT '发布人',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '公告' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_inform
-- ----------------------------
INSERT INTO `sys_inform` VALUES (7, '系统升级公告', '尊敬的用户：\n\n您好！感谢您一直以来对我们管理系统的支持与信任。\n\n为了提升我们的服务品质，优化系统性能，我们将对管理系统进行升级。此次升级将涉及系统的功能增强、界面优化以及错误修复等方面的改进。在升级过程中，我们将努力保证系统的稳定性和正常运行，但仍有可能会对部分功能产生一定影响。\n\n下面，我们将详细介绍此次管理系统升级的内容及影响，希望对您的工作产生最小程度的干扰。\n\n一、升级内容：\n\n1. 系统性能优化：我们将对系统的响应速度、稳定性和容错性进行提升，以确保用户在使用过程中的流畅体验。\n\n2. 新增功能：我们增加了一些常用的功能，比如报表导出、数据分析等，以满足用户对数据处理和分析的需求。\n\n3. 用户界面优化：我们对系统的界面进行了全面的优化，使之更加直观、简洁，方便用户使用和操作。\n\n4. 安全性加强：我们加强了系统的安全性管理，增加了多层次的权限控制和数据加密等安全保障措施，以保护用户的数据安全。\n\n二、升级影响：\n\n1. 系统停机时间：为了进行升级工作，我们将在11月11日00时00分至11月11日03时00分之间暂停系统的使用。在此期间，您将无法登录和使用管理系统，请提前做好工作安排，以免造成不便。\n\n2. 功能受限：升级期间部分功能可能会受到限制或暂时不可用，我们会尽快恢复正常运行。我们建议您提前备份好相关数据，以免数据丢失或遗漏。\n\n3. 数据同步延迟：升级后，数据同步可能会有一定的延迟，请您耐心等待，并刷新页面查看最新数据。\n\n4. 用户指南更新：由于界面优化和功能增加，系统的用户指南也将进行相应的更新。请您在使用升级后的系统前，仔细阅读最新版的用户指南，以了解和熟悉系统的新功能和操作方式。\n\n三、升级后的期望：\n\n在此次升级完成后，我们相信系统性能将会更加强大、稳定和易用。新的功能和界面将为您的工作带来更多的便利性和高效性。\n\n此次升级是我们为了提升用户体验、保障数据安全而进行的重要举措。我们将会持续跟踪和优化系统，为您提供更好的服务和支持。', '是', '启用', 1, '2024-11-07 14:20:01');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '上级菜单',
  `type` int(11) NULL DEFAULT NULL COMMENT '菜单类型',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图标',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `outside_chain` int(11) NULL DEFAULT NULL COMMENT '外链',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, 1, 'fa fa-list-ol', '主目录', 1, 0, NULL, 1, '2024-11-07 14:20:01', NULL);
INSERT INTO `sys_menu` VALUES (23, 1, 1, 'fa fa-reddit', '系统管理', 99, 0, 'system', 1, '2024-11-07 14:20:01', NULL);
INSERT INTO `sys_menu` VALUES (24, 23, 2, 'el-icon-user-solid', '用户管理', 1, 0, 'sysUser', 1, '2024-11-07 14:20:01', 'SysUser');
INSERT INTO `sys_menu` VALUES (25, 23, 2, 'fa fa-user-circle', '角色管理', 2, 0, 'sysRole', 1, '2024-11-07 14:20:01', 'SysRole');
INSERT INTO `sys_menu` VALUES (27, 23, 2, 'fa fa-list-ol', '菜单权限', 3, 0, 'sysMenu', 1, '2024-11-07 14:20:01', 'SysMenu');
INSERT INTO `sys_menu` VALUES (40, 1, 2, 'fa fa-bell-o', '公告', 0, 0, 'sysInform', 1, '2024-11-07 14:20:01', 'SysInform');
INSERT INTO `sys_menu` VALUES (41, 23, 2, 'el-icon-menu', '短信验证码', 4, 0, 'sysSmsCode', 1, '2024-11-07 14:20:01', 'SysSmsCode');
INSERT INTO `sys_menu` VALUES (42, 52, 2, 'el-icon-folder-remove', '商品分类', 3, 0, 'productClassify', 1, '2024-11-07 14:20:01', 'ProductClassify');
INSERT INTO `sys_menu` VALUES (43, 52, 2, 'el-icon-bangzhu', '商品成色', 4, 0, 'productFineness', 1, '2024-11-07 14:20:01', 'ProductFineness');
INSERT INTO `sys_menu` VALUES (44, 52, 2, 'el-icon-box', '商品', 5, 0, 'product', 1, '2024-11-07 14:20:01', 'Product');
INSERT INTO `sys_menu` VALUES (45, 1, 2, 'el-icon-place', '收货地址', 6, 0, 'shippingAddress', 1, '2024-11-07 14:20:01', 'ShippingAddress');
INSERT INTO `sys_menu` VALUES (46, 52, 2, 'fa fa-commenting-o', '商品留言', 7, 0, 'productLeaveMessage', 1, '2024-11-07 14:20:01', 'ProductLeaveMessage');
INSERT INTO `sys_menu` VALUES (47, 52, 2, 'fa fa-gratipay', '商品收藏', 8, 0, 'productFavorite', 1, '2024-11-07 14:20:01', 'ProductFavorite');
INSERT INTO `sys_menu` VALUES (50, 1, 2, 'el-icon-shopping-cart-1', '订单', 11, 0, 'tOrder', 1, '2024-11-07 14:20:01', 'TOrder');
INSERT INTO `sys_menu` VALUES (51, 1, 2, 'fa fa-eye', '商品浏览历史', 12, 0, 'productHistory', 1, '2024-11-07 14:20:01', 'ProductHistory');
INSERT INTO `sys_menu` VALUES (52, 1, 1, 'fa fa-list-ul', '商品管理', 3, 0, 'productManager', 1, '2024-11-07 14:20:01', 'productManager');
INSERT INTO `sys_menu` VALUES (53, 1, 1, 'fa fa-list-ul', '聊天管理', 4, 0, 'messageManager', 1, '2024-11-07 14:20:01', 'messageManager');
INSERT INTO `sys_menu` VALUES (55, 53, 2, 'fa fa-list-alt', '聊天房间', 14, 0, 'chatRoom', 1, '2024-11-07 14:20:01', 'ChatRoom');
INSERT INTO `sys_menu` VALUES (56, 53, 2, 'el-icon-chat-dot-square', '聊天消息', 15, 0, 'chatMessage', 1, '2024-11-07 14:20:01', 'ChatMessage');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色名称',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色描述',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', '超级管理员', 'ADMIN');
INSERT INTO `sys_role` VALUES (2, '普通用户', '普通用户', 'USER');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(11) NULL DEFAULT NULL COMMENT '菜单ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 40);
INSERT INTO `sys_role_menu` VALUES (1, 23);
INSERT INTO `sys_role_menu` VALUES (1, 24);
INSERT INTO `sys_role_menu` VALUES (1, 25);
INSERT INTO `sys_role_menu` VALUES (1, 27);
INSERT INTO `sys_role_menu` VALUES (1, 41);
INSERT INTO `sys_role_menu` VALUES (1, 52);
INSERT INTO `sys_role_menu` VALUES (1, 42);
INSERT INTO `sys_role_menu` VALUES (1, 43);
INSERT INTO `sys_role_menu` VALUES (1, 44);
INSERT INTO `sys_role_menu` VALUES (1, 46);
INSERT INTO `sys_role_menu` VALUES (1, 47);
INSERT INTO `sys_role_menu` VALUES (1, 53);
INSERT INTO `sys_role_menu` VALUES (1, 48);
INSERT INTO `sys_role_menu` VALUES (1, 49);
INSERT INTO `sys_role_menu` VALUES (1, 45);
INSERT INTO `sys_role_menu` VALUES (1, 50);
INSERT INTO `sys_role_menu` VALUES (1, 51);
INSERT INTO `sys_role_menu` VALUES (1, 55);
INSERT INTO `sys_role_menu` VALUES (1, 56);
INSERT INTO `sys_role_menu` VALUES (2, 52);
INSERT INTO `sys_role_menu` VALUES (2, 44);
INSERT INTO `sys_role_menu` VALUES (2, 45);
INSERT INTO `sys_role_menu` VALUES (2, 50);

-- ----------------------------
-- Table structure for sys_sms_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_sms_code`;
CREATE TABLE `sys_sms_code`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `mobile` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `code` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '验证码',
  `content` varchar(1010) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '短信内容',
  `expiration_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '过期时间',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '短信验证码' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_sms_code
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名称',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户密码',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '个人简介',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像(图片)',
  `sex` int(11) NULL DEFAULT NULL COMMENT '性别(单选,男,女)',
  `status` int(11) NULL DEFAULT 1 COMMENT '状态(单选,禁用,启用)',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色',
  `salt` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '加盐',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '开始时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'e904356c9cf05f9e9b4f7bf04b5261eb', '', '12345@gmail.com', '15587878787', '北京市xx区xx街道', '[{\"name\":\"微信截图_20240707150921.png\",\"url\":\"http://localhost:1000/file/img/2024/07/07/eef920a9-ed23-4e92-b592-dfc1f2af9704.png\"}]', 1, 1, 1, '7A8AC7A38D841B9B', '2024-11-07 14:20:01');
INSERT INTO `sys_user` VALUES (2, '用户1', '3a995c31b7eba336438078281f2a7dff', '', '231@qq.com', '15566778899', '北京市xx区xx街道', '[{\"name\":\"微信截图_20240707150906.png\",\"url\":\"http://localhost:1000/file/img/2024/07/07/95b54658-988e-4215-97cf-705fd5e5e662.png\"}]', 1, 1, 2, 'C57767697D38B29C', '2024-11-07 14:20:01');
INSERT INTO `sys_user` VALUES (11, '用户2', '051b2013c51367a9e1891deecf14953e', '', '2@qq.com', '18188888888', '北京市xx区xx街道', '[{\"name\":\"微信截图_20240707150837.png\",\"url\":\"http://localhost:1000/file/img/2024/07/07/1ba3ba7c-9d8f-4f3f-bffc-64aad12c0a1a.png\"}]', 0, 1, 2, '8BD44468A8BF3471', '2024-11-07 14:20:01');
INSERT INTO `sys_user` VALUES (12, 'user', 'b0cddf2c1ebfd623c1f4510d44c51f85', '', '12345@gmail.com', '15587878788', '北京市xx区xx街道', '[{\"name\":\"微信截图_20240707150921.png\",\"url\":\"http://localhost:1000/file/img/2024/07/07/eef920a9-ed23-4e92-b592-dfc1f2af9704.png\"}]', 1, 1, 2, '550E95F4C6CD0358', '2024-11-07 14:20:01');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户',
  `seller_id` int(11) NULL DEFAULT NULL COMMENT '卖家',
  `product_id` int(11) NULL DEFAULT NULL COMMENT '商品',
  `price` double NULL DEFAULT NULL COMMENT '价格',
  `status` int(11) NOT NULL COMMENT '状态(下拉框,1:待付款,2:待发货,3:已发货,3:交易成功)',
  `mode` int(11) NOT NULL COMMENT '发货方式(单选,1:邮寄,2:自提)',
  `shipping_address` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '收货地址',
  `remark` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注(多行文本,5)',
  `pay_transaction_number` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '付款交易号',
  `number` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单编号',
  `tracking_number` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '物流单号',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '下单时间',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '付款时间',
  `deliver_time` datetime(0) NULL DEFAULT NULL COMMENT '发货时间',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '成交时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (1, 12, 11, 22, 37, 4, 1, '张三-15566776677-张三张三张三张三', '123', '7cadbd5f-750e-45fd-854f-ba53e26145a8', '202411172132306256', '123123123', '2024-11-17 21:32:31', '2024-11-17 21:32:32', '2024-11-17 21:33:42', '2024-11-17 21:33:50');
INSERT INTO `t_order` VALUES (2, 12, 2, 13, 1400, 4, 1, '张三-15566776677-张三张三张三张三', '', '474a3a24-98f6-4502-a147-11ff6f003634', '202411261757248137', 'xxxxx', '2024-11-26 17:57:24', '2024-11-26 17:57:27', '2024-11-26 17:59:46', '2024-11-26 18:00:06');
INSERT INTO `t_order` VALUES (3, 12, 2, 25, 60, 4, 1, '张三-15566776677-张三张三张三张三', '已购买，期待收货', '3a5319ea-bf0d-4ea3-87fe-e19ff98f0f4a', '202411261838318009', '15567807789', '2024-11-26 18:38:32', '2024-11-26 18:38:35', '2024-11-26 18:39:11', '2024-11-26 18:39:36');

SET FOREIGN_KEY_CHECKS = 1;
