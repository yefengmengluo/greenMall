/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.security.service;

import java.util.List;

import com.wk.p3.greenmall.modules.security.Person;
import com.wk.p3.greenmall.modules.security.Principal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.modules.security.entity.SecurityUser;
import com.wk.p3.greenmall.modules.security.dao.SecurityUserDao;

/**
 * 终端用户抽象，多登陆用户对应一个用户Service
 * @author cc
 * @version 2016-01-21
 */
@Service
@Transactional(readOnly = true)
public class SecurityUserService extends CrudService<SecurityUserDao, SecurityUser> {

	public SecurityUser get(String id) {
		return super.get(id);
	}
	
	public List<SecurityUser> findList(SecurityUser securityUser) {
		return super.findList(securityUser);
	}
	
	public Page<SecurityUser> findPage(Page<SecurityUser> page, SecurityUser securityUser) {
		return super.findPage(page, securityUser);
	}
	
	@Transactional(readOnly = false)
	public void save(SecurityUser securityUser) {
		super.save(securityUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(SecurityUser securityUser) {
		super.delete(securityUser);
	}

}