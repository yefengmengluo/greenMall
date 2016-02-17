package com.wk.p3.greenmall.modules.msg.restriction;

import com.wk.p3.greenmall.modules.msg.Restriction;

/**
 * 审核后发送限制
 * Created by cc on 15-12-19.
 */
public class Confirm implements Restriction {

    @Override
    public boolean check(Object context) {
        return false;
    }

}
