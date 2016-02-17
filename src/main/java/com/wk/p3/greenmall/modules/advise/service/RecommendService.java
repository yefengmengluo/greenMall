package com.wk.p3.greenmall.modules.advise.service;

import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wk.p3.greenmall.modules.advise.DelayedRecommend;
import com.wk.p3.greenmall.modules.advise.IRecommend;
import com.wk.p3.greenmall.modules.advise.RecommendCustomer;
import com.wk.p3.greenmall.modules.advise.dao.RecommendDao;
import com.wk.p3.greenmall.modules.advise.entry.Recommend;
/**
 * 
 * @author Robin 推荐本地接口实现
 *
 */
@Service
public class RecommendService implements IRecommend{
	private DelayQueue<DelayedRecommend> queue;
	public RecommendService() {
		ExecutorService es = Executors.newCachedThreadPool();  
		queue = new DelayQueue<DelayedRecommend>(); 
		es.submit(new RecommendCustomer(queue));
	} 

	@Autowired
	RecommendDao recommendDao;

	/**
	 * 推荐信息返回
	 * 
	 * @return List<Recommend>
	 */
	public List<Recommend> list(String objectType, String recommendType, String id, String userid, String startTime,
			String endTime,String goodsType) {
		return recommendDao.list(objectType, recommendType, id, userid, startTime, endTime,goodsType);
	}

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
	public void update(String objectType, String recommendType, Boolean isRecommend, String id,String goodsType) {
		recommendDao.update(objectType, recommendType, isRecommend, id,goodsType);
	}

	/**
	 * 插入推荐信息
	 */
	public void insert(Recommend recommend) {
		recommendDao.insert(recommend);
		this.queue.add(new DelayedRecommend(recommend.getEndTime().getTime()-recommend.getStartTime().getTime(),recommend));
	}



}
