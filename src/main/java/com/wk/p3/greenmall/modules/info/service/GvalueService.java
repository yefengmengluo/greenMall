package com.wk.p3.greenmall.modules.info.service;

import com.wk.p3.greenmall.modules.info.dao.GspecDao;
import com.wk.p3.greenmall.modules.info.dao.GvalueDao;
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
public class GvalueService extends CataService<GvalueDao, Gvalue>  {


}
