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
import com.wk.p3.greenmall.modules.msg.entity.MsgTemplate;
import com.wk.p3.greenmall.modules.msg.service.MsgTemplateService;

/**
 * 模板管理Controller
 * @author cc
 * @version 2015-12-26
 */
@Controller
@RequestMapping(value = "${adminPath}/msg/msgTemplate")
public class MsgTemplateController extends BaseController {

	@Autowired
	private MsgTemplateService msgTemplateService;
	
	@ModelAttribute
	public MsgTemplate get(@RequestParam(required=false) String id) {
		MsgTemplate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = msgTemplateService.get(id);
		}
		if (entity == null){
			entity = new MsgTemplate();
		}
		return entity;
	}
	
	@RequiresPermissions("msg:msgTemplate:view")
	@RequestMapping(value = {"list", ""})
	public String list(MsgTemplate msgTemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MsgTemplate> page = msgTemplateService.findPage(new Page<MsgTemplate>(request, response), msgTemplate); 
		model.addAttribute("page", page);
		return "modules/msg/msgTemplateList";
	}

	@RequiresPermissions("msg:msgTemplate:view")
	@RequestMapping(value = "form")
	public String form(MsgTemplate msgTemplate, Model model) {
		model.addAttribute("msgTemplate", msgTemplate);
		return "modules/msg/msgTemplateForm";
	}

	@RequiresPermissions("msg:msgTemplate:edit")
	@RequestMapping(value = "save")
	public String save(MsgTemplate msgTemplate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, msgTemplate)){
			return form(msgTemplate, model);
		}
		msgTemplateService.save(msgTemplate);
		addMessage(redirectAttributes, "保存模板成功");
		return "redirect:"+Global.getAdminPath()+"/msg/msgTemplate/?repage";
	}
	
	@RequiresPermissions("msg:msgTemplate:edit")
	@RequestMapping(value = "delete")
	public String delete(MsgTemplate msgTemplate, RedirectAttributes redirectAttributes) {
		msgTemplateService.delete(msgTemplate);
		addMessage(redirectAttributes, "删除模板成功");
		return "redirect:"+Global.getAdminPath()+"/msg/msgTemplate/?repage";
	}

}