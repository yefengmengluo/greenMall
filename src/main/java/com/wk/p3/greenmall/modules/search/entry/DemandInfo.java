package com.wk.p3.greenmall.modules.search.entry;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 供应信息
 * @author robin
 *
 */
public class DemandInfo {
	//id
	@Field("id")
	private String id;
	//求购信息id
	@Field("demandid")
	private String demandId;
	//求购信息title
	@Field("title")
	private String title;
	//商品名称
	@Field("spuname")
	private String spuName;
	//需求数量
	@Field("neednumber")
	private Long needNumber;
	//最小价格
	@Field("miprice")
	private Float minPrice;
	//最大价格
	@Field("maxprice")
	private Float maxPrice;
	//地址
	@Field("localplace")
	private String localPlace;
	//规格
	@Field("spec")
	private String spec;
	//交易员姓名
	@Field("transactionername")
	private String transactionerName;
	//交易员电话
	@Field("transactionertel")
	private String transactionerTel;
	//类型
	@Field("stype")
	private String stype;
	//品牌
	@Field("brandname")
	private String brandName;
	//地区
	@Field("area")
	private String area;
	@Field("pinyin")
	private String pinyin;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDemandId() {
		return demandId;
	}

	public void setDemandId(String demandId) {
		this.demandId = demandId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSpuName() {
		return spuName;
	}

	public void setSpuName(String spuName) {
		this.spuName = spuName;
	}


	public Long getNeedNumber() {
		return needNumber;
	}

	public void setNeedNumber(Long needNumber) {
		this.needNumber = needNumber;
	}

	public Float getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}

	public Float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Float maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getLocalPlace() {
		return localPlace;
	}

	public void setLocalPlace(String localPlace) {
		this.localPlace = localPlace;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

    
	public String getTransactionerName() {
		return transactionerName;
	}

	public void setTransactionerName(String transactionerName) {
		this.transactionerName = transactionerName;
	}

	public String getTransactionerTel() {
		return transactionerTel;
	}

	public void setTransactionerTel(String transactionerTel) {
		this.transactionerTel = transactionerTel;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	
	
	
	
	
	

}
