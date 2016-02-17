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
import com.wk.p3.greenmall.modules.wechat.entity.mp.MpMessage;
import com.wk.p3.greenmall.modules.wechat.service.mp.MpMessageService;

/**
 * 推送给用户的消息Controller
 * @author cc
 * @version 2015-11-25
 */
@Controller
@RequestMapping(value = "${adminPath}/wechat/mp/mpMessage")
public class MpMessageController extends BaseController {

	@Autowired
	private MpMessageService mpMessageService;
	
	@ModelAttribute
	public MpMessage get(@RequestParam(required=false) String id) {
		MpMessage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mpMessageService.get(id);
		}
		if (entity == null){
			entity = new MpMessage();
		}
		return entity;
	}
	
	@RequiresPermissions("wechat:mp:mpMessage:view")
	@RequestMapping(value = {"list", ""})
	public String list(MpMessage mpMessage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MpMessage> page = mpMessageService.findPage(new Page<MpMessage>(request, response), mpMessage); 
		model.addAttribute("page", page);
		return "modules/wechat/mp/mpMessageList";
	}

	@RequiresPermissions("wechat:mp:mpMessage:view")
	@RequestMapping(value = "form")
	public String form(MpMessage mpMessage, Model model) {
		model.addAttribute("mpMessage", mpMessage);
		return "modules/wechat/mp/mpMessageForm";
	}

	@RequiresPermissions("wechat:mp:mpMessage:edit")
	@RequestMapping(value = "save")
	public String save(MpMessage mpMessage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mpMessage)){
			return form(mpMessage, model);
		}
		mpMessageService.save(mpMessage);
		addMessage(redirectAttributes, "保存保存消息成功成功");
		return "redirect:"+Global.getAdminPath()+"/wechat/mp/mpMessage/?repage";
	}
	
	@RequiresPermissions("wechat:mp:mpMessage:edit")
	@RequestMapping(value = "delete")
	public String delete(MpMessage mpMessage, RedirectAttributes redirectAttributes) {
		mpMessageService.delete(mpMessage);
		addMessage(redirectAttributes, "删除保存消息成功成功");
		return "redirect:"+Global.getAdminPath()+"/wechat/mp/mpMessage/?repage";
	}

}