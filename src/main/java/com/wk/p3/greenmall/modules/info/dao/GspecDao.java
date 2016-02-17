package com.wk.p3.greenmall.modules.info.dao;


import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.info.entity.Gspec;
import com.wk.p3.greenmall.modules.info.entity.Gvalue;

import java.util.List;

/**
 * Created by zhuyanqing on 2015/12/14.
 */
@MyBatisDao
public interface GspecDao extends CataDao<Gspec> {

    public List<Gvalue> findValsByGspec(Gspec gspec);
}
