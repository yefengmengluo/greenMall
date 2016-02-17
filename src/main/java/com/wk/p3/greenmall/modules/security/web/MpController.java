/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.security.web;

import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.common.web.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "${frontPath}/mpUser")
public class MpController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(MpController.class);

    @RequestMapping(value = "login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        return renderString(response,Response.success("微信登陆成功"));
    }

    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        return renderString(response,Response.success("微信登陆成功"));
    }

}
