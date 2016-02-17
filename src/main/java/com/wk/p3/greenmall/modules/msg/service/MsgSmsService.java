/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.msg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.modules.msg.entity.MsgSms;
import com.wk.p3.greenmall.modules.msg.dao.MsgSmsDao;

/**
 * 发送短信保存Service
 * @author cc
 * @version 2015-12-26
 */
@Service
@Transactional(readOnly = true)
public class MsgSmsService extends CrudService<MsgSmsDao, MsgSms> {

	public MsgSms get(String id) {
		return super.get(id);
	}
	
	public List<MsgSms> findList(MsgSms msgSms) {
		return super.findList(msgSms);
	}
	
	public Page<MsgSms> findPage(Page<MsgSms> page, MsgSms msgSms) {
		return super.findPage(page, msgSms);
	}
	
	@Transactional(readOnly = false)
	public void save(MsgSms msgSms) {
		super.save(msgSms);
	}
	
	@Transactional(readOnly = false)
	public void delete(MsgSms msgSms) {
		super.delete(msgSms);
	}
	
}