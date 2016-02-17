package com.wk.p3.greenmall.modules.user.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.user.entity.FrontUser;
import com.wk.p3.greenmall.modules.user.entity.Organization;
import com.wk.p3.greenmall.modules.user.entity.OrganizationType;

/**
 * @author zhaomeng
 * @description 机构接口
 * 2015年12月28日
 */
public interface OrganizationService {
	/**
	 * 根据id查询机构信息
	 * @param id
	 * @return
	 */
	public Organization findOrganizationById(String id);

	/**
	 * 根据id查询机构信息(地区只有id，没有名称)
	 * @param id
	 * @return
	 */
	public Organization findOrganizationByIdNoSetAreaName(String id);
	/**
	 * 根据id查询用户机构的主营产品
	 * @param organizationId
	 * @return
	 */
	public List<String> findOrganziationMainGoodsbyId(String organizationId);
	/**
	 * 修改用户认证信息
	 * @param user
	 * @param organization
	 * @param map 
	 */
    public void updateUserAndOrganization(FrontUser user, Organization organization, Map map);

    /**
	 * 修改用户信息
	 * @param user
	 */
    public void updateUser(FrontUser user);

    /**
	 * 用户完善信息
	 * @param organization
	 * @param mainGoods
	 * @param currentUser 
	 */
    public void prefectUser(Organization organization, String[] mainGoods, FrontUser currentUser);

    /**
	 * 查询企业类型
	 * @return
	 */
	public List<OrganizationType> findOrganizationType();

    /**
     * 查询企业列表
     *
     * @param org
     * @return
     */
    List<Organization> findList(Organization org);

}
