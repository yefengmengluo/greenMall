/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.wechat.service.mp;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.modules.wechat.dao.mp.MpUserDao;
import com.wk.p3.greenmall.modules.wechat.entity.mp.MpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 微信用户Service
 * @author cc
 * @version 2015-11-25
 */
@Service
@Transactional(readOnly = true)
public class MpUserService extends CrudService<MpUserDao, MpUser> {

	@Autowired
	private MpUserDao mpUserDao;

	public MpUser getByOpenId(String openId){
		return mpUserDao.getByOpenId(openId);
	}

	public MpUser get(String id) {
		return super.get(id);
	}
	
	public List<MpUser> findList(MpUser mpUser) {
		return super.findList(mpUser);
	}
	
	public Page<MpUser> findPage(Page<MpUser> page, MpUser mpUser) {
		return super.findPage(page, mpUser);
	}
	
	@Transactional(readOnly = false)
	public void save(MpUser mpUser) {
		super.save(mpUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(MpUser mpUser) {
		super.delete(mpUser);
	}

}