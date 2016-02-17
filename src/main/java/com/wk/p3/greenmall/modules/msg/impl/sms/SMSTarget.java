package com.wk.p3.greenmall.modules.msg.impl.sms;

import com.wk.p3.greenmall.common.utils.SpringContextHolder;
import com.wk.p3.greenmall.modules.msg.Msg;
import com.wk.p3.greenmall.modules.msg.Platform;
import com.wk.p3.greenmall.modules.msg.Restriction;
import com.wk.p3.greenmall.modules.msg.Target;
import com.wk.p3.greenmall.modules.msg.exception.SendException;
import com.wk.p3.greenmall.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cc on 15-12-25.
 */
@Service
public class SMSTarget implements Target {

    public List<String> userIds;

    List<Restriction> restrictions;

    public static SmsService smsService = SpringContextHolder.getBean(SmsService.class);

    @Override
    public Platform getPlatform() {
        return Platform.PhoneMSG;
    }

    @Override
    public void setRestrictions(List<Restriction> restrictions) {
        this.restrictions = restrictions;
    }

    @Override
    public List<Restriction> getRestrictions() {
        return restrictions;
    }

    @Override
    public void setUsers(List<String> users) {
        this.userIds = users;
    }

    @Override
    public void send(Msg msg) throws SendException {
        Sms sms =  new Sms(userIds,msg.toMsg());
        smsService.sendSMS(sms);
    }
}
