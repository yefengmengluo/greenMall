/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.security.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wk.p3.greenmall.common.config.Global;
import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.modules.security.entity.SecurityUser;
import com.wk.p3.greenmall.modules.security.service.SecurityUserService;

/**
 * 终端用户抽象，多登陆用户对应一个用户Controller
 * @author cc
 * @version 2016-01-21
 */
@Controller
@RequestMapping(value = "${adminPath}/security/securityUser")
public class SecurityUserController extends BaseController {

	@Autowired
	private SecurityUserService securityUserService;
	
	@ModelAttribute
	public SecurityUser get(@RequestParam(required=false) String id) {
		SecurityUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = securityUserService.get(id);
		}
		if (entity == null){
			entity = new SecurityUser();
		}
		return entity;
	}
	
	@RequiresPermissions("security:securityUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(SecurityUser securityUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SecurityUser> page = securityUserService.findPage(new Page<SecurityUser>(request, response), securityUser); 
		model.addAttribute("page", page);
		return "modules/security/securityUserList";
	}

	@RequiresPermissions("security:securityUser:view")
	@RequestMapping(value = "form")
	public String form(SecurityUser securityUser, Model model) {
		model.addAttribute("securityUser", securityUser);
		return "modules/security/securityUserForm";
	}

	@RequiresPermissions("security:securityUser:edit")
	@RequestMapping(value = "save")
	public String save(SecurityUser securityUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, securityUser)){
			return form(securityUser, model);
		}
		securityUserService.save(securityUser);
		addMessage(redirectAttributes, "保存保存用户成功");
		return "redirect:"+Global.getAdminPath()+"/security/securityUser/?repage";
	}
	
	@RequiresPermissions("security:securityUser:edit")
	@RequestMapping(value = "delete")
	public String delete(SecurityUser securityUser, RedirectAttributes redirectAttributes) {
		securityUserService.delete(securityUser);
		addMessage(redirectAttributes, "删除保存用户成功");
		return "redirect:"+Global.getAdminPath()+"/security/securityUser/?repage";
	}

}