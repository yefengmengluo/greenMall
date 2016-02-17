SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS user_sole;
DROP TABLE IF EXISTS user_token;

/* Create Tables */

CREATE TABLE user_sole
(
/* 自定义字段 */
	id varchar(64) NOT NULL COMMENT '信息编号',
	principal varchar(64) COMMENT '关联用户',
/* 公共字段 */
  PRIMARY KEY (id)
) COMMENT = '每个登陆用户对应的唯一用户';


CREATE TABLE user_token
(
/* 自定义字段 */
	id varchar(64) NOT NULL COMMENT '信息编号',
	unique_user varchar(64) COMMENT '关联用户',
	login_name varchar(64) COMMENT '用户名',
	login_type varchar(64) COMMENT '登陆类型',
	password varchar(255) COMMENT '密码',
	login_flag datetime COMMENT '登陆标示',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
/* 公共字段 */
	PRIMARY KEY (id)
) COMMENT = '登陆用户';





/* Create Indexes */

CREATE INDEX user_sole_user_id ON user_sole (principal ASC);
CREATE INDEX user_token_unique_user ON user_token (unique_user ASC);


/*CREATE INDEX test_data_parent_ids ON test_tree (parent_ids ASC);*/



