package com.wk.p3.greenmall.modules.info.dao;

import com.wk.p3.greenmall.common.persistence.CrudDao;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.info.entity.Info;
import com.wk.p3.greenmall.modules.info.entity.InfoSpecs;

import java.util.List;

/**
 * Created by liujiabao on 2015/12/28 0028.
 */
@MyBatisDao
public interface InfoSpecsDao extends CrudDao<InfoSpecs> {
    public void deleteByInfoId(String id);
    public List<InfoSpecs> getInfoSpecsByInfoIdAndSpecs(InfoSpecs infoSpecs);
    public List<InfoSpecs> getInfoSpecsByInfoId(String id);
}
