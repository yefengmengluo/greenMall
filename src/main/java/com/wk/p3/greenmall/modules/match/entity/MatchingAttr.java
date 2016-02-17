/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.match.entity;

import org.hibernate.validator.constraints.Length;

import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * 匹配属性Entity
 * @author zhaomeng
 * @version 2015-12-22
 */
public class MatchingAttr extends DataEntity<MatchingAttr> {
	
	private static final long serialVersionUID = 1L;
	private String matchingName;		// 匹配属性名称
	private String remark;				// 备注
	private String matchingStatus;		// 0是精确匹配、1是模糊匹配、2是范围匹配
	private String matchingTable;		// 匹配的表名
	private String entityName;			// 对应的实体类的属性
	private Integer weight;
	private Integer isEntrust;
	
	public Integer getIsEntrust() {
		return isEntrust;
	}

	public void setIsEntrust(Integer isEntrust) {
		this.isEntrust = isEntrust;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public MatchingAttr() {
		super();
	}

	public MatchingAttr(String id){
		super(id);
	}

	@Length(min=0, max=255, message="匹配属性名称长度必须介于 0 和 255 之间")
	public String getMatchingName() {
		return matchingName;
	}

	public void setMatchingName(String matchingName) {
		this.matchingName = matchingName;
	}
	
	@Length(min=0, max=255, message="remark长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=1, message="0是精确匹配、1是模糊匹配、2是范围匹配长度必须介于 0 和 1 之间")
	public String getMatchingStatus() {
		return matchingStatus;
	}

	public void setMatchingStatus(String matchingStatus) {
		this.matchingStatus = matchingStatus;
	}
	
	@Length(min=0, max=255, message="匹配的表名长度必须介于 0 和 255 之间")
	public String getMatchingTable() {
		return matchingTable;
	}

	public void setMatchingTable(String matchingTable) {
		this.matchingTable = matchingTable;
	}
	
	@Length(min=0, max=255, message="对应的实体类的属性长度必须介于 0 和 255 之间")
	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
}