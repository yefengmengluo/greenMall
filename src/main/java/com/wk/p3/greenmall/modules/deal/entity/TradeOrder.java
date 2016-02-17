/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.deal.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * 业务订单Entity
 * @author lf
 * @version 2015-12-15
 */
public class TradeOrder extends DataEntity<TradeOrder>{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String orderNumber;		// 订单编号
	private Integer statue;		// 订单状态(数据字典)
	private String supplyUserId;		// 卖方id
	private String supplyUserName;		// 卖方name
	private String demandUserId;		// 买方id
	private String demandName;		// 买方name
	private Date addDate;		// 下单时间
	private Date shipTime;		// 发货时间
	private String invoiceNo;		// 发票号
	private Date finishTime;		// 完成时间
	private String goodsAmount;		// 总价钱
	private String evaluationStatus;		// 评价状态（默认0：未评价，1：已经评价）
	private Date evaluationTime;		// 评价时间
	private String evaluationContent;		// 评价内容
	private String receiveProvince;		// 收货省份
	private String receiveCity;		// 收货城市
	private String receiveArea;		// 收货地区
	private String receiveDetailArea;		// 收货详细地区
	private String remarks;     //备注
	private Double supplycommission;//供应商费用
	private Double demandreturncash;//采购商返现
	private Double cashpledge;//采购商押金
	private Double goodsaccountpayable;//采购商应付款
	private String distribution;//配送方式
	private Integer payment;//支付方式
	private Integer paymenttype;//付款类型
	private Double goodstotalmoney;//产品总金额
	private Double distributionfee;//配送费用
	private Double poundage;//支付手续费
	private Double ordertotal;//订单总额
	private Integer transactiontype;//交易方式	1-合同交易，2-委托交易，3-自主交易，4-信托交易，5，现货交易，6，期货交易	
	private Date updateDate;
		
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getStatue() {
		return statue;
	}

	public void setStatue(Integer statue) {
		this.statue = statue;
	}

	public Integer getTransactiontype() {
		return transactiontype;
	}

	public void setTransactiontype(Integer transactiontype) {
		this.transactiontype = transactiontype;
	}

	public Double getSupplycommission() {
		return supplycommission;
	}

	public void setSupplycommission(Double supplycommission) {
		this.supplycommission = supplycommission;
	}

	public Double getDemandreturncash() {
		return demandreturncash;
	}

	public void setDemandreturncash(Double demandreturncash) {
		this.demandreturncash = demandreturncash;
	}	

	public Double getCashpledge() {
		return cashpledge;
	}

	public void setCashpledge(Double cashpledge) {
		this.cashpledge = cashpledge;
	}

	public Double getGoodsaccountpayable() {
		return goodsaccountpayable;
	}

	public void setGoodsaccountpayable(Double goodsaccountpayable) {
		this.goodsaccountpayable = goodsaccountpayable;
	}

	public String getDistribution() {
		return distribution;
	}

	public void setDistribution(String distribution) {
		this.distribution = distribution;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public Integer getPaymenttype() {
		return paymenttype;
	}

	public void setPaymenttype(Integer paymenttype) {
		this.paymenttype = paymenttype;
	}

	public Double getGoodstotalmoney() {
		return goodstotalmoney;
	}

	public void setGoodstotalmoney(Double goodstotalmoney) {
		this.goodstotalmoney = goodstotalmoney;
	}

	public Double getDistributionfee() {
		return distributionfee;
	}

	public void setDistributionfee(Double distributionfee) {
		this.distributionfee = distributionfee;
	}

	public Double getPoundage() {
		return poundage;
	}

	public void setPoundage(Double poundage) {
		this.poundage = poundage;
	}

	public Double getOrdertotal() {
		return ordertotal;
	}

	public void setOrdertotal(Double ordertotal) {
		this.ordertotal = ordertotal;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public TradeOrder() {
		super();
	}

	public TradeOrder(String id){
		super(id);
	}

	@Length(min=0, max=64, message="卖方id长度必须介于 0 和 64 之间")
	public String getSupplyUserId() {
		return supplyUserId;
	}

	public void setSupplyUserId(String supplyUserId) {
		this.supplyUserId = supplyUserId;
	}
	
	@Length(min=0, max=255, message="卖方name长度必须介于 0 和 255 之间")
	public String getSupplyUserName() {
		return supplyUserName;
	}

	public void setSupplyUserName(String supplyUserName) {
		this.supplyUserName = supplyUserName;
	}
	
	@Length(min=0, max=64, message="买方id长度必须介于 0 和 64 之间")
	public String getDemandUserId() {
		return demandUserId;
	}

	public void setDemandUserId(String demandUserId) {
		this.demandUserId = demandUserId;
	}
	
	@Length(min=0, max=255, message="买方name长度必须介于 0 和 255 之间")
	public String getDemandName() {
		return demandName;
	}

	public void setDemandName(String demandName) {
		this.demandName = demandName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getShipTime() {
		return shipTime;
	}

	public void setShipTime(Date shipTime) {
		this.shipTime = shipTime;
	}
	
	@Length(min=0, max=255, message="发票号长度必须介于 0 和 255 之间")
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	
	public String getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(String goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
	
	//@Length(min=1, max=1, message="评价状态（默认0：未评价，1：已经评价）长度必须介于 1 和 1 之间")
	public String getEvaluationStatus() {
		return evaluationStatus;
	}

	public void setEvaluationStatus(String evaluationStatus) {
		this.evaluationStatus = evaluationStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEvaluationTime() {
		return evaluationTime;
	}

	public void setEvaluationTime(Date evaluationTime) {
		this.evaluationTime = evaluationTime;
	}
	
	public String getEvaluationContent() {
		return evaluationContent;
	}

	public void setEvaluationContent(String evaluationContent) {
		this.evaluationContent = evaluationContent;
	}
	
	@Length(min=0, max=64, message="收货省份长度必须介于 0 和 64 之间")
	public String getReceiveProvince() {
		return receiveProvince;
	}

	public void setReceiveProvince(String receiveProvince) {
		this.receiveProvince = receiveProvince;
	}
	
	@Length(min=0, max=64, message="收货城市长度必须介于 0 和 64 之间")
	public String getReceiveCity() {
		return receiveCity;
	}

	public void setReceiveCity(String receiveCity) {
		this.receiveCity = receiveCity;
	}
	
	@Length(min=0, max=64, message="收货地区长度必须介于 0 和 64 之间")
	public String getReceiveArea() {
		return receiveArea;
	}

	public void setReceiveArea(String receiveArea) {
		this.receiveArea = receiveArea;
	}
	
	@Length(min=0, max=64, message="收货详细地区长度必须介于 0 和 64 之间")
	public String getReceiveDetailArea() {
		return receiveDetailArea;
	}

	public void setReceiveDetailArea(String receiveDetailArea) {
		this.receiveDetailArea = receiveDetailArea;
	}
}