package com.wk.p3.greenmall.modules.info.entity;

import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * Created by liujiabao on 2016/1/7 0007.
 */
public class UnitRelation extends DataEntity<UnitRelation> {
    Double multiplier;
    String fromUnitName;
    String toUnitName;
    String fromUnitCode;
    String toUnitCode;

    public Double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Double multiplier) {
        this.multiplier = multiplier;
    }

    public String getFromUnitName() {
        return fromUnitName;
    }

    public void setFromUnitName(String fromUnitName) {
        this.fromUnitName = fromUnitName;
    }

    public String getToUnitName() {
        return toUnitName;
    }

    public void setToUnitName(String toUnitName) {
        this.toUnitName = toUnitName;
    }

    public String getFromUnitCode() {
        return fromUnitCode;
    }

    public void setFromUnitCode(String fromUnitCode) {
        this.fromUnitCode = fromUnitCode;
    }

    public String getToUnitCode() {
        return toUnitCode;
    }

    public void setToUnitCode(String toUnitCode) {
        this.toUnitCode = toUnitCode;
    }
}
