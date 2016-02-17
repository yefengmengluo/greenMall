package com.wk.p3.greenmall.modules.msg;

import com.wk.p3.greenmall.modules.msg.exception.SendException;
import com.wk.p3.greenmall.modules.sys.entity.User;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * 消息发送目的对象
 * Created by cc on 15-12-14.
 */
public abstract class TargetInterceptor implements Target, SendInterceptor{

    abstract public void send0(Msg msg) throws SendException;

    //发送时的拦截流程
    public void send(Msg msg) throws SendException {
        if (preSend(msg)) {
            postSend(msg);
            send0(msg);
            afterCompletion(msg, null);
        }
    }


}
