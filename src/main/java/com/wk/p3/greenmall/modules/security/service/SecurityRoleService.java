/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.security.service;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.modules.security.Role;
import com.wk.p3.greenmall.modules.security.dao.SecurityRoleDao;
import com.wk.p3.greenmall.modules.security.entity.SecurityRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 与securityUser用户关联，保存用户权限Service
 * @author cc
 * @version 2016-01-21
 */
@Service
@Transactional(readOnly = true)
public class SecurityRoleService extends CrudService<SecurityRoleDao, SecurityRole> {

	@Autowired
	SecurityRoleDao securityRoleDao;

	public SecurityRole get(String id) {
		return super.get(id);
	}
	
	public List<SecurityRole> findList(SecurityRole securityRole) {
		return super.findList(securityRole);
	}
	
	public Page<SecurityRole> findPage(Page<SecurityRole> page, SecurityRole securityRole) {
		return super.findPage(page, securityRole);
	}
	
	@Transactional(readOnly = false)
	public void save(SecurityRole securityRole) {
		super.save(securityRole);
	}
	
	@Transactional(readOnly = false)
	public void delete(SecurityRole securityRole) {
		super.delete(securityRole);
	}

	public Set<Role> getRolesByPerson(String roleString) {
		HashSet set = new HashSet<Role>();
		if(roleString!=null && roleString.contains(",")){
			List<String> roles = new ArrayList();
			for(String id :roleString.split(",")){
				roles.add(id);
			}
			List<Role> rs = securityRoleDao.findListByRoles(roles);
			set.addAll(rs);
		}
		return set;
	}
}