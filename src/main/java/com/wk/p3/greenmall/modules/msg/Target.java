package com.wk.p3.greenmall.modules.msg;

import com.wk.p3.greenmall.modules.msg.exception.SendException;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * 消息发送目的对象
 * Created by cc on 15-12-14.
 */
public interface Target extends Serializable{
    /**
     * 获得平台类型
     * 比如平台有手机短信，微信等
     * @return
     */
    public Platform getPlatform();

    /**
     * 用户限制条件
     * 比如：
     *      1.时间范围限制
     *      2.发送时间间隔限制
     *      3.单位时间发送量限制
     *      4.审核限制,即是否需要审核
     * @param restrictions
     */
    public void setRestrictions(List<Restriction> restrictions);

    /**
     * 获得用户限制
     * @return
     */
    public List<Restriction> getRestrictions();

    /**
     * 发送的目的用户集合
     * @param userIds
     */
    public void setUsers(List<String> userIds);

    /**
     * 发送的消息
     * @param msg
     * @throws IOException
     */
    public void send(Msg msg) throws SendException;
}
