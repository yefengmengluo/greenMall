package com.wk.p3.greenmall.modules.info.entity;

import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * Created by liujiabao on 2015/12/26 0026.
 */
public class InfoUnitRelation extends DataEntity<InfoUnitRelation> {
    private Double multiplier;
    private String fromUnitName;
    private String toUnitName;
    private String fromUnitCode;
    private String toUnitCode;

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
