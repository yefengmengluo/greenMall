package com.wk.p3.greenmall.modules.advise.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.advise.entry.Recommend;

/**
 * 
 * @author Robin
 *
 */
@MyBatisDao
public interface RecommendDao {
	public Integer insert(@Param("recommend")Recommend recommend);
	/**
	 * 
	 * @param type  用户自定义类型
	 * @param userid  用户id
	 * @param startTime 开始时间
	 * @param endTime   结束时间
	 * @return
	 */
	public List<Recommend>list(@Param("objectType")String objectType,@Param("recommendType")String recommendType,@Param("id")String id,@Param("userid")String userid,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("goodsType")String goodsType);
	/**
	 * @param type  更新类型
	 * @param isRecommnd TRUE 推荐 FALSE 取消
	 * @param id 推荐对象ID
	 * @return
	 */
	public Integer update(@Param("objectType")String objectType,@Param("recommendType")String recommnedType,@Param("isRecommnd")Boolean isRecommnd,@Param("id")String id,@Param("goodsType")String goodsType);
	
}
