package com.wk.p3.greenmall.modules.sys.interceptor;

import com.wk.p3.greenmall.common.servlet.ValidateCodeServlet;
import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.common.web.Response;
import com.wk.p3.greenmall.modules.sys.utils.UserUtils;
import org.apache.shiro.session.Session;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 图形验证码拦截器
 * 拦截请求中的验证码，
 * Created by cc on 16-1-4.
 */
public class CaptchaInterceptor extends BaseController implements HandlerInterceptor  {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //验证码返回
        Session session = UserUtils.getSession();
        String code = (String) session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
        if (request.getParameter("validateCode") == null || !request.getParameter("validateCode").toUpperCase().equals(code)) {
            renderString(response,Response.error(-9, "图形验证码失败"));
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {


    }


}
