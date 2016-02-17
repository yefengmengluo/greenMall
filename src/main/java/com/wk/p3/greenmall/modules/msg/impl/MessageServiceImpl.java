package com.wk.p3.greenmall.modules.msg.impl;

import com.wk.p3.greenmall.modules.msg.*;
import com.wk.p3.greenmall.modules.msg.exception.SendException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cc on 15-12-19.
 */
@Service
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageService,DeliverInterceptor {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private DeliverInterceptor interceptor = null;

    @Override
    public void sendMessage(Sender sender,Msg msg,Target target,DeliverInterceptor interceptor) throws SendException {
        this.interceptor = interceptor;
        //条件检查
        if(msg == null || sender == null || target==null){
            throw new NullPointerException("参数为空");
        }
        //构造发送上下文
        Map<String,Object> context = new HashMap<String, Object>();
        //发送流程
        if(pre(msg, target, context)){
            post(msg, target, context);
//            logger.debug("begin to send message :"+msg.toString());
            sender.send(msg,target);
            afterCompletion(msg,target,context,null);
        }
    }

    @Override
    public void sendMessage(Sender sender, Msg msg, Target target) throws SendException {
        sendMessage(sender, msg, target, null);
    }

    @Override
    public boolean pre(Msg msg,Target target,Map<String,Object> context) throws SendException {
        List<Restriction> restrictions = target.getRestrictions();
        if(restrictions!=null){
            for(Restriction r:restrictions){
                if(r.check(context)){
                    return false;
                }
            }
        }
        logger.debug("消息投递拦截-preSend");
        return (interceptor!=null)?interceptor.pre(msg, target, context):true;
    }

    @Override
    public void post(Msg msg,Target target,Map<String,Object> context) throws SendException {
        if(interceptor!=null){
            logger.debug("消息投递拦截-postSend");
            interceptor.post(msg, target, context);
        }

    }

    @Override
    public void afterCompletion(Msg msg,Target target,Map<String,Object> context, Exception ex) throws SendException {
        if(interceptor!=null){
            logger.debug("消息投递拦截-afterCompletion");
            interceptor.afterCompletion(msg,target,context,ex);
        }
    }

}
