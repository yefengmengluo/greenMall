/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.match.entity;

import org.hibernate.validator.constraints.Length;

import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * 匹配规范Entity
 * @author zhaomeng
 * @version 2015-12-23
 */
public class SupplyDemandRelationMatching extends DataEntity<SupplyDemandRelationMatching> {
	
	private static final long serialVersionUID = 1L;
	private String relationName;		// relation_name
	private String relationRemark;		// relation_remark
	private String matchingObject;		// matching_object
	private String sortOrder;		// sort_order
	private String ifMatching;		// if_matching
	private Integer isEntrust;		// isEntrust
	

	public Integer getIsEntrust() {
		return isEntrust;
	}

	public void setIsEntrust(Integer isEntrust) {
		this.isEntrust = isEntrust;
	}

	public SupplyDemandRelationMatching() {
		super();
	}

	public SupplyDemandRelationMatching(String id){
		super(id);
	}

	@Length(min=0, max=255, message="relation_name长度必须介于 0 和 255 之间")
	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}
	
	@Length(min=0, max=255, message="relation_remark长度必须介于 0 和 255 之间")
	public String getRelationRemark() {
		return relationRemark;
	}

	public void setRelationRemark(String relationRemark) {
		this.relationRemark = relationRemark;
	}
	
	@Length(min=0, max=255, message="matching_object长度必须介于 0 和 255 之间")
	public String getMatchingObject() {
		return matchingObject;
	}

	public void setMatchingObject(String matchingObject) {
		this.matchingObject = matchingObject;
	}
	
	@Length(min=0, max=11, message="sort_order长度必须介于 0 和 11 之间")
	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	@Length(min=0, max=4, message="if_matching长度必须介于 0 和 4 之间")
	public String getIfMatching() {
		return ifMatching;
	}

	public void setIfMatching(String ifMatching) {
		this.ifMatching = ifMatching;
	}
	
}