/*
Navicat MySQL Data Transfer

Source Server         : 自己的mysql
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : spring_data

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2018-01-18 19:25:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for application
-- ----------------------------
DROP TABLE IF EXISTS `application`;
CREATE TABLE `application` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of application
-- ----------------------------
INSERT INTO `application` VALUES ('1', '1', 'el-icon-fa-user-o', '用户管理', '0', '/admin-main/user-list');
INSERT INTO `application` VALUES ('2', '2', 'el-icon-menu', '应用管理', '0', '/admin-main/app-list');
INSERT INTO `application` VALUES ('3', '3', 'el-icon-fa-user-secret', '角色管理', '0', '/admin-main/role-list');
INSERT INTO `application` VALUES ('4', '4', 'el-icon-fa-graduation-cap', '课程管理', '0', '/admin-main/course-list');
INSERT INTO `application` VALUES ('40289ffe60a7d8770160a7e68ea00000', null, '', '选课', '0', '/admin-main/course-select');
INSERT INTO `application` VALUES ('40289ffe60a7d8770160a7e73d790001', null, '', '我的课程', '0', '/admin-main/course-my-list');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `count` int(11) DEFAULT NULL,
  `has_count` int(11) DEFAULT NULL,
  `memo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `teacher_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `teacher_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('40289ffe60a7d8770160a7fadbc7000d', '1', '1', null, '美术课', '40289ffe60a7d8770160a7eb00590009', '大明白', '30');
INSERT INTO `course` VALUES ('40289ffe60a833a20160a839ac910006', '6', '1', '11111111111', '新课程', '40289ffe60a7d8770160a7eb00590009', '大明白', '35');
INSERT INTO `course` VALUES ('40289ffe60a833a20160a83f146f000c', '7', '0', '111111111', '英语课', '40289ffe60a7d8770160a7eb00590009', '大明白', '30');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sort` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '', '管理员', '0');
INSERT INTO `role` VALUES ('40289ffe60a7d8770160a7e945a60002', '001', '老师', '10');
INSERT INTO `role` VALUES ('40289ffe60a7d8770160a7e99f9f0004', '002', '学生', '10');

-- ----------------------------
-- Table structure for role_to_application
-- ----------------------------
DROP TABLE IF EXISTS `role_to_application`;
CREATE TABLE `role_to_application` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `application_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `role_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh9eau927154agrqgi0qog7foy` (`application_id`),
  KEY `FK8p039n9im34xo4m98b36v8r8v` (`role_id`),
  CONSTRAINT `FK8p039n9im34xo4m98b36v8r8v` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKh9eau927154agrqgi0qog7foy` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of role_to_application
-- ----------------------------
INSERT INTO `role_to_application` VALUES ('1', '1', '1');
INSERT INTO `role_to_application` VALUES ('2', '2', '1');
INSERT INTO `role_to_application` VALUES ('3', '3', '1');
INSERT INTO `role_to_application` VALUES ('4', '4', '1');
INSERT INTO `role_to_application` VALUES ('40289ffe60a7d8770160a7e945a70003', '40289ffe60a7d8770160a7e73d790001', '40289ffe60a7d8770160a7e945a60002');
INSERT INTO `role_to_application` VALUES ('40289ffe60a7d8770160a7e99fa40005', '40289ffe60a7d8770160a7e68ea00000', '40289ffe60a7d8770160a7e99f9f0004');
INSERT INTO `role_to_application` VALUES ('40289ffe60a7d8770160a7e99fab0006', '40289ffe60a7d8770160a7e73d790001', '40289ffe60a7d8770160a7e99f9f0004');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `age` int(11) DEFAULT NULL,
  `login_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pass_word` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '2017-12-30 22:31:59', '2017-12-30 22:32:03', '1', 'admin', '管理员', '1', '1', '1');
INSERT INTO `user` VALUES ('40289ffe60a7d8770160a7ea70710007', '2017-12-30 22:54:07', '2017-12-30 22:54:07', '1', '1', '于大勺', '1', '0', '0');
INSERT INTO `user` VALUES ('40289ffe60a7d8770160a7eb00590009', '2017-12-30 22:54:44', '2017-12-30 22:54:44', '1', '2', '大明白', '2', '0', '0');

-- ----------------------------
-- Table structure for user_to_course
-- ----------------------------
DROP TABLE IF EXISTS `user_to_course`;
CREATE TABLE `user_to_course` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `course_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaix4ssoybn2pmrnm0v2i4hx68` (`course_id`),
  KEY `FKd36k5i0ngr9kywarofpes50s2` (`user_id`),
  CONSTRAINT `FKaix4ssoybn2pmrnm0v2i4hx68` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FKd36k5i0ngr9kywarofpes50s2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_to_course
-- ----------------------------
INSERT INTO `user_to_course` VALUES ('4028818560abc5380160abc60d990000', '40289ffe60a833a20160a839ac910006', '40289ffe60a7d8770160a7ea70710007');
INSERT INTO `user_to_course` VALUES ('40289ffe60a802200160a815ffc60001', '40289ffe60a7d8770160a7fadbc7000d', '40289ffe60a7d8770160a7ea70710007');

-- ----------------------------
-- Table structure for user_to_role
-- ----------------------------
DROP TABLE IF EXISTS `user_to_role`;
CREATE TABLE `user_to_role` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `role_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKknk9kh09xew27k796uxnj1tbs` (`role_id`),
  KEY `FKnviyw65au34bn5i7y4ypm72gl` (`user_id`),
  CONSTRAINT `FKknk9kh09xew27k796uxnj1tbs` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKnviyw65au34bn5i7y4ypm72gl` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_to_role
-- ----------------------------
INSERT INTO `user_to_role` VALUES ('1', '1', '1');
INSERT INTO `user_to_role` VALUES ('40289ffe60a7d8770160a7ea70730008', '40289ffe60a7d8770160a7e99f9f0004', '40289ffe60a7d8770160a7ea70710007');
INSERT INTO `user_to_role` VALUES ('40289ffe60a7d8770160a7eb005d000a', '40289ffe60a7d8770160a7e945a60002', '40289ffe60a7d8770160a7eb00590009');
