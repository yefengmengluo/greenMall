package com.wk.p3.greenmall.modules.deal.dao;

import java.util.List;

import com.wk.p3.greenmall.common.persistence.CrudDao;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.deal.entity.Details;
import com.wk.p3.greenmall.modules.deal.entity.SupplyDemandInfo;
import com.wk.p3.greenmall.modules.deal.entity.TradeOrderSupplyDemand;
import com.wk.p3.greenmall.modules.deal.entity.Unit;

/**
 * 业务订单DAO接口
 * @author lf
 * @version 2015-12-15
 */
@MyBatisDao
public interface TradeOrderSupplyDemandDao extends CrudDao<TradeOrderSupplyDemand> {
	public List<Details> getDetails(Details Details);
	public void deleteJhds(TradeOrderSupplyDemand tradeOrderSupplyDemand);
	/**
	 * 返回购买具体产品的信息
	 * @param orderId
	 * @return
	 */
	public TradeOrderSupplyDemand findSuppleyDemandByOrderId(String orderId);
	/**
	 * 返回对应的采购单信息和供应单信息
	 * @param supplyDemandId
	 * @return
	 */
	public SupplyDemandInfo findSupplyDemandInfo(TradeOrderSupplyDemand supplyDemandId);
	
	/**
	 * 根据这些参数到category表中查询对应的单位unit_code
	 * @param pGoodsId
	 * @param price_unit_id
	 * @param number_unit_id
	 * @return
	 */
	public Unit unitCateGory(Unit unit);
	
	/**
	 * 根据这些参数来查询查询计算
	 * @param from_unit_code
	 * @param to_unit_code
	 * @return
	 */
	public String unitRelation(String fromUnitCode,String toUnitCode);
}