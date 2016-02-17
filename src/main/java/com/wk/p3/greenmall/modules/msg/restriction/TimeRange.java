package com.wk.p3.greenmall.modules.msg.restriction;

import com.wk.p3.greenmall.modules.msg.Restriction;

/**
 * 发送时刻范围限制
 * Created by cc on 15-12-14.
 */
public class TimeRange implements Restriction {

    @Override
    public boolean check(Object context) {
        return false;
    }
}
