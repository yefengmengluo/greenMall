package com.wk.p3.greenmall.modules.info.service;

import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.modules.info.dao.UnitDao;
import com.wk.p3.greenmall.modules.info.dao.UnitRelationDao;
import com.wk.p3.greenmall.modules.info.entity.InfoUnitCategory;
import com.wk.p3.greenmall.modules.info.entity.Unit;
import com.wk.p3.greenmall.modules.info.entity.UnitRelation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liujiabao on 2016/1/7 0007.
 */
@Service
@Transactional(readOnly = true)
public class UnitRelationService extends CrudService<UnitRelationDao, UnitRelation> {
    /**
     * 保存数据（插入或更新）
     *
     * @param entity
     */
    @Transactional(readOnly = false)
    public void save(UnitRelation entity) {
        if (entity.getIsNewRecord()) {
            entity.setIsNewRecord(true);
            entity.preInsert();
            dao.insert(entity);
        } else {
            entity.preUpdate();
            dao.update(entity);
        }
    }

}
