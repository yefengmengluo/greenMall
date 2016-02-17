package com.wk.p3.greenmall.modules.msg.impl;

import com.wk.p3.greenmall.modules.msg.*;
import com.wk.p3.greenmall.modules.msg.entity.MsgTemplate;
import com.wk.p3.greenmall.modules.msg.exception.ErrorException;
import com.wk.p3.greenmall.modules.msg.exception.SendException;
import com.wk.p3.greenmall.modules.msg.impl.sms.SMSTarget;
import com.wk.p3.greenmall.modules.msg.sender.SimpleSender;
import com.wk.p3.greenmall.modules.msg.service.MsgSmsService;
import com.wk.p3.greenmall.modules.msg.service.MsgTemplateService;
import com.wk.p3.greenmall.modules.msg.service.MsgUserService;
import com.wk.p3.greenmall.modules.sys.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 消息发送业务接口
 * Created by cc on 16-1-11.
 */
@Service
public class MessageBusinessServiceImpl implements MessageBusinessService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MessageService messageService;

    @Autowired
    private MsgTemplateService msgTemplateService;

    @Autowired
    private MsgUserService msgUserService;

    @Autowired
    private MsgSmsService msgSmsService;

    private Target target;

    @Override
    public void sendMatchSMSMessage(String code, List<String> ids, Map<String, ?> model) throws SendException {
        //构造消息模板
        MsgTemplate msgTemplate = new MsgTemplate();
        //业务code
        msgTemplate.setCode(code);
        //平台
        msgTemplate.setPlateform(Platform.PhoneMSG.toString());
        final List<MsgTemplate> templates =  msgTemplateService.findList(msgTemplate);
        if(templates.size()!=1){
            logger.debug("模板数据量不唯一,数量为:["+templates.size()+"],发送失败");
            throw new ErrorException();
        }
        //构造消息发送目标
        if(target==null){
            target = new SMSTarget();
        }
        FreeMarkerTemplate freeMarkerTemplate = new FreeMarkerTemplate(templates.get(0).getCode(),templates.get(0).getTemplate());
        target.setUsers(ids);
        messageService.sendMessage(new SimpleSender(),new FreeMarkerMsg(freeMarkerTemplate,model),target);
    }

    //测试专用
    public void setTarget(Target target) {
        this.target = target;
    }
}
