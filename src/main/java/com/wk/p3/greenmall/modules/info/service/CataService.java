package com.wk.p3.greenmall.modules.info.service;

import com.wk.p3.greenmall.common.service.ServiceException;
import com.wk.p3.greenmall.common.utils.Reflections;
import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.modules.info.dao.CataDao;
import com.wk.p3.greenmall.modules.info.entity.CataEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhuyanqing on 2015/12/12.
 */
@Service
@Transactional(readOnly = true)
public abstract class CataService<D extends CataDao<T>, T extends CataEntity<T>> extends CataCrudService<D, T> {
    @Transactional(readOnly = false)
    public Integer save(T entity) {
        Integer i = null;
        @SuppressWarnings("unchecked")
        Class<T> entityClass = Reflections.getClassGenricType(getClass(), 1);

        // 如果没有设置父节点，则代表为跟节点，有则获取父节点实体
        if (entity.getParent() == null ||entity.getParent().getId() == null ||entity.getParentId() == 0){
            entity.setParent(null);
        }else{
            entity.setParent(super.get(entity.getParentId()));
        }
        if (entity.getParent() == null){
            T parentEntity = null;
            try {
               // parentEntity = entityClass.getConstructor(Integer.class).newInstance(0);
                parentEntity = entityClass.newInstance();
                parentEntity.setId(0);
            } catch (Exception e) {
                throw new ServiceException(e);
            }
            entity.setParent(parentEntity);
            entity.getParent().setParentIds(StringUtils.EMPTY);
        }

        // 获取修改前的parentIds，用于更新子节点的parentIds
        String oldParentIds = entity.getParentIds();

        // 设置新的父节点串
        entity.setParentIds(entity.getParent().getParentIds()+entity.getParent().getId()+",");

        // 保存或更新实体
         i = super.save(entity);

        // 更新子节点 parentIds
        T o = null;
        try {
            o = entityClass.newInstance();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        o.setParentIds("%,"+entity.getId()+",%");
        List<T> list = dao.findByParentIdsLike(o);
        for (T e : list){
            if (e.getParentIds() != null && oldParentIds != null){
                e.setParentIds(e.getParentIds().replace(oldParentIds, entity.getParentIds()));
                preUpdateChild(entity, e);
                dao.updateParentIds(e);
            }
        }
     return i;
    }

    /**
     * 预留接口，用户更新子节前调用
     * @param childEntity
     */
    protected void preUpdateChild(T entity, T childEntity) {

    }

    @Transactional(readOnly = false)
    public Integer normalSave(T entity) {
       return super.save(entity);
    }
}
