package com.wk.p3.greenmall.modules.msg;

import com.wk.p3.greenmall.modules.msg.exception.SendException;

import java.util.Map;

/**
 * Created by cc on 15-12-30.
 */
public interface DeliverInterceptor {

    public boolean pre(Msg msg,Target target,Map<String,Object> context) throws SendException;

    public void post(Msg msg,Target target,Map<String,Object> context) throws SendException;

    public void afterCompletion(Msg msg,Target target,Map<String,Object> context, Exception ex) throws SendException;
}
