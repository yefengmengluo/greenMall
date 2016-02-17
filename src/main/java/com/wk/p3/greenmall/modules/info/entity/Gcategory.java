package com.wk.p3.greenmall.modules.info.entity;


import com.wk.p3.greenmall.modules.user.entity.Organization;

import java.util.List;
import java.util.Map;

/**
 * Created by zhuyanqing on 2015/12/11.
 */
public class Gcategory extends CataEntity<Gcategory> {

    private String code; 	// 标识
    private String imagePath; 	// 图片路径
    private String showSpec;//是否展示本类别规格（"1"展示，"0"不展示）
    private List<Gcategory> childList;//下一级别子类别
    private List<Info> supplyList;//本类别对应的供信息列表
    private List<Info> demandList;//本类别对应的求信息列表
    private List<Map> tradeList;//本类被对应的交易列表
    private List<Organization> organizationList;//本类别推荐企业

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }

    public List<Map> getTradeList() {
        return tradeList;
    }

    public void setTradeList(List<Map> tradeList) {
        this.tradeList = tradeList;
    }

    public List<Info> getSupplyList() {
        return supplyList;
    }

    public void setSupplyList(List<Info> supplyList) {
        this.supplyList = supplyList;
    }

    public List<Info> getDemandList() {
        return demandList;
    }

    public void setDemandList(List<Info> demandList) {
        this.demandList = demandList;
    }

    public List<Gcategory> getChildList() {
        return childList;
    }

    public void setChildList(List<Gcategory> childList) {
        this.childList = childList;
    }

    public String getShowSpec() {
        return showSpec;
    }

    public void setShowSpec(String showSpec) {
        this.showSpec = showSpec;
    }

    public String getParentIds() {
        return parentIds;
    }
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCode() {
        return code;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Gcategory getParent() {
        return parent;
    }

    public void setParent(Gcategory parent) {
        this.parent = parent;
    }
}
