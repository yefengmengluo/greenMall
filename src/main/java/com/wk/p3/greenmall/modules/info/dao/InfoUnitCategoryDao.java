package com.wk.p3.greenmall.modules.info.dao;

import com.wk.p3.greenmall.common.persistence.CrudDao;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.info.entity.Gcategory;
import com.wk.p3.greenmall.modules.info.entity.InfoUnitCategory;

import java.util.List;

/**
 * Created by liujiabao on 2015/12/26 0026.
 */
@MyBatisDao
public interface InfoUnitCategoryDao extends CrudDao<InfoUnitCategory> {

    public List<InfoUnitCategory> queryAllNonstandardNumberUnitByGcategory(String id);
    public List<InfoUnitCategory> queryAllNumberUnitByGcategory(String id);

    public List<InfoUnitCategory> getInfoUnitCategorysByGcategory(InfoUnitCategory infoUnitCategory);

}
