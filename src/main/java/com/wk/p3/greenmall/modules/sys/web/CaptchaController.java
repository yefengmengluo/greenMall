package com.wk.p3.greenmall.modules.sys.web;

import com.wk.p3.greenmall.modules.sys.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by cc on 15-12-8.
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/captcha")
public class CaptchaController {
    private static final String VALIDATE_CODE = "validateCode";

    @Autowired
    CaptchaService captchaService;

    /**
     * 验证请求中验证码是否匹配
     * @param request
     * @return
     */
    public static boolean validate(HttpServletRequest request){
        Object code = request.getSession(false).getAttribute(VALIDATE_CODE);
        String validateCode = request.getParameter(VALIDATE_CODE);
        if(code!=null && code.toString().toLowerCase().equals(validateCode)){
            request.getSession(false).removeAttribute(VALIDATE_CODE);
            return true;
        }else{
            request.getSession(false).removeAttribute(VALIDATE_CODE);
            return false;
        }
    }

    /**
     * 请求验证码图片
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "image")
    public void image(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        setResponseHeaders(response);
        String code = captchaService.generateCaptcha(response.getOutputStream());
        session.setAttribute(VALIDATE_CODE, code);
    }

    /**
     * 设置请求返回头信息
     * @param resp
     */
    protected void setResponseHeaders(HttpServletResponse resp) {
        resp.setContentType("image/jpeg");
        resp.setHeader("Cache-Control", "no-cache, no-store");
        resp.setHeader("Pragma", "no-cache");
        final long time = System.currentTimeMillis();
        resp.setDateHeader("Last-Modified", time);
        resp.setDateHeader("Date", time);
        resp.setDateHeader("Expires", time);
    }

}
