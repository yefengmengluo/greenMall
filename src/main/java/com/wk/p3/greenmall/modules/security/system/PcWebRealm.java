package com.wk.p3.greenmall.modules.security.system;

import com.wk.p3.greenmall.common.web.Servlets;
import com.wk.p3.greenmall.modules.security.Person;
import com.wk.p3.greenmall.modules.security.Principal;
import com.wk.p3.greenmall.modules.security.Role;
import com.wk.p3.greenmall.modules.security.SecurityService;
import com.wk.p3.greenmall.modules.sys.service.SystemService;
import com.wk.p3.greenmall.modules.sys.utils.LogUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

/**
 * Created by cc on 16-1-18.
 */
@Service
public class PcWebRealm  extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityService securityService;

    /**
     * 认证回调函数, 登录时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        Principal principal = securityService.getPrincipalByToken(authcToken);
        if (principal != null) {
            return new SimpleAccount(principal,authcToken.getCredentials(),getName());
        } else {
            return null;
        }
    }

    /**
     * 获得当前Realm名称
     * @return
     */
    public String getName() {
        return getClass().getName();
    }

    /**
     * 指定接受的token类型
     * @return
     */
    public Class getAuthenticationTokenClass() {
        return PcWebToken.class;
    }

    /**
     * 自定义加密方法
     * @return
     */
    public CredentialsMatcher getCredentialsMatcher() {
        return new CredentialsMatcher() {
            @Override
            public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
                return new String(((char[])token.getCredentials())).equals(new String(((char[])info.getCredentials())));
            }
        };
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object o = getAvailablePrincipal(principals);
        Principal principal = null;
        if(o!=null && o instanceof Principal){
            principal = (Principal) getAvailablePrincipal(principals);
        }else{
            return null;
        }
        Person person = securityService.getPersonByPrincipal(principal);
        if (person != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            for (Role role : person.getRoleSet()){
                info.addRole(role.getEnname());
            }
            // 记录登录日志
            LogUtils.saveLog(Servlets.getRequest(), "系统登录");
            return info;
        } else {
            return null;
        }
    }

    @Override
    protected void checkPermission(Permission permission, AuthorizationInfo info) {
        authorizationValidate(permission);
        super.checkPermission(permission, info);
    }

    @Override
    protected boolean[] isPermitted(List<Permission> permissions, AuthorizationInfo info) {
        if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
                authorizationValidate(permission);
            }
        }
        return super.isPermitted(permissions, info);
    }

    @Override
    public boolean isPermitted(PrincipalCollection principals, Permission permission) {
        authorizationValidate(permission);
        return super.isPermitted(principals, permission);
    }

    @Override
    protected boolean isPermittedAll(Collection<Permission> permissions, AuthorizationInfo info) {
        if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
                authorizationValidate(permission);
            }
        }
        return super.isPermittedAll(permissions, info);
    }

    /**
     * 授权验证方法
     * @param permission
     */
    private void authorizationValidate(Permission permission){
        // 模块授权预留接口
    }

    /**
     * 设定密码校验的Hash算法与迭代次数
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(SystemService.HASH_ALGORITHM);
        matcher.setHashIterations(SystemService.HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }

//	/**
//	 * 清空用户关联权限认证，待下次使用时重新加载
//	 */
//	public void clearCachedAuthorizationInfo(Principal principal) {
//		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
//		clearCachedAuthorizationInfo(principals);
//	}

    /**
     * 清空所有关联认证
     * @Deprecated 不需要清空，授权缓存保存到session中
     */
    @Deprecated
    public void clearAllCachedAuthorizationInfo() {
//		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
//		if (cache != null) {
//			for (Object key : cache.keys()) {
//				cache.remove(key);
//			}
//		}
    }

}
