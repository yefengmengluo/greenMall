package com.wk.p3.greenmall.modules.info.service;

import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.common.utils.SpringContextHolder;
import com.wk.p3.greenmall.modules.info.dao.InfoSpecsDao;
import com.wk.p3.greenmall.modules.info.entity.InfoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liujiabao on 2015/12/28 0028.
 */
@Service
@Transactional(readOnly = true)
public class InfoSpecsService extends CrudService<InfoSpecsDao,InfoSpecs> {

    private static InfoSpecsDao infoSpecsDao = SpringContextHolder.getBean(InfoSpecsDao.class);
    @Transactional(readOnly = false)
    public void deleteByInfoId(String id){
        infoSpecsDao.deleteByInfoId(id);
    }


    public List<InfoSpecs> getInfoSpecsByInfoIdAndSpecs(InfoSpecs infoSpecs){
        return infoSpecsDao.getInfoSpecsByInfoIdAndSpecs(infoSpecs);
    }

    public List<InfoSpecs> getInfoSpecsByInfoId(String id){
        return infoSpecsDao.getInfoSpecsByInfoId(id);
    }

}
