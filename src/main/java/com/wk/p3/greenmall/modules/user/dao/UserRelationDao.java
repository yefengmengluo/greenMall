/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.user.dao;

import com.wk.p3.greenmall.common.persistence.CrudDao;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.user.entity.UserRelation;

/**
 * 用户之间的关系DAO接口
 * @author cc
 * @version 2016-01-05
 */
@MyBatisDao
public interface UserRelationDao extends CrudDao<UserRelation> {

    Integer findCountUserByTheId(String currentId);

    void deleteRelationByOtherUser(String userId);

    String findBussinessUserByOtherId(String id);
}