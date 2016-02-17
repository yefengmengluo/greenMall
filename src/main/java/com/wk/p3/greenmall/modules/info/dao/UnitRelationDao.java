package com.wk.p3.greenmall.modules.info.dao;

import com.wk.p3.greenmall.common.persistence.CrudDao;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.info.entity.InfoUnitCategory;
import com.wk.p3.greenmall.modules.info.entity.Unit;
import com.wk.p3.greenmall.modules.info.entity.UnitRelation;

import java.util.List;

/**
 * Created by liujiabao on 2016/1/7 0007.
 */
@MyBatisDao
public interface UnitRelationDao extends CrudDao<UnitRelation> {
}
