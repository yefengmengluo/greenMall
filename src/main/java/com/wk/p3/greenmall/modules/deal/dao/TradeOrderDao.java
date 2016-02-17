package com.wk.p3.greenmall.modules.deal.dao;

import java.util.List;

import com.wk.p3.greenmall.common.persistence.CrudDao;
import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.deal.entity.Details;
import com.wk.p3.greenmall.modules.deal.entity.InfoVO;
import com.wk.p3.greenmall.modules.deal.entity.OrderData;
import com.wk.p3.greenmall.modules.deal.entity.OrderItem;
import com.wk.p3.greenmall.modules.deal.entity.SupplyDemandInfo;
import com.wk.p3.greenmall.modules.deal.entity.TradeOrder;
import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.user.entity.UserAddressInfo;

/**
 * 业务订单DAO接口
 * @author lf
 * @version 2015-12-15
 */
@MyBatisDao
public interface TradeOrderDao extends CrudDao<TradeOrder> {
	public TradeOrder getOrderByOrderId(String id);//根据订单编号获取订单记录
	public List<Details> getDetails(Details details);//获取订单采购单详情
	public List<TradeOrder> getStatus(TradeOrder tradeOrder);//获取订单的流水状态列表
	public void addOrderFlow(TradeOrder tradeOrder);//保存订单的流水
	public void updateData(TradeOrder tradeOrder);
	public List<OrderData> queryOrder(String pids);
	
	//返回订单详情数据
	public OrderItem findOrderSupplyDetails(String orderId);
	public OrderItem findOrderDemandDetails(String orderId);
	public List<SupplyDemandInfo> findSupplyInfo(String orderId);
	public User supplyDemandUserInfo(String id);
	
	//新增接口
	public InfoVO returnSupplyInfo(String orderid);//返回卖家信息
	public InfoVO returnDemandInfo(String orderid);//返回买家信息
	public UserAddressInfo returnDemandAddress(String orderid);//返回收货地址
	public UserAddressInfo returnSupplyAddress(String orderid);//返回发货地址
	public List<TradeOrder> getOrderIng(TradeOrder entity);//获取处在进行时的订单列表即 待跟踪的列表

	
	
}