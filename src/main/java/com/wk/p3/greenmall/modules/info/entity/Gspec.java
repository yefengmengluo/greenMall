package com.wk.p3.greenmall.modules.info.entity;


/**
 * Created by zhuyanqing on 2015/12/14.
 * 商品规格表
 */
public class Gspec extends CataEntity<Gspec> {

    private String code;
    private Integer orderItem;
    private Gcategory gcategory;
    private String specStrs;//用于展示规格值
    private String specStrsId;//用于展示规格值

    public String getSpecStrsId() {
        return specStrsId;
    }

    public void setSpecStrsId(String specStrsId) {
        this.specStrsId = specStrsId;
    }

    public String getSpecStrs() {
        return specStrs;
    }

    public void setSpecStrs(String specStrs) {
        this.specStrs = specStrs;
    }

    public String getName() {
        return name;
    }

    public void setOrderItem(Integer orderItem) {
        this.orderItem = orderItem;
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

    public Integer getOrderItem() {
        return orderItem;
    }





//    public void setOrderItem(String orderItem) {
//        if(orderItem!=null && !"".equals(orderItem)) {
//            this.orderItem = Integer.parseInt(orderItem);
//        }
//    }

    public Gcategory getGcategory() {
        return gcategory;
    }

    public void setGcategory(Gcategory gcategory) {
        this.gcategory = gcategory;
    }

    //以下方法暂时不用
    public Gspec getParent() {
        return parent;
    }

    public void setParent(Gspec parent) {
        this.parent = parent;
    }
}
