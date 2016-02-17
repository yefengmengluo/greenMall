package com.wk.p3.greenmall.modules.security.system;

import com.wk.p3.greenmall.modules.security.Principal;
import com.wk.p3.greenmall.modules.security.Person;
import com.wk.p3.greenmall.modules.user.LoginType;
import org.apache.shiro.authc.AuthenticationToken;

import java.io.Serializable;

/**
 * Created by cc on 16-1-18.
 */
public class MpPrincipal implements Principal,Serializable{
    private static final long serialVersionUID = 1L;

    private String openid;		// openId,公众号和应用关联的id
    private String unionid;		// unionId,微信中和个人关联的id
    private Person user;

    public MpPrincipal(String openid, String unionid) {
        this.openid = openid;
        this.unionid = unionid;
        this.user = null;
    }

    @Override
    public String getId() {
        return openid;
    }

    @Override
    public AuthenticationToken getAuthentication(){
        return null;
    }

    public String getLoginName() {
        return openid+unionid;
    }

    @Override
    public LoginType getType() {
        return LoginType.mp;
    }

    @Override
    public String getPersonId() {
        return user.getId();
    }

    public Person getUser() {
        return user;
    }
}
