/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.wechat.web.mp;

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
import com.wk.p3.greenmall.modules.wechat.entity.mp.MpInformation;
import com.wk.p3.greenmall.modules.wechat.service.mp.MpInformationService;

/**
 * 用户发布的信息Controller
 * @author cc
 * @version 2015-11-25
 */
@Controller
@RequestMapping(value = "${adminPath}/wechat/mp/mpInformation")
public class MpInformationController extends BaseController {

	@Autowired
	private MpInformationService mpInformationService;
	
	@ModelAttribute
	public MpInformation get(@RequestParam(required=false) String id) {
		MpInformation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mpInformationService.get(id);
		}
		if (entity == null){
			entity = new MpInformation();
		}
		return entity;
	}
	
	@RequiresPermissions("wechat:mp:mpInformation:view")
	@RequestMapping(value = {"list", ""})
	public String list(MpInformation mpInformation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MpInformation> page = mpInformationService.findPage(new Page<MpInformation>(request, response), mpInformation); 
		model.addAttribute("page", page);
		return "modules/wechat/mp/mpInformationList";
	}

	@RequiresPermissions("wechat:mp:mpInformation:view")
	@RequestMapping(value = "form")
	public String form(MpInformation mpInformation, Model model) {
		model.addAttribute("mpInformation", mpInformation);
		return "modules/wechat/mp/mpInformationForm";
	}

	@RequiresPermissions("wechat:mp:mpInformation:edit")
	@RequestMapping(value = "save")
	public String save(MpInformation mpInformation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mpInformation)){
			return form(mpInformation, model);
		}
		mpInformationService.save(mpInformation);
		addMessage(redirectAttributes, "保存用户发布的信息保存成功");
		return "redirect:"+Global.getAdminPath()+"/wechat/mp/mpInformation/?repage";
	}
	
	@RequiresPermissions("wechat:mp:mpInformation:edit")
	@RequestMapping(value = "delete")
	public String delete(MpInformation mpInformation, RedirectAttributes redirectAttributes) {
		mpInformationService.delete(mpInformation);
		addMessage(redirectAttributes, "删除用户发布的信息保存成功");
		return "redirect:"+Global.getAdminPath()+"/wechat/mp/mpInformation/?repage";
	}

}