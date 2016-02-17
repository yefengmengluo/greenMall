package com.wk.p3.greenmall.modules.match.service;

import java.util.List;
import java.util.Map;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.modules.info.entity.Info;
import com.wk.p3.greenmall.modules.match.entity.MatchingAttr;
import com.wk.p3.greenmall.modules.match.entity.SupplyDemandRelationMatching;
import com.wk.p3.greenmall.modules.sys.entity.User;

/**
 * Created by cc on 15-12-14.
 */
public interface MatchService {
	/**
	 * 根据供求信息得到商机匹配信息(返回的是一个TreeMap集合,键是分数、值是对应的供求信息id)
	 * @param info
	 * @return 
	 */
	public Map<Double, String> findMatchingBySupplyAndDemand(Info info);
	/**
	 * 根据供求信息id得到商机匹配信息(返回的是一个TreeMap集合,键是分数、值是对应的供求信息id)
	 * @param infoId(注意：此处传参必须是info的id+info的状态)
	 * @return
	 */
	public Map<Double,String> findMatchingBySupplyAndDemandId(String infoId);
	/**
	 * 根据用户信息得到商机匹配信息(返回的是一个TreeMap集合,键是分数、值是对应的供求信息id)
	 * @param personId
	 * @return
	 */
	public Map<Double, String> findMatchingByUser(String personId);
	/**
	 * 根据供求信息infoId(注意：此处传参必须是info的id+info的状态)和分数删除用户不感兴趣的数据
	 */
	public void removeUserMatchById(String infoId,Double grade);
	/**
	 * 查询匹配属性列表
	 * @return
	 */
	public List<MatchingAttr> findMatchingAttrList();
	/**
	 * 添加匹配规范
	 * @param supplyDemandRelationMatching 
	 * @param checkmatch 
	 * @param ifUpdate 
	 * @param ifCover 
	 * @return 
	 */
	public String saveMatchingRelation(SupplyDemandRelationMatching supplyDemandRelationMatching,String[] relationWeight, String[] checkmatch, String ifUpdate, String ifCover);
	/**
	 * 显示匹配规范列表 
	 * @param supplyDemandRelationMatching 
	 * @param page 
	 * @return
	 */
	public Page<SupplyDemandRelationMatching> findMatchRelationList(Page<SupplyDemandRelationMatching> page, SupplyDemandRelationMatching supplyDemandRelationMatching);
	/**
	 * 删除匹配规范
	 * @param id
	 */
	public void deleteMatchRelation(String id);
	/**
	 * 根据id查询匹配规范
	 * @param id
	 * @return
	 */
	public SupplyDemandRelationMatching findRelationById(String id);
	/**
	 * 根据id查询中间表（查询此供求信息匹配的属性字段以及权重）
	 * @param id
	 * @return
	 */
	public List<MatchingAttr> findMatchRelationByRealtionId(String id);
	
}
