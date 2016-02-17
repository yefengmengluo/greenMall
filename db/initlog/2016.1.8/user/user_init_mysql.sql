
/* 2015-12-29 */
/* 用户地址新增人员记录*/
ALTER TABLE `user_address_info`
MODIFY COLUMN `update_by`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新者' AFTER `telephone`,
ADD COLUMN `create_by`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL AFTER `telephone`;

ALTER TABLE `user_address_info`
ADD COLUMN `create_date`  datetime NOT NULL AFTER `area_name`,
ADD COLUMN `update_date`  datetime NOT NULL AFTER `create_date`;

/* 2015-12-29 */
alter table `sys_user` add column organization_id  national varchar(64) comment '所属机构id';
ALTER TABLE `sys_user`  ADD COLUMN `faxnumber` VARCHAR(255) NULL ;
ALTER TABLE `sys_user`  ADD COLUMN `qq` VARCHAR(255) NULL ;




