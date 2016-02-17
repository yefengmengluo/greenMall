package com.wk.p3.greenmall.modules.msg.restriction;

import com.wk.p3.greenmall.modules.msg.Restriction;

/**
 * 单位时间内发送总量限制
 * Created by cc on 15-12-19.
 */
public class AmountLimit implements Restriction {
    @Override
    public boolean check(Object context) {
        return false;
    }
}
