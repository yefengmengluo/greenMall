/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.match.dao;

import java.util.List;
import java.util.Map;

import com.wk.p3.greenmall.common.persistence.CrudDao;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.match.entity.SupplyDemandRelationMatching;

/**
 * 匹配规范DAO接口
 * @author zhaomeng
 * @version 2015-12-23
 */
@MyBatisDao
public interface SupplyDemandRelationMatchingDao extends CrudDao<SupplyDemandRelationMatching> {
	/**
	 * 添加匹配规范
	 * @param supplyDemandRelationMatching
	 * @return
	 */
	String insertsupplyDemandRelationMatching(SupplyDemandRelationMatching supplyDemandRelationMatching);
	/**
	 * 删除匹配规范
	 * @param id
	 */
	void deleteMatchRelationByRelationId(String id);
	/**
	 * 根据匹配对象和是否匹配查询
	 * @param string 
	 * @param id
	 */
	List<SupplyDemandRelationMatching> findRelationMatchingByObjectAndifMatching(Map map);
	/**
	 * 修改所有是否匹配为否
	 */
	void ifMatchingNo(String matchingObject);
	/**
	 * 查询匹配名称
	 * @param supplyDemandRelationMatching
	 * @return
	 */
	Integer findResultByName(SupplyDemandRelationMatching supplyDemandRelationMatching);
	
}