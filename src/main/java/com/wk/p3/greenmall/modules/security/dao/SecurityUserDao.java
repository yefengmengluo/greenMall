/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.security.dao;

import com.wk.p3.greenmall.common.persistence.CrudDao;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.security.entity.SecurityUser;

/**
 * 终端用户抽象，多登陆用户对应一个用户DAO接口
 * @author cc
 * @version 2016-01-21
 */
@MyBatisDao
public interface SecurityUserDao extends CrudDao<SecurityUser> {
	
}