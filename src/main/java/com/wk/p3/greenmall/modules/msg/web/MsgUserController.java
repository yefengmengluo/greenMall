/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.msg.web;

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
import com.wk.p3.greenmall.modules.msg.entity.MsgUser;
import com.wk.p3.greenmall.modules.msg.service.MsgUserService;

/**
 * 用户配置管理Controller
 * @author cc
 * @version 2015-12-26
 */
@Controller
@RequestMapping(value = "${adminPath}/msg/msgUser")
public class MsgUserController extends BaseController {

	@Autowired
	private MsgUserService msgUserService;
	
	@ModelAttribute
	public MsgUser get(@RequestParam(required=false) String id) {
		MsgUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = msgUserService.get(id);
		}
		if (entity == null){
			entity = new MsgUser();
		}
		return entity;
	}
	
	@RequiresPermissions("msg:msgUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(MsgUser msgUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MsgUser> page = msgUserService.findPage(new Page<MsgUser>(request, response), msgUser); 
		model.addAttribute("page", page);
		return "modules/msg/msgUserList";
	}

	@RequiresPermissions("msg:msgUser:view")
	@RequestMapping(value = "form")
	public String form(MsgUser msgUser, Model model) {
		model.addAttribute("msgUser", msgUser);
		return "modules/msg/msgUserForm";
	}

	@RequiresPermissions("msg:msgUser:edit")
	@RequestMapping(value = "save")
	public String save(MsgUser msgUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, msgUser)){
			return form(msgUser, model);
		}
		msgUserService.save(msgUser);
		addMessage(redirectAttributes, "保存用户配置成功");
		return "redirect:"+Global.getAdminPath()+"/msg/msgUser/?repage";
	}
	
	@RequiresPermissions("msg:msgUser:edit")
	@RequestMapping(value = "delete")
	public String delete(MsgUser msgUser, RedirectAttributes redirectAttributes) {
		msgUserService.delete(msgUser);
		addMessage(redirectAttributes, "删除用户配置成功");
		return "redirect:"+Global.getAdminPath()+"/msg/msgUser/?repage";
	}

}