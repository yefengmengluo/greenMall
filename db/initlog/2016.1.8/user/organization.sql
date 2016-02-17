/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : jeesite

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2015-12-31 14:54:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for organization_info
-- ----------------------------
DROP TABLE IF EXISTS `organization_info`;
CREATE TABLE `organization_info` (
  `id` varchar(64) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` int(10) NOT NULL COMMENT '公司类型 (数据字典)',
  `image_path` varchar(128) default NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `create_by` varchar(64) NOT NULL,
  `update_by` varchar(64) NOT NULL,
  `regist_date` datetime default NULL COMMENT '注册时间',
  `is_entrust` int(10) unsigned default '0' COMMENT '是否是委托人（没有注册）的公司信息(默认0:否，1：是)',
  `regist_number` varchar(32) default NULL COMMENT '注册号',
  `province_id` varchar(32) default NULL COMMENT '省份',
  `city_id` varchar(32) default NULL COMMENT '城市',
  `area_id` varchar(32) default NULL COMMENT '区/县',
  `detail_area` varchar(32) default NULL COMMENT '详细地区',
  `postcode` varchar(32) default NULL COMMENT '邮编',
  `fax_number` varchar(32) default NULL COMMENT '传真',
  `remarks` text COMMENT '备注',
  `person_name` varchar(255) default NULL COMMENT '默认联系人',
  `phone_mob` varchar(32) default NULL COMMENT '默认手机号',
  `phone_tel` varchar(32) default NULL COMMENT '默认座机号',
  `qq_number` varchar(32) default NULL COMMENT '默认qq',
  `email` varchar(64) default NULL COMMENT '默认邮箱',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构信息';

-- ----------------------------
-- Table structure for organization_main_goods
-- ----------------------------
DROP TABLE IF EXISTS `organization_main_goods`;
CREATE TABLE `organization_main_goods` (
  `id` varchar(64) default NULL,
  `goods_type` int(10) default NULL COMMENT '主营产品(数据字典)',
  `organization_id` varchar(64) default NULL,
  KEY `FK_Reference_43` (`organization_id`),
  CONSTRAINT `FK_Reference_43` FOREIGN KEY (`organization_id`) REFERENCES `organization_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司的主营产品';

-- ----------------------------
-- Table structure for organization_person
-- ----------------------------
DROP TABLE IF EXISTS `organization_person`;
CREATE TABLE `organization_person` (
  `id` varchar(64) NOT NULL,
  `person_name` varchar(255) NOT NULL COMMENT '联系人姓名',
  `phone_mob` varchar(32) NOT NULL COMMENT '联系人手机号',
  `phone_tel` varchar(32) default NULL COMMENT '联系人座机',
  `organization_id` varchar(64) NOT NULL COMMENT '组织id',
  `qq_number` varchar(32) default NULL COMMENT 'qq号',
  `email` varchar(64) default NULL COMMENT 'email',
  PRIMARY KEY  (`id`),
  KEY `FK_Reference_66` (`organization_id`),
  CONSTRAINT `FK_Reference_66` FOREIGN KEY (`organization_id`) REFERENCES `organization_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司联系人';

-- ----------------------------
-- Table structure for organization_phone_tels
-- ----------------------------
DROP TABLE IF EXISTS `organization_phone_tels`;
CREATE TABLE `organization_phone_tels` (
  `id` varchar(64) NOT NULL,
  `phone_tel` varchar(32) NOT NULL COMMENT '座机',
  `organization_id` varchar(64) NOT NULL COMMENT '机构',
  PRIMARY KEY  (`id`),
  KEY `FK_Reference_67` (`organization_id`),
  CONSTRAINT `FK_Reference_67` FOREIGN KEY (`organization_id`) REFERENCES `organization_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司座机号';

-- ----------------------------
-- Table structure for sys_mdict
-- ----------------------------
DROP TABLE IF EXISTS `sys_mdict`;
CREATE TABLE `sys_mdict` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `parent_id` int(10) unsigned NOT NULL,
  `parent_ids` varchar(2000) NOT NULL,
  `name` varchar(100) NOT NULL,
  `sort` decimal(10,0) NOT NULL,
  `description` varchar(100) default NULL,
  `create_by` varchar(64) NOT NULL,
  `update_by` varchar(64) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `remarks` varchar(255) NOT NULL,
  `del_flag` char(1) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

