/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : jeesite

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2016-02-03 18:12:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for front_user
-- ----------------------------
DROP TABLE IF EXISTS `front_user`;
CREATE TABLE `front_user` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `email` varchar(200) default NULL COMMENT '邮箱',
  `phone` varchar(200) default NULL COMMENT '电话',
  `mobile` varchar(200) default NULL COMMENT '手机',
  `photo` varchar(1000) default NULL COMMENT '用户头像',
  `login_ip` varchar(100) default NULL COMMENT '最后登陆IP',
  `login_date` datetime default NULL COMMENT '最后登陆时间',
  `login_flag` varchar(64) default NULL COMMENT '是否可登录',
  `create_ip` varchar(100) NOT NULL,
  `create_by` varchar(64) default NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) default NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) default NULL COMMENT '备注信息',
  `organization_id` varchar(64) default NULL COMMENT '所属机构id',
  `faxnumber` varchar(255) default NULL,
  `qq` varchar(255) default NULL,
  `statue` varchar(255) default '0' COMMENT '用户状态',
  `person_id` varchar(64) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `sys_user_update_date` USING BTREE (`update_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';