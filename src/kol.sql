/*
Navicat MySQL Data Transfer

Source Server         : common
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : kol

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2015-03-22 14:38:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `kol_channel`
-- ----------------------------
DROP TABLE IF EXISTS `kol_channel`;
CREATE TABLE `kol_channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `create_date` varchar(14) DEFAULT NULL,
  `update_date` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kol_channel
-- ----------------------------

-- ----------------------------
-- Table structure for `kol_comment`
-- ----------------------------
DROP TABLE IF EXISTS `kol_comment`;
CREATE TABLE `kol_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `testimonials_id` bigint(20) NOT NULL,
  `contents` varchar(5000) NOT NULL,
  `create_date` varchar(14) NOT NULL,
  `update_date` varchar(14) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  'user_ip' varchar(200),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kol_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `kol_fast_comment`
-- ----------------------------
DROP TABLE IF EXISTS `kol_fast_comment`;
CREATE TABLE `kol_fast_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `testimonial_id` bigint(20) NOT NULL COMMENT '??ID',
  `grade` varchar(1) NOT NULL,
  `create_time` varchar(14) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kol_fast_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `kol_testimonials`
-- ----------------------------
DROP TABLE IF EXISTS `kol_testimonials`;
CREATE TABLE `kol_testimonials` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `contents` varchar(5000) NOT NULL,
  `create_date` varchar(14) NOT NULL,
  `update_date` varchar(14) DEFAULT NULL,
  `file_id` varchar(20) DEFAULT NULL,
  `channel_id` bigint(20) DEFAULT NULL,
  `title` varchar(200) NOT NULL,
  `is_top` varchar(2) DEFAULT NULL, -- 是否置顶  0: 是 
  'user_ip' varchar(200),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kol_testimonials
-- ----------------------------

-- ----------------------------
-- Table structure for `kol_user`
-- ----------------------------
DROP TABLE IF EXISTS `kol_user`;
CREATE TABLE `kol_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(50) NOT NULL,
  `mail` varchar(50) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `regist_type` varchar(1) DEFAULT '' COMMENT '0ϵͳע 1ûע',
  `nick_name` varchar(50) DEFAULT NULL,
  `pwd` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kol_user
-- ----------------------------

-- ----------------------------
-- Table structure for `kol_commemorate_board` 纪念册
-- ----------------------------
DROP TABLE IF EXISTS `kol_commemorate_board`;
CREATE TABLE `kol_commemorate_board` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL, -- 创建者ID
  `comments` varchar(4000),-- 纪念描述
  `file_id` varchar(20), -- 图片id
  `view_times` bigint(20), -- 查看次数
  `create_date` varchar(14) NOT NULL,
  `update_date` varchar(14) NOT NULL,
  `commemorate_date` varchar(8) DEFAULT NULL, -- 展示日期
  `top_times` bigint(20) DEFAULT 0, -- 被顶次数
  'user_ip' varchar(200),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `kol_files` 纪念册
-- ----------------------------
DROP TABLE IF EXISTS `kol_files`;
CREATE TABLE `kol_files` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL, -- 创建者ID
  `file_path` varchar(200),-- 纪念描述
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `kol_files` 发表感言的图片
-- ----------------------------
DROP TABLE IF EXISTS `kol_testimonials_files`;
CREATE TABLE `kol_testimonials_files` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL, -- 创建者ID
  `file_path` varchar(200),-- 纪念描述
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `kol_testimonials_totop` 置顶
-- ----------------------------
DROP TABLE IF EXISTS `kol_testimonials_totop`;
CREATE TABLE `kol_testimonials_totop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `contents` varchar(5000) NOT NULL,
  `create_date` varchar(14) NOT NULL,
  `update_date` varchar(14) DEFAULT NULL,
  `file_id` varchar(20) DEFAULT NULL,
  `channel_id` bigint(20) DEFAULT NULL,
  `title` varchar(200) NOT NULL,
  'user_ip' varchar(200),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `kol_testimonials_totop` 置顶
-- ----------------------------
DROP TABLE IF EXISTS `kol_operate_tracking`;
CREATE TABLE `kol_operate_tracking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `browser_name` varchar(100),
  `browser_version` varchar(20),
  `operate_name` varchar(200) NOT NULL,
  `operate_date` varchar(14) NOT NULL,
  `current_page` varchar(100),
  `user_agent` varchar(100),
  `local_addr` varchar(50),
  `remote_addr` varchar(50),
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




--------------------- TODO resource
DROP TABLE IF EXISTS `t_contact`;
CREATE TABLE `t_contact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `user_mobile` varchar(20) NOT NULL,
  `user_phone` varchar(20) NOT NULL,
  `user_mail` varchar(100) DEFAULT NULL,
  `user_qq` varchar(20) DEFAULT NULL,
  `user_source` varchar(1000) DEFAULT NULL,
  `create_time` varchar(14) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_form`;
CREATE TABLE `t_form` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `user_mobile` varchar(20) NOT NULL,
  `user_phone` varchar(20) NOT NULL,
  `pre_time` varchar(14) DEFAULT NULL,
  `user_qq` varchar(20) DEFAULT NULL,
  `user_address` varchar(1000) DEFAULT NULL,
  `create_time` varchar(14) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_advice`;
CREATE TABLE `t_advice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `user_mobile` varchar(20) NOT NULL,
  `user_phone` varchar(20) NOT NULL,
  `create_time` varchar(14) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
