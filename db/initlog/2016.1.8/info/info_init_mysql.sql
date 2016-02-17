ALTER TABLE `goods_spec_value`
CHANGE COLUMN `gvalue` `val`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL AFTER `spec_id`;


-- 单位转换 以及维护表整理
SET SESSION FOREIGN_KEY_CHECKS=0;
drop table if exists supply_demand_unit;

/*==============================================================*/
/* Table: supply_demand_unit                                    */
/*==============================================================*/
create table supply_demand_unit
(
   id                   int(10) unsigned not null auto_increment,
   name                 varchar(255) character set utf8 not null comment '单位名字',
   code                 varchar(255) character set utf8 not null comment '单位标示',
   create_date          datetime not null comment '创建时间',
   update_date          datetime not null comment '更新时间',
   create_user          varchar(64) character set utf8 not null,
   update_user          varchar(64) character set utf8 not null,
   del_flag             varchar(1) character set utf8 not null default '0',
   primary key (id)
);
ALTER TABLE `supply_demand_unit`
ADD UNIQUE INDEX `uk_code` (`code`) USING BTREE ;
alter table supply_demand_unit comment '所有单位记录表';


drop table if exists supply_demand_unit_relation;

/*==============================================================*/
/* Table: supply_demand_unit_relation                           */
/*==============================================================*/
create table supply_demand_unit_relation
(
   id                   int(10) unsigned not null,
   multiplier           double(10,2) not null comment '乘数（from_unit*multiplier=to_unit）',
   from_unit_name       varchar(255) character set utf8 not null comment '起始单位',
   to_unit_name         varchar(255) character set utf8 not null comment '转化成的单位',
   from_unit_code       varchar(255) character set utf8 not null,
   to_unit_code         varchar(255) character set utf8 not null,
   create_date          datetime not null,
   update_date          datetime not null,
   create_user          varchar(64) character set utf8 not null,
   update_user          varchar(64) character set utf8 not null,
   del_flag             varchar(1) character set utf8 not null default '0',
   primary key (id)
);

alter table supply_demand_unit_relation comment '单位之间的转换关系表';

drop table if exists supply_demand_unit_category;

/*==============================================================*/
/* Table: supply_demand_unit_category                           */
/*==============================================================*/
create table supply_demand_unit_category
(
   id                   int(10) unsigned not null,
   gcategory_id         int(10) unsigned not null comment '产品id',
   unit_code            national varchar(255) not null comment '单位 标示',
   create_date          datetime not null,
   update_date          datetime not null,
   create_by            national varchar(64) not null,
   update_by            national varchar(64) not null,
   order_item           int(10) unsigned,
   is_default           int(1) not null comment '是否是类型的默认单位(默认0：否，1：是。一个类型只有一个默认的单位，用于统计报表时，单位的统一)',
   statue               int(1)  not null comment '标示状态（0：可标准化的单位，-1：不可标准化的单位）',
   unit_id              int(10) unsigned not null comment '单位id',
   primary key (id)
);

alter table supply_demand_unit_category comment '供求单位码表（与产品有关系）';
ALTER TABLE `supply_demand_unit_category`
ADD COLUMN `unit_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位名称' AFTER `gcategory_id`;

-- 供求表增加非标准化单位
ALTER TABLE `supply_demand_info`
MODIFY COLUMN `number_unit_value`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标准性数量单元' AFTER `price_unit_value`,
ADD COLUMN `nonstandard_number_unit_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '非标准性单位' AFTER `price_unit_id`;
ALTER TABLE `supply_demand_info`
ADD COLUMN `nonstandard_number_unit_id`  int(10) UNSIGNED NULL COMMENT '非标准化单位id' AFTER `price_unit_id`;
ALTER TABLE `supply_demand_info`
ADD COLUMN `nonstandard_number`  int(10) NULL COMMENT '非标准性单位的数量' AFTER `price_unit_id`;

ALTER TABLE `supply_demand_info_check`
MODIFY COLUMN `number_unit_value`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标准性数量单元' AFTER `price_unit_value`,
ADD COLUMN `nonstandard_number_unit_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '非标准性单位' AFTER `price_unit_id`;
ALTER TABLE `supply_demand_info_check`
ADD COLUMN `nonstandard_number_unit_id`  int(10) UNSIGNED NULL COMMENT '非标准化单位id' AFTER `price_unit_id`;
ALTER TABLE `supply_demand_info_check`
ADD COLUMN `nonstandard_number`  int(10) NULL COMMENT '非标准性单位的数量' AFTER `price_unit_id`;


-- 2015-12-28
-- info关联规格增加删除字段
ALTER TABLE `supply_demand_spec`
ADD COLUMN `del_flag`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 0 AFTER `spec_value_id`;

--2015-12--29
-- 规格表建立唯一索引 字段spec_value_id非空
ALTER TABLE `supply_demand_spec`
ADD UNIQUE INDEX `fk_supply_demand_id_spec_value_id` (`supply_demand_id`, `spec_value_id`) USING BTREE ;
ALTER TABLE `supply_demand_spec`
MODIFY COLUMN `spec_value_id`  int(10) UNSIGNED NOT NULL AFTER `spec_id`;
-- 增加人员和时间的记录
ALTER TABLE `supply_demand_spec`
ADD COLUMN `update_date`  datetime NULL AFTER `del_flag`,
ADD COLUMN `create_date`  datetime NULL AFTER `update_date`,
ADD COLUMN `update_by`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL AFTER `create_date`,
ADD COLUMN `create_by`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL AFTER `update_by`;

ALTER TABLE `supply_demand_info`
MODIFY COLUMN `nonstandard_number`  double(10,0) NULL DEFAULT NULL COMMENT '非标准性单位的数量' AFTER `price_unit_id`;


ALTER TABLE `supply_demand_info_check`
MODIFY COLUMN `nonstandard_number`  double(10,0) NULL DEFAULT NULL COMMENT '非标准性单位的数量' AFTER `price_unit_id`;

-- 商品类别 标签 中间表（通过关联到数据字典表）


-- 商品类别code唯一
ALTER TABLE `goods_gcategory`
DROP PRIMARY KEY,
ADD PRIMARY KEY (`id`, `code`);


-- 2016-1-7
-- 单位表增加是否标准化单位的状态标示
ALTER TABLE `supply_demand_unit`
ADD COLUMN `statue`  int(1) NOT NULL COMMENT '标示状态（0：可标准化的单位，-1：不可标准化的单位）' AFTER `del_flag`;
ALTER TABLE `supply_demand_unit`
CHANGE COLUMN `create_user` `create_by`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL AFTER `update_date`,
CHANGE COLUMN `update_user` `update_by`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL AFTER `create_by`;
ALTER TABLE `supply_demand_unit_relation`
CHANGE COLUMN `create_user` `create_by`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL AFTER `update_date`,
CHANGE COLUMN `update_user` `update_by`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL AFTER `create_by`;
ALTER TABLE `supply_demand_unit_category`
MODIFY COLUMN `is_default`  int(1) NOT NULL DEFAULT 0 COMMENT '是否是类型的默认单位(默认0：否，1：是。一个类型只有一个默认的单位，用于统计报表时，单位的统一)' AFTER `order_item`;
ALTER TABLE `supply_demand_unit_category`
MODIFY COLUMN `id`  int(10) UNSIGNED NOT NULL AUTO_INCREMENT FIRST ;
ALTER TABLE `supply_demand_unit_category`
ADD COLUMN `del_flag`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' AFTER `unit_id`;

ALTER TABLE `supply_demand_unit_relation`
MODIFY COLUMN `id`  int(10) UNSIGNED NOT NULL AUTO_INCREMENT FIRST ;
ALTER TABLE `supply_demand_unit_relation`
MODIFY COLUMN `multiplier`  double(20,10) NOT NULL COMMENT '乘数（from_unit*multiplier=to_unit）' AFTER `id`;

ALTER TABLE `supply_demand_info`
MODIFY COLUMN `number`  double(20,10) NULL DEFAULT NULL COMMENT '数量' AFTER `number_unit_value`;
