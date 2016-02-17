package com.wk.p3.greenmall.modules.advise;

import java.util.List;

/**
 * Created by cc on 15-12-14.
 * 推荐的对象接口
 */
public interface Recommend {
    /**
     * 获得推荐对象的标签
     * @return
     */
    public List<Tag> getTags();

    /**
     * 获得推荐对象的定位
     * @return
     */
    public Location getLocation();
}
