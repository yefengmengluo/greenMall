/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.security.dao;

import com.wk.p3.greenmall.common.persistence.CrudDao;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.security.entity.SecurityUserToken;

/**
 * 保存用户账号密码登陆凭证DAO接口
 * @author cc
 * @version 2016-01-21
 */
@MyBatisDao
public interface SecurityUserTokenDao extends CrudDao<SecurityUserToken> {

    public String getUserIdByTokenId(String id);

    public SecurityUserToken findbyToken(SecurityUserToken userToken);

}