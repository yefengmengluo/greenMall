/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.sys.dao;

import com.wk.p3.greenmall.common.persistence.TreeDao;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.sys.entity.Office;

/**
 * 机构DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	
}
