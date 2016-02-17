package com.wk.p3.greenmall.modules.user.service;

import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.common.utils.SpringContextHolder;
import com.wk.p3.greenmall.modules.user.dao.UserAddressInfoDao;
import com.wk.p3.greenmall.modules.user.entity.UserAddressInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liujiabao on 2015/12/29 0029.
 */
@Service
public class UserAddressInfoService extends CrudService<UserAddressInfoDao,UserAddressInfo>{

    private static UserAddressInfoDao userAddressInfoDao = SpringContextHolder.getBean(UserAddressInfoDao.class);


}
