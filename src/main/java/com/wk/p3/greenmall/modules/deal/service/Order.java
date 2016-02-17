package com.wk.p3.greenmall.modules.deal.service;

import java.util.List;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.modules.deal.entity.Details;
import com.wk.p3.greenmall.modules.deal.entity.TradeOrder;

public interface Order {
	public TradeOrder getOrder(TradeOrder t) throws Exception;//返回单挑数据	 Key是参数的名称，Value是参数的值
	public List<TradeOrder> resultList(TradeOrder t,String count) throws Exception;//返回多条数据    Key是参数的名称，Value是参数的值
	public Page<Details> getDetails(Page<Details> page,Details Details);


}
