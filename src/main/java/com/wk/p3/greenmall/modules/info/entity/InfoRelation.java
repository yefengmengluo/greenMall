package com.wk.p3.greenmall.modules.info.entity;

import com.wk.p3.greenmall.common.persistence.DataEntity;
import com.wk.p3.greenmall.modules.sys.entity.User;

import java.util.Date;

/**
 * Created by zhuyanqing on 2016/1/27.
 * 供求信息关系实体
 */
public class InfoRelation extends DataEntity<InfoRelation> {

    private String id;
    private Info fromInfo;
    private Info toInfo;
    private Integer relation;//0：意向，1:委托(此关系产生的途径)
    private Integer fromStatue;//意向 0：没有，1：有
    private Integer toStatue;//意向 0：没有，1：有
    private Integer statue;//待撮合 0，撮合中 1，撮合成功 2，撮合失败 -1
    private String publishUser;//相关用户，用于查询，不在指定主动方或被动方
    private Info info;//相关供求，用于查询时封装数据

    public Integer getRelationType() {
        return relationType;
    }

    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }

    private Integer relationType; //意向种类 用于查询时封装数据 -1 双方无意向，1 单方有意向，2双方有意向

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public String getPublishUser() {
        return publishUser;
    }

    public void setPublishUser(String publishUser) {
        this.publishUser = publishUser;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    @Override

    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Info getFromInfo() {
        return fromInfo;
    }

    public void setFromInfo(Info fromInfo) {
        this.fromInfo = fromInfo;
    }

    public Info getToInfo() {
        return toInfo;
    }

    public void setToInfo(Info toInfo) {
        this.toInfo = toInfo;
    }

    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
    }

    public Integer getFromStatue() {
        return fromStatue;
    }

    public void setFromStatue(Integer fromStatue) {
        this.fromStatue = fromStatue;
    }

    public Integer getToStatue() {
        return toStatue;
    }

    public void setToStatue(Integer toStatue) {
        this.toStatue = toStatue;
    }
}


