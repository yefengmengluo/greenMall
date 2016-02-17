/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.msg.entity;

import com.wk.p3.greenmall.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * 用户配置管理Entity
 * @author cc
 * @version 2015-12-26
 */
public class MsgUser extends DataEntity<MsgUser> {
	
	private static final long serialVersionUID = 1L;
	private User user;				// 关联用户
	private String plateform;		// 模板使用平台
	private String plateformid;		// 平台id，比如短信平台为手机号，微信公众号为openId
	private String restriction;		// 用户发送限制条件比如：周期发，定时发，延时发等配置
	
	public MsgUser() {
		super();
	}

	public MsgUser(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=64, message="模板使用平台长度必须介于 0 和 64 之间")
	public String getPlateform() {
		return plateform;
	}

	public void setPlateform(String plateform) {
		this.plateform = plateform;
	}
	
	@Length(min=0, max=64, message="平台id，比如短信平台为手机号，微信公众号为openId长度必须介于 0 和 64 之间")
	public String getPlateformid() {
		return plateformid;
	}

	public void setPlateformid(String plateformid) {
		this.plateformid = plateformid;
	}
	
	@Length(min=0, max=255, message="用户发送限制条件比如：周期发，定时发，延时发等配置长度必须介于 0 和 255 之间")
	public String getRestriction() {
		return restriction;
	}

	public void setRestriction(String restriction) {
		this.restriction = restriction;
	}
	
}