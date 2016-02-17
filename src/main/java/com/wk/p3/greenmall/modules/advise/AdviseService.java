package com.wk.p3.greenmall.modules.advise;

/**
 * Created by cc on 15-12-14.
 * 推荐服务类
 */
public interface AdviseService {
    /**
     * 通过一个可推荐的对象得到推荐的对象
     * @param recommendAble
     * @return
     */
    public Recommend getRecommendsBy(RecommendAble recommendAble);
}
