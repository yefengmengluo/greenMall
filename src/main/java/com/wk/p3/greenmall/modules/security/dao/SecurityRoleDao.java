/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.security.dao;

import com.wk.p3.greenmall.common.persistence.CrudDao;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.security.Role;
import com.wk.p3.greenmall.modules.security.entity.SecurityRole;

import java.util.List;
import java.util.Set;

/**
 * 与securityUser用户关联，保存用户权限DAO接口
 * @author cc
 * @version 2016-01-21
 */
@MyBatisDao
public interface SecurityRoleDao extends CrudDao<SecurityRole> {
    List<Role> findListByRoles(List<String> roles);
}