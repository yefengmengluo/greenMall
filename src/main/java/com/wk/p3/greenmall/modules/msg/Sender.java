package com.wk.p3.greenmall.modules.msg;

import com.wk.p3.greenmall.modules.msg.exception.SendException;

/**
 * Created by cc on 15-12-14.
 * 数据发送对象,每种发送对象有不同的发送方法
 */

public interface Sender{

    /**
     * 投递到队列
     * @param msg       消息对象
     * @param target    消息发送目的对象
     * @throws SendException
     */
    public void send(Msg msg,Target target) throws SendException;

}
