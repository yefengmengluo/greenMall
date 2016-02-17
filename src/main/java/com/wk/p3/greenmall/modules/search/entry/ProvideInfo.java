package com.wk.p3.greenmall.modules.search.entry;

import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 
 * @author Robin
 * 供应信息
 *
 */
public class ProvideInfo {
	@Field("id")
	private String id;
	//供应信息id
	@Field("infoid")
	private String infoid;
	//产品名称
	@Field("spuname")
	private String spuName;
	//品牌名称
	@Field("brandname")
	private String brandName;
	//规格信息
	@Field("spec")
	private String spec;
	//产品产地
	@Field("produceplace")
	private String producePlace;
	@Field("area")
	private String area;
	//末级类目
	@Field("category")
	private String category;
	//价格
	@Field("price")
	private Float price;
	//交易地点
	@Field("transtionalplace")
	private String transtionalPlace;
	//供应商
	@Field("supplier")
	private String supplier;
	//更新时间
	@Field("updatetime")
	private Date updateTime;
	//品种
	@Field("breed")
	private String breed;
	@Field("pinyin")
	private String pinyin;
	public String getInfoid() {
		return infoid;
	}
	public void setInfoid(String infoid) {
		this.infoid = infoid;
	}
	public String getSpuName() {
		return spuName;
	}
	public void setSpuName(String spuName) {
		this.spuName = spuName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getProducePlace() {
		return producePlace;
	}
	public void setProducePlace(String producePlace) {
		this.producePlace = producePlace;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getTranstionalPlace() {
		return transtionalPlace;
	}
	public void setTranstionalPlace(String transtionalPlace) {
		this.transtionalPlace = transtionalPlace;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	
	
	
	


}
