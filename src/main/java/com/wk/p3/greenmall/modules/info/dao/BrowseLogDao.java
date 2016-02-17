package com.wk.p3.greenmall.modules.info.dao;

import com.wk.p3.greenmall.common.persistence.CrudDao;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.info.entity.BrowseLog;

/**
 * Created by zhuyanqing on 2016/1/29.
 */
@MyBatisDao
public interface BrowseLogDao extends CrudDao<BrowseLog>{
    public Integer count(BrowseLog browseLog);
}
