SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS mp_information;
DROP TABLE IF EXISTS mp_user;
DROP TABLE IF EXISTS mp_message;

/* Create Tables */

CREATE TABLE mp_message
(
/* 自定义字段 */
	id varchar(64) NOT NULL COMMENT '信息编号',
	user_id varchar(64) COMMENT '关联用户',
	info_type varchar(64) COMMENT '信息类型',
	title varchar(100) COMMENT '信息名称',
	content varchar(255) COMMENT '信息内容',
	show_date datetime COMMENT '显示日期',
	send_date datetime COMMENT '推送时间',
	send_flag char(1) DEFAULT '0' NOT NULL COMMENT '是否允许推送',
/* 公共字段 */
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记,推送成功后可以置为删除',
	PRIMARY KEY (id)
) COMMENT = '推送给用户的消息';




CREATE TABLE mp_information
(
/* 自定义字段 */
	id varchar(64) NOT NULL COMMENT '信息编号',
	user_id varchar(64) COMMENT '关联用户',
	info_type varchar(64) COMMENT '信息类型',
	title varchar(100) COMMENT '信息名称',
	content varchar(255) COMMENT '信息内容',
	show_date datetime COMMENT '显示日期',
/* 公共字段 */
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '用户发布的信息';



CREATE TABLE mp_user
(
/* 自定义字段 */
	id 								varchar(64) 		NOT NULL 	COMMENT '编号',
	user_id varchar(64) 		COMMENT '业务主表ID',
	name 							varchar(100) 		COMMENT '名称',
  subscribe 				varchar(8) 				COMMENT '是否订阅',
  openId 						varchar(64) 		COMMENT 'openId',
  unionId 					varchar(64) 		COMMENT 'unionId',
  nickname 					varchar(64) 		COMMENT '昵称',
  sex 							varchar(64) 		COMMENT '性别',
  language 					varchar(64) 		COMMENT '语言',
  city 							varchar(64) 		COMMENT '城市',
  province 					varchar(64) 		COMMENT '省',
  country 					varchar(64) 		COMMENT '国家',
  headImgUrl 				varchar(300) 		COMMENT '图像URL',
  remark 						varchar(100) 		COMMENT '标记',
  subscribeTime 		varchar(32) 				COMMENT '订阅时间',
  sexId 						varchar(32) 				COMMENT '性别id',
  groupId 					varchar(64) 				COMMENT '分组Id',
/* 公共字段 */
	create_by varchar(64)  COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64)  COMMENT '更新者',
	update_date datetime  COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '微信用户';


/* Create Indexes */

CREATE INDEX mp_user_openId ON mp_user (openId ASC);
CREATE INDEX information_user_id ON mp_information (user_id ASC);
CREATE INDEX message_user_id ON mp_message (user_id ASC);

/*CREATE INDEX test_data_parent_ids ON test_tree (parent_ids ASC);*/



