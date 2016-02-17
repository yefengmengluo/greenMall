package com.wk.p3.greenmall.modules.info.dao;

import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.info.entity.Gcategory;

import java.util.List;


/**
 * Created by zhuyanqing on 2015/12/11.
 */
@MyBatisDao
public interface GcategoryDao extends CataDao<Gcategory> {

    public List<Gcategory> getList(Gcategory gcategory);
    public List<Gcategory> getListByParent(Gcategory gcategory);
    public Gcategory getByCode(Gcategory gcategory);
}
