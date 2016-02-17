package com.wk.p3.greenmall.modules.info.entity;

import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * Created by liujiabao on 2015/12/26 0026.
 * 对应类型的单位记录
 */
public class InfoUnitCategory extends DataEntity<InfoUnitCategory> {

    Gcategory gcategory;
    private Integer gcategoryId;
    public String unitCode;
    public String unitName;
    private Integer orderItem;
    private Integer isDefault;
    private Integer statue;
    private Integer unitId;
    Unit unit;

    public Integer getGcategoryId() {
        return gcategoryId;
    }

    public void setGcategoryId(Integer gcategoryId) {
        this.gcategoryId = gcategoryId;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    public Gcategory getGcategory() {
        return gcategory;
    }

    public void setGcategory(Gcategory gcategory) {
        this.gcategory = gcategory;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public Integer getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(Integer orderItem) {
        this.orderItem = orderItem;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
