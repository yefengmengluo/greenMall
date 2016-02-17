package com.wk.p3.greenmall.modules.info.entity;


/**
 * Created by zhuyaning on 2015/12/14.
 * 商品规格值表
 */
public class Gvalue extends CataEntity<Gvalue> {

    private String val;
    private Integer orderItem;
    private Gcategory gcategory;
    private Gspec gspec;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Integer getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(Integer orderItem) {
        this.orderItem = orderItem;
    }

    public Gcategory getGcategory() {
        return gcategory;
    }

    public void setGcategory(Gcategory gcategory) {
        this.gcategory = gcategory;
    }

    public Gspec getGspec() {
        return gspec;
    }

    public void setGspec(Gspec gspec) {
        this.gspec = gspec;
    }

    //以下方法暂时不用
    public Gvalue getParent() {
        return parent;
    }

    public void setParent(Gvalue parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Gvalue{" +
                "val='" + val + '\'' +
                ", orderItem=" + orderItem +
                ", gcategory=" + gcategory +
                ", delFlag=" + delFlag +
                ", gspec=" + gspec +
                '}';
    }
}
