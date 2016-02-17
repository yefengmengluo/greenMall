package com.wk.p3.greenmall.modules.msg.restriction;

import com.wk.p3.greenmall.modules.msg.Restriction;

/**
 * 发送时间间隔限制
 * Created by cc on 15-12-19.
 */
public class PeriodLimit implements Restriction {
    @Override
    public boolean check(Object context) {
        return false;
    }
}
