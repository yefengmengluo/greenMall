/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.msg.dao;

import com.wk.p3.greenmall.common.persistence.CrudDao;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.msg.entity.MsgSms;

/**
 * 发送短信保存DAO接口
 * @author cc
 * @version 2015-12-26
 */
@MyBatisDao
public interface MsgSmsDao extends CrudDao<MsgSms> {
	
}