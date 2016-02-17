/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.security.entity;

import com.wk.p3.greenmall.modules.security.Person;
import com.wk.p3.greenmall.modules.security.Role;
import org.hibernate.validator.constraints.Length;

import com.wk.p3.greenmall.common.persistence.DataEntity;

import java.util.HashSet;
import java.util.Set;

/**
 * 终端用户抽象，多登陆用户对应一个用户Entity
 * @author cc
 * @version 2016-01-21
 */
public class SecurityUser extends DataEntity<SecurityUser> implements Person {
	
	private static final long serialVersionUID = 1L;
	private String userType;		// 用户类型
	private String roles;		// 角色id,逗号隔开
	private Set<Role> roleSet;
	
	public SecurityUser() {
		super();
	}

	public SecurityUser(String id){
		super(id);
	}

	@Length(min=0, max=1, message="用户类型长度必须介于 0 和 1 之间")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roleSet = roles;
	}

	@Override
	public Set<Role> getRoleSet() {
		return roleSet;
	}
}