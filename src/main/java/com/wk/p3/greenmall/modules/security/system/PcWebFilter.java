package com.wk.p3.greenmall.modules.security.system;

import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.common.web.Response;
import com.wk.p3.greenmall.modules.sys.security.UsernamePasswordToken;
import com.wk.p3.greenmall.modules.user.LoginType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
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

/**
 * pcWeb用户登陆过滤器，用于拦截未登陆用户
 * 流程：
 *      1.已经登陆用户,直接通过请求
 *      2.未登陆用户(两种情况)
 *          无TargetURI参数,redirect到登陆页面LoginURI
 *          有TargetURI参数,进入登陆流程(两种情况)
 *              成功登陆，redirect到TargetURI
 *              登陆失败，redirect到FailureURI
 *
 * Created by cc on 15-12-1.
 */
@Service
public class PcWebFilter implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(PcWebFilter.class);
    private static final String targetName = "TargetURI" ;
    //servletPath path
    private String failureURI;
    private String loginURI;

    protected boolean executeLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String targetURI = "/" ;
        AuthenticationToken token = createToken(request, response);
        if (token == null) {
            response.getWriter().print("账号密码参数错误");
            response.flushBuffer();
            return false;
        }
        try {
            SecurityUtils.getSubject().login(token);
            //登陆成功
            HttpSession session = request.getSession();
            if(session.getAttribute(targetName)!=null){
                targetURI = session.getAttribute(targetName).toString();
                session.removeAttribute(targetName);
            }
        } catch (AuthenticationException e) {
            logger.debug("是否登陆:["+false+"],TargetURI:["+targetURI+"],AuthenticationException:["+e.getMessage()+"]");
            //WebUtils.issueRedirect(request, response, this.failureURI);
            Response.render(response,Response.error(-1,"登陆失败"));
            return false;
        }catch (Exception e){
            logger.debug("是否登陆:[" + false + "],TargetURI:[" + targetURI + "],Exception:[" + e.getMessage() + "]");
            response.getWriter().print("验证账号密码时内部错误");
            response.flushBuffer();
            return false;
        }
        WebUtils.issueRedirect(request, response,targetURI);
        logger.debug("是否登陆:["+true+"],TargetURI:["+targetURI+"]");
        return true;
    }

    /**
     * 判断下面几种情况
     * 是否需要登陆
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws Exception
     */
    public boolean onPreHandle(HttpServletRequest request, HttpServletResponse response, Object mappedValue) throws Exception {
        HttpSession session = request.getSession();
        if(SecurityUtils.getSubject().isAuthenticated()){
            logger.debug("是否登陆:["+true+"]");
            return true;
        }else{
            //无TargetURI，redirect到登陆表单,加targetURI参数
            if(session.getAttribute(targetName)==null){
                String targetURI =request.getServletPath();
                session.setAttribute(targetName,targetURI);
                WebUtils.issueRedirect(request, response, this.loginURI);
                logger.debug("是否登陆:["+false+"],TargetURI:["+targetURI+"]");
                return false;
            }else{//有TargetURI，redirect进入登陆流程
                logger.debug("是否登陆:["+false+"],TargetURI:"+"[]");
                return executeLogin(request,response);
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
    protected AuthenticationToken createToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpServletRequest httpRequest = request;
        String username = httpRequest.getParameter("username");
        String password = httpRequest.getParameter("password");
        String loginType = httpRequest.getParameter("loginType");
        if(StringUtils.isBlank(username) || StringUtils.isBlank(username) || StringUtils.isBlank(loginType)){
            return null;
        }
        String rememberMe = httpRequest.getParameter("rememberMe");
        return new PcWebToken(username,password,"false".equals(rememberMe)?false:true,LoginType.parse(loginType));
    }

    public void setFailureURI(String failureURI) {
        this.failureURI = failureURI;
    }

    public void setLoginURI(String loginURI) {
        this.loginURI = loginURI;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return onPreHandle(request,response,handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
