package com.wk.p3.greenmall.modules.msg.sender;

import com.wk.p3.greenmall.modules.msg.Msg;
import com.wk.p3.greenmall.modules.msg.Target;
import com.wk.p3.greenmall.modules.msg.Sender;
import com.wk.p3.greenmall.modules.msg.Template;
import com.wk.p3.greenmall.modules.msg.exception.SendException;
import org.apache.activemq.ScheduledMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.io.IOException;
import java.util.Date;

/**
 * Created by cc on 15-12-14.
 */
public class FixTimeSender extends AbstractSender {

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 固定发送时间
     */
    private Date fixDate;

    public FixTimeSender(Date fixDate) {
        this.fixDate = fixDate;
    }

    @Override
    public void onMessageConfig(ObjectMessage message) throws JMSException {
//        message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,periodTime);
    }


}
