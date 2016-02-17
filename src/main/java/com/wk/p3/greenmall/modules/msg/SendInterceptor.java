package com.wk.p3.greenmall.modules.msg;

import com.wk.p3.greenmall.modules.msg.exception.SendException;

import java.io.Serializable;

/**
 * Created by cc on 15-12-14.
 * 数据发送对象,每种发送对象有不同的发送方法
 */

public interface SendInterceptor{

    public boolean preSend(Msg msg) throws SendException;

    public void postSend(Msg msg) throws SendException;

    public void afterCompletion(Msg msg, Exception ex) throws SendException;

}
