package com.wk.p3.greenmall.modules.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;

import java.util.Set;

/**
 * Created by cc on 15-12-8.
 */
public interface SecurityService {

    /**
     * 通过用户名，密码构造登陆凭证
     * @param username
     * @param password
     * @return
     */
    public Principal newPrincipalFromPassword(String username,String password,String loginType);

    /**
     * 登陆
     */
    public void login(Principal principal) throws AuthenticationException;

    /**
     * 登出
     */
    public void logout();

    /**
     * 登陆状态
     */
    public boolean isLogged();

    /**
     * 登录用户注册,并绑定到user
     * @param principal
     */
    public Person register(Principal principal);

    /**
     * 获得当前登录用户
     * @return
     */
    public Person currentPerson();

    /**
     * 给用户Person设置角色
     * @param person
     * @param roles
     */
    public void setRoles(Person person, Set<Role> roles);

    /**
     * 通过AuthenticationToken,查找Principal实例
     * @param authcToken
     * @return
     */
    public Principal getPrincipalByToken(AuthenticationToken authcToken);

    /**
     * 通过principal找到对应的唯一用户
     * @param principal
     * @return
     */
    public Person getPersonByPrincipal(Principal principal);

}


