package com.wk.p3.greenmall.modules.advise;

import java.util.concurrent.DelayQueue;

public class RecommendCustomer implements Runnable{

	private DelayQueue<DelayedRecommend> delayQueue;

	public RecommendCustomer(DelayQueue<DelayedRecommend> delayQueue) {
		super();
		this.delayQueue = delayQueue;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				delayQueue.take().run();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}


}
