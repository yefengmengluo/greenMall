package com.wk.p3.greenmall.modules.info.service;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.service.BaseService;
import com.wk.p3.greenmall.modules.info.dao.CataDao;
import com.wk.p3.greenmall.modules.info.entity.CataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhuyanqing on 2015/12/12.
 */
@Transactional(readOnly = true)
public abstract class CataCrudService<D extends CataDao<T>, T extends CataEntity<T>> extends BaseService {
    /**
     * 持久层对象
     */
    @Autowired
    protected D dao;

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public T get(Integer id) {
        return dao.get(id);
    }

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    public T get(T entity) {
        return dao.get(entity);
    }

    /**
     * 查询列表数据
     * @param entity
     * @return
     */
    public List<T> findList(T entity) {
        return dao.findList(entity);
    }

    /**
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    public Page<T> findPage(Page<T> page, T entity) {
        entity.setPage(page);
        page.setList(dao.findList(entity));
        return page;

    }

    /**
     * 保存数据（插入或更新）
     * @param entity
     */
    @Transactional(readOnly = false)
    public Integer save(T entity) {
        if (entity.getIsNewRecord()){
            entity.preInsert();
            return dao.insert(entity);
        }else{
            entity.preUpdate();
            return dao.update(entity);
        }
    }

    /**
     * 删除数据
     * @param entity
     */
    @Transactional(readOnly = false)
    public Integer delete(T entity) {
       return dao.delete(entity);
    }
}
