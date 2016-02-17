package com.wk.p3.greenmall.modules.info.dao;


import com.wk.p3.greenmall.modules.info.entity.CataEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/12/12.
 */
public interface CataDao<T extends CataEntity<T>> extends CataCrudDao<T> {
    /**
     * 找到所有子节点
     * @param entity
     * @return
     */
    public List<T> findByParentIdsLike(T entity);

    /**
     * 更新所有父节点字段
     * @param entity
     * @return
     */
    public int updateParentIds(T entity);
}
