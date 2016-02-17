
/* Drop Tables */

DROP TABLE IF EXISTS user_relation;

/* Create Tables */


CREATE TABLE user_relation
(
/* 自定义字段 */
	id varchar(64) NOT NULL COMMENT '信息编号',
	the_user varchar(64) COMMENT '用户',
	other_user varchar(64) COMMENT '关联用户',
	relation_type varchar(64) COMMENT '关联类型',
	/* 公共字段 */
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '用户关联表';



/* Create Indexes */

CREATE INDEX user_relation_current_user ON user_relation (the_user ASC);
CREATE INDEX user_relation_relation_user ON user_relation (other_user ASC);


/*CREATE INDEX test_data_parent_ids ON test_tree (parent_ids ASC);*/



