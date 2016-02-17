/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.deal.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.modules.deal.entity.SupplyDemandInfo;
import com.wk.p3.greenmall.modules.deal.dao.SupplyDemandInfoDao;

/**
 * 业务订单Service
 * @author lf
 * @version 2015-12-25
 */
@Service
@Transactional(readOnly = true)
public class SupplyDemandInfoService extends CrudService<SupplyDemandInfoDao, SupplyDemandInfo> {

	public SupplyDemandInfo get(String id) {
		return super.get(id);
	}
	
	public List<SupplyDemandInfo> findList(SupplyDemandInfo supplyDemandInfo) {
		return super.findList(supplyDemandInfo);
	}
	
	public Page<SupplyDemandInfo> findPage(Page<SupplyDemandInfo> page, SupplyDemandInfo supplyDemandInfo) {
		return super.findPage(page, supplyDemandInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(SupplyDemandInfo supplyDemandInfo) {
		super.save(supplyDemandInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(SupplyDemandInfo supplyDemandInfo) {
		super.delete(supplyDemandInfo);
	}
	
}