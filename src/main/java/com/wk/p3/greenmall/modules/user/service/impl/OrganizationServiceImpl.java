package com.wk.p3.greenmall.modules.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wk.p3.greenmall.modules.info.entity.Gcategory;
import com.wk.p3.greenmall.modules.sys.service.AreaService;
import com.wk.p3.greenmall.modules.user.dao.FrontUserDao;
import com.wk.p3.greenmall.modules.user.entity.FrontUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.sys.service.SystemService;
import com.wk.p3.greenmall.modules.sys.utils.UserUtils;
import com.wk.p3.greenmall.modules.user.dao.OrganizationDao;
import com.wk.p3.greenmall.modules.user.entity.Organization;
import com.wk.p3.greenmall.modules.user.entity.OrganizationType;
import com.wk.p3.greenmall.modules.user.service.OrganizationService;


/**
 * @author zhaomeng
 * @description 机构接口实现类
 * 2015年12月29日
 */
@Service
@Transactional(readOnly = true)
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationDao  organizationDao;

    @Autowired
    private FrontUserDao frontUserDao;

    @Autowired
    private AreaService areaService;

    @Override
	public Organization findOrganizationById(String id) {
        Organization organization = organizationDao.findOrganizationById(id);
        if (organization != null) {
            organization.setProvinceId(areaService.get(organization.getProvinceId()).getName());
			organization.setCityId(areaService.get(organization.getCityId()).getName());
			organization.setArea(areaService.get(organization.getArea()).getName());
        }
        return organization;
    }

    @Override
	public Organization findOrganizationByIdNoSetAreaName(String id) {
        Organization organization = organizationDao.findOrganizationById(id);
        return organization;
    }

	@Override
	public List<String> findOrganziationMainGoodsbyId(String organizationId) {
		return organizationDao.findOrganziationMainGoodsbyId(organizationId);
	}

	public List<Integer> findOrganziationMainIdsGoodsbyId(String organizationId) {
		return organizationDao.findOrganziationMainIdsGoodsbyId(organizationId);
	}

	@Override
	@Transactional(readOnly = false)
    public void updateUserAndOrganization(FrontUser user, Organization organization, Map map) {
        if(map.containsKey("mainGoods")){
			organizationDao.deleteMainGoodsById(map.get("id").toString());
			organizationDao.insertMainGoodsById(map);
		}
		organizationDao.updateOrganization(organization);
        frontUserDao.update(user);
    }
	@Transactional(readOnly = false)
	public void updateOrganization(String organizationId){

		organizationDao.deleteMainGoodsById(organizationId);
		organizationDao.delete(organizationId);
	}

	@Override
	@Transactional(readOnly = false)
    public void updateUser(FrontUser user) {
        frontUserDao.update(user);
    }

	@Override
	@Transactional(readOnly = false)
    public void prefectUser(Organization organization, String[] mainGoods, FrontUser user) {
        user.setOrganizationId(organization.getId());
		organization.setIsNewRecord(true);
		organizationDao.insert(organization);
		Map map=new HashMap();
		map.put("id", organization.getId());
		map.put("mainGoods", mainGoods);
		organizationDao.insertMainGoodsById(map);
		user.setOrganizationId(organization.getId());
        frontUserDao.update(user);
    }

	@Override
	public List<OrganizationType> findOrganizationType() {
		return organizationDao.findOrganizationType();
	}

    @Override
    public List<Organization> findList(Organization org) {
        return organizationDao.findList(org);
    }

	@Transactional(readOnly = false)
    public void prefectUser(Organization organization,String[] mainGoods) {
		organization.setIsNewRecord(true);
		try{
		organizationDao.insert(organization);}
		catch (Exception e){
			e.printStackTrace();
		}
		Map map=new HashMap();
		map.put("id", organization.getId());
		map.put("mainGoods", mainGoods);
		organizationDao.insertMainGoodsById(map);
	}

	/*
	*
	* 根据主营产品类型查询企业列表
	* */
	@Transactional(readOnly = true)
	public List<Organization> findOrganizationByMainGoodsType(Gcategory gcategory){
		return organizationDao.findOrganizationByMainGoodsType(gcategory);
	};

}
