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
import com.wk.p3.greenmall.modules.msg.entity.MsgSms;
import com.wk.p3.greenmall.modules.msg.service.MsgSmsService;

import java.util.List;

/**
 * 发送短信保存Controller
 * @author cc
 * @version 2015-12-26
 */
@Controller
@RequestMapping(value = "${adminPath}/msg/msgSms")
public class MsgSmsController extends BaseController {

	@Autowired
	private MsgSmsService msgSmsService;
	
	@ModelAttribute
	public MsgSms get(@RequestParam(required=false) String id) {
		MsgSms entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = msgSmsService.get(id);
		}
		if (entity == null){
			entity = new MsgSms();
		}
		return entity;
	}
	
	@RequiresPermissions("msg:msgSms:view")
	@RequestMapping(value = {"list", ""})
	public String list(MsgSms msgSms, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MsgSms> page = msgSmsService.findPage(new Page<MsgSms>(request, response), msgSms);
		List<MsgSms> list = page.getList();
		for(MsgSms sms:list){
			String result = sms.getResult();
			if(result.startsWith("ok") && result.contains(":")){
				String[] s = result.split(":");
				if(s.length==3){
					sms.setResult(s[2]);
				}
			}
		}
		model.addAttribute("page", page);
		return "modules/msg/msgSmsList";
	}

	@RequiresPermissions("msg:msgSms:view")
	@RequestMapping(value = "form")
	public String form(MsgSms msgSms, Model model) {
		model.addAttribute("msgSms", msgSms);
		return "modules/msg/msgSmsForm";
	}

	@RequiresPermissions("msg:msgSms:edit")
	@RequestMapping(value = "save")
	public String save(MsgSms msgSms, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, msgSms)){
			return form(msgSms, model);
		}
		msgSmsService.save(msgSms);
		addMessage(redirectAttributes, "保存发送短信成功");
		return "redirect:"+Global.getAdminPath()+"/msg/msgSms/?repage";
	}
	
	@RequiresPermissions("msg:msgSms:edit")
	@RequestMapping(value = "delete")
	public String delete(MsgSms msgSms, RedirectAttributes redirectAttributes) {
		msgSmsService.delete(msgSms);
		addMessage(redirectAttributes, "删除发送短信成功");
		return "redirect:"+Global.getAdminPath()+"/msg/msgSms/?repage";
	}

}