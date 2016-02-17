package com.wk.p3.greenmall.modules.user.entity;
import org.hibernate.validator.constraints.Length;

import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * 
 * @author zhaomeng
 * @version 2015-12-28
 */
public class OrganizationMainGoods extends DataEntity<OrganizationMainGoods> {
	
	private static final long serialVersionUID = 1L;
	private String goodsType;		// 主营产品(数据字典)
	private String organizationId;		// organization_id
	
	public OrganizationMainGoods() {
		super();
	}

	public OrganizationMainGoods(String id){
		super(id);
	}

	@Length(min=0, max=10, message="主营产品(数据字典)长度必须介于 0 和 10 之间")
	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	
	@Length(min=0, max=64, message="organization_id长度必须介于 0 和 64 之间")
	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	
}