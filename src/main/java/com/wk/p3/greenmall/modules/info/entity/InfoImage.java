package com.wk.p3.greenmall.modules.info.entity;

import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * Created by zhuyanqing on 2016/2/1.
 */
public class InfoImage extends DataEntity<InfoImage>{
    private String id;
    private String infoId;
    private String path;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
