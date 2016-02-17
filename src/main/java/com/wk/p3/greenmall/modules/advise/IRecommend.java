package com.wk.p3.greenmall.modules.advise;

import java.util.List;

import com.wk.p3.greenmall.modules.advise.entry.Recommend;

public interface IRecommend {
	/**
	 * 
	 * @param objectType  信息类型
	 * @param recommendType 推荐类型
	 * @param id  
	 * @param userid   用户id
	 * @param startTime 开始时间  yyyy-MM-dd HH:mm:ss
	 * @param endTime   结束时间  yyy-MM-dd HH:mm:ss
	 * @return
	 */
	public List<Recommend> list(String objectType, String recommendType, String id, String userid, String startTime,String endTime,String goodsType) ;

	/**
	 * @param objectType
	 *            类型信息 Recommend.ObjectType
	 * @param recommendType
	 *            推荐平台信息 Recommend.RecommendType
	 * @param isRecommend
	 *            TRUE 推荐 FALSE 取消推荐
	 * @param id
	 *            用户推荐信息id
	 */
	public void update(String objectType, String recommendType, Boolean isRecommend, String id,String goodsType) ;

	/**
	 * 插入推荐信息
	 */
	public void insert(Recommend recommend) ;

}
