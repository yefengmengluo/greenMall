SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS security_user_token;
DROP TABLE IF EXISTS security_user;
DROP TABLE IF EXISTS security_user_role;
DROP TABLE IF EXISTS security_role;
DROP TABLE IF EXISTS security_permission_tree;

/* Create Tables */


CREATE TABLE security_user_token
(
/* 自定义字段 */
	id varchar(64) NOT NULL COMMENT '信息编号',
	unique_user varchar(64) COMMENT '关联用户',
	login_name varchar(64) COMMENT '用户名',
	login_type varchar(64) COMMENT '登陆类型',
	password varchar(255) COMMENT '密码',
	login_ip varchar(100) COMMENT '最后登陆IP',
	login_date datetime COMMENT '最后登陆时间',
	login_flag varchar(64) COMMENT '是否可登录',
/* 公共字段 */
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记,推送成功后可以置为删除',
	PRIMARY KEY (id)
) COMMENT = '登陆用户';



CREATE TABLE security_user
(
	id varchar(64) NOT NULL COMMENT '编号',
	user_type char(1) COMMENT '用户类型',
	roles text DEFAULT '' COMMENT '角色id,逗号隔开',
/* 公共字段 */
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' COMMENT '删除标记,推送成功后可以置为删除',
	PRIMARY KEY (id)
) COMMENT = '用户表';


/*
CREATE TABLE security_user_role
(
	user_id varchar(64) NOT NULL COMMENT '用户编号',
	roles text NOT NULL COMMENT '角色id,逗号隔开',
	PRIMARY KEY (user_id, role_id)
) COMMENT = '用户-角色集合';
*/


CREATE TABLE security_role
(
	id varchar(64) NOT NULL COMMENT '编号',
	name varchar(100) NOT NULL COMMENT '角色名称',
	enname varchar(255) COMMENT '英文名称',
	role_type varchar(255) COMMENT '角色类型',
	data_scope char(1) COMMENT '数据范围',
	is_sys varchar(64) COMMENT '是否系统数据',
	useable varchar(64) COMMENT '是否可用',
	permission varchar(200) COMMENT '权限标识',
/* 公共字段 */
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记,推送成功后可以置为删除',
	PRIMARY KEY (id)
) COMMENT = '角色表';


/*
CREATE TABLE security_permission_tree
(
	id varchar(64) NOT NULL COMMENT '编号',
	parent_id varchar(64) NOT NULL COMMENT '父级编号',
	parent_ids varchar(2000) NOT NULL COMMENT '所有父级编号',
	name varchar(100) NOT NULL COMMENT '名称',
	sort decimal(10,0) NOT NULL COMMENT '排序',
	href varchar(2000) COMMENT '链接',
	target varchar(20) COMMENT '目标',
	icon varchar(100) COMMENT '图标',
	is_show char(1) NOT NULL COMMENT '是否在菜单中显示',
	permission varchar(200) COMMENT '权限标识',

	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记,推送成功后可以置为删除',
	PRIMARY KEY (id)
) COMMENT = '权限树表';

*/

/* Create Indexes */

CREATE INDEX security_user_token_unique_user ON security_user_token (unique_user ASC);


/*CREATE INDEX test_data_parent_ids ON test_tree (parent_ids ASC);*/



