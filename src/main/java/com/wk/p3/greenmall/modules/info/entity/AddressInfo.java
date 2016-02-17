package com.wk.p3.greenmall.modules.info.entity;

import com.wk.p3.greenmall.common.persistence.DataEntity;
import com.wk.p3.greenmall.modules.sys.entity.Area;
import com.wk.p3.greenmall.modules.sys.entity.User;

/**
 * Created by zhuyanqing on 2015/12/14.
 * 用户地址
 */
public class AddressInfo extends DataEntity{

    private Area province;
    private Area city;
    private Area area;
    private String warehouse;
    private String detailArea;
    private User user;
    private String username;
    private String telephone;
    private Integer isDefault;
    private String postcode;
    private String type;

    public Area getProvince() {
        return province;
    }

    public void setProvince(Area province) {
        this.province = province;
    }

    public Area getCity() {
        return city;
    }

    public void setCity(Area city) {
        this.city = city;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getDetailArea() {
        return detailArea;
    }

    public void setDetailArea(String detailArea) {
        this.detailArea = detailArea;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
