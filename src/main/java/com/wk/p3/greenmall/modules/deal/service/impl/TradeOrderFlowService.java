/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.deal.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.modules.deal.entity.TradeOrderFlow;
import com.wk.p3.greenmall.modules.deal.dao.TradeOrderFlowDao;

/**
 * 业务订单Service
 * @author lf
 * @version 2015-12-15
 */
@Service
@Transactional(readOnly = true)
public class TradeOrderFlowService extends CrudService<TradeOrderFlowDao, TradeOrderFlow> {

	public TradeOrderFlow get(String id) {
		return super.get(id);
	}
	
	public List<TradeOrderFlow> findList(TradeOrderFlow tradeOrderFlow) {
		return super.findList(tradeOrderFlow);
	}
	
	public Page<TradeOrderFlow> findPage(Page<TradeOrderFlow> page, TradeOrderFlow tradeOrderFlow) {
		return super.findPage(page, tradeOrderFlow);
	}
	
	@Transactional(readOnly = false)
	public void save(TradeOrderFlow tradeOrderFlow) {
		super.save(tradeOrderFlow);
	}
	
	@Transactional(readOnly = false)
	public void delete(TradeOrderFlow tradeOrderFlow) {
		super.delete(tradeOrderFlow);
	}
	
}