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
import com.wk.p3.greenmall.modules.security.entity.SecurityUserToken;
import com.wk.p3.greenmall.modules.security.service.SecurityUserTokenService;

/**
 * 保存用户账号密码登陆凭证Controller
 * @author cc
 * @version 2016-01-21
 */
@Controller
@RequestMapping(value = "${adminPath}/security/securityUserToken")
public class SecurityUserTokenController extends BaseController {

	@Autowired
	private SecurityUserTokenService securityUserTokenService;
	
	@ModelAttribute
	public SecurityUserToken get(@RequestParam(required=false) String id) {
		SecurityUserToken entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = securityUserTokenService.get(id);
		}
		if (entity == null){
			entity = new SecurityUserToken();
		}
		return entity;
	}
	
	@RequiresPermissions("security:securityUserToken:view")
	@RequestMapping(value = {"list", ""})
	public String list(SecurityUserToken securityUserToken, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SecurityUserToken> page = securityUserTokenService.findPage(new Page<SecurityUserToken>(request, response), securityUserToken); 
		model.addAttribute("page", page);
		return "modules/security/securityUserTokenList";
	}

	@RequiresPermissions("security:securityUserToken:view")
	@RequestMapping(value = "form")
	public String form(SecurityUserToken securityUserToken, Model model) {
		model.addAttribute("securityUserToken", securityUserToken);
		return "modules/security/securityUserTokenForm";
	}

	@RequiresPermissions("security:securityUserToken:edit")
	@RequestMapping(value = "save")
	public String save(SecurityUserToken securityUserToken, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, securityUserToken)){
			return form(securityUserToken, model);
		}
		securityUserTokenService.save(securityUserToken);
		addMessage(redirectAttributes, "保存保存登陆信息成功");
		return "redirect:"+Global.getAdminPath()+"/security/securityUserToken/?repage";
	}
	
	@RequiresPermissions("security:securityUserToken:edit")
	@RequestMapping(value = "delete")
	public String delete(SecurityUserToken securityUserToken, RedirectAttributes redirectAttributes) {
		securityUserTokenService.delete(securityUserToken);
		addMessage(redirectAttributes, "删除保存登陆信息成功");
		return "redirect:"+Global.getAdminPath()+"/security/securityUserToken/?repage";
	}

}