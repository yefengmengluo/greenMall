package com.wk.p3.greenmall.modules.match.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.utils.CacheUtils;
import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.modules.info.dao.InfoDao;
import com.wk.p3.greenmall.modules.info.entity.Info;
import com.wk.p3.greenmall.modules.match.dao.BussinessMatchingDao;
import com.wk.p3.greenmall.modules.match.dao.MatchingAttrDao;
import com.wk.p3.greenmall.modules.match.dao.SupplyDemandRelationMatchingDao;
import com.wk.p3.greenmall.modules.match.entity.MatchingAttr;
import com.wk.p3.greenmall.modules.match.entity.SupplyDemandRelationMatching;
import com.wk.p3.greenmall.modules.match.service.MatchService;
import com.wk.p3.greenmall.modules.match.util.DoComparator;
import com.wk.p3.greenmall.modules.match.util.MatchingUtils;
import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.user.UserService;
import com.wk.p3.greenmall.modules.user.entity.FrontUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author zhaomeng
 * @description 商机匹配实现
 * 2015年12月15日
 */   
@Service
@Transactional(readOnly = true)
public class MatchServiceImpl implements MatchService {

	public static final Integer WEIGHT = 100;

	@Autowired
	private MatchingAttrDao matchingAttrDao;

	@Autowired
	private SupplyDemandRelationMatchingDao supplyDemandRelationMatchingDao;

	@Autowired
	private BussinessMatchingDao bussinessMatchingDao;

	@Autowired
	private InfoDao infoDao;

	@Autowired
	private UserService userService;

	@Override
	public Map<Double,String> findMatchingByUser(String personId) {
		FrontUser user = userService.getUserByPersonId(personId);
		Map<Double,String> resultMap = (Map<Double, String>) CacheUtils.get("userRelation", user.getId());
		if(resultMap!=null){
			return resultMap;
		}else{
			resultMap=new TreeMap<Double, String>(new DoComparator());
			Double gradeForAddress = 0.0;
			List<String> goodsTypes=bussinessMatchingDao.findGoodsTypeByUser(user.getId());
			Map<String,String> userAddress=bussinessMatchingDao.findAddressByUser(user.getId());
			Info searchInfo=new Info();
			for (String goodsType : goodsTypes) {
				searchInfo.setPgoodsId(goodsType);
				List<Info> infoList=infoDao.findList(searchInfo);
				for (Info infoResult : infoList) {
					String infoValue1 = userAddress.get("provinceName");
					String infoValue2 = userAddress.get("cityName");
					String infoValue3 = userAddress.get("areaName");
					String matchValue1 = infoResult.getProvinceName();
					String matchValue2 = infoResult.getCityName();
					String matchValue3 = infoResult.getAreaName();
					gradeForAddress = MatchingUtils.getGradeForAddress(infoValue1,infoValue2,infoValue3,matchValue1,matchValue2,matchValue3,WEIGHT);
					if(gradeForAddress!=0.0){
						resultMap.put(gradeForAddress, infoResult.getId());
					}
				}
			}
			CacheUtils.put("userRelation", user.getId(), resultMap);
		}
		return resultMap;
	}

	@Override
	public List<MatchingAttr> findMatchingAttrList() {
		return matchingAttrDao.findAllList(new MatchingAttr());
	}

	@Override
	@Transactional(readOnly = false)
	public String saveMatchingRelation(SupplyDemandRelationMatching supplyDemandRelationMatching,String[] relationWeight, String[] checkmatch,String ifUpdate,String ifCover) {
		Integer nameVaild=supplyDemandRelationMatchingDao.findResultByName(supplyDemandRelationMatching);
			if(nameVaild>=1){
				return "nameError";
			}
			if(StringUtils.isBlank(ifCover)){
				Map map=new HashMap();
				map.put("matchingObject", supplyDemandRelationMatching.getMatchingObject());
				map.put("id", supplyDemandRelationMatching.getId());
				List<SupplyDemandRelationMatching> result = supplyDemandRelationMatchingDao.findRelationMatchingByObjectAndifMatching(map);
				if(result.size()>1){
					return "1";
				}
			}else{
				if(ifCover.equals("1")){
					supplyDemandRelationMatchingDao.ifMatchingNo(supplyDemandRelationMatching.getMatchingObject());
				}else if(ifCover.equals("2")){
					supplyDemandRelationMatching.setIfMatching("0");
				}
			}
			if("1".equals(ifUpdate)){
				supplyDemandRelationMatching.preUpdate();
				supplyDemandRelationMatchingDao.update(supplyDemandRelationMatching);
				bussinessMatchingDao.deleteRealtionByMatchingId(supplyDemandRelationMatching.getId());
				for (int i = 0; i < checkmatch.length; i++) {
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("id", UUID.randomUUID().toString().replace("-", ""));
					map.put("relationId", supplyDemandRelationMatching.getId());
					map.put("matchAttrId", checkmatch[i]);
					map.put("weight", Integer.parseInt(relationWeight[i]));
					map.put("isEntrust", supplyDemandRelationMatching.getIsEntrust());
					bussinessMatchingDao.saveRelation(map);
				}
			}else{
				supplyDemandRelationMatching.preInsert();
				supplyDemandRelationMatchingDao.insert(supplyDemandRelationMatching);
				for (int i = 0; i < checkmatch.length; i++) {
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("id", UUID.randomUUID().toString().replace("-", ""));
					map.put("relationId", supplyDemandRelationMatching.getId());
					map.put("matchAttrId", checkmatch[i]);
					map.put("weight", Integer.parseInt(relationWeight[i]));
					map.put("isEntrust", supplyDemandRelationMatching.getIsEntrust());
					bussinessMatchingDao.saveRelation(map);
				}
			}
		return null;
	}

	@Override
	public Page<SupplyDemandRelationMatching> findMatchRelationList(Page<SupplyDemandRelationMatching> page, SupplyDemandRelationMatching supplyDemandRelationMatching) {
		supplyDemandRelationMatching.setPage(page);
		page.setList(supplyDemandRelationMatchingDao.findList(supplyDemandRelationMatching));
		return page;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<Double, String> findMatchingBySupplyAndDemandId(String infoId) {
		Map<Double,String> resultMap = (Map<Double, String>) CacheUtils.get("matchingRelation", infoId);
		if(null==resultMap||resultMap.isEmpty()){
			Info info = infoDao.get(infoId.substring(0, infoId.length()-1));
			resultMap = this.findMatchingBySupplyAndDemand(info);
		}
		System.out.println(resultMap);
		return resultMap;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<Double,String> findMatchingBySupplyAndDemand(Info info) {
		Map<Double,String> resultMap = (Map<Double, String>) CacheUtils.get("matchingRelation", info.getId()+info.getStatue());
		if(null!=resultMap&&(!resultMap.isEmpty())){
			return resultMap;
		}else{
			//注：以下名词解释:infoValue=“用户当前发布的供求信息”,matchValue=“经过简单过滤后的数据”
			Info searchInfo=new Info();
			if(info.getType().equals("demand")){
				searchInfo.setType("supply");
			}else if(info.getType().equals("supply")){
				searchInfo.setType("demand");
			}
			searchInfo.setGoodsId(info.getGoodsId());
			searchInfo.setStatue(1);
            List<MatchingAttr> relationList=matchingAttrDao.findMathcingRelationByType(info.getType());
            if(relationList!=null&&(!relationList.isEmpty())){
                Integer isEntrust = relationList.get(0).getIsEntrust();
                if(isEntrust==0){
                    searchInfo.setIsEntrust(0);
                }
            }
            List<Info> infoList=infoDao.findList(searchInfo);
            infoList.remove(info);
            resultMap=new TreeMap<Double,String>(new DoComparator());
			for (Info infoResult : infoList) {
				Double gradeForDate = 0.0;
				Double gradeForNumber = 0.0;
				Double gradeForAddress = 0.0;
				for (MatchingAttr matchingAttr : relationList) {
                    if(matchingAttr.getMatchingName().contains("date")){
						Long infoValue1=info.getValidateStartDate().getTime();
						Long infoValue2=info.getValidateEndDate().getTime();
						Long matchValue1=infoResult.getValidateStartDate().getTime();
						Long matchValue2=infoResult.getValidateEndDate().getTime();
						gradeForDate = MatchingUtils.getGradeForScope(Long.toString(infoValue1),Long.toString(infoValue2),Long.toString(matchValue1),Long.toString(matchValue2),matchingAttr.getWeight());
					}else if(matchingAttr.getMatchingName().contains("number")){
						Double infoValue = info.getNumber();
						Double matchValue = infoResult.getNumber();
						gradeForNumber = MatchingUtils.getGradeForSingle(infoValue,matchValue,matchingAttr.getWeight());
					}else if(matchingAttr.getMatchingName().contains("address")){
						String infoValue1 = info.getProvinceName();
						String infoValue2 = info.getCityName();
						String infoValue3 = info.getAreaName();
						String matchValue1 = infoResult.getProvinceName();
						String matchValue2 = infoResult.getCityName();
						String matchValue3 = infoResult.getAreaName();
						gradeForAddress = MatchingUtils.getGradeForAddress(infoValue1,infoValue2,infoValue3,matchValue1,matchValue2,matchValue3,matchingAttr.getWeight());
					}
				}
				Double finalResult=gradeForDate+gradeForNumber+gradeForAddress;
				if(finalResult!=0.0){
					resultMap.put(finalResult, infoResult.getId());
					//拿出当前对象缓存
					Map<Double,String> currentMap = (Map<Double, String>) CacheUtils.get("matchingRelation", infoResult.getId()+infoResult.getStatue());
					if(null!=currentMap){
						if(!currentMap.containsValue(info.getId())){
							currentMap.put(finalResult, info.getId());
							CacheUtils.remove("matchingRelation", infoResult.getId()+infoResult.getStatue());
							CacheUtils.put("matchingRelation", infoResult.getId()+infoResult.getStatue());
						}
					}
				}
			}
			CacheUtils.put("matchingRelation", info.getId()+info.getStatue(), resultMap);
		}
		return resultMap;
	}

	@SuppressWarnings("deprecation")
	@Override
	@Transactional(readOnly = false)
	public void deleteMatchRelation(String id) {
		supplyDemandRelationMatchingDao.delete(id);
		supplyDemandRelationMatchingDao.deleteMatchRelationByRelationId(id);
	}

	@Override
	public SupplyDemandRelationMatching findRelationById(String id) {
		SupplyDemandRelationMatching supplyDemandRelationMatching = supplyDemandRelationMatchingDao.get(id);
		return supplyDemandRelationMatching;
	}

	@Override
	public List<MatchingAttr> findMatchRelationByRealtionId(String id) {
		return bussinessMatchingDao.findMatchRelationByRealtionId(id);
	}

	@Override
	public void removeUserMatchById(String id, Double grade) {
		Map<Double,String> resultMap = (Map<Double, String>) CacheUtils.get("matchingRelation", id);
		resultMap.remove(grade);
		CacheUtils.remove(id);
		CacheUtils.put(id, resultMap);
	}
}
