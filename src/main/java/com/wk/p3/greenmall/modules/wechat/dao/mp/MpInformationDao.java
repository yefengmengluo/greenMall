/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.wechat.dao.mp;

import com.wk.p3.greenmall.common.persistence.CrudDao;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.wechat.entity.mp.MpInformation;

/**
 * 用户发布的信息DAO接口
 * @author cc
 * @version 2015-11-25
 */
@MyBatisDao
public interface MpInformationDao extends CrudDao<MpInformation> {
	
}