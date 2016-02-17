package com.wk.p3.greenmall.modules.advise;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import com.wk.p3.greenmall.common.utils.SpringContextHolder;
import com.wk.p3.greenmall.modules.advise.dao.RecommendDao;
import com.wk.p3.greenmall.modules.advise.entry.Recommend;

public class DelayedRecommend implements Delayed, Runnable {
	
    //执行时间
	private long submitTime;
	//延迟时间
	@SuppressWarnings("unused")
	private long delayTime;
	private Recommend recommend;


	public DelayedRecommend(long submitTime,Recommend recommend) {
		super();
		delayTime = submitTime;
		this.submitTime = TimeUnit.NANOSECONDS.convert(submitTime, TimeUnit.MILLISECONDS) + System.nanoTime();
		this.recommend=recommend;
	}

	@Override
	public void run() {
		RecommendDao recommendDao=SpringContextHolder.getBean("recommendDao");
		recommendDao.update(recommend.getObjectType(), recommend.getRecommendType(), false, recommend.getObjectId(),recommend.getGoodsType());
	}

	@SuppressWarnings("static-access")
	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(submitTime - System.nanoTime(), unit.NANOSECONDS);
	}

	@Override
	public int compareTo(Delayed o) {
		DelayedRecommend that = (DelayedRecommend) o;
		return submitTime > that.submitTime ? 1 : (submitTime < that.submitTime ? -1 : 0);
	}
}
