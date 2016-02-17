/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : jeesite

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2015-12-30 20:09:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for matching_attr
-- ----------------------------
DROP TABLE IF EXISTS `matching_attr`;
CREATE TABLE `matching_attr` (
  `id` varchar(64) NOT NULL COMMENT '匹配属性id',
  `matching_name` varchar(255) default NULL COMMENT '匹配属性名称',
  `remark` varchar(255) default NULL,
  `matchObject` varchar(255) default NULL,
  `create_by` varchar(255) default NULL COMMENT '创建人',
  `create_date` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of matching_attr
-- ----------------------------
INSERT INTO `matching_attr` VALUES ('1', 'date', '上市时间', 'demandAndSupply', 'zhaomeng', '2015-12-25 09:38:37');
INSERT INTO `matching_attr` VALUES ('2', 'address', '发货地和收货地', 'demandAndSupply', 'zhaomeng', '2015-12-25 09:38:37');
INSERT INTO `matching_attr` VALUES ('3', 'number', '供求数量', 'demandAndSupply', 'zhaomeng', '2015-12-25 09:38:37');

-- ----------------------------
-- Table structure for matching_attr_relation
-- ----------------------------
DROP TABLE IF EXISTS `matching_attr_relation`;
CREATE TABLE `matching_attr_relation` (
  `id` varchar(64) NOT NULL default '',
  `relation_id` varchar(64) default NULL,
  `matchattr_id` varchar(64) default NULL,
  `weight` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of matching_attr_relation
-- ----------------------------
INSERT INTO `matching_attr_relation` VALUES ('04e6a0a0f22143018775840a6f808ac2', '51314d5a1f5e4b36a7c9f47050bcc328', '3', '1000');
INSERT INTO `matching_attr_relation` VALUES ('5a09a0f3604f4a9d82b972f7ac4e04c9', '51314d5a1f5e4b36a7c9f47050bcc328', '2', '100');
INSERT INTO `matching_attr_relation` VALUES ('a8010208abab4c30b373f46544c07589', '8819ff9ba33f496ab805d85596993487', '1', '50');
INSERT INTO `matching_attr_relation` VALUES ('b6c3e00ad0454d71a19cd8effbaa21fe', '8819ff9ba33f496ab805d85596993487', '3', '100');
INSERT INTO `matching_attr_relation` VALUES ('e5b01a56b9584a5c9a9c6788e6479dcb', '8819ff9ba33f496ab805d85596993487', '2', '50');
INSERT INTO `matching_attr_relation` VALUES ('ed069dfeb1574ae6b1c417bc4cf335f7', '51314d5a1f5e4b36a7c9f47050bcc328', '1', '100');

-- ----------------------------
-- Table structure for supply_demand_relation_matching
-- ----------------------------
DROP TABLE IF EXISTS `supply_demand_relation_matching`;
CREATE TABLE `supply_demand_relation_matching` (
  `id` varchar(64) NOT NULL,
  `relation_name` varchar(255) default NULL,
  `relation_remark` varchar(255) default NULL,
  `remarks` varchar(255) default NULL COMMENT '说明',
  `matching_object` varchar(255) default NULL,
  `sort_order` int(11) default NULL,
  `if_matching` tinyint(4) default NULL,
  `create_date` datetime default NULL,
  `update_date` datetime default NULL,
  `create_by` varchar(64) default NULL,
  `update_by` varchar(64) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供求信息，匹配规范';

-- ----------------------------
-- Records of supply_demand_relation_matching
-- ----------------------------
INSERT INTO `supply_demand_relation_matching` VALUES ('51314d5a1f5e4b36a7c9f47050bcc328', '222', '上市时间,发货地和收货地,供求数量', '针对求购商规则', 'demand', '12', '1', '2015-12-30 17:14:51', '2015-12-30 17:14:51', '1', '1');
INSERT INTO `supply_demand_relation_matching` VALUES ('8819ff9ba33f496ab805d85596993487', '1111', '上市时间,发货地和收货地,供求数量', '针对供应商规则', 'supply', '212', '1', '2015-12-30 17:14:27', '2015-12-30 17:14:27', '1', '1');
