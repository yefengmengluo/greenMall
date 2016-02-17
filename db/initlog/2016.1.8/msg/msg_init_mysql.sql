/* Drop Tables */

DROP TABLE IF EXISTS msg_template;
DROP TABLE IF EXISTS msg_sms;
DROP TABLE IF EXISTS msg_user;

/* 消息发送的模板 */
CREATE TABLE msg_template
(
/* 自定义字段 */
	id varchar(64) NOT NULL COMMENT '模板编号',
	plateform varchar(64) COMMENT '模板使用平台',
	title varchar(100) COMMENT '模板名称',
	content varchar(255) COMMENT '模板内容',
	code varchar(64) COMMENT '业务类型编号',
/* 公共字段 */
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '模板类型';


/* 短信消息 */
CREATE TABLE msg_sms
(
/* 自定义字段 */
	id varchar(64) NOT NULL COMMENT '模板编号',
	mid varchar(64) NULL COMMENT '模板编号',
	tele TINYTEXT COMMENT '发送目标手机号，多个用逗号分开',
	msg varchar(100) COMMENT '短信内容',
	extno varchar(8) COMMENT '参数等于1时拆分短信',
	result TINYTEXT COMMENT '发送结果',
/* 公共字段 */
	create_by varchar(64)  NULL COMMENT '创建者',
	create_date datetime  NULL COMMENT '创建时间',
	update_by varchar(64)  NULL COMMENT '更新者',
	update_date datetime  NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '短信消息';



/* 短信消息 */
CREATE TABLE msg_user
(
/* 自定义字段 */
	id varchar(64) NOT NULL COMMENT '模板编号',
	user_id varchar(64) COMMENT '关联用户',
	plateform varchar(64) COMMENT '模板使用平台',
	plateformId varchar(64) COMMENT '平台id，比如短信平台为手机号，微信公众号为openId',
	restriction varchar(255) COMMENT '用户发送限制条件比如：周期发，定时发，延时发等配置',
/* 公共字段 */
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '短信消息';


/* Create Indexes */
CREATE INDEX msg_sms_mid ON msg_sms (mid ASC);
CREATE INDEX msg_user_user_id ON msg_user (user_id ASC);