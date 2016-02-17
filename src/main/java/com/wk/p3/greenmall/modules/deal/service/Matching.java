package com.wk.p3.greenmall.modules.deal.service;

import java.util.List;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.modules.deal.entity.Match;
import com.wk.p3.greenmall.modules.deal.entity.TradeOrder;
import com.wk.p3.greenmall.modules.deal.entity.TradeOrderSupplyDemand;

public interface Matching {
	/**
	 * 匹配接口，根据采购单id或者供应单id，返回采购详情和供应详情
	 * @return
	 */
	public List<Page<?>> resultDetails(TradeOrderSupplyDemand tradeOrderSupplyDemand);
}
