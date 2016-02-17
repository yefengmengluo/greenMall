/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.msg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.modules.msg.entity.MsgUser;
import com.wk.p3.greenmall.modules.msg.dao.MsgUserDao;

/**
 * 用户配置管理Service
 * @author cc
 * @version 2015-12-26
 */
@Service
@Transactional(readOnly = true)
public class MsgUserService extends CrudService<MsgUserDao, MsgUser> {

	public MsgUser get(String id) {
		return super.get(id);
	}
	
	public List<MsgUser> findList(MsgUser msgUser) {
		return super.findList(msgUser);
	}
	
	public Page<MsgUser> findPage(Page<MsgUser> page, MsgUser msgUser) {
		return super.findPage(page, msgUser);
	}
	
	@Transactional(readOnly = false)
	public void save(MsgUser msgUser) {
		super.save(msgUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(MsgUser msgUser) {
		super.delete(msgUser);
	}
	
}