package com.wk.p3.greenmall.modules.deal.entity;

import java.util.Date;
/**
 * 
 * @author lf
 *
 */
public class OrderData {
	private String demandname;
	private String pgoodsname;
	private String goodsname;
	private Double number;
	private Integer statue;
	private Date updateDate;
    private String addressId;//供求信息表中对应的address_id
    private String orderNumber;//订单编码
    private String userAddressId;//user_address_info表中的id
    private String demandId;//买家Id
    private String supplyId;//卖家Id
    private String pids;
    
	public String getPids() {
		return pids;
	}

	public void setPids(String pids) {
		this.pids = pids;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getUserAddressId() {
		return userAddressId;
	}

	public void setUserAddressId(String userAddressId) {
		this.userAddressId = userAddressId;
	}

	public String getDemandId() {
		return demandId;
	}

	public void setDemandId(String demandId) {
		this.demandId = demandId;
	}

	public String getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(String supplyId) {
		this.supplyId = supplyId;
	}

	public String getDemandname() {
		return demandname;
	}

	public void setDemandname(String demandname) {
		this.demandname = demandname;
	}

	public String getPgoodsname() {
		return pgoodsname;
	}

	public void setPgoodsname(String pgoodsname) {
		this.pgoodsname = pgoodsname;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public Integer getStatue() {
		return statue;
	}

	public void setStatue(Integer statue) {
		this.statue = statue;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
