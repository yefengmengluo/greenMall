package com.wk.p3.greenmall.modules.security.impl;

import com.wk.p3.greenmall.modules.security.Person;
import com.wk.p3.greenmall.modules.security.Principal;
import com.wk.p3.greenmall.modules.security.Role;
import com.wk.p3.greenmall.modules.security.SecurityService;
import com.wk.p3.greenmall.modules.security.entity.SecurityUser;
import com.wk.p3.greenmall.modules.security.entity.SecurityUserToken;
import com.wk.p3.greenmall.modules.security.service.SecurityRoleService;
import com.wk.p3.greenmall.modules.security.service.SecurityUserService;
import com.wk.p3.greenmall.modules.security.service.SecurityUserTokenService;
import com.wk.p3.greenmall.modules.security.system.PcWebToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cas.CasToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by cc on 16-1-20.
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    SecurityUserService securityUserService ;

    @Autowired
    SecurityRoleService securityRoleService ;

    @Autowired
    SecurityUserTokenService securityUserTokenService ;


    @Override
    public Principal newPrincipalFromPassword(String username, String password,String loginType) {
        SecurityUserToken userToken = new SecurityUserToken();
        userToken.setLoginName(username);
        userToken.setPassword(password);
        userToken.setLoginType(loginType);
        return userToken;
    }

    @Override
    public void login(Principal principal) throws AuthenticationException {
        SecurityUtils.getSubject().login(principal.getAuthentication());
    }

    @Override
    public void logout() {
        SecurityUtils.getSubject().logout();
    }

    @Override
    public boolean isLogged() {
        return SecurityUtils.getSubject().isAuthenticated();
    }

    @Override
    @Transactional(readOnly = false)
    public Person register(Principal principal) {
        AuthenticationToken a = principal.getAuthentication();
        Person person = null;
        if(a instanceof PcWebToken){//用户密码验证
            PcWebToken token = (PcWebToken)a;
            //构造usertoken和user类
            SecurityUserToken userToken = new SecurityUserToken();
            SecurityUser user = new SecurityUser();
            //设置usertoken和user类
            userToken.setLoginName(token.getUsername());
            userToken.setPassword(new String(token.getPassword()));
            userToken.setLoginType(token.getLoginType().toString());
            //保存usertoken和user类
            securityUserService.save(user);
            userToken.setUniqueUser(user.getId());
            securityUserTokenService.save(userToken);
            person = user;
        }else if(a instanceof CasToken){//微信的CasToken
            //todo 后面再实现
        }else{
            //todo 后面再实现
        }
        return person;
    }

    @Override
    public Person currentPerson() {
        Object subject = SecurityUtils.getSubject().getPrincipal();
        if(subject instanceof Principal){
            String userId = ((Principal)subject).getPersonId();
            SecurityUser user = securityUserService.get(userId);
            Set<Role> roles = securityRoleService.getRolesByPerson(user.getRoles());
            user.setRoles(roles);
            return user;
        }else{
            return null;
        }
    }


    private String getRoleids(Set<Role> roles){
        StringBuffer sb = new StringBuffer();
        for(Role role:roles){
            sb.append(role.getId()).append(",");
        }
        int i = sb.lastIndexOf(",");
        if(i>0){
            sb.deleteCharAt(i);
        }
        return sb.toString();
    }

    @Override
    @Transactional(readOnly = false)
    public void setRoles(Person person, Set<Role> roles) {
        SecurityUser securityUser = securityUserService.get(person.getId());
        securityUser.setRoles(getRoleids(roles));
        securityUserService.save(securityUser);
    }

    @Override
    public Principal getPrincipalByToken(AuthenticationToken t) {
        if(t instanceof PcWebToken){//用户密码验证
            PcWebToken token = (PcWebToken)t;
            //构造usertoken和user类
            SecurityUserToken userToken = new SecurityUserToken();
            //设置usertoken和user类
            userToken.setLoginName(token.getUsername());
            userToken.setPassword(new String(token.getPassword()));
            userToken.setLoginType(token.getLoginType().toString());
            SecurityUserToken securityUserToken = securityUserTokenService.findbyToken(userToken);
            if(securityUserToken!=null){
                return securityUserToken;
            }else{
                return null;
            }
        }else if(t instanceof CasToken){//微信的CasToken

        }else{

        }
        return null;
    }

    @Override
    public Person getPersonByPrincipal(Principal principal) {
        String userId = securityUserTokenService.getUserIdByTokenId(principal.getId());
        SecurityUser securityUser = securityUserService.get(userId);
        Set<Role> roles = securityRoleService.getRolesByPerson(securityUser.getRoles());
        securityUser.setRoles(roles);
        return securityUser;
    }


}
