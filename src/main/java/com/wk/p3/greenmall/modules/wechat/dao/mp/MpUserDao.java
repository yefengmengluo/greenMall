/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.wechat.dao.mp;

import com.wk.p3.greenmall.common.persistence.CrudDao;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.wechat.entity.mp.MpUser;

/**
 * 微信用户DAO接口
 * @author cc
 * @version 2015-11-25
 */
@MyBatisDao
public interface MpUserDao extends CrudDao<MpUser> {

    /**
     * 根据openId获得微信用户对象
     * @param openId
     * @return
     */
    public MpUser getByOpenId(String openId);

	
}