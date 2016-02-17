package com.wk.p3.greenmall.modules.msg;

/**
 * 对用户的限制接口
 * 限制接口包含着对用户的限制
 * Created by cc on 15-12-14.
 */
public interface Restriction {

    public boolean check(Object context);

}
