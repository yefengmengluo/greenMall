/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.wechat.service.mp;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.modules.wechat.entity.mp.MpInformation;
import com.wk.p3.greenmall.modules.wechat.dao.mp.MpInformationDao;

/**
 * 用户发布的信息Service
 * @author cc
 * @version 2015-11-25
 */
@Service
@Transactional(readOnly = true)
public class MpInformationService extends CrudService<MpInformationDao, MpInformation> {

	public MpInformation get(String id) {
		return super.get(id);
	}
	
	public List<MpInformation> findList(MpInformation mpInformation) {
		return super.findList(mpInformation);
	}
	
	public Page<MpInformation> findPage(Page<MpInformation> page, MpInformation mpInformation) {
		return super.findPage(page, mpInformation);
	}
	
	@Transactional(readOnly = false)
	public void save(MpInformation mpInformation) {
		super.save(mpInformation);
	}
	
	@Transactional(readOnly = false)
	public void delete(MpInformation mpInformation) {
		super.delete(mpInformation);
	}
	
}