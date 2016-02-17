/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.user.entity;

import org.hibernate.validator.constraints.Length;

import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * 用户之间的关系Entity
 * @author cc
 * @version 2016-01-05
 */
public class UserRelation extends DataEntity<UserRelation> {
	
	private static final long serialVersionUID = 1L;
	private String theUser;		// 用户
	private String otherUser;		// 关联用户
	private String relationType;		// 关联类型
	
	public UserRelation() {
		super();
	}

	public UserRelation(String id){
		super(id);
	}

	@Length(min=0, max=64, message="用户长度必须介于 0 和 64 之间")
	public String getTheUser() {
		return theUser;
	}

	public void setTheUser(String theUser) {
		this.theUser = theUser;
	}
	
	@Length(min=0, max=64, message="关联用户长度必须介于 0 和 64 之间")
	public String getOtherUser() {
		return otherUser;
	}

	public void setOtherUser(String otherUser) {
		this.otherUser = otherUser;
	}
	
	@Length(min=0, max=64, message="关联类型长度必须介于 0 和 64 之间")
	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	
}