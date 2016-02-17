package com.wk.p3.greenmall.modules.info.entity;

import com.wk.p3.greenmall.common.persistence.DataEntity;

import java.util.List;

/**
 * Created by liujiabao on 2016/1/7 0007.
 */
public class Unit extends DataEntity<Unit> {
    String name;
    String code;
    Integer statue;

    List<UnitRelation> unitRelations;


    public List<UnitRelation> getUnitRelations() {
        return unitRelations;
    }

    public void setUnitRelations(List<UnitRelation> unitRelations) {
        this.unitRelations = unitRelations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }
}
