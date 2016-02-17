/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.sys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wk.p3.greenmall.modules.sys.dao.AreaDao;
import com.wk.p3.greenmall.modules.sys.entity.Area;
import com.wk.p3.greenmall.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wk.p3.greenmall.common.service.TreeService;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> {

	public List<Area> findAll(){
		return UserUtils.getAreaList();
	}

	@Autowired
	AreaDao areaDao;

	@Transactional(readOnly = false)
	public void save(Area area) {
		super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Area area) {
		super.delete(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	public List<Area>ajaxAllProvince(){

		//code 100000==中国
		Area area = areaDao.getByCode("100000") ;
		List<Area> areas = areaDao.getListByParentId(area.getId());
		return areas;
	}
	public List<Area>ajaxAllCityByPovinceId(String provinceId){

		List<Area> areas = areaDao.getListByParentId(provinceId);
		return areas;
	}
	public List<Area>ajaxAllAreaByCityId(String cityId){

		List<Area> areas = areaDao.getListByParentId(cityId);
		return areas;
	}

	public List<Area> ajaxAllChildrenByParentId(String pid){
		List<Area> areas = areaDao.getListByParentId(pid);
		return areas;
	}

	/**
	 * 得到所有的省市县 数据
	 * @return
	 */
	public List<Area> getAllProvinceCityAndAreas(){
		List<Area> provinces = ajaxAllProvince();
		for (Area province:provinces){
			List<Area> citys = ajaxAllCityByPovinceId(province.getId());
			province.setCitys(citys);
			for(Area city:citys){
				List<Area> areas = ajaxAllAreaByCityId(city.getId());
				city.setAreas(areas);
			}
		}
		return provinces;
	}
}
