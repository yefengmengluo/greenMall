/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.modules.security.entity.SecurityUserToken;
import com.wk.p3.greenmall.modules.security.dao.SecurityUserTokenDao;

/**
 * 保存用户账号密码登陆凭证Service
 * @author cc
 * @version 2016-01-21
 */
@Service
@Transactional(readOnly = true)
public class SecurityUserTokenService extends CrudService<SecurityUserTokenDao, SecurityUserToken> {

	@Autowired
	SecurityUserTokenDao securityUserTokenDao;

	public SecurityUserToken get(String id) {
		return super.get(id);
	}
	
	public List<SecurityUserToken> findList(SecurityUserToken securityUserToken) {
		return super.findList(securityUserToken);
	}
	
	public Page<SecurityUserToken> findPage(Page<SecurityUserToken> page, SecurityUserToken securityUserToken) {
		return super.findPage(page, securityUserToken);
	}
	
	@Transactional(readOnly = false)
	public void save(SecurityUserToken securityUserToken) {
		super.save(securityUserToken);
	}
	
	@Transactional(readOnly = false)
	public void delete(SecurityUserToken securityUserToken) {
		super.delete(securityUserToken);
	}

	public String getUserIdByTokenId(String id) {
		SecurityUserToken token = new SecurityUserToken(id);
		List<SecurityUserToken> list =  super.findList(token);
		if(list.size()==1){
			return list.get(0).getUniqueUser();
		}else{
			return null;
		}
	}

	public SecurityUserToken findbyToken(SecurityUserToken userToken) {
		List<SecurityUserToken> list =  super.findList(userToken);
		if(list.size()==1){
			return list.get(0);
		}else{
			return null;
		}
	}
}