package com.wk.p3.greenmall.modules.match.dao;

import java.util.List;
import java.util.Map;

import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.info.entity.Info;
import com.wk.p3.greenmall.modules.match.entity.MatchingAttr;

/**
 * @author zhaomeng
 * @description 商机匹配Dao
 * 2015年12月23日
 */
@MyBatisDao
public interface BussinessMatchingDao {

	/**
	 * 保存属性与关系中间表
	 * @param map
	 */
	void saveRelation(Map<String, Object> map);
	/**
	 * 删除中间表中数据
	 * @param id
	 */
	void deleteRealtionByMatchingId(String id);
	/**
	 * 通过关系id查询属性和权重
	 * @param id
	 * @return
	 */
	List<MatchingAttr> findMatchRelationByRealtionId(String id);
	/**
	 * 通过用户id查询用户公司主营产品
	 * @param id
	 * @return
	 */
	List<String> findGoodsTypeByUser(String id);
	/**
	 * 通过用户id查询用户地址
	 * @param id
	 * @return
	 */
	Map<String, String> findAddressByUser(String id);


}
