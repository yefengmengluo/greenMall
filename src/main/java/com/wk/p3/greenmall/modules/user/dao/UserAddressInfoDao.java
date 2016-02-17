package com.wk.p3.greenmall.modules.user.dao;

import com.wk.p3.greenmall.common.persistence.CrudDao;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.user.entity.UserAddressInfo;

import java.util.List;

/**
 * Created by liujiabao on 2015/12/29 0029.
 */
@MyBatisDao
public interface UserAddressInfoDao extends CrudDao<UserAddressInfo> {
}
