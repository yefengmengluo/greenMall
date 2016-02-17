package com.wk.p3.greenmall.modules.deal.entity;

/**
 * 
 * @author lf
 *
 */
public class OppVO {
	private String id;
	private String goodsName;
	private String statue;
	private String number;
    private String priceUnitValue;
       
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPriceUnitValue() {
		return priceUnitValue;
	}

	public void setPriceUnitValue(String priceUnitValue) {
		this.priceUnitValue = priceUnitValue;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
