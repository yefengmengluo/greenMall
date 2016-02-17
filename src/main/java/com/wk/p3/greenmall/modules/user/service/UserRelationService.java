/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.user.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.modules.user.entity.UserRelation;
import com.wk.p3.greenmall.modules.user.dao.UserRelationDao;

/**
 * 用户之间的关系Service
 * @author cc
 * @version 2016-01-05
 */
@Service
@Transactional(readOnly = true)
public class UserRelationService extends CrudService<UserRelationDao, UserRelation> {

	public UserRelation get(String id) {
		return super.get(id);
	}
	
	public List<UserRelation> findList(UserRelation userRelation) {
		return super.findList(userRelation);
	}
	
	public Page<UserRelation> findPage(Page<UserRelation> page, UserRelation userRelation) {
		return super.findPage(page, userRelation);
	}
	
	@Transactional(readOnly = false)
	public void save(UserRelation userRelation) {
		super.save(userRelation);
	}

	@Transactional(readOnly = false)
	public void findAndSave(UserRelation userRelation) throws Exception {
		if(super.findList(userRelation).size()==0){
			super.save(userRelation);
		}else{
			throw new Exception("存在相同的记录,保存失败");
		}

	}



	
	@Transactional(readOnly = false)
	public void delete(UserRelation userRelation) {
		super.delete(userRelation);
	}
}