package com.wk.p3.greenmall.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.wk.p3.greenmall.common.persistence.CrudDao;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.info.entity.Gcategory;
import com.wk.p3.greenmall.modules.user.entity.Organization;
import com.wk.p3.greenmall.modules.user.entity.OrganizationType;

/**
 * 机构dao
 * @author zhaomeng
 * @version 2015-12-16
 */
@MyBatisDao
public interface OrganizationDao extends CrudDao<Organization>{

	/**
	 * 根据id查询机构信息
	 * @param id
	 * @return
	 */
	Organization findOrganizationById(String id);
	/**
	 * 根据id查询用户机构的主营产品
	 * @param organizationId
	 * @return
	 */
	List<String> findOrganziationMainGoodsbyId(String id);
	/**
	 * 修改供应商字段
	 * @param organization
	 */
	void updateOrganization(Organization organization);
	/**
	 * 通过机构id删除主营产品
	 * @param object
	 */
	void deleteMainGoodsById(String id);
	/**
	 * 添加主营产品
	 * @param map
	 */
	void insertMainGoodsById(Map map);
	/**
	 * 查询企业类型
	 * @return
	 */
	List<OrganizationType> findOrganizationType();
	List<Organization> findOrganizationByMainGoodsType(Gcategory gcategory);
	List<Integer> findOrganziationMainIdsGoodsbyId(String id);

}
