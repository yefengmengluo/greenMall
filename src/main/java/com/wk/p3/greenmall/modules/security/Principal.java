package com.wk.p3.greenmall.modules.security;

import com.wk.p3.greenmall.modules.user.LoginType;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * 登录用户凭证
 * 多个用户凭证 对应一个user
 * Created by cc on 15-11-28.
 */
public interface Principal{

    /**
     * 获得登录凭证(登录用户)id,对于所有实现类,id必须唯一
     * @return
     */
    public String getId();

    /**
     * 获得登陆凭证
     * 比如：用户名密码，微信openId等
     * @return
     */
    public AuthenticationToken getAuthentication();

    /**
     * 获得登录凭证(登录用户)的登录的类型，比如：
     *  微信登录为：MpUser
     * @return
     */
    public LoginType getType();

    /**
     * 获得PersonId
     * @return
     */
    public String getPersonId();

}
