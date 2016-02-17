package com.wk.p3.greenmall.modules.info.entity;

import com.wk.p3.greenmall.common.persistence.DataEntity;
import com.wk.p3.greenmall.modules.sys.entity.User;

/**
 * Created by liujiabao on 2016/1/12 0012.
 */
public class InfoMessage extends DataEntity<InfoMessage> {
    private String message;
    private Integer statue;
    private String remarks;
    private String supplyDemandId;
    private String type;
    private String statueIntro;
    private Integer isEntrust;
    private  String entrustSupplyDemandId;
    private String publishUser;//信息发布者
    private String name;
    private String telephone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPublishUser() {
        return publishUser;
    }

    public void setPublishUser(String publishUser) {
        this.publishUser = publishUser;
    }

    public String getEntrustSupplyDemandId() {
        return entrustSupplyDemandId;
    }

    public void setEntrustSupplyDemandId(String entrustSupplyDemandId) {
        this.entrustSupplyDemandId = entrustSupplyDemandId;
    }

    public String getStatueIntro() {
        return statueIntro;
    }

    public void setStatueIntro(String statueIntro) {
        this.statueIntro = statueIntro;
    }

    public Integer getIsEntrust() {
        return isEntrust;
    }

    public void setIsEntrust(Integer isEntrust) {
        this.isEntrust = isEntrust;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    @Override
    public String getRemarks() {
        return remarks;
    }

    @Override
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSupplyDemandId() {
        return supplyDemandId;
    }

    public void setSupplyDemandId(String supplyDemandId) {
        this.supplyDemandId = supplyDemandId;
    }
}
