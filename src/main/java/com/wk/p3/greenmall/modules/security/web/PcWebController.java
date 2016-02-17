/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.security.web;

import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.common.web.Response;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "${frontPath}/pcUser/")
public class PcWebController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(PcWebController.class);

    @RequestMapping(value = "login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        return renderString(response,Response.success("pcWeb登陆成功"));
    }

    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        SecurityUtils.getSubject().logout();
        return renderString(response,Response.success("pcWeb登出成功"));
    }

    @RequestMapping(value = "loginForm")
    public String loginForm(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "modules/security/loginForm";
    }
}
