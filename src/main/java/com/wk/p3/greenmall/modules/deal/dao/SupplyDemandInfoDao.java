/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.deal.dao;

import java.util.List;

import com.wk.p3.greenmall.common.persistence.CrudDao;
import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.deal.entity.SupplyDemandInfo;

/**
 * 业务订单DAO接口
 * @author lf
 * @version 2015-12-25
 */
@MyBatisDao
public interface SupplyDemandInfoDao extends CrudDao<SupplyDemandInfo> {
	//新增查询供求信息接口
	public List<SupplyDemandInfo> querySupplyInfo(SupplyDemandInfo supplyDemandInfo);
	public List<SupplyDemandInfo> queryDemandInfo(SupplyDemandInfo supplyDemandInfo);
}