/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.deal.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * 业务订单Entity
 * @author lf
 * @version 2015-12-15
 */
public class TradeOrderFlow extends DataEntity<TradeOrderFlow> {
	
	private static final long serialVersionUID = 1L;
	private String orderFlowId;		// 订单流水号
	private String orderId;		// 订单号
	private String statue;		// 订单状态(数据字典)
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
	
	public TradeOrderFlow() {
		super();
	}

	public TradeOrderFlow(String id){
		super(id);
	}

	@Length(min=0, max=64, message="订单流水号长度必须介于 0 和 64 之间")
	public String getOrderFlowId() {
		return orderFlowId;
	}

	public void setOrderFlowId(String orderFlowId) {
		this.orderFlowId = orderFlowId;
	}
	
	@Length(min=1, max=64, message="订单号长度必须介于 1 和 64 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=1, max=10, message="订单状态(数据字典)长度必须介于 1 和 10 之间")
	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
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
	
	@Length(min=1, max=1, message="评价状态（默认0：未评价，1：已经评价）长度必须介于 1 和 1 之间")
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