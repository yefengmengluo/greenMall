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
drop table if exists supply_demand_relation;

/*==============================================================*/
/* Table: supply_demand_relation                                */
/*==============================================================*/
create table supply_demand_relation
(
   id                   varchar(64) not null,
   from_info_id         varchar(64) character set utf8 not null comment '关系发起方id',
   to_info_id           varchar(64) character set utf8 not null comment '关系接收方id',
   relation             int(10) not null comment '0：意向，1:委托',
   statue               int(1) comment '默认0：正常，-1：无意向，-2：删除,1:有意向',
   create_date          datetime,
   update_date          datetime,
   create_by            varchar(64) character set utf8,
   update_by            varchar(64) character set utf8,
   remarks              text character set utf8,
   primary key (id)
);

alter table supply_demand_relation comment '供求关系表';


-- 2016.1.18
-- 供求信息表增加代替发布人字段
ALTER TABLE `supply_demand_info`
ADD COLUMN `replace_publish_user`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '代替发布人' AFTER `order_uploading_id`;