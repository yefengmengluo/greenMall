/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.security.entity;

import com.wk.p3.greenmall.modules.security.Role;
import org.hibernate.validator.constraints.Length;

import com.wk.p3.greenmall.common.persistence.DataEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 与securityUser用户关联，保存用户权限Entity
 * @author cc
 * @version 2016-01-21
 */
public class SecurityRole extends DataEntity<SecurityRole> implements Role {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 角色名称
	private String enname;		// 英文名称
	private String roleType;		// 角色类型
	private String dataScope;		// 数据范围
	private String isSys;		// 是否系统数据
	private String useable;		// 是否可用
	private String permission;		// 权限标识
	
	public SecurityRole() {
		super();
	}

	public SecurityRole(String id){
		super(id);
	}

	@Length(min=1, max=100, message="角色名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="英文名称长度必须介于 0 和 255 之间")
	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}
	
	@Length(min=0, max=255, message="角色类型长度必须介于 0 和 255 之间")
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	
	@Length(min=0, max=1, message="数据范围长度必须介于 0 和 1 之间")
	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}
	
	@Length(min=0, max=64, message="是否系统数据长度必须介于 0 和 64 之间")
	public String getIsSys() {
		return isSys;
	}

	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}
	
	@Length(min=0, max=64, message="是否可用长度必须介于 0 和 64 之间")
	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}
	
	@Length(min=0, max=200, message="权限标识长度必须介于 0 和 200 之间")
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Override
	public List<String> getPermissions() {
		List list  = new ArrayList();
		for(String p :permission.split(",")){
			list.add(p);
		}
		return list;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SecurityRole)) return false;
		if (!super.equals(o)) return false;
		SecurityRole that = (SecurityRole) o;
		return !(getId() != null ? !getId().equals(that.getId()) : that.getId() != null);
	}

	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : 0;
	}
}