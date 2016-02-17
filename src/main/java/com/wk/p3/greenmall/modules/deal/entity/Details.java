package com.wk.p3.greenmall.modules.deal.entity;

import com.wk.p3.greenmall.common.persistence.DataEntity;
import com.wk.p3.greenmall.modules.info.entity.AddressInfo;

public class Details extends DataEntity<Details> {
	private static final long serialVersionUID = 1L;
	private TradeOrderSupplyDemand tradeOrderSupplyDemand;//业务供求信息表即进货单或者是供货单
	private AddressInfo addressInfo;//发货地址或者是收货地址
    private TradeOrder tradeOrder;//订单数据表
    private String supplyid;//采购单编号
    private String demandid;//供应单编号    
    
	public AddressInfo getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(AddressInfo addressInfo) {
		this.addressInfo = addressInfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSupplyid() {
		return supplyid;
	}

	public void setSupplyid(String supplyid) {
		this.supplyid = supplyid;
	}

	public String getDemandid() {
		return demandid;
	}

	public void setDemandid(String demandid) {
		this.demandid = demandid;
	}

	public TradeOrder getTradeOrder() {
		return tradeOrder;
	}

	public void setTradeOrder(TradeOrder tradeOrder) {
		this.tradeOrder = tradeOrder;
	}

	public TradeOrderSupplyDemand getTradeOrderSupplyDemand() {
		return tradeOrderSupplyDemand;
	}

	public void setTradeOrderSupplyDemand(TradeOrderSupplyDemand tradeOrderSupplyDemand) {
		this.tradeOrderSupplyDemand = tradeOrderSupplyDemand;
	}

	private String supplyAddress;//供应商地址
	private String supppyPhone;//供应商电话
	private String supplyName;//供应商名称

	public String getSupplyAddress() {
		return supplyAddress;
	}

	public void setSupplyAddress(String supplyAddress) {
		this.supplyAddress = supplyAddress;
	}

	public String getSupppyPhone() {
		return supppyPhone;
	}

	public void setSupppyPhone(String supppyPhone) {
		this.supppyPhone = supppyPhone;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
}
