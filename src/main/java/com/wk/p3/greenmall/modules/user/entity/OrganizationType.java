package com.wk.p3.greenmall.modules.user.entity;

import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * 企业类型实体类
 * @author zhaomeng
 * @description 
 * 2015年12月31日
 */
public class OrganizationType extends DataEntity<OrganizationType>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
