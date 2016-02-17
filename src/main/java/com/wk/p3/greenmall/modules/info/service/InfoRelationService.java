package com.wk.p3.greenmall.modules.info.service;

import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.modules.info.dao.InfoRelationDao;
import com.wk.p3.greenmall.modules.info.entity.InfoRelation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhuyanqing on 2016/1/28.
 */
@Service
@Transactional(readOnly = true)
public class InfoRelationService extends CrudService<InfoRelationDao,InfoRelation>{

}

