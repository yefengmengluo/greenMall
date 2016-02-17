package com.wk.p3.greenmall.modules.msg.impl.mp;

import com.wk.p3.greenmall.modules.msg.Msg;
import com.wk.p3.greenmall.modules.msg.Platform;
import com.wk.p3.greenmall.modules.msg.Restriction;
import com.wk.p3.greenmall.modules.msg.Target;
import com.wk.p3.greenmall.modules.msg.exception.SendException;
import com.wk.p3.greenmall.modules.msg.impl.sms.Sms;
import com.wk.p3.greenmall.modules.msg.impl.sms.SmsService;
import com.wk.p3.greenmall.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cc on 15-12-25.
 */
@Service
public class MpTarget implements Target {

    public List<String> users;

    @Autowired
    SmsService smsService;

    @Override
    public Platform getPlatform() {
        return Platform.PhoneMSG;
    }

    @Override
    public void setRestrictions(List<Restriction> restrictions) {

    }

    @Override
    public List<Restriction> getRestrictions() {
        return null;
    }

    @Override
    public void setUsers(List<String> users) {
        this.users = users;
    }

    @Override
    public void send(Msg msg) throws SendException {
        //通过用户User得到openId

        //通过一个MpService.send(openid,msg)发送消息

    }
}
