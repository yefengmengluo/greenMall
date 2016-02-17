/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.msg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.modules.msg.entity.MsgTemplate;
import com.wk.p3.greenmall.modules.msg.dao.MsgTemplateDao;

/**
 * 模板管理Service
 * @author cc
 * @version 2015-12-26
 */
@Service
@Transactional(readOnly = true)
public class MsgTemplateService extends CrudService<MsgTemplateDao, MsgTemplate> {

	public MsgTemplate get(String id) {
		return super.get(id);
	}
	
	public List<MsgTemplate> findList(MsgTemplate msgTemplate) {
		return super.findList(msgTemplate);
	}
	
	public Page<MsgTemplate> findPage(Page<MsgTemplate> page, MsgTemplate msgTemplate) {
		return super.findPage(page, msgTemplate);
	}
	
	@Transactional(readOnly = false)
	public void save(MsgTemplate msgTemplate) {
		super.save(msgTemplate);
	}
	
	@Transactional(readOnly = false)
	public void delete(MsgTemplate msgTemplate) {
		super.delete(msgTemplate);
	}
	
}