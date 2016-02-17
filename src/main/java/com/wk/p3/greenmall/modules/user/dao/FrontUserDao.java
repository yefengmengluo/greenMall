/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.user.dao;

import com.wk.p3.greenmall.common.persistence.CrudDao;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.user.entity.FrontUser;

/**
 * 前台用户类DAO接口
 *
 * @author zm
 * @version 2016-01-28
 */
@MyBatisDao
public interface FrontUserDao extends CrudDao<FrontUser> {

    Integer getUserByMobile(String mobile);

    Integer getCountUser(FrontUser frontUser);

    FrontUser getUserByCondition(FrontUser frontUser);
}