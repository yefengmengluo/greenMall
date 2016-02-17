package com.wk.p3.greenmall.modules.info.service;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.modules.info.dao.InfoUnitCategoryDao;
import com.wk.p3.greenmall.modules.info.entity.Gcategory;
import com.wk.p3.greenmall.modules.info.entity.InfoUnitCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liujiabao on 2015/12/26 0026.
 */

@Service
public class InfoUnitCategoryService extends CrudService<InfoUnitCategoryDao,InfoUnitCategory> {
    @Autowired
    InfoUnitCategoryDao infoUnitCategoryDao;


    /**
     * 根据种类类型 查询所有的非标准单位
     * @param id
     * @return
     */
    public List<InfoUnitCategory> queryAllNonstandardNumberUnitByGcategory(String id){
        List<InfoUnitCategory> nonstandardNumberUnits = infoUnitCategoryDao.queryAllNonstandardNumberUnitByGcategory(id);
        return nonstandardNumberUnits;
    }
    /**
     * 根据种类类型 查询所有的非标准单位
     * @param id
     * @return
     */
    public List<InfoUnitCategory> queryAllNumberUnitByGcategory(String id){
        List<InfoUnitCategory> numberUnits =  infoUnitCategoryDao.queryAllNumberUnitByGcategory(id);
        return numberUnits;
    }

    /**
     * 查询分页数据
     *根据 类型查询此类型下的所有单位
     * @param page   分页对象
     * @return
     */
    @Transactional(readOnly = true)
    public Page<InfoUnitCategory> getInfoUnitCategorysByGcategory(Page<InfoUnitCategory> page, InfoUnitCategory infoUnitCategory) {
        infoUnitCategory.setPage(page);
        page.setList(dao.getInfoUnitCategorysByGcategory(infoUnitCategory));
        return page;
    }
    /**
     *根据 类型查询此类型下的所有单位
     * @return
     */
    @Transactional(readOnly = true)
    public List<InfoUnitCategory> getInfoUnitCategorysByGcategory(InfoUnitCategory infoUnitCategory) {
        return dao.getInfoUnitCategorysByGcategory(infoUnitCategory);
    }
    /**
     * 保存数据（插入或更新）
     *
     * @param entity
     */
    @Transactional(readOnly = false)
    public void save(InfoUnitCategory entity) {
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
