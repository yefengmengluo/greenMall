package com.wk.p3.greenmall.modules.info.service;

import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.modules.info.dao.UnitDao;
import com.wk.p3.greenmall.modules.info.entity.InfoUnitCategory;
import com.wk.p3.greenmall.modules.info.entity.Unit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liujiabao on 2016/1/7 0007.
 */
@Service
@Transactional(readOnly = true)
public class UnitService extends CrudService<UnitDao, Unit> {
    /**
     * 保存数据（插入或更新）
     *
     * @param entity
     */
    @Transactional(readOnly = false)
    public void save(Unit entity) {
        if (entity.getIsNewRecord()) {
            entity.setIsNewRecord(true);
            entity.preInsert();
            dao.insert(entity);
        } else {
            entity.preUpdate();
            dao.update(entity);
        }
    }
    public List<Unit> findAllExceptUnitIds(InfoUnitCategory infoUnitCategory){

        return dao.findAllExceptUnitIds(infoUnitCategory);
    }

}
