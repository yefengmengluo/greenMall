ALTER TABLE `supply_demand_info_check`
ADD COLUMN `nonstandard_number_unit`  int(10) NULL DEFAULT NULL AFTER `record_date`;

-- 纯信息化的供求

-- ----------------------------
-- Table structure for supply_demand_info_message
-- ----------------------------
DROP TABLE IF EXISTS `supply_demand_info_message`;
CREATE TABLE `supply_demand_info_message` (
  `id` varchar(64) NOT NULL,
  `message` text NOT NULL,
  `create_by` varchar(64) NOT NULL,
  `update_by` varchar(64) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `statue` int(1) NOT NULL DEFAULT '0' COMMENT '状态(默认0：待审核，1：已审核通过，-1：审核未通过，-2：删除)',
  `remarks` varchar(500) DEFAULT NULL,
  `supply_demand_id` varchar(64) DEFAULT NULL,
  `type` varchar(32) NOT NULL COMMENT 'supply,demand',
  `statue_intro` text COMMENT '审核说明',
  `is_entrust` int(1) unsigned DEFAULT '0' COMMENT '是否是委托（默认0：否，1：是）',
   entrust_supply_demand_id varchar(64) character set utf8 comment '委托的供求信息id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='纯信息化的供求信息表';

-- 删除一些必填项
ALTER TABLE `supply_demand_info`
MODIFY COLUMN `create_by`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL AFTER `update_date`,
MODIFY COLUMN `update_by`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL AFTER `create_by`,
MODIFY COLUMN `publish_user`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '消息所属人员id' AFTER `order_uploading_id`;

ALTER TABLE `user_address_info`
MODIFY COLUMN `create_by`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL AFTER `telephone`,
MODIFY COLUMN `update_by`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '更新者' AFTER `create_by`;

ALTER TABLE `organization_info`
MODIFY COLUMN `create_by`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL AFTER `update_date`,
MODIFY COLUMN `update_by`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL AFTER `create_by`;


-- 供求关系表
DROP TABLE IF EXISTS `supply_demand_relation`;
CREATE TABLE `supply_demand_relation` (
  `id` varchar(64) NOT NULL,
  `from_info_id` varchar(64) NOT NULL COMMENT '关系发起方id',
  `to_info_id` varchar(64) NOT NULL COMMENT '关系接收方id',
  `relation` int(10) NOT NULL COMMENT '0：意向，1:委托',
  `to_statue` int(1) DEFAULT NULL COMMENT '接收方是否有意向（0：没有，1：有）',
  `from_statue` int(1) DEFAULT NULL COMMENT '发起方是否有意向（0：没有，1：有）',
  `statue` int(1) DEFAULT NULL COMMENT '状态，待撮合 0，撮合中 1，撮合成功 2，撮合失败 -1',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `remarks` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 2016.1.18
-- 供求信息表增加代替发布人字段
ALTER TABLE `supply_demand_info`
ADD COLUMN `replace_publish_user`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '代替发布人' AFTER `order_uploading_id`;


-- 2016.1.23
-- 改变一些必填信息为可为空
ALTER TABLE `supply_demand_info_message`
MODIFY COLUMN `create_by`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL AFTER `message`,
MODIFY COLUMN `update_by`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL AFTER `create_by`;

ALTER TABLE `supply_demand_info_message`
ADD COLUMN `publish_user`  varchar(64) NULL DEFAULT '信息发布者' AFTER `entrust_supply_demand_id`;
ALTER TABLE `supply_demand_info_message`
MODIFY COLUMN `publish_user`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `entrust_supply_demand_id`;

-- ----------------------------
-- Table structure for `browse_log` 2016.2.3
-- ----------------------------
DROP TABLE IF EXISTS `browse_log`;
CREATE TABLE `browse_log` (
  `id` varchar(64) NOT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `map_longitude` varchar(64) DEFAULT NULL,
  `map_latitude` varchar(64) DEFAULT NULL,
  `access_type` varchar(32) DEFAULT NULL,
  `os` int(2) DEFAULT NULL,
  `browser` varchar(64) DEFAULT NULL,
  `target_type` int(4) DEFAULT NULL,
  `target_id` varchar(64) DEFAULT NULL,
  `user_id` varchar(64) DEFAULT NULL,
  `user_type` int(2) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- 2016.2.3
ALTER TABLE `supply_demand_info`
ADD COLUMN `source`  int(1) NULL DEFAULT NULL COMMENT '供求信息产生途径 1.PC前台 2.wx 3.PC后台' AFTER `production_area_name`;

