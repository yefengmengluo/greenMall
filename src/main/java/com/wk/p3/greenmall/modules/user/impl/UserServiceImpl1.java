package com.wk.p3.greenmall.modules.user.impl;

import com.wk.p3.greenmall.modules.sys.service.SystemService;
import com.wk.p3.greenmall.modules.wechat.service.mp.MpUserService;
import me.chanjar.weixin.mp.api.WxMpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cc on 15-12-8.
 */

@Service
public class UserServiceImpl1{// implements UserService<UserSole> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SystemService systemService;


    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private MpUserService mpUserService;

//    @Override
//    @Transactional(readOnly = true)
//    public AuthenticationInfo authenticate(AuthenticationToken authcToken,AuthorizingRealm authorizingRealm) throws AuthenticationException,UserException {
//
//        int activeSessionSize = systemService.getSessionDao().getActiveSessions(false).size();
//        if (logger.isDebugEnabled()){
//            logger.debug("login submit, active session size: {}, username: {}", activeSessionSize, authcToken.getPrincipal());
//        }
//
//        //用户使用账号密码登陆
//        if(authcToken instanceof UsernamePasswordToken){
//            UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
//            // 校验登录验证码
//            if (LoginController.isValidateCodeLogin(token.getUsername(), false, false)){
//                Session session = UserUtils.getSession();
//                String code = (String)session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
//                if (token.getCaptcha() == null || !token.getCaptcha().toUpperCase().equals(code)){
//                    throw new AuthenticationException("msg:验证码错误, 请重试.");
//                }
//            }
//            // 校验用户名密码
//            Principal user = systemService.getUserByLoginName(token.getUsername());
//            if (user != null) {
//                switch (user.getType()){
//                    case pcWeb :
//                        UserToken mallUser = (UserToken)user;
//                        if (Global.NO.equals(mallUser.getLoginFlag())){
//                            throw new AuthenticationException("msg:该已帐号禁止登录.");
//                        }
//                        byte[] salt = Encodes.decodeHex(mallUser.getPassword().substring(0,16));
//                        return new SimpleAuthenticationInfo(
//                                mallUser.getId(),
//                                mallUser.getPassword().substring(16),
//                                ByteSource.Util.bytes(salt),
//                                authorizingRealm.getName()
//                        );
//                    case admin :
//                        User userAdmin = (User)user;
//                        if (Global.NO.equals(userAdmin.getLoginFlag())){
//                            throw new AuthenticationException("msg:该已帐号禁止登录.");
//                        }
//                        byte[] salt1 = Encodes.decodeHex(userAdmin.getPassword().substring(0,16));
//                        return new SimpleAuthenticationInfo(
//                                userAdmin.getId(),
//                                userAdmin.getPassword().substring(16),
//                                ByteSource.Util.bytes(salt1),
//                                authorizingRealm.getName()
//                        );
//                    default:
//                        throw new NoSuchUserType("暂不支持这样的登录用户类型");
//                }
//            } else {
//                return null;
//            }
//        //用户使用微信单点登陆
//        }else if(authcToken instanceof CasToken){
//            CasToken token = (CasToken) authcToken;
//            // 通过code请求微信得到完整的微信用户信息
//            WxMpUser wxMpUser = null;
//            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
//            try {
//                wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken((String)token.getCredentials());
//            } catch (WxErrorException e) {
//                throw new AuthenticationException("腾讯服务器验证code失败");
//            }
//            if (logger.isDebugEnabled()){
//                logger.debug("wxMpOAuth2AccessToken", wxMpOAuth2AccessToken.toString());
//            }
//            try {
//                wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken,null);
//            } catch (WxErrorException e) {
//                throw new AuthenticationException("腾讯服务器获得微信个人信息失败");
//            }
//            if (logger.isDebugEnabled()){
//                logger.debug("wxMpUser", wxMpUser.toString());
//            }
//
//            MpUser mpUser = mpUserService.getByOpenId(wxMpUser.getOpenId());
//
//            if (mpUser != null) {//存在该用户，直接返回SimpleAuthenticationInfo
//                if (logger.isDebugEnabled()){
//                    logger.debug("存在该微信公众号用户:", "[openId=",wxMpUser.getOpenId(),",unionId=",wxMpUser.getUnionId(),"]");
//                }
//            } else {//不存在该用户，新建该用户，同时直接返回SimpleAuthenticationInfo
//                mpUser = Util.convert(wxMpUser);
//                mpUserService.save(mpUser);
//                if (logger.isDebugEnabled()){
//                    logger.debug("不存在的微信公众号用户:", "[openId=",wxMpUser.getOpenId(),",unionId=",wxMpUser.getUnionId(),"],保存成功");
//                }
//            }
//            return new SimpleAuthenticationInfo(mpUser.getOpenid(),ByteSource.Util.bytes(mpUser.getOpenid().toString()), authorizingRealm.getName());
//        }
//        return null;
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public AuthorizationInfo authorize(Principal principal) {
//        User user = systemService.getUserByLoginName(principal.getLoginName());
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        if (user != null) {
//            switch (user.getType()){
//                case admin :
//                    List<Menu> list = UserUtils.getMenuList();
//                    for (Menu menu : list){
//                        if (StringUtils.isNotBlank(menu.getPermission())){
//                            // 添加基于Permission的权限信息
//                            for (String permission : StringUtils.split(menu.getPermission(),",")){
//                                info.addStringPermission(permission);
//                            }
//                        }
//                    }
//                    // 添加用户权限
//                    info.addStringPermission("user");
//                    // 添加用户角色信息
//                    for (Role role : user.getRoleList()){
//                        info.addRole(role.getEnname());
//                    }
//                    // 更新登录IP和时间
//                    systemService.updateUserLoginInfo(user);
//                    // 记录登录日志
//                    LogUtils.saveLog(Servlets.getRequest(), "系统登录");
//                    break;
//                case pcWeb :
//                    info.addStringPermission("user");
//                    break;
//                default:
//            }
//        }
//        return info;
//    }

    /**

    @Override
    public void login(String username, String passwd, Boolean rememberMe,LoginType loginType) throws AuthenticationException {
        boolean isRememberMe = (rememberMe==null)?false:rememberMe;
        AuthenticationToken token = new UsernamePasswordToken(username, passwd ,isRememberMe, loginType);
        SecurityUtils.getSubject().login(token);
    }

    @Override
    @Transactional(readOnly = false)
    public void register(Principal user) throws UserException {
        switch (user.getType()){
            case pcWeb :
                UserToken token = (UserToken)user;
                if(userTokenService.getByLoginName(user.getLoginName()) == null) {
                    UserSole sole = new UserSole();
                    userSoleService.save(sole);
                    token.setUniqueUser(sole.getId());
                    userTokenService.save(token);
                }else{
                    throw new AlreadyRegistered("已经注册的用户");
                }
                break;
            case admin :
            default:
                throw new NoSuchUserType("暂不支持这样的登录用户类型");
        }
    }

    @Override
    public void logout() throws UserException {
        SecurityUtils.getSubject().logout();
    }

    @Override
    public Principal currentUser() {
        Object principal =  SecurityUtils.getSubject().getPrincipal();
        if (principal!=null){
            Principal result = systemService.getPrincipalById(principal.toString());
            return result;
        }else{
            return null;
        }
    }

    @Override
    public Session currentSession() {
        return SecurityUtils.getSubject().getSession();
    }


    @Override
    @Transactional(readOnly = false)
    public void bind(UserSole uniqueUser, Set<Principal> userSet) {
        for(Principal principal:userSet){
            principal.setUniqueUser(uniqueUser.getId());
            userSoleService.save(uniqueUser);
        }

    }

    @Override
    public Principal getPrincipal(String name, LoginType loginType) {
        return systemService.getPrincipal(name,loginType);
    }

    @Override
    public Principal getPrincipal(String id) {
        return systemService.getPrincipalById(id);
    }
**/

}
