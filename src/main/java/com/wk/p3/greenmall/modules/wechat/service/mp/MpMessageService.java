/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.wechat.service.mp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.modules.wechat.entity.mp.MpMessage;
import com.wk.p3.greenmall.modules.wechat.dao.mp.MpMessageDao;

/**
 * 推送给用户的消息Service
 * @author cc
 * @version 2015-11-25
 */
@Service
@Transactional(readOnly = true)
public class MpMessageService extends CrudService<MpMessageDao, MpMessage> {

	
	public MpMessage get(String id) {
		MpMessage mpMessage = super.get(id);
		return mpMessage;
	}
	
	public List<MpMessage> findList(MpMessage mpMessage) {
		return super.findList(mpMessage);
	}
	
	public Page<MpMessage> findPage(Page<MpMessage> page, MpMessage mpMessage) {
		return super.findPage(page, mpMessage);
	}
	
	@Transactional(readOnly = false)
	public void save(MpMessage mpMessage) {
		super.save(mpMessage);
	}
	
	@Transactional(readOnly = false)
	public void delete(MpMessage mpMessage) {
		super.delete(mpMessage);
	}
	
}