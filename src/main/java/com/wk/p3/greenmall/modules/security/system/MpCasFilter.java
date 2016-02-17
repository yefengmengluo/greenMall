package com.wk.p3.greenmall.modules.security.system;

import com.wk.p3.greenmall.common.utils.StringUtils;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * Created by cc on 15-12-1.
 *
 * 微信公众号登陆拦截,onPreHandle为核心方法，分下面几种情况
 *  一.登陆，直接放行
 *  二.未登陆，重定向,验证code,直接拒绝:(根据code和state来区分)
 *      1.第一次请求,无code，无state参数,重定向到腾讯,取得code，再让用户按同一url重新请求服务器
 *      2.第二次重新请求服务器，有code,有state参数，且session中有state对应的值,进去code验证流程
 *          结果为验证通过，放行；验证没有通过，直接拒绝请求
 *      3.其他情况，直接拒绝请求
 *
 */
@Service
public class MpCasFilter implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(MpCasFilter.class);

    // the name of the parameter service ticket in url
    private static final String TICKET_PARAMETER = "code";

    // the url where the application is redirected if the CAS service ticket validation failed (example : /mycontextpatch/cas_error.jsp)
    private String failureUrl;

    private static final String code = "code";
    private static final String state  = "state";

    private boolean baseInfo = true;

    private Random random = new Random();
    private WxMpService wxMpService;
    private String httpServerUrl;

    public void setWxMpService(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    public void setHttpServerUrl(String httpServerUrl) {
        this.httpServerUrl = httpServerUrl;
    }

    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        AuthenticationToken token = createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }
        try {
            SecurityUtils.getSubject().login(token);
            return true;
        } catch (AuthenticationException e) {
            WebUtils.issueRedirect(request, response, this.failureUrl);
            return false;
        }
    }

    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpSession session = ((HttpServletRequest)request).getSession();
        if(SecurityUtils.getSubject().isAuthenticated()){
            return true;
        }else{
            //第1种情况
            if(StringUtils.isEmpty(request.getParameter(code)) && StringUtils.isEmpty(request.getParameter(state))){
                Long stateCode = random.nextLong();
                session.setAttribute(state, stateCode.toString());
                String redirect = wxMpService.oauth2buildAuthorizationUrl(httpServerUrl+((HttpServletRequest)request).getRequestURI(), infoType(), stateCode.toString());
                WebUtils.issueRedirect(request, response, redirect);
                return false;
            }else{
                if(!StringUtils.isEmpty(request.getParameter(code)) && !StringUtils.isEmpty(request.getParameter(state))){
                    //第2种情况
                    if(session.getAttribute(state).equals(request.getParameter(state).replace("#wechat_redirect", ""))) {
                        //session.setAttribute(allow,random.nextLong());
                        return executeLogin(request,response);
                    }else{//3其他情况
                        return false;
                    }
                }else{//3其他情况
                    return false;
                }
            }
        }
    }

    /**
     * The token created for this authentication is a CasToken containing the CAS service ticket received on the CAS service url (on which
     * the filter must be configured).
     *
     * @param request the incoming request
     * @param response the outgoing response
     * @throws Exception if there is an error processing the request.
     */
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String ticket = httpRequest.getParameter(TICKET_PARAMETER);
        return new CasToken(ticket);
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }
    public void setBaseInfo(boolean baseInfo) {
        this.baseInfo = baseInfo;
    }
    private String infoType(){
        return this.baseInfo?"snsapi_base":"snsapi_userinfo";
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = onPreHandle(request,response,handler);
        return result;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
