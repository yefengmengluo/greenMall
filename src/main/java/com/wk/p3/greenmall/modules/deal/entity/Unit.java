package com.wk.p3.greenmall.modules.deal.entity;

public class Unit {
	private String pGoodsId;
	private String priceUnitId;
	private String numberUnitId;
    private String unitName;
    private String id;
    private String unitCode;
    
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getpGoodsId() {
		return pGoodsId;
	}

	public void setpGoodsId(String pGoodsId) {
		this.pGoodsId = pGoodsId;
	}

	public String getPriceUnitId() {
		return priceUnitId;
	}

	public void setPriceUnitId(String priceUnitId) {
		this.priceUnitId = priceUnitId;
	}

	public String getNumberUnitId() {
		return numberUnitId;
	}

	public void setNumberUnitId(String numberUnitId) {
		this.numberUnitId = numberUnitId;
	}

}
