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
import com.wk.p3.greenmall.modules.security.entity.SecurityRole;
import com.wk.p3.greenmall.modules.security.service.SecurityRoleService;

/**
 * 与securityUser用户关联，保存用户权限Controller
 * @author cc
 * @version 2016-01-21
 */
@Controller
@RequestMapping(value = "${adminPath}/security/securityRole")
public class SecurityRoleController extends BaseController {

	@Autowired
	private SecurityRoleService securityRoleService;
	
	@ModelAttribute
	public SecurityRole get(@RequestParam(required=false) String id) {
		SecurityRole entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = securityRoleService.get(id);
		}
		if (entity == null){
			entity = new SecurityRole();
		}
		return entity;
	}
	
	@RequiresPermissions("security:securityRole:view")
	@RequestMapping(value = {"list", ""})
	public String list(SecurityRole securityRole, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SecurityRole> page = securityRoleService.findPage(new Page<SecurityRole>(request, response), securityRole); 
		model.addAttribute("page", page);
		return "modules/security/securityRoleList";
	}

	@RequiresPermissions("security:securityRole:view")
	@RequestMapping(value = "form")
	public String form(SecurityRole securityRole, Model model) {
		model.addAttribute("securityRole", securityRole);
		return "modules/security/securityRoleForm";
	}

	@RequiresPermissions("security:securityRole:edit")
	@RequestMapping(value = "save")
	public String save(SecurityRole securityRole, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, securityRole)){
			return form(securityRole, model);
		}
		securityRoleService.save(securityRole);
		addMessage(redirectAttributes, "保存保存用户角色成功");
		return "redirect:"+Global.getAdminPath()+"/security/securityRole/?repage";
	}
	
	@RequiresPermissions("security:securityRole:edit")
	@RequestMapping(value = "delete")
	public String delete(SecurityRole securityRole, RedirectAttributes redirectAttributes) {
		securityRoleService.delete(securityRole);
		addMessage(redirectAttributes, "删除保存用户角色成功");
		return "redirect:"+Global.getAdminPath()+"/security/securityRole/?repage";
	}

}