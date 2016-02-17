package com.wk.p3.greenmall.modules.deal.entity;

import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.user.entity.UserAddressInfo;

/**
 * 订单详情数据
 * @author lf
 */
public class OrderItem {
	private TradeOrder tradeOrder;
	private UserAddressInfo addressInfo;//返回发货人信息	
	private User user;
	private String orderId;// 订单id
    private String addressDelFlag;//标识user_address_info表中的记录是否被删除
    private String userDelFlag;//标识sys_user表中的数据是否被删除
    
	public String getAddressDelFlag() {
		return addressDelFlag;
	}

	public void setAddressDelFlag(String addressDelFlag) {
		this.addressDelFlag = addressDelFlag;
	}

	public String getUserDelFlag() {
		return userDelFlag;
	}

	public void setUserDelFlag(String userDelFlag) {
		this.userDelFlag = userDelFlag;
	}	
	
	public UserAddressInfo getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(UserAddressInfo addressInfo) {
		this.addressInfo = addressInfo;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public TradeOrder getTradeOrder() {
		return tradeOrder;
	}

	public void setTradeOrder(TradeOrder tradeOrder) {
		this.tradeOrder = tradeOrder;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
