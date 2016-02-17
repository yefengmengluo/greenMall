package com.wk.p3.greenmall.modules.security.system;

import com.wk.p3.greenmall.common.config.Global;
import com.wk.p3.greenmall.common.security.shiro.session.SessionDAO;
import com.wk.p3.greenmall.modules.security.Principal;
import com.wk.p3.greenmall.modules.sys.utils.UserUtils;
import com.wk.p3.greenmall.modules.wechat.Util;
import com.wk.p3.greenmall.modules.wechat.entity.mp.MpUser;
import com.wk.p3.greenmall.modules.wechat.service.mp.MpUserService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

/**
 * Created by cc on 15-11-26.
 */
@Service
public class MpCasRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MpUserService mpUserService;

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private SessionDAO sessionDao;

    /**
     * 认证回调函数, 登录时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        CasToken token = (CasToken) authcToken;
        // 通过code请求微信得到完整的微信用户信息
        WxMpUser wxMpUser = null;
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
        try {
             wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken((String)token.getCredentials());
        } catch (WxErrorException e) {
            throw new AuthenticationException("腾讯服务器验证code失败");
        }
        if (logger.isDebugEnabled()){
            logger.debug("wxMpOAuth2AccessToken", wxMpOAuth2AccessToken.toString());
        }
        try {
            wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken,null);
        } catch (WxErrorException e) {
            throw new AuthenticationException("腾讯服务器获得微信个人信息失败");
        }
        if (logger.isDebugEnabled()){
            logger.debug("wxMpUser", wxMpUser.toString());
        }

        MpUser mpUser = mpUserService.getByOpenId(wxMpUser.getOpenId());

        if (mpUser != null) {//存在该用户，直接返回SimpleAuthenticationInfo
            if (logger.isDebugEnabled()){
                logger.debug("存在该微信公众号用户:", "[openId=",wxMpUser.getOpenId(),",unionId=",wxMpUser.getUnionId(),"]");
            }
        } else {//不存在该用户，新建该用户，同时直接返回SimpleAuthenticationInfo
            mpUser = Util.convert(wxMpUser);
            mpUserService.save(mpUser);
            if (logger.isDebugEnabled()){
                logger.debug("不存在的微信公众号用户:", "[openId=",wxMpUser.getOpenId(),",unionId=",wxMpUser.getUnionId(),"],保存成功");
            }
        }
        Principal principal = new MpPrincipal(mpUser.getOpenid(),mpUser.getUnionid());
        return new SimpleAuthenticationInfo(principal,ByteSource.Util.bytes(principal.toString()),getName());
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object principalObj = getAvailablePrincipal(principals);
        Principal principal = null;
        if(!(principalObj instanceof MpPrincipal)){
            return null;
        }else{
            principal = (MpPrincipal)principalObj;
        }
        if(!(principal instanceof MpPrincipal)) return null;
        // 获取当前已登录的用户
        if (!Global.TRUE.equals(Global.getConfig("user.multiAccountLogin"))){
            Collection<Session> sessions = sessionDao.getActiveSessions(true, principal, UserUtils.getSession());
            if (sessions.size() > 0){
                // 如果是登录进来的，则踢出已在线用户
                if (UserUtils.getSubject().isAuthenticated()){
                    for (Session session : sessions){
                        sessionDao.delete(session);
                    }
                }
                // 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。
                else{
                    UserUtils.getSubject().logout();
                    throw new AuthenticationException("msg:账号已在其它地方登录，请重新登录。");
                }
            }
        }
        MpUser mpUser = mpUserService.getByOpenId(principal.getId());
        if (mpUser != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//            List<Menu> list = UserUtils.getMenuList();
//            for (Menu menu : list){
//                if (StringUtils.isNotBlank(menu.getPermission())){
//                    // 添加基于Permission的权限信息
//                    for (String permission : StringUtils.split(menu.getPermission(),",")){
//                        info.addStringPermission(permission);
//                    }
//                }
//            }
            // 添加用户权限
            info.addStringPermission("user");
            // 添加用户角色信息

            //for (Role role : user.getRoleList()){
                //info.addRole(role.getEnname());
            //}
            // 记录登录日志
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
     * token和Principal的匹配全部通过
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        CredentialsMatcher matcher = new AllowAllCredentialsMatcher();
        setCredentialsMatcher(matcher);
    }


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
    public Class getAuthenticationTokenClass() {
        return CasToken.class;
    }

}