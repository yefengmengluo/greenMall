package com.wk.p3.greenmall.modules.deal.entity;

import java.util.Date;

import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * 业务订单Entity
 * @author lf
 * @version 2015-12-15
 */
public class TradeOrderSupplyDemand extends DataEntity<TradeOrderSupplyDemand>{
	
	private static final long serialVersionUID = 1L;
	private String orderId;		    // order_id
	private String supplyId;		// supply_id
	private String demandId;		// demand_id
	private String totalPrice;		// total_price
	private String perPrice;		// per_price
	private String number;		    // number
	private String imagePath;		// 
	private String priceUnitId;		// price_unit_id
	private String numberUnitId;		// number_unit_id
	private String priceUnitValue;		// price_unit_value
	private String numberUnitValue;		// number_unit_value
	private String remarks;//备注
	private String spec;//产品规格
	private String pgoodsName;//父类
	private String goodsName;//具体产品名称
	private String supplyRemarks;
	private String delFlag;	

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getSupplyRemarks() {
		return supplyRemarks;
	}

	public void setSupplyRemarks(String supplyRemarks) {
		this.supplyRemarks = supplyRemarks;
	}
	
	public String getPgoodsName() {
		return pgoodsName;
	}

	public void setPgoodsName(String pgoodsName) {
		this.pgoodsName = pgoodsName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public TradeOrderSupplyDemand() {
		super();
	}

	public TradeOrderSupplyDemand(String id){
		super(id);
	}

	//@Length(min=1, max=64, message="order_id长度必须介于 1 和 64 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	//@Length(min=1, max=64, message="supply_id长度必须介于 1 和 64 之间")
	public String getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(String supplyId) {
		this.supplyId = supplyId;
	}
	
	//@Length(min=1, max=64, message="demand_id长度必须介于 1 和 64 之间")
	public String getDemandId() {
		return demandId;
	}

	public void setDemandId(String demandId) {
		this.demandId = demandId;
	}
	
	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getPerPrice() {
		return perPrice;
	}

	public void setPerPrice(String perPrice) {
		this.perPrice = perPrice;
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	//@Length(min=0, max=128, message="ͼƬ长度必须介于 0 和 128 之间")
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	//@Length(min=1, max=10, message="price_unit_id长度必须介于 1 和 10 之间")
	public String getPriceUnitId() {
		return priceUnitId;
	}

	public void setPriceUnitId(String priceUnitId) {
		this.priceUnitId = priceUnitId;
	}
	
	//@Length(min=1, max=10, message="number_unit_id长度必须介于 1 和 10 之间")
	public String getNumberUnitId() {
		return numberUnitId;
	}

	public void setNumberUnitId(String numberUnitId) {
		this.numberUnitId = numberUnitId;
	}
	
	//@Length(min=1, max=64, message="price_unit_value长度必须介于 1 和 64 之间")
	public String getPriceUnitValue() {
		return priceUnitValue;
	}

	public void setPriceUnitValue(String priceUnitValue) {
		this.priceUnitValue = priceUnitValue;
	}
	
	//@Length(min=1, max=64, message="number_unit_value长度必须介于 1 和 64 之间")
	public String getNumberUnitValue() {
		return numberUnitValue;
	}

	public void setNumberUnitValue(String numberUnitValue) {
		this.numberUnitValue = numberUnitValue;
	}
	
}