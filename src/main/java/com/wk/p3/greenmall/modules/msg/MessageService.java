package com.wk.p3.greenmall.modules.msg;

import com.wk.p3.greenmall.modules.msg.exception.SendException;

/**
 * 消息服务类
 * 拦截器的优先级低于限制条件
 * Created by cc on 15-12-14.
 */
public interface MessageService {

    /**
     * 消息发送接口
     * @param sender               消息发送器
     * @param msg                  消息对象
     * @param target               消息发送的目标对象
     * @param interceptor          消息发送后的拦截器
     * @throws SendException
     */
    public void sendMessage(Sender sender,Msg msg,Target target,DeliverInterceptor interceptor) throws SendException;

    /**
     * 消息发送接口
     * @param sender               消息发送器
     * @param msg                  消息对象
     * @param target               消息发送的目标对象
     * @throws SendException
     */
    public void sendMessage(Sender sender,Msg msg,Target target) throws SendException;
}
