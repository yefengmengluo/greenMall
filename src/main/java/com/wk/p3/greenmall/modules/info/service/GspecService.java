package com.wk.p3.greenmall.modules.info.service;

import com.wk.p3.greenmall.modules.info.dao.GspecDao;
import com.wk.p3.greenmall.modules.info.entity.Gspec;
import com.wk.p3.greenmall.modules.info.entity.Gvalue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhuyanqing on 2015/12/16.
 */
@Service
@Transactional(readOnly = true)
public class GspecService extends CataService<GspecDao, Gspec>  {

    @Override
    public List<Gspec> findList(Gspec entity) {
        return super.findList(entity);
    }

    /**
     * 根据规格查询所对应的所有规格值，不分页
     * @param entity
     * @return
     */
    public List<Gvalue> findValsByGspec(Gspec entity){
        return dao.findValsByGspec(entity);
    }
}
