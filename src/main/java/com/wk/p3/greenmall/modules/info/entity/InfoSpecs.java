package com.wk.p3.greenmall.modules.info.entity;

import com.wk.p3.greenmall.common.persistence.BaseEntity;
import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * Created by liujiabao on 2015/12/28 0028.
 */
public class InfoSpecs extends DataEntity<InfoSpecs> {
    private String specValue;
    private String supplyDemandId;
    private Integer gcategoryId;
    private Integer specId;
    private Integer specValueId;
    private Integer orderItem;
    private String specName;

    public String getSpecValue() {
        return specValue;
    }

    public void setSpecValue(String specValue) {
        this.specValue = specValue;
    }

    public String getSupplyDemandId() {
        return supplyDemandId;
    }

    public void setSupplyDemandId(String supplyDemandId) {
        this.supplyDemandId = supplyDemandId;
    }

    public Integer getGcategoryId() {
        return gcategoryId;
    }

    public void setGcategoryId(Integer gcategoryId) {
        this.gcategoryId = gcategoryId;
    }

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public Integer getSpecValueId() {
        return specValueId;
    }

    public void setSpecValueId(Integer specValueId) {
        this.specValueId = specValueId;
    }

    public Integer getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(Integer orderItem) {
        this.orderItem = orderItem;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }
}
