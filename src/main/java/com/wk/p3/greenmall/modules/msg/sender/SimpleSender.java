package com.wk.p3.greenmall.modules.msg.sender;

import com.wk.p3.greenmall.common.utils.SpringContextHolder;
import com.wk.p3.greenmall.modules.msg.Msg;
import com.wk.p3.greenmall.modules.msg.Target;
import com.wk.p3.greenmall.modules.msg.Sender;
import com.wk.p3.greenmall.modules.msg.Template;
import com.wk.p3.greenmall.modules.msg.exception.SendException;
import com.wk.p3.greenmall.modules.msg.impl.TargetToSend;
import com.wk.p3.greenmall.modules.sys.dao.UserDao;
import org.apache.activemq.ScheduledMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;
import java.io.IOException;

/**
 * Created by cc on 15-12-14.
 */
@Service
public class SimpleSender extends AbstractSender {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessageConfig(ObjectMessage message) {
        //发送一条及时消息
    }

}
